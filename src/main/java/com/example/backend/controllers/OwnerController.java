package com.example.backend.controllers;


import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Owner;
import com.example.backend.services.HouseService;
import com.example.backend.services.ImageService;
import com.example.backend.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.example.backend.util.ExtractId.ExtractIdFromToken;

@RestController
@RequestMapping("/api")
public class OwnerController {

    private final OwnerService ownerService;
    private final HouseService houseService;

    private final ImageService imageService;

    @Autowired
    public OwnerController(OwnerService theOwnerService, HouseService houseService, ImageService imageService) {
        ownerService = theOwnerService;
        this.houseService = houseService;
        this.imageService = imageService;
    }

    // expose "/owners" and return a list of owners
    @GetMapping("/owners")
    public List<Owner> findAll() {
        return ownerService.findAll();
    }


    @GetMapping("/owners/statistic")
    public List<Integer> statistic( @CookieValue("uss") String cookie){
        return  ownerService.statistics(Math.toIntExact(ExtractIdFromToken(cookie)));

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

//    @PutMapping("/add-house/{ownerId}")
//    public House addHouseForOwner(@PathVariable Integer ownerId, @RequestBody House theHouse, @RequestParam("image") MultipartFile[] files) throws Exception {
//        House addedHouse = houseService.save(theHouse);
//        return ownerService.addHouse(ownerId,
//                imageService.addImagesToHouse(files, addedHouse));
//    }
    @PutMapping("/add-house")
    public int addHouseForOwner( @CookieValue("uss") String cookie, @RequestBody House theHouse) {
        House addedHouse = houseService.save(theHouse);
        return ownerService.addHouse(Math.toIntExact(ExtractIdFromToken(cookie)), addedHouse);
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
    }


}














