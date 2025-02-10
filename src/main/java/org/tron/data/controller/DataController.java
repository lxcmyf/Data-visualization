package org.tron.data.controller;

import com.alibaba.fastjson.JSONObject;
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
import org.tron.data.service.impl.TransactionService;
import org.tron.data.vo.FromAddressVO;
import org.tron.data.vo.TxTypeVO;

@Controller
public class DataController {

    @Autowired
    private BlockGenerateInfoService generateInfoService;

    @Autowired
    private TransactionService transactionService;

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

    @RequestMapping("/txStatistics")
    public String txStatistics(){
        return "txStatistics";
    }

    @RequestMapping("/insertTransactions")
    public JSONObject insertTransactions(){
        transactionService.fetchAndInsertTransactions();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "OK");
        return jsonObject;
    }

    @PostMapping("/getFromAddressTop")
    @ResponseBody
    public List<FromAddressVO> getFromAddressTop(@RequestBody QueryParam queryParam){
       return transactionService.getFromAddressTop(queryParam);
    }

    @PostMapping("/getTopTxTypeByFromAddress")
    @ResponseBody
    public List<TxTypeVO> getTopTxTypeByFromAddress(@RequestBody QueryParam queryParam){
        return transactionService.getTopTxTypeByFromAddress(queryParam);
    }

}
