package com.tintotango.tingotango.Controller;

import com.tintotango.tingotango.Controller.DTO.ResponseDTO;
import com.tintotango.tingotango.Exceptions.KidsException;
import com.tintotango.tingotango.Model.Kid;
import com.tintotango.tingotango.Model.ListaDECircular;
import com.tintotango.tingotango.Service.ListaDECircularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path= "/tingotango")
public class ListaDECircularController {
    @Autowired
    private ListaDECircularService listaDECircularService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    listaDECircularService.getAllKids(),null),HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    null,errors),HttpStatus.OK);
        }//FIN DEL TRY
    }//FIN DEL PUBLIC GET MAPPING GET ALL KIDS

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDTO> addKidToEnd(@RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listaDECircularService.addKidToEnd(kid),null),HttpStatus.OK);
    }

    @PostMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody  Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listaDECircularService.addKidToStart(kid),null),HttpStatus.OK);
    }

    @PostMapping(path = "/insertinpos/{position}")
    public ResponseEntity<ResponseDTO> insertInPosition(@PathVariable int position, @RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listaDECircularService.insertInPosition(position,kid),null),HttpStatus.OK);
    }

    @DeleteMapping(path="/deletebyid/{id}")
    public ResponseEntity<ResponseDTO> deleteById( @PathVariable String id) throws KidsException {
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listaDECircularService.deleteById(id),null),HttpStatus.OK);
    }





}
