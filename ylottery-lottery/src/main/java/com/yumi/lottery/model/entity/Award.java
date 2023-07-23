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
public class Award extends Model<Award> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String awardName;

    private String awardDesc;

    /**
     * 0 实物奖品，1 优惠卷，2 体验卡
     */
    private Integer awardType;

    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
