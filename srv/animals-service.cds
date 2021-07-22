using { com.sap.animalsservice.entities as entities } from '../db/schema';

service AnimalsService {

    entity Dogs as select from entities.Dogs {
        Dogs.animals.ID,
        Dogs.animals.name,
        Dogs.animals.owner.ID as owner_ID,
        Dogs.dogbreed
    };

    entity Cats as select from entities.Cats {
        Cats.animals.ID,
        Cats.animals.name,
        Cats.animals.owner.ID as owner_ID,
        Cats.catbreed
    };

    entity Users as select from entities.Users {
        Users.ID,
        Users.name,
        Users.animals
    };

    entity Animals as select from entities.Animals {
        Animals.owner,
        Animals.type,
        Animals.ID
    };

    action swapAnimals(firstAnimalId: UUID, secondAnimalId: UUID);

}