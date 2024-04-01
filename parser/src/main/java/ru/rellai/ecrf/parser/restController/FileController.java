package ru.rellai.ecrf.parser.restController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.rellai.ecrf.parser.dto.FileUploadDto;
import ru.rellai.ecrf.parser.service.FileStorageService;

import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileStorageService fileStorageService;


    @PostMapping(path = "/files",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Upload study model", description = "This endpoint upload study model")
    @SecurityRequirement(name = "Authentication OAuth2")
    public FileUploadDto uploadFile(
            @RequestParam("file") MultipartFile document)
            throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(document.getOriginalFilename()));
        long size = document.getSize();

        String fileCode = fileStorageService.saveFile(fileName, document);

        FileUploadDto fileResponse = new FileUploadDto();
        fileResponse.setSize(size);
        fileResponse.setFileName(fileName);
        fileResponse.setDownloadUri(fileCode + fileName);

        return fileResponse;
    }



    @GetMapping(path = "/files/{fileName:.+}")
    @Operation(summary = "Download file", description = "This endpoint download files from storage. " +
            "When file name is \"template_crf.xlsx\" it returns template file")
    @SecurityRequirement(name = "Authentication OAuth2")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        // Load file as Resource
        Resource resource = (fileName.equals("template_crf.xlsx")) ?
                fileStorageService.getTemplate() :
                fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
