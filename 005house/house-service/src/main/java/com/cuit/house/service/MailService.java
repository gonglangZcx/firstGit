package com.cuit.house.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MailService {

    @Value("${domain.name}")
    private String domainName;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;
    /*
我们用的gava的本地缓存,最大缓存100,
expireAfterAccess 注册后15分钟没有从本地缓存进行移除
*/
    public static Cache<String, String> registerCache =
            CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(15, TimeUnit.MINUTES)
                    .removalListener(new RemovalListener<String, String>() {
                        @Override
                        public void onRemoval(RemovalNotification<String, String> notification) {
                            //15分钟还没有激活 ，直接删除
                        }
                    }).build();
    //邮件发送
    /*
    1.借助key-eamil的关系    key 激活码   value 邮件地址
    2.借助spring mail发送邮件
    3，借助异步框架进行异步操作
     */
    @Async //异步调用这个方法.
    public void registerNotiify(String email) {
        //生成指定长度的字母和数字的随机组合字符串
        String randomKey = RandomStringUtils.randomAlphabetic(10);
        registerCache.put(randomKey,email);

        //解析来是不是要发送邮件，邮件的内容是什么？
        //一个连接+randomKey 是内容
        String url="http://"+domainName+"/accounts/verify?key="+randomKey;
        //发送邮件，发送标题+邮件内容+邮件地址
        sendMail("房产平台激活验证码",url,email);
    }

    private void sendMail(String title, String url, String email) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(title);
        message.setText(url);
        message.setTo(email);
        mailSender.send(message);
    }
}
