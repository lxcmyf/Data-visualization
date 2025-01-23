package org.tron.data.schedule;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.List;
import org.tron.data.entity.BlockGenerateInfo;
import org.tron.data.service.BlockGenerateInfoService;

@Slf4j
@Service
public class CsvImportSchedule {

  @Autowired
  private BlockGenerateInfoService generateInfoService;

  @Scheduled(cron = "0 0 9 * * ?")
  public void importCsvToDatabase() {
    try {
      String csvFilePath124 = "/data/analysis/172.10.10.124.csv";
      String csvFilePath126 = "/data/analysis/172.10.8.126.csv";
      String csvFilePath141 = "/data/analysis/172.10.7.141.csv";
      String csvFilePath187 = "/data/analysis/172.10.6.187.csv";

      List<BlockGenerateInfo> records124 = getBlockGenerateInfos(csvFilePath124);
      List<BlockGenerateInfo> records126 = getBlockGenerateInfos(csvFilePath126);
      List<BlockGenerateInfo> records141 = getBlockGenerateInfos(csvFilePath141);
      List<BlockGenerateInfo> records187 = getBlockGenerateInfos(csvFilePath187);

      // 插入到数据库
      generateInfoService.insertBatch("124", records124);
      generateInfoService.insertBatch("126", records126);
      generateInfoService.insertBatch("141", records141);
      generateInfoService.insertBatch("187", records187);

      log.info("CSV 文件成功导入数据库！");

      deleteFile(csvFilePath124);
      deleteFile(csvFilePath126);
      deleteFile(csvFilePath141);
      deleteFile(csvFilePath187);
    } catch (Exception e) {
      e.printStackTrace();
      log.error("CSV 文件导入失败：" + e.getMessage());
    }
  }

  private static List<BlockGenerateInfo> getBlockGenerateInfos(String csvFilePath) throws FileNotFoundException {
    return new CsvToBeanBuilder<BlockGenerateInfo>(new FileReader(csvFilePath))
        .withType(BlockGenerateInfo.class)
        .build()
        .parse();
  }

  public static void deleteFile(String filePath) {
    Path path = Paths.get(filePath);
    if (Files.exists(path)) {
      try {
        Files.delete(path);
        log.info("文件已删除: {}", filePath);
      } catch (Exception e) {
        log.error("无法删除文件: {}", filePath);
        e.printStackTrace();
      }
    } else {
      log.warn("文件不存在: {}", filePath);
    }
  }
}
