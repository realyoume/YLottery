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
public class Strategy extends Model<Strategy> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 0 弹性概率，1 固定概率
     */
    private Integer type;

    private LocalDateTime createTime;

    private String strategyDesc;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
