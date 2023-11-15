package com.tintotango.tingotango.Model;

import lombok.Data;

@Data
public class NodeECircular {
    private Kid data;
    private NodeECircular previous;
    private NodeECircular Next;
    public NodeECircular(Kid data) {
        this.data = data;
    }
}//FIN DE LA CLASE NODE E MODEL
