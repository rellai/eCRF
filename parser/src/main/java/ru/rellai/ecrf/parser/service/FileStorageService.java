package ru.rellai.ecrf.parser.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FileStorageService {

    String saveFile(String fileName, MultipartFile multipartFile) throws IOException;

    Resource loadFileAsResource(String fileName);

    Resource getTemplate();

}
