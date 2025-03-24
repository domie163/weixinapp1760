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
 * 二手物品：(SecondHandGoods)表实体类
 *
 */
@TableName("`second_hand_goods`")
@Data
@EqualsAndHashCode(callSuper = false)
public class SecondHandGoods implements Serializable {

    // SecondHandGoods编号
    @TableId(value = "second_hand_goods_id", type = IdType.AUTO)
    private Integer second_hand_goods_id;

    // 物品编号
    @TableField(value = "`serial_number`")
    private String serial_number;
    // 物品名称
    @TableField(value = "`item_name`")
    private String item_name;
    // 物品类型
    @TableField(value = "`item_type`")
    private String item_type;
    // 封面图片
    @TableField(value = "`cover_photo`")
    private String cover_photo;
    // 物品数量
    @TableField(value = "`item_quantity`")
    private Integer item_quantity;
    // 单件价格
    @TableField(value = "`unit_price`")
    private String unit_price;
    // 商品规格
    @TableField(value = "`commodity_specification`")
    private String commodity_specification;
    // 介绍详情
    @TableField(value = "`introduction_details`")
    private String introduction_details;
    // 售卖用户
    @TableField(value = "`selling_users`")
    private Integer selling_users;

    // 点击数
    @TableField(value = "hits")
    private Integer hits;

    // 点赞数
    @TableField(value = "praise_len")
    private Integer praise_len;








    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
