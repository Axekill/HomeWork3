package ru.hogwarts.school.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
    private final StudentService service;
    private final AvatarRepository repository;

    @Autowired
    public AvatarService(StudentService service, AvatarRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        Student student = service.findStudent(studentId);
        Path filePath = Path.of(avatarsDir, studentId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)

        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        repository.save(avatar);

    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    public Avatar findAvatar(Long id) {
        return repository.findByStudent_Id(id).orElse(new Avatar());
    }

    public List<Avatar> getAvatarPage(int pageNumber, int pageSize) {
        var page = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(page).getContent();
    }
}
