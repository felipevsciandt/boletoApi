package com.example.boleto.BoletoApi.service;

import com.example.boleto.BoletoApi.client.ContaFeignClient;
import com.example.boleto.BoletoApi.model.Boleto;
import com.example.boleto.BoletoApi.model.EnumStatus;
import com.example.boleto.BoletoApi.repository.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BoletoService {

    @Autowired
    BoletoRepository repository;

    @Autowired
    ContaFeignClient client;

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

    public void pagar(Long idConta, Long idBoleto) {
        try {
            Optional<Boleto> object = repository.findById(idBoleto);
            if (!object.isPresent()) {
                // Lanbcar ecexaso
            }
            Boleto boleto = object.get();
            client.pagarBoleto(idConta, boleto.getValor());
            boleto.setStatus(EnumStatus.PAGO);
        } catch (Exception e) {
            throw new RuntimeException("Erro!");
        }
    }
}
