package com.project.demo.controller;

import com.project.demo.entity.PurchaseOrder;
import com.project.demo.service.PurchaseOrderService;
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
 * 购买订单：(PurchaseOrder)表控制层
 *
 */
@RestController
@RequestMapping("/purchase_order")
public class PurchaseOrderController extends BaseController<PurchaseOrder, PurchaseOrderService> {

    /**
     * 购买订单对象
     */
    @Autowired
    public PurchaseOrderController(PurchaseOrderService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        String sql = "SELECT MAX(purchase_order_id) AS max FROM "+"purchase_order";
        Integer max = service.selectBaseCount(sql);
        sql = ("SELECT count(*) count FROM `second_hand_goods` INNER JOIN `purchase_order` ON second_hand_goods.serial_number=purchase_order.serial_number WHERE second_hand_goods.item_quantity < purchase_order.purchase_quantity AND purchase_order.purchase_order_id="+max).replaceAll("&#60;","<");
        Integer count = service.selectBaseCount(sql);
        if(count>0){
            sql = "delete from "+"purchase_order"+" WHERE "+"purchase_order_id"+" ="+max;
            service.deleteBaseSql(sql);
            return error(30000,"库存不足!");
        }
        sql = "UPDATE `second_hand_goods` INNER JOIN `purchase_order` ON second_hand_goods.serial_number=purchase_order.serial_number SET second_hand_goods.item_quantity= second_hand_goods.item_quantity - purchase_order.purchase_quantity WHERE purchase_order.purchase_order_id="+max;
        service.updateBaseSql(sql);
        return success(1);
    }

}
