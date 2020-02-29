package com.cyun.test.controller;

import com.cyun.dto.LoginUserDTO;
import com.cyun.model.FileInfo;
import com.cyun.utils.http.HttpUtil;
import com.cyun.utils.http.JSONResult;
import com.cyun.test.service.FileService;
import com.cyun.utils.token.UserTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/file")
@Api(value = "测试管理", tags = {"测试管理"})
public class FileController {
    @Autowired
    FileService fileService;

    @GetMapping(value = "/list/image")
    @ApiOperation("创建活动")
    @ApiImplicitParam(name = "name", value = "姓名", dataType = "string", required = true)
    public JSONResult<List<FileInfo>> listImage(String name) throws Exception {
        LoginUserDTO loginUser = UserTokenUtils.getLoginUserDTO();
        if (loginUser.getStatus().equals(0)){
            return HttpUtil.writeSuccessJSON();
        }
        return HttpUtil.writeJSONObject(fileService.listImage());
    }

}
