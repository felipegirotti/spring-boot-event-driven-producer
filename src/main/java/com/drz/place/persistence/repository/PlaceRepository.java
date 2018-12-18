package com.drz.place.persistence.repository;

import com.drz.place.persistence.entity.Place;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlaceRepository extends PagingAndSortingRepository<Place, Long> {
}
