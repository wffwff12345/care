package com.freedom.care.service.curd;

import com.freedom.care.entity.crud.OrderMasterEntity;
import com.freedom.care.repository.crud.OrderMasterRepository;
import com.freedom.common.service.BaseCrudPageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderMasterService extends BaseCrudPageService<OrderMasterEntity, UUID> {

    @Resource
    OrderMasterRepository repository;

    @Override
    protected OrderMasterRepository getRepository() {
        return repository;
    }
}
