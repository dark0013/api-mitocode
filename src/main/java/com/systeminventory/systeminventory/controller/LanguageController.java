/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

/**
 *
 * @author Alain
 */
@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LocaleResolver localResolver;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @GetMapping("/locale/{loc}")
    public ResponseEntity<Void> changeLocale(@PathVariable("loc") String loc) {
        Locale local;
        switch (loc) {
            case "en":
                local = Locale.ENGLISH;
                break;
            case "fr":
                local = Locale.FRANCE;
                break;
            default:
                local = Locale.ROOT;
                break;
        }

        localResolver.setLocale(httpServletRequest, httpServletResponse, local);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/locale/{loc}")
//    public ResponseEntity<Void> changeLocale(@PathVariable("loc") String loc, HttpServletRequest request, HttpServletResponse response) {
//        Locale locale = switch (loc.toLowerCase()) {
//            case "en" ->
//                Locale.ENGLISH;
//            case "fr" ->
//                Locale.FRENCH;
//            case "es" ->
//                new Locale("es", "ES");
//            default ->
//                Locale.getDefault();
//        };
//
//        localeResolver.setLocale(request, response, locale);
//        return ResponseEntity.ok().build();
//    }
}
