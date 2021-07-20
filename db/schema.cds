    namespace com.sap.animalsservice.entities;

    using { cuid } from '@sap/cds/common';

    entity Users : cuid {
        animals : Association to many Animals on animals.owner = $self;
        name : String(100);
    }

    entity Animals : cuid {
        name : String(100);
        owner : Association to Users;
    }

    entity Cats {
       animalFields : Association to Animals;
       catbreed : String(100);
    }

    entity Dogs {
        animalFields : Association to Animals;
        dogbreed : String(100);
    }