package com.jc.controller;

import com.jc.entity.ResponseResult;
import com.jc.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "订单管理")
@RestController
@RequestMapping("/order")
public class OrderController {

    @ApiOperation("删除订单")
    @ApiImplicitParam(name = "id", value = "订单id", dataType = "int", paramType = "path",required = true)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseResult deleteOrder(@PathVariable int id){
        return new ResponseResult(20000,"成功删除订单",new User(1,"张三","男",23));
    }

}
