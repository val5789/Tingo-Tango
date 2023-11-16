package com.tintotango.tingotango.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Question {
    private String question;
    private List<String> options;
    private Byte correctPos;
    private String id;


   //public Question(String questionText, ArrayList<String> strings, Byte correctPos) {
   // }
}//FIN DE LA CLASE PREGUNTAS
