package ru.rellai.ecrf.parser.service;


import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.rellai.ecrf.parser.ParserApplication;
import ru.rellai.ecrf.parser.config.FileStorageProperties;
import ru.rellai.ecrf.parser.error.FileNotFoundException;
import ru.rellai.ecrf.parser.error.FileStorageException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    private final String templateCrfName;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        this.templateCrfName = fileStorageProperties.getTemplateCrfName();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the " +
                    "uploaded files will be stored.", ex);
        }
    }

    @Override
    public String saveFile(String fileName, MultipartFile multipartFile) throws IOException {

        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = fileStorageLocation.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }
        return fileCode;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }

    @Override
    public Resource getTemplate() {
        try {
            Resource resource = new UrlResource(Objects.requireNonNull(ParserApplication.class.getClassLoader()
                    .getResource(templateCrfName)).toURI());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + templateCrfName);
            }
        } catch (MalformedURLException | URISyntaxException ex) {
            throw new FileNotFoundException("File not found " + templateCrfName, ex);
        }
    }
}
