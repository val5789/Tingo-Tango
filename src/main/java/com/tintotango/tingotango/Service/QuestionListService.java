package com.tintotango.tingotango.Service;

import com.tintotango.tingotango.Exceptions.KidsException;
import com.tintotango.tingotango.Model.Question;
import com.tintotango.tingotango.Model.QuestionList;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
@Service
public class QuestionListService {
    private QuestionList questions;

    public QuestionListService() {
        questions = new QuestionList();

        try (BufferedReader reader = new BufferedReader(new FileReader("BancoDePreguntas.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String questionText = parts[0];
                String[] options = parts[1].split(",");
                Byte correctpos = Byte.parseByte(parts[2]);
                String id = parts [3];

               // Question question = new Question(questionText, new ArrayList<>(List.of(options)), correctPos);


                Question question = new Question(questionText, Arrays.asList(options).toString(), correctpos, Collections.singletonList(id));
                questions.addQuestion(question);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String addNewQuestion(Question newQuestion){
        questions.addQuestion(newQuestion);
        return "Adicionado";
    }
    public String deleteQuestionById(String questionId)throws KidsException{
        boolean output = questions.deleteQuestion(questionId);
        if (output){
            return "Eliminado";
        }
        else{
            throw new KidsException("No se encontro la id");
        }
    }
    public String updateQuestion(String questionId,Question updatedQuestion) throws KidsException{
        try {
            questions.updateQuestion(questionId,updatedQuestion);
            return "Pregunta actualizada";
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }

    public List<Question> getAll(){
        return questions.getAll();
    }

    public Question getQuestionById(String questionId) throws KidsException{
        try {
            return questions.getQuestionById(questionId);
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }


}
