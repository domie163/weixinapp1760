package com.project.demo.controller;

import com.project.demo.entity.ItemClassification;
import com.project.demo.service.ItemClassificationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物品分类：(ItemClassification)表控制层
 *
 */
@RestController
@RequestMapping("/item_classification")
public class ItemClassificationController extends BaseController<ItemClassification, ItemClassificationService> {

    /**
     * 物品分类对象
     */
    @Autowired
    public ItemClassificationController(ItemClassificationService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
