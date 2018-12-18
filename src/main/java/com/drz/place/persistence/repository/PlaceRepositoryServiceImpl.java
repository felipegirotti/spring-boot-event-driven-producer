package com.drz.place.persistence.repository;

import com.drz.place.dto.place.factory.PlaceDTOFactory;
import com.drz.place.persistence.entity.PlaceNotFoundException;
import com.drz.place.persistence.entity.factory.PlaceFactory;
import com.drz.place.persistence.sender.PlaceSender;
import com.drz.place.dto.place.PlaceDTO;
import com.drz.place.persistence.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlaceRepositoryServiceImpl implements PlaceRepositoryService {

    private PlaceRepository placeRepository;

    private PlaceSender placeSender;

    @Autowired
    public PlaceRepositoryServiceImpl(PlaceRepository placeRepository, PlaceSender placeSender) {
        this.placeRepository = placeRepository;
        this.placeSender = placeSender;
    }

    @Override
    public PlaceDTO save(PlaceDTO placeDTO) throws PlaceNotFoundException {
        Place place = PlaceFactory.create(placeDTO);
        if (placeDTO.getId() != null) {
            PlaceDTO placeFound = find(placeDTO.getId());
            place.setId(placeFound.getId());
        }

        placeRepository.save(place);

        placeDTO.setId(place.getId());
        placeSender.sendSave(placeDTO);

        return placeDTO;
    }

    @Override
    public PlaceDTO find(Long id) throws PlaceNotFoundException {
        Optional<Place> placeOptional = placeRepository.findById(id);
        if (placeOptional.isPresent()) {
            Place place = placeOptional.get();
            return PlaceDTOFactory.create(place);
        }

        throw new PlaceNotFoundException();
    }

    @Override
    public void delete(Long id) throws PlaceNotFoundException {
        Optional<Place> placeOptional = placeRepository.findById(id);
        if (placeOptional.isPresent()) {
            Place place = placeOptional.get();
            placeRepository.delete(place);
            placeSender.sendDelete(PlaceDTOFactory.create(place));

            return;
        }

        throw new PlaceNotFoundException();
    }
}
