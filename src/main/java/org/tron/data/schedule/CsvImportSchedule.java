package org.tron.data.schedule;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.List;
import org.tron.data.entity.BlockGenerateInfo;
import org.tron.data.service.BlockGenerateInfoService;

@Service
public class CsvImportSchedule {

  @Autowired
  private BlockGenerateInfoService generateInfoService;

  // 每天凌晨执行一次
//  @Scheduled(cron = "0 0 0 * * ?")
//  @Scheduled(cron = "0 * * * * ?")
  public void importCsvToDatabase() {
    try {
      String csvFilePath124 = "/Users/liuxincheng/dev/172.10.10.124.csv";
//      String csvFilePath126 = "/Users/liuxincheng/dev/172.10.8.126.csv";
//      String csvFilePath141 = "/Users/liuxincheng/dev/172.10.7.141.csv";
//      String csvFilePath187 = "/Users/liuxincheng/dev/172.10.6.187.csv";

      List<BlockGenerateInfo> records124 = getBlockGenerateInfos(csvFilePath124);
//      List<BlockGenerateInfo> records126 = getBlockGenerateInfos(csvFilePath126);
//      List<BlockGenerateInfo> records141 = getBlockGenerateInfos(csvFilePath141);
//      List<BlockGenerateInfo> records187 = getBlockGenerateInfos(csvFilePath187);

      // 插入到数据库
      generateInfoService.insertBatch("124", records124);
//      generateInfoService.insertBatch("126", records126);
//      generateInfoService.insertBatch("141", records141);
//      generateInfoService.insertBatch("187", records187);

      System.out.println("CSV 文件成功导入数据库！");
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("CSV 文件导入失败：" + e.getMessage());
    }
  }

  private static List<BlockGenerateInfo> getBlockGenerateInfos(String csvFilePath) throws FileNotFoundException {
    return new CsvToBeanBuilder<BlockGenerateInfo>(new FileReader(csvFilePath))
        .withType(BlockGenerateInfo.class)
        .build()
        .parse();
  }
}
