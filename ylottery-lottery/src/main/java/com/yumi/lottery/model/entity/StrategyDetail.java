package com.yumi.lottery.model.entity;

import java.math.BigDecimal;
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
public class StrategyDetail extends Model<StrategyDetail> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer strategyId;

    private Integer awardId;

    private String awardName;

    private Integer awardStock;

    private LocalDateTime createTime;

    private BigDecimal awardRate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
