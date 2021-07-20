using { com.sap.animalsservice.entities as entities } from '../db/schema';

service AnimalsService {

    entity Dogs as select from entities.Dogs {
        Dogs.animalFields.ID,
        Dogs.animalFields.name,
        Dogs.animalFields.owner.ID as ownerId,
        Dogs.dogbreed
    };

    entity Cats as select from entities.Cats {
        Cats.animalFields.ID,
        Cats.animalFields.name,
        Cats.animalFields.owner.ID as ownerId,
        Cats.catbreed
    };

    entity Users as projection on entities.Users {
        Users.ID,
        Users.name,
        Users.animals.ID as animalsId
    }

    action swapAnimals(firstAnimalId: UUID, secondAnimalId: UUID);

}