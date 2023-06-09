package com.cuit.house.service;

import com.cuit.house.mapper.UserMapper;
import com.cuit.house.pojo.User;
import com.cuit.house.uitls.BeanHelper;
import com.cuit.house.uitls.HashUtis;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileService fileService;
@Autowired
private MailService mailService;

@Value("${file.prefix}")
private String prefix;

    public List<User> getUsers() {
        return userMapper.selectUsers();
    }
/*
1.对密码加密
2.存储图片 ,还存储到数据库中
3.发送邮件
 */
    public boolean addAccount(User account) {
        //密码加密了
        account.setPasswd(HashUtis.encryPassword(account.getPasswd()));
        List<String> imgList = fileService.getImgPath(Lists.newArrayList(account.getAvatarFile()));
        if(!imgList.isEmpty()){
            account.setAvatar(imgList.get(0));
        }
        BeanHelper.setDefaultProp(account,User.class);
        BeanHelper.onInsert(account);
        account.setEnable(0);//未激活
        userMapper.insert(account);
        mailService.registerNotiify(account.getEmail());
        return true;
    }

    public boolean enable(String key) {
        String email = MailService.registerCache.getIfPresent(key);
        if(StringUtils.isBlank(email)){
            return false;
        }
        User updateUser=new User();
        updateUser.setEmail(email);
        updateUser.setEnable(1);
        userMapper.update(updateUser);
        return true;
    }

    public User auth(String username, String password) {
        User user=new User();
        user.setEmail(username);
        user.setEnable(1);
        List<User> list=getUseByQuery(user);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    public List<User> getUseByQuery(User user) {
        List<User> list=userMapper.selectUsersByQuery(user);
    list.forEach(u-> {
        u.setAvatar(prefix+u.getAvatar());
    });
        return list;
    }

    public void updateUser(User updateUser, String email) {
        updateUser.setEmail(email);
        BeanHelper.onUpdate(updateUser);
        userMapper.update(updateUser);
    }
}
