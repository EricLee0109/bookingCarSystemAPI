package com.bookingcar.web.rest;

import com.bookingcar.domain.*;
import com.bookingcar.repository.CarRepository;
import com.bookingcar.repository.EmployeeRepository;
import com.bookingcar.repository.UserRepository;
import com.bookingcar.service.*;
import com.bookingcar.web.rest.errors.BadRequestAlertException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.bookingcar.web.rest.vm.CarAttributePayload;
import com.bookingcar.web.rest.vm.CreateCarVM;
import com.bookingcar.web.rest.vm.ImagePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import static com.bookingcar.utils.Utils.saveFile;

/**
 * REST controller for managing {@link com.bookingcar.domain.Car}.
 */
@RestController
@RequestMapping("/api")
public class CarResource {

    private final Logger log = LoggerFactory.getLogger(CarResource.class);

    private static final String ENTITY_NAME = "car";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CarService carService;

    private final CarRepository carRepository;

    private final ShowRoomService showRoomService;

    private final CustomerService customerService;
    private final CarModelService carModelService;
    private final CarImageService carImageService;

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    private final AttributeService attributeService;

    private final CarAttributeService carAttributeService;

    private final EmployeeService employeeService;

    public CarResource(CarService carService, CarRepository carRepository, ShowRoomService showRoomService,
                       CustomerService customerService, CarModelService carModelService, CarImageService carImageService,
                       UserRepository userRepository, EmployeeRepository employeeRepository, AttributeService attributeService,
                       CarAttributeService carAttributeService, EmployeeService employeeService) {
        this.carService = carService;
        this.carRepository = carRepository;
        this.showRoomService = showRoomService;
        this.customerService = customerService;
        this.carModelService = carModelService;
        this.carImageService = carImageService;
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.carAttributeService = carAttributeService;
        this.attributeService = attributeService;
        this.employeeService = employeeService;
    }

    /**
     * {@code POST  /cars} : Create a new car.
     *
     * @param createCarVM the car to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new car, or with status {@code 400 (Bad Request)} if the car has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */

//    @PostMapping("/cars")
//    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car) throws URISyntaxException {
//        log.debug("REST request to save Car : {}", car);
//        if (car.getId() != null) {
//            throw new BadRequestAlertException("A new car cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        Car result = carService.save(car);
//        return ResponseEntity
//            .created(new URI("/api/cars/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@Valid @RequestBody CreateCarVM createCarVM, Principal principal) throws URISyntaxException {
        log.debug("REST request to save Car : {}", createCarVM);

        Optional<User> user = userRepository.findOneByLogin(principal.getName());
        Car saveCar = new Car();

        Authority adminAuth = new Authority();
        adminAuth.setName("ROLE_ADMIN");

        if (user.get().getAuthorities().contains(adminAuth)) {
            Employee employee = employeeService.findOne(createCarVM.getEmployeeId()).get();
            saveCar.setEmployee(employee);
        } else {
            saveCar.setEmployee(employeeRepository.findByUser(user.get()));
        }
        saveCar.setName(createCarVM.getName());
        saveCar.setPrice(createCarVM.getPrice());
        saveCar.setStatus(createCarVM.getStatus());
        saveCar.setLicensePlate(createCarVM.getLicensePlate());

        ShowRoom showRoom = showRoomService.findOne(createCarVM.getShowroomId()).get();
        saveCar.setShowroom(showRoom);
        Customer customer = customerService.findOne(createCarVM.getCustomerId()).get();
        saveCar.setCustomer(customer);
        CarModel carModel = carModelService.findOne(createCarVM.getCarModelId()).get();
        saveCar.setCarmodel(carModel);


        log.debug("REST request to save Car : {}", saveCar);

        Car result = carService.save(saveCar);

//        saveCarImage(ENTITY_NAME, createCarVM.getImagePayloads(), result.getId());

        for (CarAttributePayload carAttribute: createCarVM.getCarattributes()) {
            Attribute attribute = attributeService.findOne(carAttribute.getAttributeId()).get();
            CarAttribute carAttribute1 = new CarAttribute();
            carAttribute1.setAttribute(attribute);
            carAttribute1.setCar(result);
            carAttribute1.setAttributeValue(carAttribute.getAttributeValue());
            carAttributeService.save(carAttribute1);
        }
//
//        createCarVM.getCarattributes().forEach(carAttribute -> {
//            Attribute attribute = attributeService.findOne(carAttribute.getAttributeId()).get();
//            CarAttribute carAttribute1 = new CarAttribute();
//            carAttribute1.setAttribute(attribute);
//            carAttribute1.setCar(result);
//            carAttribute1.setAttributeValue(carAttribute.getAttributeValue());
//            carAttributeService.save(carAttribute1);
//        });

