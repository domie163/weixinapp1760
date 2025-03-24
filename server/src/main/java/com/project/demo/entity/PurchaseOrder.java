package com.project.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 购买订单：(PurchaseOrder)表实体类
 *
 */
@TableName("`purchase_order`")
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrder implements Serializable {

    // PurchaseOrder编号
    @TableId(value = "purchase_order_id", type = IdType.AUTO)
    private Integer purchase_order_id;

    // 物品编号
    @TableField(value = "`serial_number`")
    private String serial_number;
    // 物品名称
    @TableField(value = "`item_name`")
    private String item_name;
    // 单件价格
    @TableField(value = "`unit_price`")
    private String unit_price;
    // 售卖用户
    @TableField(value = "`selling_users`")
    private Integer selling_users;
    // 购买用户
    @TableField(value = "`purchase_user`")
    private Integer purchase_user;
    // 用户姓名
    @TableField(value = "`user_name`")
    private String user_name;
    // 购买数量
    @TableField(value = "`purchase_quantity`")
    private Integer purchase_quantity;
    // 总计价格
    @TableField(value = "`total_price`")
    private String total_price;
    // 联系方式
    @TableField(value = "`contact_information`")
    private String contact_information;
    // 收货地址
    @TableField(value = "`shipping_address`")
    private String shipping_address;
    // 购买备注
    @TableField(value = "`purchase_notes`")
    private String purchase_notes;




    // 支付状态
    @TableField(value = "pay_state")
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @TableField(value = "pay_type")
    private String pay_type;






    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
