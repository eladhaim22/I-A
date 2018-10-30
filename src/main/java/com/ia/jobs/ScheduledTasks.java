package com.ia.jobs;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.ia.dto.ClaimDTO;
import com.ia.dto.PurchasFTPDTO;
import com.ia.entity.Purchase;
import com.ia.service.PurchaseService;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.io.*;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@Configuration
@EnableScheduling
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private FtpClient ftpClient;

    @Autowired
    private PurchaseService purchaseService;

    @Value("${ftp.address}")
    private String ftpAddress;

    @Value("${ftp.port}")
    private int ftpPort;

    @Value("${ftp.user}")
    private String ftpUser;

    @Value("${ftp.password}")
    private String ftpPassword;

    @Value("${ftp.destination.url}")
    private String destinationUrl;

    @Value("${ftp.source.url}")
    private String sourceUrl;

    @Value("${job.fileUrl}")
    private String jobFileUrl;

    @Autowired
    private ResourceLoader resourceLoader;

    @Scheduled(fixedRate = 2000)
    //@Scheduled(cron="0 0 11 59 * *")
    public void generateAllPurchase() throws IOException {
        try {
            Date truncatedDate = DateUtils.truncate(new Date(), Calendar.DATE);
            List<PurchasFTPDTO> purchases = purchaseService.getAllTodayPurchase(truncatedDate);
            this.generateCsv(purchases);
            ftpClient.open(ftpAddress,ftpPort ,ftpUser ,ftpPassword );
            ftpClient.upload(sourceUrl,destinationUrl);
        } catch (IOException e) {
            throw e;
        }finally {
            ftpClient.close();
        }
    }

    public void generateCsv(List<PurchasFTPDTO> purchases) {
        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = mapper.schemaFor(PurchasFTPDTO.class);
            schema = schema.withColumnSeparator(';').withoutQuoteChar().withHeader();
            ObjectWriter myObjectWriter = mapper.writer(schema);
            File tempFile = new File(sourceUrl);
            FileOutputStream tempFileOutputStream = null;
            tempFileOutputStream = new FileOutputStream(tempFile);

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
            OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
            myObjectWriter.writeValue(writerOutputStream, purchases);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}