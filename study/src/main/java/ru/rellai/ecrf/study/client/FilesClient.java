package ru.rellai.ecrf.study.client;


import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "template",
        url = "http://localhost:8082/")
public interface FilesClient {

    @GetMapping(value = "/files/template")
    Response downloadFile();

}
