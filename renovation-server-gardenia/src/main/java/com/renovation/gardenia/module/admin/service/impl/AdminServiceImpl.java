package com.renovation.gardenia.module.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renovation.common.enums.APIExceptionCode;
import com.renovation.common.exception.APIException;
import com.renovation.gardenia.module.admin.dto.AdminDto;
import com.renovation.gardenia.module.admin.entity.Admin;
import com.renovation.gardenia.module.admin.mapper.AdminMapper;
import com.renovation.gardenia.module.admin.mapstruct.AdminDtoMapStruct;
import com.renovation.gardenia.module.admin.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO 管理员service实现
 * @Author SAKURA
 * @Date 2020/12/09 17:03
 * @Version 1.0
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    private AdminDtoMapStruct adminDtoMapStruct;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveOrUpdateAdmin(AdminDto adminDto) {
        Admin admin = adminDtoMapStruct.toEntity(adminDto);
        return this.saveOrUpdate(admin);
    }
}
