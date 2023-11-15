package com.tintotango.tingotango.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class ResponseDTO {
    private int code;
    Object data;
    List<String> error;
}//FIN DE LA CLASE RESPONSE DTO
