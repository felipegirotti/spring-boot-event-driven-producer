package com.drz.place.persistence.repository;

import com.drz.place.persistence.entity.PlaceNotFoundException;
import com.drz.place.dto.place.PlaceDTO;

public interface PlaceRepositoryService {

    public PlaceDTO save(PlaceDTO placeDTO) throws PlaceNotFoundException;

    public PlaceDTO find(Long id) throws PlaceNotFoundException;

    public void delete(Long id) throws PlaceNotFoundException;
}
