package com.khrd.homework_spring_data_jpa_2.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponse<T> {
    private HttpStatus httpStatus;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
}