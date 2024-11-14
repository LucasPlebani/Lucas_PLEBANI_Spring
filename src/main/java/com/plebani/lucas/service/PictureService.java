package com.plebani.lucas.service;

import com.plebani.lucas.model.PictureModel;
import com.plebani.lucas.repository.PictureRepository;
import com.plebani.lucas.exception.PictureNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    // Get All Pictures
    public List<PictureModel> getAllPictures() {
        return pictureRepository.findAll();
    }


    public PictureModel createPicture(PictureModel picture) {
      
        return pictureRepository.save(picture);
    }

 
    public Optional<PictureModel> getPictureById(Long id) {
        return pictureRepository.findById(id);
    }

  
    public PictureModel updatePicture(Long id, PictureModel pictureDetails) {
       
        return pictureRepository.findById(id).map(picture -> {
            picture.setTitle(pictureDetails.getTitle());
            picture.setPhotographer(pictureDetails.getPhotographer());
            picture.setReleaseDate(pictureDetails.getReleaseDate());
            picture.setStatus(pictureDetails.getStatus());
            return pictureRepository.save(picture);
        }).orElseThrow(() -> new PictureNotFoundException("Picture not found with ID: " + id));
    }

 
    public void deletePicture(Long id) {
      
        pictureRepository.findById(id).orElseThrow(() -> new PictureNotFoundException("Picture not found with ID: " + id));
        pictureRepository.deleteById(id);
    }
}
