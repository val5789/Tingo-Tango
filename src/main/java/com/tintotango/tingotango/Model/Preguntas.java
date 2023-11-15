package com.tintotango.tingotango.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Preguntas {
    private String question;
    private String poscorrecta;
    private List<String>  opciones;
}//FIN DE LA CLASE PREGUNTAS
