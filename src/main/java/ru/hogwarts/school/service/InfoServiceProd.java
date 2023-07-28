package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hogwarts.school.model.Info;

@Service
@Profile("admin")
public class InfoServiceProd implements InfoService {

    @Value("${server.port}")
    private int serverPort;

    @Override
    public int getPort() {
        return serverPort;
    }
    @EventListener
    public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
        serverPort = event.getWebServer().getPort();
    }
}