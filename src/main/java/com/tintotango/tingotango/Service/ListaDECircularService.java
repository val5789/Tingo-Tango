package com.tintotango.tingotango.Service;

import com.tintotango.tingotango.Exceptions.KidsException;
import com.tintotango.tingotango.Model.Kid;
import com.tintotango.tingotango.Model.ListaDECircular;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ListaDECircularService {
    private ListaDECircular kids;

    public ListaDECircularService(){
        //"BASE DE DATO DE LOS NIÑOS"
        kids = new ListaDECircular();
        kids.addKidToEnd(new Kid("Valeria Osorio Rios", (byte)20,"1001"));
        kids.addKidToEnd(new Kid("Jhair Sebastian Torres", (byte)19,"1002"));
        kids.addKidToEnd(new Kid("Sergio Nuñez", (byte)19,"1003"));
        kids.addKidToEnd(new Kid("Sebastian Rugeles", (byte)19,"1004"));
        kids.addKidToEnd(new Kid("Jhon Jaime Madrid", (byte)18,"1005"));
        kids.addKidToEnd(new Kid("Nicol Mariana Valencia Davila", (byte)22,"1006"));
    }// fin del public lista circular service

    public List<Kid> getAllKids()throws KidsException {
        return kids.getAllKids();
    }

    public String addKidToEnd(Kid newKid){
        kids.addKidToEnd(newKid);
        return "Adicionado";
    }

    public String addKidToStart(Kid kid){
        kids.addToStart(kid);
        return "Adicionado";
    }

    public String moveKidToPosition(int currentPosition, int newPosition, Kid id) throws KidsException {
        kids.moveKidToPosition(currentPosition,newPosition,id);
        return "Movido";
    }











}//FIN DE LA PUBLIC CLASS LISTA DE CIRCULAR SERVICE
