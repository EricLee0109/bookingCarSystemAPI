package com.bookingcar.web.rest;

import com.bookingcar.domain.CarImage;
import com.bookingcar.repository.CarImageRepository;
import com.bookingcar.service.CarImageService;
import com.bookingcar.service.CarService;
import com.bookingcar.web.rest.errors.BadRequestAlertException;
import com.bookingcar.domain.Car;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import  org.springframework.util.StringUtils;
import org.apache.commons.io.FileUtils;
import java.io.File;

/**
 * REST controller for managing {@link com.bookingcar.domain.CarImage}.
 */
@RestController
@RequestMapping("/api")
public class CarImageResource {

    private final Logger log = LoggerFactory.getLogger(CarImageResource.class);

    private static final String ENTITY_NAME = "carImage";
    private final String FILE_PATH_ROOT = System.getProperty("user.dir") + "/user-photos/";
    private final String DOMAIN = "http://localhost:8080";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarImageService carImageService;

    private final CarImageRepository carImageRepository;

    private final CarService carService;

    public CarImageResource(CarImageService carImageService, CarImageRepository carImageRepository, CarService carService) {
        this.carImageService = carImageService;
        this.carImageRepository = carImageRepository;
        this.carService = carService;
    }

    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }


    @RequestMapping(
        path = "/upload",
        method = RequestMethod.POST,
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CarImage> saveImage( @RequestPart("images") MultipartFile multipartFile, String description, long carId)
        throws IOException, URISyntaxException {
        if (multipartFile.isEmpty()) {
            throw new BadRequestAlertException("Request must include image", ENTITY_NAME, "idexists");
        }

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String generatedString = UUID.randomUUID().toString();
        String uploadDir = "user-photos/";

        saveFile(uploadDir, generatedString + "-" + fileName, multipartFile);

        String url = DOMAIN + "/api/image/" + generatedString + "-" +fileName ;

        CarImage carImage = new CarImage();
        Car car = carService.findOne(carId).get();

        carImage.setCarImageUrl(url);
        carImage.setImageDescription(description);
        carImage.setCar(car);

        log.debug("===" + carImage.getCarImageUrl() + carImage.getImageDescription());

        CarImage result = carImageService.save(carImage);

        return ResponseEntity
            .created(new URI("/api/car-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }



    /**
     * {@code PUT  /car-images/:id} : Updates an existing carImage.
     *
     * @param id the id of the carImage to save.
     * @param carImage the carImage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carImage,
     * or with status {@code 400 (Bad Request)} if the carImage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the carImage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/car-images/{id}")
    public ResponseEntity<CarImage> updateCarImage(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CarImage carImage
    ) throws URISyntaxException {
        log.debug("REST request to update CarImage : {}, {}", id, carImage);
        if (carImage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carImage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carImageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CarImage result = carImageService.update(carImage);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carImage.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /car-images/:id} : Partial updates given fields of an existing carImage, field will ignore if it is null
     *
     * @param id the id of the carImage to save.
     * @param carImage the carImage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated carImage,
     * or with status {@code 400 (Bad Request)} if the carImage is not valid,
     * or with status {@code 404 (Not Found)} if the carImage is not found,
     * or with status {@code 500 (Internal Server Error)} if the carImage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/car-images/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CarImage> partialUpdateCarImage(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CarImage carImage
    ) throws URISyntaxException {
        log.debug("REST request to partial update CarImage partially : {}, {}", id, carImage);
        if (carImage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, carImage.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carImageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CarImage> result = carImageService.partialUpdate(carImage);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, carImage.getId().toString())
        );
    }


    @GetMapping("/image/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
        String path = System.getProperty("user.dir");
        byte[] image = new byte[0];
        try {
            image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    /**
     * {@code GET  /car-images} : get all the carImages.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of carImages in body.
     */
    @GetMapping("/car-images")
    public List<CarImage> getAllCarImages(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all CarImages");
        return carImageService.findAll();
    }

    /**
     * {@code GET  /car-images/:id} : get the "id" carImage.
     *
     * @param id the id of the carImage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the carImage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/car-images/{id}")
    public ResponseEntity<CarImage> getCarImage(@PathVariable Long id) {
        log.debug("REST request to get CarImage : {}", id);
        Optional<CarImage> carImage = carImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(carImage);
    }

    /**
     * {@code DELETE  /car-images/:id} : delete the "id" carImage.
     *
     * @param id the id of the carImage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/car-images/{id}")
    public ResponseEntity<Void> deleteCarImage(@PathVariable Long id) {
        log.debug("REST request to delete CarImage : {}", id);
        carImageService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
