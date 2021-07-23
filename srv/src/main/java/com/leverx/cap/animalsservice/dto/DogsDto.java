package com.leverx.cap.animalsservice.dto;

import com.leverx.cap.animalsservice.dto.enums.AnimalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DogsDto extends AnimalsDto {

    private String dogbreed;

    public DogsDto() {
        super.setType(AnimalType.DOG);
    }
}
