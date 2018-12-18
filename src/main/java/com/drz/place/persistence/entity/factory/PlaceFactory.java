package com.drz.place.persistence.entity.factory;

import com.drz.place.dto.place.PlaceDTO;
import com.drz.place.persistence.entity.Place;

public class PlaceFactory {

    public static Place create(PlaceDTO placeDTO) {
        return new Place(
                placeDTO.getId(),
                placeDTO.getClientId(),
                placeDTO.getName(),
                placeDTO.getLatitude(),
                placeDTO.getLongitude()
        );
    }
}
