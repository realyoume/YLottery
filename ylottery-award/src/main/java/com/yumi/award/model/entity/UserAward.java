package com.yumi.award.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yumi
 * @since 2023-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAward extends Model<UserAward> {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer userId;

    private String userActivityId;

    private Integer awardId;

    private Integer awardType;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
