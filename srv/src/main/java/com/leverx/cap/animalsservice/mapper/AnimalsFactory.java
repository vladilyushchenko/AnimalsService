package com.leverx.cap.animalsservice.mapper;

import cds.gen.com.sap.animalsservice.entities.Animals;
import cds.gen.com.sap.animalsservice.entities.Cats;
import cds.gen.com.sap.animalsservice.entities.Dogs;
import org.springframework.stereotype.Component;

@Component
public class AnimalsFactory {

    public Cats createCats() {
        return Cats.create();
    }

    public Animals createAnimals() {
        return Animals.create();
    }

    public Dogs createDogs() {
        return Dogs.create();
    }
}
