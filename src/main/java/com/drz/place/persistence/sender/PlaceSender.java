package com.drz.place.persistence.sender;

import com.drz.place.dto.place.PlaceDTO;

public interface PlaceSender {

    public void sendSave(PlaceDTO placeDTO);

    public void sendDelete(PlaceDTO placeDTO);

}
