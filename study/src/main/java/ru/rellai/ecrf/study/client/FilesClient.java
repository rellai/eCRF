package ru.rellai.ecrf.study.client;


import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.rellai.ecrf.study.config.FeignConfig;

@FeignClient(
        name = "template",
        url = "http://localhost:8082/",
        configuration = FeignConfig.class)
public interface FilesClient {

    @GetMapping(value = "api/v1/files/{fileName:.+}")
    Response downloadFile(@PathVariable String fileName);

    //@RequestLine("POST /files")
    @PostMapping(path = "api/v1/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response uploadFile(@RequestPart(value = "file") MultipartFile file);

}
