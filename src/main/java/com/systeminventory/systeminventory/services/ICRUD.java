/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.services;

import java.util.List;

/**
 *
 * @author Alain
 */
public interface ICRUD<T, ID> {

    T save(T t);

    T update(T t, ID id);

    void delete(ID id);

    List<T> findAll();

    T findbyId(ID id);
}
