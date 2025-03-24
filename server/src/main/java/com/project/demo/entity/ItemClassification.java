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
 * 物品分类：(ItemClassification)表实体类
 *
 */
@TableName("`item_classification`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ItemClassification implements Serializable {

    // ItemClassification编号
    @TableId(value = "item_classification_id", type = IdType.AUTO)
    private Integer item_classification_id;

    // 物品类型
    @TableField(value = "`item_type`")
    private String item_type;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
