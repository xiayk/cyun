package com.cyun.group.controller;

import com.cyun.group.service.StoreService;
import com.cyun.param.EditStoreParam;
import com.cyun.param.SaveStoreParam;
import com.cyun.param.StoreParam;
import com.cyun.result.StoreDetailResult;
import com.cyun.utils.http.HttpUtil;
import com.cyun.utils.http.JSONResult;
import com.cyun.utils.token.UserTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@Api(value = "商家管理", tags = {"商家管理"})
@Slf4j
public class StoreController {

    @Autowired
    private StoreService storeService;


    @ApiOperation("添加商家")
    @PostMapping("/save")
    public JSONResult<String> saveStore(@RequestBody SaveStoreParam param) throws Exception {
        param.setCreateUserId(UserTokenUtils.getLoginUserDTO().getId());
        storeService.addStore(param);
        return HttpUtil.writeSuccessJSON();
    }

    @ApiOperation("查看商家详情")
    @PostMapping("/detail/{id}")
    public JSONResult<StoreDetailResult> detail(@PathVariable("id") String id){
        return HttpUtil.writeSuccessJSON(storeService.getStoreDetail(id));
    }

    @ApiOperation("编辑商家信息")
    @PostMapping("/edit")
    public JSONResult editStore(@RequestBody EditStoreParam param) throws Exception {
        param.setUpdateUserId(UserTokenUtils.getLoginUserDTO().getId());
        storeService.editStore(param);
        return HttpUtil.writeSuccessJSON();
    }

    @ApiOperation("获取商家list")
    @PostMapping("/list")
    public JSONResult getStorelist(@RequestBody StoreParam param) throws Exception {
        return HttpUtil.writeSuccessJSON(storeService.getStores(param));
    }

    @ApiOperation("shanchu商家")
    @PostMapping("/del/{id}")
    public JSONResult getStorelist(@PathVariable("id") String id) throws Exception {
        storeService.delStore(id, UserTokenUtils.getLoginUserDTO().getId());
        return HttpUtil.writeSuccessJSON();
    }
}
