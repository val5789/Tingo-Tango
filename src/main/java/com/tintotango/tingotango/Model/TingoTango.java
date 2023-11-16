package com.tintotango.tingotango.Model;

import com.tintotango.tingotango.Controller.DTO.DataStructureDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TingoTango {
    private ListaDECircular participants;
    private List<Question> questions;
    private boolean gameState;
    private boolean answerState;
    private Kid awaitingKid;
    private NodeECircular awaitingNode;
    private DataStructureDTO awaitingQuestion;

}

