package com.yumi.award.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yumi
 * @since 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
