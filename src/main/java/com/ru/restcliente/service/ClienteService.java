package com.ru.restcliente.service;

import com.ru.restcliente.dto.ClienteDTO;
import com.ru.restcliente.entities.Cliente;
import com.ru.restcliente.repositories.ClienteRepository;
import com.ru.restcliente.service.exceptions.DatabaseException;
import com.ru.restcliente.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public Page<ClienteDTO> findAllPaged(Pageable pageable){
        Page<Cliente> list = repository.findAll(pageable);
        return list.map(x -> new ClienteDTO(x));
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id){
        Optional<Cliente> obj = repository.findById(id);
        Cliente entity = obj.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado..."));
        return new ClienteDTO(entity);
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO dto){
        Cliente entity = new Cliente();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new ClienteDTO(entity);
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto){
        try{
            Cliente entity = repository.getOne(id);
            entity.setName(dto.getName());
            entity =repository.save(entity);
            return new ClienteDTO(entity);
        }catch (EntityExistsException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found " + id);
        }catch (DataIntegrityViolationException e ){
            throw new DatabaseException("Integrity violation.");
        }
    }
}
