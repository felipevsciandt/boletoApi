package com.example.boleto.BoletoApi.service;

import com.example.boleto.BoletoApi.model.Boleto;
import com.example.boleto.BoletoApi.repository.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BoletoService {

    @Autowired
    BoletoRepository repository;

    @Transactional
    public List<Boleto> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Boleto findById(Long id) {
        return repository.findById(id).get();
    }

    @Transactional
    public Boleto createBoleto(Boleto boleto) {
        return repository.save(boleto);
    }

    @Transactional
    public void deleteBoletoById(Long id) {
        repository.deleteById(id);
    }
}
