package com.renovation.gardenia.module.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName Admin
 * @Description TODO 管理员
 * @Author SAKURA
 * @Date 2020/12/09 16:40
 * @Version 1.0
 */
@Data
@EqualsAndHashCode
@TableName("gardenia_admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = -724755143507268656L;

    private Long id;

    /**
     * 名称
     */
    private String name;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
