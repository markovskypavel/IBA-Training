package by.iba.markovsky.festival.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service("emailService")
public class EmailService {

    @Autowired
    private JavaMailSender sender;

    public void sendEmail(String email, String subject, String text) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(text);

        sender.send(message);
    }

    public void sendEmailWithPicture(String email, String subject, String text, String path, String pictureName) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(text);

        /*Add attachment*/
        ClassPathResource file = new ClassPathResource(path);
        helper.addAttachment(pictureName, file);

        sender.send(message);
    }

    public void sendEmailWithInlineText(String email, String subject, String htmlText, String path, String pictureName) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject(subject);

        /*Inline messages*/
        helper.setText(/*"<html><body>Here is a cat picture! <img src='cid:id101'/><body></html>"*/htmlText, true);
        ClassPathResource fileInline = new ClassPathResource(path);
        helper.addInline(pictureName, fileInline);

        sender.send(message);
    }

}
