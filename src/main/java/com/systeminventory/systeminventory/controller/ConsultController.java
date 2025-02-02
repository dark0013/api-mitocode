/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.controller;

import com.systeminventory.systeminventory.dto.ConsultDto;
import com.systeminventory.systeminventory.dto.ConsultListExamDto;
import com.systeminventory.systeminventory.model.Consult;
import com.systeminventory.systeminventory.model.Exam;

//import com.systeminventory.systeminventory.services.PatientServiceImpl;
import com.systeminventory.systeminventory.services.impl.ConsultServiceImpl;
import jakarta.validation.Valid;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
@RequestMapping("/consults")
@RequiredArgsConstructor
public class ConsultController {

//    private final PatientServicesImpl servi;
    private final ConsultServiceImpl servi;
    @Qualifier("DefaultMapper")
    private final ModelMapper mapper;



//    @PostMapping
//    public ResponseEntity<Void> save(@Valid @RequestBody ConsultDto patient) {
//        Consult obj = servi.save(convertToEntity(patient));
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsult()).toUri();
//        return ResponseEntity.created(location).build();
//    }
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody  ConsultListExamDto dto) {
        Consult cons = this.convertToEntity(dto.getConsult());
        List<Exam> exams = mapper.map(dto.getLstExam(), new TypeToken<List<Exam>>() {
        }.getType());
        Consult obj = servi.saveTransactional(cons, exams);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsult()).toUri();
        return ResponseEntity.created(location).build();
    }

  

    private ConsultDto convertToDto(Consult obj) {
        return mapper.map(obj, ConsultDto.class);
    }

    private Consult convertToEntity(ConsultDto dto) {
        return mapper.map(dto, Consult.class);
    }

}
