package com.zjp.echartsdemo.controller;

import com.zjp.echartsdemo.entity.BlockGenerateInfo;
import com.zjp.echartsdemo.entity.QueryParam;
import com.zjp.echartsdemo.service.BlockGenerateInfoService;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataController {

    @Autowired
    private BlockGenerateInfoService generateInfoService;

    @PostMapping("/getBlockGenerateInfo")
    @ResponseBody
    public List<BlockGenerateInfo> getBlockGenerateInfo(@RequestBody QueryParam queryParam){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        List<BlockGenerateInfo> blockGenerateInfoList = generateInfoService.selectByParam(queryParam);
        blockGenerateInfoList.forEach(o -> o.setUpdateTimeStr(dateFormat.format(o.getUpdateTime())));
        return blockGenerateInfoList;
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
