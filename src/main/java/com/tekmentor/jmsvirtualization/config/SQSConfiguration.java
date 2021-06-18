package com.tekmentor.jmsvirtualization.config;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class SQSConfiguration {
    private int wiremockPort;
    private String rootDir;
    private int serverPort;

    public int getWiremockPort() {
        return wiremockPort;
    }

    public void setWiremockPort(int wiremockPort) {
        this.wiremockPort = wiremockPort;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getServerPort() {
        return serverPort;
    }
}
