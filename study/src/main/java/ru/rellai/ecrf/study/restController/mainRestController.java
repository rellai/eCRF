package ru.rellai.ecrf.study.restController;

import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rellai.ecrf.study.client.FilesClient;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
public class mainRestController {

    private final FilesClient filesClient;

        @GetMapping("/files/template")
        public Void getTemplates () throws IOException {
            final Response response = filesClient.downloadFile();
            final Response.Body body = response.body();
            final InputStream inputStream = body.asInputStream();

            return null;
        }

}
