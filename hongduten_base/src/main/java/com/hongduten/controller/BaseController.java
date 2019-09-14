package com.hongduten.controller;

import com.hongduten.entity.PageResult;
import com.hongduten.entity.Result;
import com.hongduten.entity.StatusCode;
import com.hongduten.pojo.Label;
import com.hongduten.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dudu on 2019/9/14.
 */
@RestController
@RequestMapping("/label")
public class BaseController {

    @Autowired
    private LabelService labelService;

    /*** 查询全部列表 * @return */
//    @RequestMapping(method = RequestMethod.GET)
//    public Result<List> findAll() {
//        return new Result<>(true, StatusCode.OK, "查询成功", labelService.findAll());
//    }
    @RequestMapping("/hello")
    public String test() {
        System.out.println("Hello World!测试Rest 风格!");
        return "Hello Rest!";
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Result findSearch(@RequestBody Map searchMap) {
        Object o = labelService.findSearch(searchMap);
        if(!StringUtils.isEmpty(o)) {
            return new Result(true, StatusCode.OK, "查询成功", o);
        } else {
            return new Result(false, StatusCode.ERROR, "查询失败", null);
        }
    }

    /**
     * 分页查询
     * @param searcheMap
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.GET)
    public Result findSearch(@RequestBody Map searcheMap, @PathVariable int page, @PathVariable int size) {
        Page pageList = labelService.findSearch(searcheMap,page, size);
        PageResult pageResult = new PageResult(pageList.getTotalElements(), pageList.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Result> findAll() {
        Object object
                = new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
        List o = new ArrayList();
//        System.out.println("进入了hahah ");
        o.add(object);
        return o;
    }

    /*** 根据ID查询标签 * @param id * @return */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成 功", labelService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /*** 修改标签 * @param label * @return */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Label label, @PathVariable String id) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /*** 删除标签 * @param id * @return */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
}
