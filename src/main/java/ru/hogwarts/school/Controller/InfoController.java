package ru.hogwarts.school.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Info;
import ru.hogwarts.school.service.InfoService;

@RestController
public class InfoController {
    @Autowired
    InfoService infoService;

    @GetMapping("/get_port")
    public int getPort() {
        return infoService.getPort();
    }


}
