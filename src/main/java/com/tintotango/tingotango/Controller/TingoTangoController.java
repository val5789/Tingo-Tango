package com.tintotango.tingotango.Controller;

import com.tintotango.tingotango.Controller.DTO.DataStructureDTO;
import com.tintotango.tingotango.Controller.DTO.ResponseDTO;
import com.tintotango.tingotango.Exceptions.KidsException;
import com.tintotango.tingotango.Model.Kid;
import com.tintotango.tingotango.Model.Question;
import com.tintotango.tingotango.Service.TingoTangoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="tingotango")
public class TingoTangoController {
    @Autowired
    private TingoTangoService tingoTangoService;

    @PostMapping(path = "/addquestion")
    public ResponseEntity<ResponseDTO> addQuestion(@RequestBody Question newQuestion) {
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                tingoTangoService.addQuestion(newQuestion), null), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deletequestionbyid/{id}")
    public ResponseEntity<ResponseDTO> deleteQuestion(@PathVariable String id) {
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.deleteQuestionById(id), null), HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    null, errors), HttpStatus.OK);
        }
    }

    @PutMapping(path = "/updatequestionbyid/{id}")
    public ResponseEntity<ResponseDTO> updateQuestionById(@PathVariable String id, @RequestBody Question newQuestion) {
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.updateQuestionById(id, newQuestion), null), HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    null, errors), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/getquestions")
    public ResponseEntity<ResponseDTO> getQuestions() {
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                tingoTangoService.getQuestions(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/getquestionbyid/{id}")
    public ResponseEntity<ResponseDTO> getQuestionById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.getQuestionById(id), null), HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    null, errors), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/getkids")
    public ResponseEntity<ResponseDTO> getAll() {
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.getAllKids(), null), HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null, errors), HttpStatus.OK);
        }
    }


    @PostMapping(path = "/addkidtostart")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody Kid kid) {
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.addToStart(kid), null), HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null, errors), HttpStatus.OK);
        }
    }

    @PostMapping(path = "/addkidtoend")
    public ResponseEntity<ResponseDTO> addToEnd(@RequestBody Kid kid){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.addKidToEnd(kid),null),HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }

    @GetMapping(path = "/rolegame/{direction}")
    public ResponseEntity<ResponseDTO> roleGame(@PathVariable String direction) {
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.roleGame(direction), null), HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null, errors), HttpStatus.OK);
        }
    }

    @GetMapping(path = "/getawaitingquestion")
    public ResponseEntity<ResponseDTO> getAwaitingQuestion() {
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                tingoTangoService.getQuestion(), null), HttpStatus.OK);
    }

    @PostMapping(path = "/answerquestion")
    public ResponseEntity<ResponseDTO> answerQuestion(@RequestBody DataStructureDTO response) {

        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.answerQuestion(response), null), HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null, errors), HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/deletekidinpos/{pos}")
    public ResponseEntity<ResponseDTO> deleteKidInPos(@PathVariable int pos) {
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.deleteKidInPos(pos), null), HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null, errors), HttpStatus.OK);
        }
    }
}
