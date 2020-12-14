package com.renovation.gardenia.module.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.renovation.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class Admin extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -724755143507268656L;

    /**
     * 名称
     */
    private String name;
}
