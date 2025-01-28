/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.controller;

import com.systeminventory.systeminventory.dto.SpecialtyDto;
import com.systeminventory.systeminventory.dto.SpecialtyDto;
import com.systeminventory.systeminventory.model.Patient;
import com.systeminventory.systeminventory.model.Specialty;

import com.systeminventory.systeminventory.services.impl.PatientServiceImpl;
//import com.systeminventory.systeminventory.services.PatientServiceImpl;
import com.systeminventory.systeminventory.services.impl.PatientServicesImpl;
import com.systeminventory.systeminventory.services.impl.SpecialtyServiceImpl;
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
@RequestMapping("/specialty")
@RequiredArgsConstructor
public class SpecialtyController {

//    private final PatientServicesImpl servi;
    private final SpecialtyServiceImpl servi;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SpecialtyDto>> findAll() {

        List<SpecialtyDto> list = servi.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDto> findById(@PathVariable("id") Integer id) {
        Specialty obj = servi.findbyId(id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    //no lo eh probado
    @GetMapping("/hateoas/{id}")
    public EntityModel<SpecialtyDto> finModelHateoas(@PathVariable("id") Integer id) {
        Specialty obj = servi.findbyId(id);
        EntityModel<SpecialtyDto> resource = EntityModel.of(mapper.map(obj, SpecialtyDto.class));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("patient-info1"));
        return resource;
    }


    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SpecialtyDto patient) {
        Specialty obj = servi.save(convertToEntity(patient));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSpecialty()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDto> update(@PathVariable("id") Integer id, @Valid @RequestBody SpecialtyDto patient) {
        patient.setIdSpecialty(id);
        Specialty obj = servi.update(convertToEntity(patient), id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        servi.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private SpecialtyDto convertToDto(Specialty obj) {
        return mapper.map(obj, SpecialtyDto.class);
    }

    private Specialty convertToEntity(SpecialtyDto dto) {
        return mapper.map(dto, Specialty.class);
    }

}
