package com.renovation.gardenia.module.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.renovation.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ClassName Customer
 * @Description TODO
 * @Author SAKURA
 * @Date 2020/12/27 15:08
 * @Version 1.0
 */
@Data
@EqualsAndHashCode
@TableName("rose_customer")
public class Customer extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -6865309454471140480L;

    /**
     * 名称
     */
    private String name;
}
