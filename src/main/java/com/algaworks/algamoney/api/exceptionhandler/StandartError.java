package com.algaworks.algamoney.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StandartError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm ", timezone = "GMT")
    private Instant timestamp ;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
