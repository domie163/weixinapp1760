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
 * 失物广场：(LostAndFoundSquare)表实体类
 *
 */
@TableName("`lost_and_found_square`")
@Data
@EqualsAndHashCode(callSuper = false)
public class LostAndFoundSquare implements Serializable {

    // LostAndFoundSquare编号
    @TableId(value = "lost_and_found_square_id", type = IdType.AUTO)
    private Integer lost_and_found_square_id;

    // 发布人员
    @TableField(value = "`publisher`")
    private Integer publisher;
    // 物品名称
    @TableField(value = "`item_name`")
    private String item_name;
    // 物品类型
    @TableField(value = "`item_type`")
    private String item_type;
    // 物品图片
    @TableField(value = "`item_picture`")
    private String item_picture;
    // 丢失时间
    @TableField(value = "`lost_time`")
    private Timestamp lost_time;
    // 丢失地点
    @TableField(value = "`lost_location`")
    private String lost_location;
    // 联系方式
    @TableField(value = "`contact_information`")
    private String contact_information;
    // 开启悬赏
    @TableField(value = "`open_the_reward_offer`")
    private String open_the_reward_offer;
    // 悬赏价格
    @TableField(value = "`reward_price`")
    private String reward_price;
    // 物品描述
    @TableField(value = "`item_description`")
    private String item_description;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;




    // 当前位置
    @TableField(value = "location_address")
    private String location_address;
    // 当前位置经度
    @TableField(value = "location_lng")
    private String location_lng;
    // 当前位置纬度
    @TableField(value = "location_lat")
    private String location_lat;



}
