package com.example.backend.repository;

import com.example.backend.models.entity.House;
import com.example.backend.models.entity.Owner;

public interface OwnerRepoCustom {
    Owner getOwnerAlongWithHouses(int theId);
    void updateOwner(Owner theOwner);
}
