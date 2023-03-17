package com.freedom.care.service.curd;

import com.freedom.care.entity.crud.OrderDetailEntity;
import com.freedom.care.repository.crud.OrderDetailRepository;
import com.freedom.common.service.BaseCrudPageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderDetailService extends BaseCrudPageService<OrderDetailEntity, UUID> {

    @Resource
    OrderDetailRepository repository;

    @Override
    protected OrderDetailRepository getRepository() {
        return repository;
    }
}
