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
 * 以物换物：(Barter)表实体类
 *
 */
@TableName("`barter`")
@Data
@EqualsAndHashCode(callSuper = false)
public class Barter implements Serializable {

    // Barter编号
    @TableId(value = "barter_id", type = IdType.AUTO)
    private Integer barter_id;

    // 发布人员
    @TableField(value = "`publisher`")
    private Integer publisher;
    // 物品名称
    @TableField(value = "`item_name`")
    private String item_name;
    // 物品类型
    @TableField(value = "`item_type`")
    private String item_type;
    // 所需易物
    @TableField(value = "`required_barter`")
    private String required_barter;
    // 物品图片
    @TableField(value = "`item_picture`")
    private String item_picture;
    // 联系方式
    @TableField(value = "`contact_information`")
    private String contact_information;
    // 物品介绍
    @TableField(value = "`item_introduction`")
    private String item_introduction;

    // 点击数
    @TableField(value = "hits")
    private Integer hits;









    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
