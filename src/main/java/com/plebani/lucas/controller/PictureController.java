package com.plebani.lucas.controller;

import com.plebani.lucas.model.PictureModel;
import com.plebani.lucas.service.PictureService;
import com.plebani.lucas.exception.PictureNotFoundException; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pictures")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    
    @GetMapping
    public List<PictureModel> getAllPictures() {
        return pictureService.getAllPictures();
    }

   
    @PostMapping
    public ResponseEntity<PictureModel> createPicture(@RequestBody PictureModel picture) {
        PictureModel createdPicture = pictureService.createPicture(picture);
        return ResponseEntity.status(201).body(createdPicture); 
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<PictureModel> getPictureById(@PathVariable Long id) {
        return pictureService.getPictureById(id)
                .map(ResponseEntity::ok) 
                .orElseThrow(() -> new PictureNotFoundException("Picture not found with id: " + id));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<PictureModel> updatePicture(@PathVariable Long id, @RequestBody PictureModel pictureDetails) {
        try {
            PictureModel updatedPicture = pictureService.updatePicture(id, pictureDetails);
            return ResponseEntity.ok(updatedPicture); 
        } catch (PictureNotFoundException e) {
            throw new PictureNotFoundException("Picture not found with id: " + id); 
        }
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePicture(@PathVariable Long id) {
        try {
            pictureService.deletePicture(id);
            return ResponseEntity.noContent().build(); 
        } catch (PictureNotFoundException e) {
            throw new PictureNotFoundException("Picture not found with id: " + id); 
        }
    }

    
    @GetMapping("/test")
    public String testEndpoint() {
        return "Endpoint is working!";
    }
}

