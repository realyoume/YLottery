package com.yumi.lottery.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Builder;
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
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserActivity extends Model<UserActivity> {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer userId;

    private Integer activityId;

    private Integer lotteryCount;

    /**
     * 0 有效，1 过期
     */
    private Integer expired;

    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
