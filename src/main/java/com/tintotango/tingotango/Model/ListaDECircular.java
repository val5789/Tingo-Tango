package com.tintotango.tingotango.Model;

import com.tintotango.tingotango.Exceptions.KidsException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListaDECircular {
    private NodeECircular head;
    private NodeECircular tail;
    private int size;

    public List<Kid> getAllKids() throws KidsException {
        if (this.head == null) {
            throw new KidsException("Lista vacía");
        } else {
            List<Kid> kids = new ArrayList<>();
            NodeECircular current = this.head;

            do {
                kids.add(current.getData());
                current = current.getNext();
            } while (current != null && current != this.head);

            return kids;
        }
    }

    public void addKidToEnd(Kid kid) {
        NodeECircular newNode = new NodeECircular(kid);

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            newNode.setNext(newNode);
            newNode.setPrevious(newNode);
        } else {
            newNode.setPrevious(this.tail);
            newNode.setNext(this.head);
            this.tail.setNext(newNode);
            this.head.setPrevious(newNode);
            this.tail = newNode;
        }

        this.size++;
    }

    public void addToStart(Kid kid) {
        NodeECircular newNode = new NodeECircular(kid);

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            newNode.setNext(newNode);
            newNode.setPrevious(newNode);
        } else {
            newNode.setNext(this.head);
            newNode.setPrevious(this.tail);
            this.head.setPrevious(newNode);
            this.tail.setNext(newNode);
            this.head = newNode;
        }

        this.size++;
    }


    public void moveKidToPosition(int currentPosition, int newPosition, Kid kid) throws KidsException {
        //SI LA NUEVA POSICION VA A SER LA CABEZA
        if (newPosition==1){
            this.addToStart(kid);
        }

        //SI LA NUEVA POSICION ES EL FINAL DE LA LISTA
        else if (newPosition == this.size){
            this.addKidToEnd(kid);
        }

        //ELIMINAR EL NIÑO DE LA POSICION ACTUAL

        removeKidAtPosition(currentPosition);

        //INSERTAR EL NIÑO EN LA NUEVA POSICION

        insertKidAtPosition(newPosition, kid);


    }

    private void removeKidAtPosition(int position) throws KidsException {
        if (position < 1 || position > this.size) {
            throw new KidsException("Posición inválida para eliminar");
        }

        NodeECircular current = this.head;
        for (int i = 1; i < position; i++) {
            current = current.getNext();
        }

        // Enlazar el nodo anterior y siguiente para omitir el nodo actual
        current.getPrevious().setNext(current.getNext());
        current.getNext().setPrevious(current.getPrevious());

        this.size--;
    }

    private void insertKidAtPosition(int position, Kid kid) throws KidsException {
        if (position < 1 || position > this.size + 1) {
            throw new KidsException("Posición inválida para insertar");
        }

        NodeECircular newNode = new NodeECircular(kid);
        if (position == 1) {
            // Insertar al inicio
            newNode.setNext(this.head);
            newNode.setPrevious(this.tail);
            this.head.setPrevious(newNode);
            this.tail.setNext(newNode);
            this.head = newNode;
        } else {
            NodeECircular current = this.head;
            for (int i = 1; i < position - 1; i++) {
                current = current.getNext();
            }

            // Insertar en la posición indicada
            newNode.setNext(current.getNext());
            newNode.setPrevious(current);
            current.getNext().setPrevious(newNode);
            current.setNext(newNode);

            if (position == this.size + 1) {
                // Actualizar la cola si se inserta al final
                this.tail = newNode;
            }
        }

        this.size++;
    }



















}//FIN DEL PUBLIC CLASS LISTA DE CIRCULAR
