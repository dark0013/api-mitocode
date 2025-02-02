/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.config;

import com.systeminventory.systeminventory.dto.MedicDto;
import com.systeminventory.systeminventory.model.Medic;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Alain
 */
@Configuration
public class MapperConfig {

    @Bean("DefaultMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean("medicMapper")
    public ModelMapper medicMapper() {

        ModelMapper mapper = new ModelMapper();
        TypeMap<MedicDto, Medic> typeMap = mapper.createTypeMap(MedicDto.class, Medic.class);
        typeMap.addMapping(MedicDto::getPrimaryname, (dest, v) -> dest.setFirstName((String) v));
        typeMap.addMapping(MedicDto::getSurname, (dest, v) -> dest.setLastName((String) v));
        typeMap.addMapping(MedicDto::getCmpmedic, (dest, v) -> dest.setCmp((String) v));
        typeMap.addMapping(MedicDto::getPhoto, (dest, v) -> dest.setPhotoUrl((String) v));

        TypeMap<Medic, MedicDto> toDto = mapper.createTypeMap(Medic.class, MedicDto.class);
        toDto.addMapping(Medic::getFirstName, MedicDto::setPrimaryname);
        toDto.addMapping(Medic::getLastName, MedicDto::setSurname);
        toDto.addMapping(Medic::getCmp, MedicDto::setCmpmedic);
        toDto.addMapping(Medic::getPhotoUrl, MedicDto::setPhoto);

        return mapper;
    }
}
