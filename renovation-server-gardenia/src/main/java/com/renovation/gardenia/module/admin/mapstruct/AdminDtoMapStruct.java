package com.renovation.gardenia.module.admin.mapstruct;

import com.renovation.common.config.BaseMapStruct;
import com.renovation.gardenia.module.admin.dto.AdminDto;
import com.renovation.gardenia.module.admin.entity.Admin;
import org.mapstruct.Mapper;

/**
 * @ClassName AdminDtoMapstruct
 * @Description TODO 管理员入参实体转换
 * @Author SAKURA
 * @Date 2020/12/10 15:06
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface AdminDtoMapStruct extends BaseMapStruct<AdminDto, Admin> {
}
