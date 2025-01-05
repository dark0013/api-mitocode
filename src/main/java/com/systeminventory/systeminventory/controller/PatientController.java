/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.controller;

import com.systeminventory.systeminventory.dto.PatientDto;
import com.systeminventory.systeminventory.model.Patient;

import com.systeminventory.systeminventory.services.impl.PatientServiceImpl;
//import com.systeminventory.systeminventory.services.PatientServiceImpl;
import com.systeminventory.systeminventory.services.impl.PatientServicesImpl;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import org.springframework.hateoas.EntityModel;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//import static org.springframework.hateoas.EntityModel.of;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
/**
 *
 * @author Alain
 */
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

//    private final PatientServicesImpl servi;
    private final PatientServiceImpl servi;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PatientDto>> findAll() {
//        List<PatientDto> list = servi.findAll()
//                .stream()
//                .map(e -> mapper.map(e, PatientDto.class))
//                .collect(Collectors.toList());
        List<PatientDto> list = servi.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, OK);
    }

//    @GetMapping("/nombres")
//    public ResponseEntity<List<Map<String, String>>> nombrePacientes() {
//        List<Object[]> list = servi.nombrePacientes();
//        List<Map<String, String>> nombres = new ArrayList<>();
//
//        for (Object[] obj : list) {
//            Map<String, String> nameMap = new HashMap<>();
//            nameMap.put("firstName", (String) obj[0]);
//            nameMap.put("lastName", (String) obj[1]);
//            nombres.add(nameMap);
//        }
//
//        return new ResponseEntity<>(nombres, OK);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> findById(@PathVariable("id") Integer id) {
        Patient obj = servi.findbyId(id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    //no lo eh probado
    @GetMapping("/hateoas/{id}")
    public EntityModel<PatientDto> finModelHateoas(@PathVariable("id") Integer id) {
        Patient obj = servi.findbyId(id);
        EntityModel<PatientDto> resource = EntityModel.of(mapper.map(obj, PatientDto.class));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("patient-info1"));
        return resource;
    }

//    @GetMapping("/hateoas/{id}")
//    public EntityModel<PatientDto> findByIdHateoas(@PathVariable("id") Integer id) {
//        Patient obj = servi.findbyId(id);
//        EntityModel<PatientDto> resource = of(mapper.map(obj, PatientDto.class));
//        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findByIdHateoas(id));
//
//        resource.add(link1.withRel("patient-info1"));
//        return resource;
//    }
//    @PostMapping
//    public ResponseEntity<Patient> save(@RequestBody Patient patient) {
//        return new ResponseEntity<>(servi.save(patient), CREATED);
//
//    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PatientDto patient) {
        Patient obj = servi.save(convertToEntity(patient));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> update(@PathVariable("id") Integer id, @Valid @RequestBody PatientDto patient) {
        patient.setIdPatient(id);
        Patient obj = servi.update(convertToEntity(patient), id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        servi.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private PatientDto convertToDto(Patient obj) {
        return mapper.map(obj, PatientDto.class);
    }

    private Patient convertToEntity(PatientDto dto) {
        return mapper.map(dto, Patient.class);
    }

}
