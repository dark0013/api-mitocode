/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systeminventory.systeminventory.services.impl;

import com.systeminventory.systeminventory.exception.NewModelNotFoundException;
import com.systeminventory.systeminventory.repository.IGenericRepo;
import com.systeminventory.systeminventory.services.ICRUD;
import java.util.List;

/**
 *
 * @author Alain
 */
public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T save(T t) {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) {
        getRepo().findById(id).orElseThrow(() -> new NewModelNotFoundException("ID NOT FOUND" + id));
        return getRepo().save(t);
    }

    @Override
    public void delete(ID id) {
        getRepo().findById(id).orElseThrow(() -> new NewModelNotFoundException("ID NOT FOUND" + id));
        getRepo().deleteById(id);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findbyId(ID id) {
        return getRepo().findById(id).orElseThrow(() -> new NewModelNotFoundException("ID NOT FOUND" + id));
    }

}
