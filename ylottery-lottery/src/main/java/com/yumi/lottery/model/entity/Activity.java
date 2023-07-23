package com.yumi.lottery.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yumi
 * @since 2023-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Activity extends Model<Activity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String activityName;

    /**
     * 活动描述
     */
    private String activityDesc;

    private Integer activityStrategyId;

    private LocalDateTime createTime;

    private LocalDateTime validTime;

    private LocalDateTime expireTime;

    /**
     * 活动库存
     */
    private Integer stock;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
