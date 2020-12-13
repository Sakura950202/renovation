package com.renovation.gardenia.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.renovation.gardenia.admin.dto.AdminDto;
import com.renovation.gardenia.admin.entity.Admin;

/**
 * @ClassName AdminService
 * @Description TODO 管理员service
 * @Author SAKURA
 * @Date 2020/12/09 17:02
 * @Version 1.0
 */
public interface AdminService extends IService<Admin> {

    /**
     * 新增或修改管理员
     * @param adminDto
     * @return
     */
    Boolean saveOrUpdateAdmin(AdminDto adminDto);

}
