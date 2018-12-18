package com.drz.place.business;

import com.drz.place.dto.place.PlaceDTO;
import com.drz.place.persistence.entity.PlaceNotFoundException;
import com.drz.place.persistence.repository.PlaceRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlaceServiceImpl implements PlaceService {

    private PlaceRepositoryService placeRepositoryService;

    @Autowired
    public PlaceServiceImpl(PlaceRepositoryService placeRepositoryService) {
        this.placeRepositoryService = placeRepositoryService;
    }

    public PlaceDTO save(PlaceDTO place) throws PlaceNotFoundException {
        return placeRepositoryService.save(place);
    }

    public PlaceDTO find(Long id) throws PlaceNotFoundException {
        return this.placeRepositoryService.find(id);
    }

    public void delete(Long id) throws PlaceNotFoundException {
        this.placeRepositoryService.delete(id);
    }
}
