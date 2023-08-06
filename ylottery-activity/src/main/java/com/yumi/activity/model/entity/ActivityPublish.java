package com.yumi.activity.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yumi
 * @since 2023-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ActivityPublish extends Model<ActivityPublish> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String activityName;

    private String activityDesc;

    private Integer activityStrategyId;

    private String activityStrategyInfo;

    private String activityDetailsInfo;

    private LocalDateTime createTime;

    private LocalDateTime validTime;

    private LocalDateTime expireTime;

    private Integer capacity;

    private Integer state;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
