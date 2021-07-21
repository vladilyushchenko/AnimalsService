package com.sap.cap.animalsservice.dto;

import com.sap.cap.animalsservice.dto.enums.AnimalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CatsDto extends AnimalsDto {

    private String catbreed;

    public CatsDto() {
        super.setType(AnimalType.CAT);
    }
}
