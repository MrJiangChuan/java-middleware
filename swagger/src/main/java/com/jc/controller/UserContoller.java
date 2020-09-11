package com.jc.controller;

import com.jc.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserContoller {

    @ApiOperation("获取指定id用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "int", paramType = "path", required = true)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable int id) {
        return new User(id, "Tom", "male", 24);
    }

    @ApiOperation("根据id删除指定用户")
    @ApiImplicitParam(name = "id", value = "用户Id", dataType = "int", paramType = "path", required = true)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int id) {

        return "删除用户成功";
    }

    @ApiOperation("增加用户")
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        return "增加用户成功：" + user;
    }

    @ApiOperation("修改指定id的用户")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "int", paramType = "path", required = true)
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        return "修改用户成功：" + user;
    }

}
