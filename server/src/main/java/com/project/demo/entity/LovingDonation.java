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
 * 爱心捐助：(LovingDonation)表实体类
 *
 */
@TableName("`loving_donation`")
@Data
@EqualsAndHashCode(callSuper = false)
public class LovingDonation implements Serializable {

    // LovingDonation编号
    @TableId(value = "loving_donation_id", type = IdType.AUTO)
    private Integer loving_donation_id;

    // 捐助人员
    @TableField(value = "`donors`")
    private Integer donors;
    // 捐助日期
    @TableField(value = "`date_of_donation`")
    private Timestamp date_of_donation;
    // 捐助物品
    @TableField(value = "`donation_items`")
    private String donation_items;
    // 捐助数量
    @TableField(value = "`number_of_donations`")
    private Integer number_of_donations;
    // 备注信息
    @TableField(value = "`remarks`")
    private String remarks;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
