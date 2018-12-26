package com.drz.place.web.controller.v1;

import com.drz.place.business.PlaceService;
import com.drz.place.dto.place.ErrorResponse;
import com.drz.place.dto.place.PlaceDTO;
import com.drz.place.persistence.entity.PlaceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PlaceController {

    private PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping(value = "/place/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlaceDTO getPlace(@PathVariable Long id) throws PlaceNotFoundException {
        return placeService.find(id);
    }

    @PostMapping(value = "/place", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlaceDTO create(@RequestBody @Valid PlaceDTO place) throws PlaceNotFoundException {
        place.setId(null);
        return placeService.save(place);
    }

    @PutMapping(value = "/place/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlaceDTO update(@PathVariable Long id, @RequestBody @Valid PlaceDTO place) throws PlaceNotFoundException {
        place.setId(id);
        return placeService.save(place);
    }

    @DeleteMapping(value = "/place/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity delete(@PathVariable Long id) throws PlaceNotFoundException {
        placeService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(produces = "application/json")
    @ExceptionHandler(PlaceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(PlaceNotFoundException ex) {
        return new ErrorResponse(ex.getMessage(), ex.getCode());
    }
}
