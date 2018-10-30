package com.ia.jobs;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FtpClient {

    private FTPClient ftp;

    public void open(String server,int port,String user,String password) throws IOException {
        ftp = new FTPClient();

        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        ftp.connect(server, port);
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }

        ftp.login(user, password);
    }

    public void upload(String fileName,String destination) throws IOException {
        FileOutputStream out = new FileOutputStream(destination);
        ftp.storeFile(destination, new FileInputStream(fileName));
    }

    public void close() throws IOException {
        ftp.disconnect();
    }
}
