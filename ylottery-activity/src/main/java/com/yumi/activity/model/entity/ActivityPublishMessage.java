package com.yumi.activity.model.entity;

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
 * @since 2023-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityPublishMessage extends Model<ActivityPublishMessage> {

    private static final long serialVersionUID = 1L;

    /**
     * 消息发布信息的主键与消息发布的主键保持一致
     */
    private Integer id;

    private Integer state1;

    private Integer state2;

    private Integer state3;

    private LocalDateTime state1ChangeDate;

    private LocalDateTime state2ChangeDate;

    private LocalDateTime state3ChangeDate;

    private LocalDateTime createDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
