package com.renovation.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName BaseEntity
 * @Description TODO 基础实体类
 * @Author SAKURA
 * @Date 2020/12/09 16:49
 * @Version 1.0
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -3553523603432589556L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
