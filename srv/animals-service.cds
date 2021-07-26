using { com.sap.animalsservice.entities as entities } from '../db/schema';

service AnimalsService @(restrict: [
            { grant: 'READ', to: 'any' },
            { grant: '*', to: 'authenticated-user'}
        ])  {

    entity Users as projection on entities.Users;

    entity Animals as projection on entities.Animals;

    entity Dogs as select from entities.Dogs {
        Dogs.ID,
        Dogs.animals.name,
        Dogs.animals.owner,
        Dogs.dogbreed
    };

    entity Cats as select from entities.Cats {
        Cats.ID,
        Cats.animals.name,
        Cats.animals.owner.ID as owner_ID,
        Cats.catbreed
    };

    action swapAnimalsOwners(firstAnimalId: UUID, secondAnimalId: UUID);
    
}