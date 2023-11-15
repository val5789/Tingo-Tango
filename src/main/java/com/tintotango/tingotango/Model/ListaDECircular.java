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

    public void insertInPosition (int position, Kid kid) {
        if (position == 1) {
            this.addToStart(kid);

        } else if (position > this.size) {
            this.addKidToEnd(kid);

        } else if (position <= this.size) {
            NodeECircular temp = this.head;
            int posAct = 1;
            while (posAct < position - 1) {
                temp = temp.getNext();
                posAct++;
            }
            NodeECircular newNode = new NodeECircular(kid);
            temp.getNext().setPrevious(newNode);
            newNode.setNext(temp.getNext());
            newNode.setPrevious(temp);
            temp.setNext(newNode);
            this.size++;
        }

    }

    public void deleteById(String id) throws KidsException {
        if (this.head == null) {
            throw new KidsException("Lista vacía");
        } else if (this.head.getData().getId().equals(id)) {
            // El nodo a eliminar es la cabeza
            if (this.size == 1) {
                // Único nodo en la lista
                this.head = null;
                this.tail = null;
            } else {
                // Hay más de un nodo en la lista
                this.head = this.head.getNext();
                this.head.setPrevious(this.tail);
                this.tail.setNext(this.head);
            }
            this.size--;
        } else {
            NodeECircular temp = this.head.getNext();
            while (temp != this.head) {
                if (temp.getData().getId().equals(id)) {
                    NodeECircular previous = temp.getPrevious();
                    NodeECircular next = temp.getNext();
                    previous.setNext(next);
                    next.setPrevious(previous);
                    this.size--;
                    break;
                }
                temp = temp.getNext();
            }
        }
    }

    public void deleteByPosition(int position) throws KidsException {
        if (position <= 0 || position > this.size) {
            throw new KidsException("Fuera de rango");
        }

        if (this.size == 1) {
            // Único nodo en la lista
            this.head = null;
            this.tail = null;
        } else {
            NodeECircular current = this.head;

            for (int i = 1; i < position; i++) {
                current = current.getNext();
            }

            if (current == this.head) {
                // El nodo a eliminar es la cabeza
                this.head = current.getNext();
                this.head.setPrevious(this.tail);
                this.tail.setNext(this.head);
            } else if (current == this.tail) {
                // El nodo a eliminar es la cola
                this.tail = current.getPrevious();
                this.tail.setNext(this.head);
                this.head.setPrevious(this.tail);
            } else {
                // El nodo a eliminar está en el medio
                NodeECircular previous = current.getPrevious();
                NodeECircular next = current.getNext();
                previous.setNext(next);
                next.setPrevious(previous);
            }
        }

        this.size--;
    }

    public void moveKidToPosition(int currentPosition, int newPosition) throws KidsException {
        if (this.head == null) {
            throw new KidsException("Lista vacía");
        }

        int actualPos = currentPosition % this.size;
        int newPos = newPosition % this.size;

        NodeECircular kidNode = getNodeByPosition(actualPos);

        if (kidNode == null) {
            throw new KidsException("Niño no encontrado en la posición actual");
        }

        deleteByPosition(actualPos);

        if (newPosition == 1) {
            addToStart(kidNode.getData());
        } else if (newPosition == this.size + 1) {
            addKidToEnd(kidNode.getData());
        } else {
            insertInPosition(newPosition,kidNode.getData());
        }
    }

    private NodeECircular getNodeByPosition(int position) throws KidsException {
        if (this.head == null) {
            throw new KidsException("Lista vacía");
        }

        if (position < 1 || position > this.size) {
            throw new KidsException("Posición inválida");
        }

        NodeECircular current = this.head;

        for (int i = 1; i < position; i++) {
            current = current.getNext();
        }

        return current;
    }





































}//FIN DEL PUBLIC CLASS LISTA DE CIRCULAR
