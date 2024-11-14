package com.plebani.lucas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plebani.lucas.model.PictureModel;

public interface PictureRepository extends JpaRepository<PictureModel, Long> {
}