package com.tintotango.tingotango.Service;

import com.tintotango.tingotango.Controller.DTO.DataStructureDTO;
import com.tintotango.tingotango.Exceptions.KidsException;
import com.tintotango.tingotango.Model.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Data
@Service
public class TingoTangoService {

    private final QuestionListService questionService;
    private final ListaDECircularService listDEService;
    //Tingo Tango object
    private TingoTango game;
    @Autowired
    public TingoTangoService(QuestionListService questionService,ListaDECircularService listDEService){
        this.listDEService = listDEService;
        this.questionService = questionService;

        this.game = new TingoTango(listDEService.getKids(),
                questionService.getAll(),false,false,
                null,null,null);
    }

    public String addNewQuestion (Question newQuestion){
        questionService.addNewQuestion(newQuestion);
        return "Pregunta adicionada";
    }

    public String deleteQuestionById(String questionId) throws KidsException {
        if(!game.isGameState()){
            try {
                return questionService.deleteQuestionById(questionId);
            } catch (KidsException e) {
                throw new KidsException(e.getMessage());
            }
        }
        else{
            throw new KidsException("No se puede eliminar una pregunta si el juego esta en curso");
        }
    }

    public String updateQuestionById(String questionId,Question updQuestion)throws KidsException {
        if (!game.isGameState()) {
            try {
                return questionService.updateQuestion(questionId, updQuestion);
            } catch (KidsException e) {
                throw new KidsException(e.getMessage());
            }
        } else {
            throw new KidsException("No se puede actualizar una pregutna con el juego en curso");
        }
    }

    public List<Question> getQuestions(){
        return questionService.getAll();
    }

    public Question getQuestionById(String questionId) throws KidsException{
        try {
            return questionService.getQuestionById(questionId);
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }

    public String addKidToEnd (Kid kid){
        return listDEService.addKidToEnd(kid);
    }

    public String addToStart (Kid kid){
        return listDEService.addKidToStart(kid);
    }

    public String deleteKidInPos(int position)throws KidsException{
        if(!game.isGameState()) {
            try {
                return listDEService.deleteByPosition(position);
            } catch (KidsException e) {
                throw new KidsException(e.getMessage());
            }
        }
        else {
            throw new KidsException("No se puede eliminar un participante " +
                    "mientras el juego esta en curso");
        }
    }

    public List<Kid> getAllKids () throws KidsException{
        try {
            return listDEService.getAllKids();
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }

    public String moveKid(int pos, String kidId) throws KidsException {
        if (game.getAwaitingKid() == null || !kidId.equals(game.getAwaitingKid().getId())) {
            try {
                listDEService.moveKidToPosition(pos, pos + 1); // Sumar 1 para ajustar la posición
                return "Niño movido";
            } catch (KidsException e) {
                throw new KidsException(e.getMessage());
            }
        } else {
            throw new KidsException("No se puede mover puesto que no se ha respondido");
        }
    }



    public DataStructureDTO roleGame() throws KidsException{
        if(game.getAwaitingKid()==null) {
            Random rand = new Random();
            int randomPosition = rand.nextInt(2000);
            int actualKidPosition = randomPosition % listDEService.getKids().getSize();
            int actualQuestionPos = randomPosition % questionService.getAll().size();

            NodeECircular temp;
            if (game.getAwaitingKid() == null) {
                temp = listDEService.getKids().getHead();
            } else {
                temp = game.getAwaitingNode();
            }
            while (actualKidPosition > 0) {
                temp = temp.getNext();
                actualKidPosition--;
            }
            Question question = questionService.getAll().get(actualQuestionPos);
            Question newQuestion = new Question(question.getQuestion(),question.getOpciones(),
                    question.getPoscorrecta(), question.getIdpregunta());

            game.setAnswerState(true);
            game.setAwaitingKid(temp.getData());
            game.setGameState(true);
            game.setAwaitingNode(temp);
            game.setAwaitingQuestion(new DataStructureDTO(temp.getData(),newQuestion));

            return new DataStructureDTO(temp.getData(), newQuestion);
        }
        else {
            throw new KidsException("No se puede jugar si todavia no se ha respondido");
        }

    }
    public DataStructureDTO getQuestion (){
        return game.getAwaitingQuestion();
    }

    public String answerQuestion(DataStructureDTO response)throws KidsException{
        if(response.getKidData().getId().equals(game.getAwaitingQuestion().getKidData().getId())){

            Question question = questionService.getQuestionById(response.getQuestionData().getIdpregunta());

            if(question.getQuestion().equals(response.getQuestionData().getPoscorrecta())){
                game.setAnswerState(false);
                game.setAwaitingKid(null);
                return "Respuesta correcta, continua"+ game.getAwaitingQuestion().getKidData().getName();
            }
            else {
                //Change parameters
                game.setAwaitingNode(game.getAwaitingNode().getNext());
                game.setAnswerState(false);
                listDEService.getKids().deleteById(game.getAwaitingKid().getId());
                game.setAwaitingKid(null);
                return "Jugador eliminado continua"+game.getAwaitingNode().getData().getName();
            }
        }
        else{
            throw new KidsException("Debe respodner el jugador preguntado");
        }
    }
    

}
