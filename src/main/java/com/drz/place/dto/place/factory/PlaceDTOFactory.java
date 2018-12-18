package com.drz.place.dto.place.factory;

import com.drz.place.dto.place.PlaceDTO;
import com.drz.place.persistence.entity.Place;

public class PlaceDTOFactory {

    public static PlaceDTO create(Place place) {
        return new PlaceDTO(
                place.getId(),
                place.getClientId(),
                place.getName(),
                place.getLatitude(),
                place.getLongitude()
        );
    }
}
