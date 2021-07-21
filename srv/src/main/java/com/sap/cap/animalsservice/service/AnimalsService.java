package com.sap.cap.animalsservice.service;

import com.sap.cap.animalsservice.dto.AnimalsDto;
import org.springframework.stereotype.Service;

public interface AnimalsService {

    AnimalsDto createAnimals(AnimalsDto animalsDto);
}
