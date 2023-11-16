package com.tintotango.tingotango.Model;

import com.tintotango.tingotango.Exceptions.KidsException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionList {
    private List<Question> questionlist;

    public QuestionList(){
        questionlist=new ArrayList<>();
    }
    public List<Question> getAll(){

        return questionlist;
    }

    public Question getQuestionById (String questionId) throws KidsException{
        for (Question question : questionlist){
            if (question.getIdpregunta().equals(questionId)){
                return question;
            }
        }
        throw new KidsException("No hay pregunta con ese id");
    }

    public void addQuestion (Question newquest){
        questionlist.add(newquest);
    }
    public boolean deleteQuestion(String questionId){
        return questionlist.removeIf(question -> question.getIdpregunta().equals(questionId));
    }
    public void updateQuestion(String questionId, Question updateQuestion) throws KidsException{
        boolean matchFound = false;
        for (int i= 0; i<questionlist.size();i++){
            if (questionlist.get(i).getIdpregunta().equals(questionId)){
                questionlist.set(i,updateQuestion);
                matchFound=true;
                break;
            }
        }
        if (!matchFound){
            throw new KidsException("No se encontro");
        }
    }








}
