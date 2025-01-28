/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.controller;


import com.systeminventory.systeminventory.dto.ExamDto;
import com.systeminventory.systeminventory.model.Exam;

//import com.systeminventory.systeminventory.services.PatientServiceImpl;
import com.systeminventory.systeminventory.services.impl.ExamServiceImpl;
import jakarta.validation.Valid;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {

//    private final PatientServicesImpl servi;
    private final ExamServiceImpl servi;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ExamDto>> findAll() {

        List<ExamDto> list = servi.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<ExamDto> findById(@PathVariable("id") Integer id) {
        Exam obj = servi.findbyId(id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    //no lo eh probado
    @GetMapping("/hateoas/{id}")
    public EntityModel<ExamDto> finModelHateoas(@PathVariable("id") Integer id) {
        Exam obj = servi.findbyId(id);
        EntityModel<ExamDto> resource = EntityModel.of(mapper.map(obj, ExamDto.class));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("patient-info1"));
        return resource;
    }


    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ExamDto patient) {
        Exam obj = servi.save(convertToEntity(patient));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdExam()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDto> update(@PathVariable("id") Integer id, @Valid @RequestBody ExamDto patient) {
        patient.setIdExam(id);
        Exam obj = servi.update(convertToEntity(patient), id);
        return new ResponseEntity<>(convertToDto(obj), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        servi.delete(id);
        return new ResponseEntity<>(NO_CONTENT);
    }

    private ExamDto convertToDto(Exam obj) {
        return mapper.map(obj, ExamDto.class);
    }

    private Exam convertToEntity(ExamDto dto) {
        return mapper.map(dto, Exam.class);
    }

}
