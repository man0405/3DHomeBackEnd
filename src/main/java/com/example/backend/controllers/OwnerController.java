package com.example.backend.controllers;


import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Owner;
import com.example.backend.services.HouseService;
import com.example.backend.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OwnerController {

    private OwnerService ownerService;
    @Autowired
    private HouseService houseService;

    @Autowired
    public OwnerController(OwnerService theOwnerService) {
        ownerService = theOwnerService;
    }

    // expose "/owners" and return a list of owners
    @GetMapping("/owners")
    public List<Owner> findAll() {
        return ownerService.findAll();
    }

    // add mapping for GET /owners/{ownerId}

    @GetMapping("/owners/{ownerId}")
    public Owner getOwner(@PathVariable int ownerId) {

        Owner theOwner = ownerService.findById(ownerId);

        if (theOwner == null) {
            throw new RuntimeException("Owner id not found - " + ownerId);
        }
        return theOwner;
    }


    // add mapping for POST /owners - add new owner

    @PostMapping("/owners")
    public Owner addOwner(@RequestBody Owner theOwner) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theOwner.setId(0);
        return ownerService.save(theOwner);
    }

    //  add mapping for PUT /owners - update existing owner

    @PutMapping("/owners")
    public Owner updateOwner(@RequestBody Owner theOwner) {
        return ownerService.save(theOwner);
    }

    // add mapping for PUT /owners - add house for an owner

    @PutMapping("/add-house/{ownerId}")
    public House addHouseForOwner(@PathVariable Integer ownerId, @RequestBody House theHouse){
        int addedHouse = houseService.save(theHouse);
        return ownerService.addHouse(ownerId, addedHouse);
    }


    // add mapping for DELETE /owners/{ownerId} - delete owner

    @DeleteMapping("/owners/{ownerId}")
    public String deleteOwner(@PathVariable int ownerId) {

        Owner tempOwner = ownerService.findById(ownerId);

        // throw exception if null

        if (tempOwner == null) {
            throw new RuntimeException("Owner id not found - " + ownerId);
        }

        ownerService.deleteById(ownerId);

        return "Deleted owner id - " + ownerId;
        // Hellobwew
    }


}














