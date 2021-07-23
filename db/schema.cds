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

    // DELETE on animals.ID = id to return previous
    entity Cats : cuid {
        animals : Association to Animals on animals.ID = ID;
        catbreed : String(100);
    }

    // DELETE on animals.ID = id to return previous
    entity Dogs : cuid {
        animals : Association to Animals on animals.ID = ID;
        dogbreed : String(100);
    }