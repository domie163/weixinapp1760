package com.project.demo.controller;

import com.project.demo.entity.LostAndFoundSquare;
import com.project.demo.service.LostAndFoundSquareService;
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
 * 失物广场：(LostAndFoundSquare)表控制层
 *
 */
@RestController
@RequestMapping("/lost_and_found_square")
public class LostAndFoundSquareController extends BaseController<LostAndFoundSquare, LostAndFoundSquareService> {

    /**
     * 失物广场对象
     */
    @Autowired
    public LostAndFoundSquareController(LostAndFoundSquareService service) {
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
