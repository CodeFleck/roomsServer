package com.daniel.rooms.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ScheduleProperties {

    @Value("${file.storage.download}")
    private String downloadDirectory;

    @Value("${mail.smtp.schedule.csv.destination.user}")
    private String scheduleDestinationEmail;

    @Value("${file.storage.url.prefix}")
    private String fileStorageUrlPrefix;

    @Value("${file.storage.uri}")
    private String fileStorageUri;

    @Value("${mail.smtp.host}")
    private String smtpHost;

    @Value("${mail.smtp.port}")
    private String smtpPort;

    @Value("${mail.smtp.user}")
    private String sourceEmail;

    @Value("${mail.smtp.destination.user}")
    private String destinationEmail;

    @Value("${mail.smtp.order.invalid.address.destination.user}")
    private String destinationEmailInvalidAddress;

    @Value("${mail.smtp.password}")
    private String sourceEmailPassword;

    public String getSourceEmailPassword() {
        return sourceEmailPassword;
    }

    public String getDownloadDirectory() {
        return downloadDirectory;
    }

    public String getScheduleDestinationEmail() {
        return scheduleDestinationEmail;
    }

    public String getFileStorageUrlPrefix() {
        return fileStorageUrlPrefix;
    }

    public String getFileStorageUri() {
        return fileStorageUri;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public String getSourceEmail() {
        return sourceEmail;
    }

    public String getDestinationEmail() {
        return destinationEmail;
    }

    public String getDestinationEmailInvalidAddress() {
        return destinationEmailInvalidAddress;
    }
}
