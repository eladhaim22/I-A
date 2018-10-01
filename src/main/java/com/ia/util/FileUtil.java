package com.ia.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class FileUtil {

    public void storeFile(String fileNamePath,byte[] byteArray) throws IOException {
            FileOutputStream output = new FileOutputStream(fileNamePath);
            output.write(byteArray);
    }
}
