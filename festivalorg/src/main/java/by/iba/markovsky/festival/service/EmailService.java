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

    public void sendEmail(String email, String subject, String text) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(text);

        /*Add attachment*/
/*        ClassPathResource file = new ClassPathResource("/static/img/404.png");
        helper.addAttachment("OOPS!", file);*/

        /*Inline messages*/
/*        helper.setText("<html><body>Here is a cat picture! <img src='cid:id101'/><body></html>", true);
        ClassPathResource fileInline = new ClassPathResource("cat.jpg");
        helper.addInline("id101", file);*/

        sender.send(message);
    }

}