        return ResponseEntity
            .created(new URI("/api/cars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    private void saveCarImage(String entityName, List<ImagePayload> payload, long carId) {
        payload.forEach(imagePayload -> {
            if (imagePayload.getImage().isEmpty()) {
                throw new BadRequestAlertException("Request must include image", entityName, "idexists");
            }
        });

        payload.forEach(imagePayload -> {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(imagePayload.getImage().getOriginalFilename()));
            String generatedString = UUID.randomUUID().toString();
            String uploadDir = "user-photos/";
            String filePath = generatedString + "-" + fileName;

            try {
                saveFile(uploadDir, filePath, imagePayload.getImage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String url = uploadDir + filePath;

            CarImage carImage = new CarImage();
            Car car = carService.findOne(carId).get();

            carImage.setCarImageUrl(url);
            carImage.setImageDescription(imagePayload.getImageDescription());
            carImage.setCar(car);

            log.debug("===" + carImage.getCarImageUrl() + carImage.getImageDescription());
            CarImage result = carImageService.save(carImage);

            CarImage saveCarImage = new CarImage();
            saveCarImage.setImageDescription(imagePayload.getImageDescription());
            saveCarImage.setCarImageUrl(url);
            carImageService.save(saveCarImage);
        });

    }

//    @PostMapping("/cars")
//    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car, @RequestBody List<CarAttribute> carAttributeList) throws URISyntaxException {
//        log.debug("REST request to save Car : {}", car);
//        if (car.getId() != null) {
//            throw new BadRequestAlertException("A new car cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        Car result = carService.save(car);
//        return ResponseEntity
//            .created(new URI("/api/cars/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

    /**
     * {@code PUT  /cars/:id} : Updates an existing car.
     *
     * @param id the id of the car to save.
     * @param car the car to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated car,
     * or with status {@code 400 (Bad Request)} if the car is not valid,
     * or with status {@code 500 (Internal Server Error)} if the car couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Car car)
        throws URISyntaxException {
        log.debug("REST request to update Car : {}, {}", id, car);
        if (car.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, car.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Car result = carService.update(car);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, car.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /cars/:id} : Partial updates given fields of an existing car, field will ignore if it is null
     *
     * @param id the id of the car to save.
     * @param car the car to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated car,
     * or with status {@code 400 (Bad Request)} if the car is not valid,
     * or with status {@code 404 (Not Found)} if the car is not found,
     * or with status {@code 500 (Internal Server Error)} if the car couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cars/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Car> partialUpdateCar(@PathVariable(value = "id", required = false) final Long id, @NotNull @RequestBody Car car)
        throws URISyntaxException {
        log.debug("REST request to partial update Car partially : {}, {}", id, car);
        if (car.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, car.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!carRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Car> result = carService.partialUpdate(car);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, car.getId().toString())
        );
    }

    /**
     * {@code GET  /cars} : get all the cars.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cars in body.
     */
    @GetMapping("/cars")
    public List<Car> getAllCars() {
        log.debug("REST request to get all Brands");
        return carRepository.findAll();
    }

    @GetMapping("/cars/search")
    public List<Car> gerCars(@RequestParam(required = false, defaultValue = "") String area,
                             @RequestParam(required = false, defaultValue = "0") long minPrice,
                             @RequestParam(required = false, defaultValue =  Long.MAX_VALUE + "") long maxPrice,
                             @RequestParam(required = false, defaultValue = "-1") long brandId,
                             @RequestParam(required = false, defaultValue = "-1") long modelId) {
        log.debug("REST request to get all Cars");
        if (brandId < 0) {
            return carService.findAllWithoutBrand(area, minPrice, maxPrice);
        }
        if (modelId < 0) {
            return carService.findAll(area, minPrice, maxPrice, brandId);
        }
        return carService.findWithCarModel(area, minPrice, maxPrice, brandId, modelId);
    }

    @GetMapping("/cars/model/{brandId}")
    public List<Car> getCarByBrand(@PathVariable Long brandId) {
        log.debug("REST request to get Car by brand Id : {}", brandId);
        List<Car> cars = carService.findByCarmodel_Brand_Id(brandId);
        return cars;
    }

    /**
     * {@code GET  /cars/:id} : get the "id" car.
     *
     * @param id the id of the car to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the car, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        log.debug("REST request to get Car : {}", id);
        Optional<Car> car = carService.findOne(id);
        return ResponseUtil.wrapOrNotFound(car);
    }

    /**
     * {@code DELETE  /cars/:id} : delete the "id" car.
     *
     * @param id the id of the car to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        log.debug("REST request to delete Car : {}", id);
        carService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
