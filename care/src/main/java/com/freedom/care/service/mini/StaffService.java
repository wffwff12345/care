package com.freedom.care.service.mini;

import com.freedom.care.entity.crud.OrderDetailEntity;
import com.freedom.care.entity.crud.OrderMasterEntity;
import com.freedom.care.entity.crud.ServeEntity;
import com.freedom.care.repository.crud.OrderDetailRepository;
import com.freedom.care.repository.crud.OrderMasterRepository;
import com.freedom.care.repository.crud.ServeCategoryRepository;
import com.freedom.care.repository.crud.ServeRepository;
import com.freedom.care.service.curd.OrderDetailService;
import com.freedom.care.service.curd.OrderMasterService;
import com.freedom.common.model.ResultModel;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class StaffService {
    @Resource
    private OrderDetailRepository orderDetailRepository;

    @Resource
    private OrderMasterRepository orderMasterRepository;

    public ResultModel getOrderList() {
        List<Map<String, Object>> list = new ArrayList<>();
        Iterable<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findAll();
        orderMasterRepository.findAll().forEach(orderMasterEntity -> {
            Map<String, Object> map = new HashMap<>();
            Stream<OrderDetailEntity> stream = StreamSupport.stream(orderDetailEntities.spliterator(), false);
            map.put("no", orderMasterEntity.getNo());
            map.put("serveName", orderMasterEntity.getServeName());
            map.put("children", stream.filter(x -> orderMasterEntity.getNo().equals(x.getOrderNo())));
            list.add(map);
        });
        return ResultModel.data(list);
    }

}
