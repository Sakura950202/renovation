package com.renovation.rose.module.customer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Customer implements Serializable {
    private static final long serialVersionUID = -6865309454471140480L;

    /**
     * 名称
     */
    private String name;

    private LocalDateTime createTime;

    private LocalDateTime time;
}
