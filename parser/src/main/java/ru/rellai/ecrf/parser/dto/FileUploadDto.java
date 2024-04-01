package ru.rellai.ecrf.parser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FileUploadDto {

    @Schema(
            description = "Name of uploaded file",
            name = "fileName",
            type = "string",
            example = "template.xlsx")
    private String fileName;

    @Schema(
            description = "Uri to download file",
            name = "downloadUri",
            type = "string",
            example = "/temp/VgQp0ZMt")
    private String downloadUri;

    @Schema(
            description = "Size of file in bytes",
            name = "size",
            type = "long",
            example = "4137")
    private long size;

}
