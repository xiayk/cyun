package com.cyun.group.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
@Api(value = "商家管理", tags = {"商家管理"})
@Slf4j
public class StoreController {
}
