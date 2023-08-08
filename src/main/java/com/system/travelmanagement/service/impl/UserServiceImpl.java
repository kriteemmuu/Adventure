package com.system.travelmanagement.service.impl;
import com.system.travelmanagement.Entity.User;
import com.system.travelmanagement.Pojo.UserPojo;
import com.system.travelmanagement.Repo.UserRepo;
import com.system.travelmanagement.config.PasswordEncoderUtil;
import com.system.travelmanagement.exception.AppException;
import com.system.travelmanagement.service.UserService;
import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import freemarker.template.Template;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final JavaMailSender mailSender;
    private final ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    @Qualifier("emailConfigBean")
    private Configuration emailConfig;
    @Override
    public String saveUser(UserPojo userPojo) {

        User user = new User();


        user.setEmail(userPojo.getEmail());
        user.setFullname(userPojo.getFullname());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));
        userRepo.save(user);
        return "Created";

    }

    @Override
    public UserPojo findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));
        return new UserPojo(user);
    }

    @Override
    public List<User> fetchAll() {
        return this.userRepo.findAll();
    }

    @Override
    public void deletebyid(Integer id) {
        userRepo.deleteById(id);
    }

    @Override
    public User fetchbyid(Integer id) {
            User user=userRepo.findById(id).orElseThrow(()->
                    new RuntimeException("notfound"));
            user=User.builder().fullname(user.getFullname())
                    .email(user.getEmail())
                    .build();
            return user;

    }

    public String generatePassword() {
        int length = 8;
        String password = "";
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int randomChar = (int)(r.nextInt(94) + 33);
            char c = (char)randomChar;
            password += c;
        }
        return password;
    }

    private void sendPassword(String email, String password ){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your new password is: "+ password);
        message.setText(password);
        mailSender.send(message);
    }
    @Override
    public void processPasswordResetRequest(String email){
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            String password = generatePassword();
            sendPassword(email, password);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodePassword = passwordEncoder.encode(password);
            user.setPassword(encodePassword);
            userRepo.save(user);
        }
    }
    @Override
    public void sendEmail() {
        try {
            Map<String, String> model = new HashMap<>();

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template template = emailConfig.getTemplate("emailTemp.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            mimeMessageHelper.setTo("sendfrom@yopmail.com");
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject("Registration");
            mimeMessageHelper.setFrom("sendTo@yopmail.com");

            taskExecutor.execute(new Thread() {
                public void run() {
                    mailSender.send(message);
                }
            });
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
