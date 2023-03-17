package com.freedom.care.service.curd;

import com.freedom.care.entity.crud.AddressEntity;
import com.freedom.care.repository.crud.AddressRepository;
import com.freedom.common.service.BaseCrudPageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService extends BaseCrudPageService<AddressEntity, UUID> {

    @Resource
    AddressRepository repository;

    @Override
    protected AddressRepository getRepository() {
        return repository;
    }
}
