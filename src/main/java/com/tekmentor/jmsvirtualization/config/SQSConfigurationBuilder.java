package com.tekmentor.jmsvirtualization.config;

public final class SQSConfigurationBuilder {
    private int wiremockPort;
    private String rootDir;
    private int serverPort;

    private SQSConfigurationBuilder() {
    }

    public static SQSConfigurationBuilder aSQSConfiguration() {
        return new SQSConfigurationBuilder();
    }

    public SQSConfigurationBuilder withWiremockPort(int wiremockPort) {
        this.wiremockPort = wiremockPort;
        return this;
    }

    public SQSConfigurationBuilder withRootDir(String rootDir) {
        this.rootDir = rootDir;
        return this;
    }

    public SQSConfiguration build() {
        SQSConfiguration sQSConfiguration = new SQSConfiguration();
        sQSConfiguration.setWiremockPort(wiremockPort);
        sQSConfiguration.setRootDir(rootDir);
        sQSConfiguration.setServerPort(serverPort);
        return sQSConfiguration;
    }

    public SQSConfigurationBuilder withServerPort(int port) {
        this.serverPort = port;
        return this;
    }
}
