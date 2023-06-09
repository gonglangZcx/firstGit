package com.cuit.house.controller;

import com.cuit.house.constants.CommonConstants;
import com.cuit.house.pojo.User;
import com.cuit.house.result.ResultMsg;
import com.cuit.house.service.UserService;
import com.cuit.house.uitls.HashUtis;
import com.cuit.house.uitls.UserHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("user")
    @ResponseBody
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @RequestMapping("accounts/register")
    public String accountRegister(User account, ModelMap modelMap){
        if(account==null || account.getName()==null){
            return "/user/accounts/register";
        }
        //用户验证
        ResultMsg resultMsg = UserHelper.validate(account);
        if(resultMsg.isSuccess() && userService.addAccount(account)){
            modelMap.put("email",account.getEmail());
            return "/user/accounts/registerSubmit";
        }else{
            return "redirect:/accounts/register?"+resultMsg.asUrlParams();
        }
    }
    //激活邮件
    @RequestMapping("/accounts/verify")
    public String verify(String key){
        boolean result=userService.enable(key);
        if(result){
            return "redirect:/index?"+ResultMsg.successMsg("激活成功").asUrlParams();
        }else{
            return "redirect:/accounts/register?"+ResultMsg.errorMsg("激活失败，请确认连接是否过期");
        }
    }
    //登录接口
    @RequestMapping("/accounts/signin")
    public String singnin(HttpServletRequest req){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String target = req.getParameter("target");
        if(username==null||password==null){
            req.setAttribute("target",target);
            return "/user/accounts/signin";
        }
        User user=userService.auth(username,password);
        if(user==null){
            return "redirect:/accounts/signin?"+"target="+target+"&username="+username+
                    "&"+ResultMsg.errorMsg("用户名和密码错误").asUrlParams();
        }else{
            HttpSession session = req.getSession();
            session.setAttribute(CommonConstants.USER_ATTRIBUTE,user);
            session.setAttribute(CommonConstants.PLAN_USER_ATTRIBUTE,user);
            return StringUtils.isNoneBlank(target)?"redirect:"+target:"redirect:/index";
        }
    }
    //登录退出
    @RequestMapping("accounts/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/index";
    }

    //个人中心
    @RequestMapping("/accounts/profile")
    public String profile(HttpServletRequest  req,User updateUser,ModelMap modelMap){
        if(updateUser.getEmail()==null){
            return "/user/accounts/profile";
        }
        userService.updateUser(updateUser,updateUser.getEmail());
        User user=new User();
        user.setEmail(updateUser.getEmail());
        List<User> users = userService.getUseByQuery(user);
        req.getSession().setAttribute(CommonConstants.USER_ATTRIBUTE,users.get(0));
        return "redirect:/accounts/profile?"+ResultMsg.successMsg("更新成功").asUrlParams();
    }

    //修改密码
    @RequestMapping("accounts/changePassword")
    public String changePassword(String email,String password,String newPassword,
                                 String confirmPassword,ModelMap modelMap){
        //先判断用户名和密码是否正确
        User user = userService.auth(email, password);
        if(user==null||!confirmPassword.equals(newPassword)){
            return "direct:/accounts/profile?"+ResultMsg.errorMsg("密码错误").asUrlParams();
        }
        User updateUser=new User();
        updateUser.setPasswd(HashUtis.encryPassword(newPassword));
        userService.updateUser(updateUser,email);
        return "direct:/accounts/profile?"+ResultMsg.errorMsg("更新成功").asUrlParams();
    }

}
