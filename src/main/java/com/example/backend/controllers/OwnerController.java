package com.example.backend.controllers;


import com.example.backend.dto.VisitCustomerRes;
import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Owner;
import com.example.backend.services.HouseService;
import com.example.backend.services.ImageService;
import com.example.backend.services.OwnerService;
import com.example.backend.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.example.backend.util.ExtractId.ExtractIdFromToken;

@RestController
@RequestMapping("/api")
public class OwnerController {

    private final OwnerService ownerService;
    private final HouseService houseService;

    private final VisitService visitService;

    @Autowired
    public OwnerController(OwnerService theOwnerService, HouseService houseService, VisitService visitService) {
        ownerService = theOwnerService;
        this.houseService = houseService;
        this.visitService = visitService;
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

    @GetMapping("/owner")
    public Owner getOwner( @RequestHeader("Authorization")  String cookie) {

        Owner theOwner = ownerService.findById(Math.toIntExact(ExtractIdFromToken(cookie)));

        if (theOwner == null) {
            throw new RuntimeException("Owner id not found - " + Math.toIntExact(ExtractIdFromToken(cookie)));
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


    @GetMapping("/visitCustomerInfo")
    public List<VisitCustomerRes> findVisitInfo(@CookieValue("uss") String cookie){
        return ownerService.findVisitCustomerInfo(Math.toIntExact(ExtractIdFromToken(cookie)));
    }


    @GetMapping("/visitPerMonth")
    public List<Integer> totalVisitPerMonth(@CookieValue("uss") String cookie ){
        return visitService.totalVisitPerMonth(Math.toIntExact(ExtractIdFromToken(cookie)));
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














