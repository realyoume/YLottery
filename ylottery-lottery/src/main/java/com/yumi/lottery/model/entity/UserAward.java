package com.yumi.lottery.model.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
