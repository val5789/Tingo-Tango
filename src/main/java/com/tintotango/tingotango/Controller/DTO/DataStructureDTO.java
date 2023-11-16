package com.tintotango.tingotango.Controller.DTO;

import com.tintotango.tingotango.Model.Kid;
import com.tintotango.tingotango.Model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DataStructureDTO {
    private Kid kidData;
    private Question questionData;
}
