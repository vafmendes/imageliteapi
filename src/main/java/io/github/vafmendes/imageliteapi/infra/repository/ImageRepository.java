package io.github.vafmendes.imageliteapi.infra.repository;

import io.github.vafmendes.imageliteapi.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {

}
