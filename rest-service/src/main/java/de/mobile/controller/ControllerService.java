package de.mobile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface ControllerService {

    default ResponseEntity<String> createLocation(String path, String resource) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(path)
                .buildAndExpand(resource).toUri();
        return ResponseEntity.created(location).build();

    }

}
