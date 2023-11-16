package com.tintotango.tingotango.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Question {
    private String question;
    private String idpregunta;
    private byte poscorrecta;
    private List<String>  opciones;

    public Question(String questionText, ArrayList<String> strings, Byte correctPos) {
    }
}//FIN DE LA CLASE PREGUNTAS
