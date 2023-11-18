package com.example.backend.dao;

import com.example.backend.models.entity.Owner;

public interface OwnerRepoCustom {
    Owner getOwnerAlongWithHouses(int theId);
    void updateOwner(Owner theOwner);
}
