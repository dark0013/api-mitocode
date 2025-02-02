/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.controller;


import com.systeminventory.systeminventory.dto.MedicDto;
import com.systeminventory.systeminventory.model.Medic;

import com.systeminventory.systeminventory.services.impl.MedicServiceImpl;
import jakarta.validation.Valid;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * @author Alain
 */
@RestController
@RequestMapping("/medic")
@RequiredArgsConstructor
public class MedicController {

//    private final PatientServicesImpl servi;
    private final MedicServiceImpl servi;
  
    @Qualifier("medicMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<MedicDto>> findAll() {

        List<MedicDto> list = servi.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<MedicDto> findById(@PathVariable("id") Integer id) {
        Medic obj = servi.findbyId(id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    //no lo eh probado
    @GetMapping("/hateoas/{id}")
    public EntityModel<MedicDto> finModelHateoas(@PathVariable("id") Integer id) {
        Medic obj = servi.findbyId(id);
        EntityModel<MedicDto> resource = EntityModel.of(mapper.map(obj, MedicDto.class));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("patient-info1"));
        return resource;
    }


    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MedicDto patient) {
        Medic obj = servi.save(convertToEntity(patient));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedic()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicDto> update(@PathVariable("id") Integer id, @Valid @RequestBody MedicDto patient) {
        patient.setIdMedic(id);
        Medic obj = servi.update(convertToEntity(patient), id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        servi.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private MedicDto convertToDto(Medic obj) {
        return mapper.map(obj, MedicDto.class);
    }

    private Medic convertToEntity(MedicDto dto) {
        return mapper.map(dto, Medic.class);
    }

}
