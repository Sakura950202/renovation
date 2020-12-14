package com.renovation.gardenia.module.admin.controller;

import com.renovation.gardenia.module.admin.dto.AdminDto;
import com.renovation.gardenia.module.admin.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName AdminController
 * @Description TODO 管理员controller
 * @Author SAKURA
 * @Date 2020/12/09 17:06
 * @Version 1.0
 */
@Api(tags = "管理员相关接口")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @ApiOperation("管理员新增或修改")
    @PostMapping("/saveOrUpdateAdmin")
    public Boolean saveOrUpdateAdmin(@RequestBody @Valid AdminDto adminDto) {
        return adminService.saveOrUpdateAdmin(adminDto);
    }

}
