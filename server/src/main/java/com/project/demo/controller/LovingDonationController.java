package com.project.demo.controller;

import com.project.demo.entity.LovingDonation;
import com.project.demo.service.LovingDonationService;
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
 * 爱心捐助：(LovingDonation)表控制层
 *
 */
@RestController
@RequestMapping("/loving_donation")
public class LovingDonationController extends BaseController<LovingDonation, LovingDonationService> {

    /**
     * 爱心捐助对象
     */
    @Autowired
    public LovingDonationController(LovingDonationService service) {
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
