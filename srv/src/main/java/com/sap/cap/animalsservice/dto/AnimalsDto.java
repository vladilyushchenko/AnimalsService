package com.sap.cap.animalsservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.cap.animalsservice.dto.enums.AnimalType;
import lombok.Data;

@Data
public class AnimalsDto {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("owner_ID")
    private String ownerId;

    private String name;

    private AnimalType type;
}
