package com.drz.place.business;

import com.drz.place.dto.place.PlaceDTO;
import com.drz.place.persistence.entity.PlaceNotFoundException;

public interface PlaceService {

    public PlaceDTO save(PlaceDTO place) throws PlaceNotFoundException;

    public PlaceDTO find(Long id) throws PlaceNotFoundException;

    public void delete(Long id) throws PlaceNotFoundException;
}
