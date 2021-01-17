package com.daniel.rooms.service;

import com.daniel.rooms.properties.ScheduleProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

@Service
public class EmailService {

    private ScheduleProperties scheduleProperties;

    public EmailService(ScheduleProperties scheduleProperties) {
        this.scheduleProperties = scheduleProperties;
    }

    public void sendScheduleByEmail(String fileName) {
        try {
            JavaMailSenderImpl sender = configureOriginMail();
            MimeMessage message = sender.createMimeMessage();
            Thread.sleep(5000);
            FileSystemResource file = new FileSystemResource(new File("download/" + fileName));
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.addAttachment(fileName, file);
            helper.setTo(scheduleProperties.getScheduleDestinationEmail());
            helper.setSubject("Aqui esta a escala da semana");
            Properties properties = getMailProperties();
            sender.setJavaMailProperties(properties);
            helper.setText(buildBodyMail(fileName), true);
            sender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            System.out.println("Erro ao enviar email de notificacao de criacao do CSV de contingencia.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String buildBodyMail(String fileName) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><p>Segue em anexo a escala da semana.");
        sb.append("</p><br><br><br><br></body></html>");
        return  sb.toString();
    }

    private Properties getMailProperties() {
        Properties p = new Properties();
        p.setProperty("mail.smtp.host", scheduleProperties.getSmtpHost());
        p.setProperty("mail.smtp.port", scheduleProperties.getSmtpPort());
        p.setProperty("mail.smtp.from", scheduleProperties.getSourceEmail());
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.starttls.enable", "true");
        p.setProperty("mail.smtp.socketFactory.port", scheduleProperties.getSmtpHost());
        return p;
    }

    private JavaMailSenderImpl configureOriginMail() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setProtocol("smtp");
        sender.setPassword(scheduleProperties.getSourceEmailPassword());
        sender.setUsername(scheduleProperties.getSourceEmail());
        return sender;
    }
}
