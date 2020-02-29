package com.cyun.sys.controller;

import com.cyun.utils.http.HttpUtil;
import com.cyun.utils.http.JSONResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * Description:
 *
 * @Auther: xiayk
 * @date: 2019-12-22 23:08
 **/
@RestController
@RequestMapping("/data/")
public class DataController {

    @PostMapping("home")
    public JSONResult<Map<String, Object>> homeData(){
        Map<String, Object> map = new HashMap<>();
        return HttpUtil.writeSuccessJSON(map);
    }
}
