package sg.edu.iss.team5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender emailSender;

    public void sendRegistrationEmail(String sendTo, String stuName, String courseName){
    	String text = "Test";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Course Registration Successful");
        message.setText(text);
        message.setTo(sendTo);
        message.setFrom("team5caps2022@gmail.com");

        emailSender.send(message);
    }

}