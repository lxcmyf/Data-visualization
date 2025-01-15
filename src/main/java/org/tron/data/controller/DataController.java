package org.tron.data.controller;

import org.tron.data.entity.BlockGenerateInfo;
import org.tron.data.entity.QueryParam;
import org.tron.data.service.BlockGenerateInfoService;
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
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //        blockGenerateInfoList.forEach(o -> o.setUpdateTimeStr(dateFormat.format(o.getUpdateTime())));
        return generateInfoService.selectByParam(queryParam);
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
