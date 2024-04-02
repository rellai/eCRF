package ru.rellai.ecrf.study.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionMessageDto {

    private String timestamp;

    private int status;

    private String error;

    private String message;

    private String path;

    @Override
    public String toString() {
        return "ExceptionMessage [timestamp=" + timestamp +
                ", status=" + status +
                ", error=" + error +
                ", message=" + message +
                ", path=" + path + "]";
    }

}