package io.github.vafmendes.imageliteapi.application.images;

import io.github.vafmendes.imageliteapi.domain.entity.Image;
import io.github.vafmendes.imageliteapi.domain.enums.ImageExtension;
import io.github.vafmendes.imageliteapi.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {

    private final ImageService service;


    /*private static final Logger log = LoggerFactory.getLogger(ImagesController.class);*/

    @PostMapping
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags")List<String> tags
            ) throws IOException {
        log.info("Imagem recebida: name: {}, size, {}", file.getOriginalFilename(), file.getSize());
//        log.info("Content type: {}", file.getContentType());
//        log.info("Media Type: {}", MediaType.valueOf(file.getContentType()));



        Image image = Image.builder()
                .name(name)
                .tags(String.join(",", tags))
                .size(file.getSize())
                .extension(ImageExtension.valueOf(MediaType.valueOf(file.getContentType())))
                .file(file.getBytes())
                .build();

        service.save(image);

        return ResponseEntity.ok().build();

    }


}
