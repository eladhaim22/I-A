package com.ia.jobs;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.ia.dto.ClaimDTO;
import com.ia.entity.Claim;
import com.ia.repository.ClaimRepository;
import com.ia.service.ClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Configuration
@EnableScheduling
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private FtpClient ftpClient;

    @Autowired
    private ClaimService claimService;

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

    @Scheduled(cron="0 0 12 * * *")
    public void getAllClamis() throws IOException {
        try {
            ftpClient.open(ftpAddress,ftpPort ,ftpUser ,ftpPassword );
            ftpClient.download(sourceUrl,destinationUrl );
            List<ClaimDTO> claimDTOS = loadObjectList();
            claimService.updateClaimsFromFTP(claimDTOS);
        } catch (IOException e) {
            throw e;
        }finally {
            ftpClient.close();
        }
    }

    public List<ClaimDTO> loadObjectList() {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withColumnSeparator(';').withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = resourceLoader.getResource(jobFileUrl).getFile();
            MappingIterator<ClaimDTO> readValues =
                    mapper.readerFor(ClaimDTO.class).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            log.error("Error occurred while loading object list from file " , e);
            return Collections.emptyList();
        }
    }

}