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
 * @since 2023-07-26
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
     * 活动容量
     */
    private Integer capacity;

    /**
     * 0 未开始，1 进行中，2 活动售空，3 活动过期
     */
    private String status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
