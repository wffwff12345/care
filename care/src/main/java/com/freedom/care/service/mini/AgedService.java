package com.freedom.care.service.mini;

import com.freedom.care.entity.crud.ServeEntity;
import com.freedom.care.repository.crud.ServeCategoryRepository;
import com.freedom.care.repository.crud.ServeRepository;
import com.freedom.common.model.ResultModel;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class AgedService {

    @Resource
    private ServeCategoryRepository categoryRepository;

    @Resource
    private ServeRepository serveRepository;

    public ResultModel getServeList() {
        List<Map<String, Object>> list = new ArrayList<>();
        Iterable<ServeEntity> serves = serveRepository.findAll();
        categoryRepository.findAll().forEach(category -> {
            Map<String, Object> map = new HashMap<>();
            Stream<ServeEntity> stream = StreamSupport.stream(serves.spliterator(), false);
            map.put("no", category.getNo());
            map.put("name", category.getName());
            map.put("children", stream.filter(x -> category.getNo().equals(x.getCategoryNo())));
            list.add(map);
        });
        return ResultModel.data(list);
    }

    public ResultModel getServeById(UUID id) {
        return ResultModel.data("test");
    }
}
