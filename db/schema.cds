    namespace com.sap.animalsservice.entities;

    using { cuid } from '@sap/cds/common';

    type AnimalType : String enum { CAT; DOG; };

    entity Users : cuid {
        animals : Association to many Animals on animals.owner = $self;
        name : String(100);
    }

    entity Animals : cuid {
        name : String(100);
        type : AnimalType;
        owner : Association to Users;
    }

    entity Cats {
        animals : Association to Animals;
        catbreed : String(100);
    }

    entity Dogs {
        animals : Association to Animals;
        dogbreed : String(100);
    }