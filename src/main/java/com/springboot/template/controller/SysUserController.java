package com.springboot.template.controller;

import com.springboot.template.entity.TmpTables;
import com.springboot.template.service.TmpTablerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by jinyu on 2018/8/22.
 */
@Controller
@RequestMapping("")
public class SysUserController {

    @Autowired
    private TmpTablerService tmpTablerService;

    @RequestMapping("/test")
    @ResponseBody
    public String userTest(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.getId();
        //session.setAttribute("name","test session listener");
        return "access no problem";
    }


    @RequestMapping("/tmptable/{id}")
    @ResponseBody
    public TmpTables findTableById(@PathVariable Integer id){
        return tmpTablerService.selectByPrimaryKey(id);
    }
}
