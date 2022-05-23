package edu.fjnu501.controller;

import edu.fjnu501.domain.Customer;
import edu.fjnu501.domain.Result;
import edu.fjnu501.service.CustomerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = {"/account"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = {"/get/info/{uid}"})
    @ResponseBody
    public Result getAccountInfoByUid(@PathVariable(value = "uid") int uid) {
        Customer customer = customerService.getCustomerInfoByUid(uid);
        return new Result(200, "获取成功", customer);
    }

    @RequestMapping(value = {"/register"})
    @ResponseBody
    public Result register(@RequestBody Customer customer) {
        String username = null;
        try {
            username = customerService.registerAccount(customer);
        } catch (RuntimeException e) {
          return new Result(400, e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500, "注册失败", null);
        }
        return new Result(200, "注册成功", username);
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody Customer customer) {
//        Customer login = customerService.login(customer);
//        return login;

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(customer.getUsername(), customer.getPassword());
        token.setRememberMe(true);
        try {
            subject.login(token);
        } catch (UnknownAccountException unknownAccountException) {
            return new Result(501, "账号不存在", null);
        } catch (ExcessiveAttemptsException e) {
            return new Result(500, e.getMessage(), null);
        } catch (IncorrectCredentialsException e) {
            return new Result(502, e.getMessage(), null);
        } catch (Exception e) {
            return new Result(400, "登录失败", null);
        }
        Object principal = subject.getPrincipal();
        customerService.getUserInfoAfterLogin((Customer) principal);
        return new Result(200, "登录成功", principal);
    }

    @RequestMapping(value = "/unlogin")
    @ResponseBody
    public Result toLogin() {
        return new Result(201, "请先登录", null);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Result updateInfo(@RequestBody Customer customer) {
        try {
            customerService.updateInfo(customer);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//            return new Result(400, "身份证重复", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500, "更新失败", null);
        }
        return new Result(200, "更新成功", null);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (Exception e) {
            return new Result(500, "登出失败", null);
        }
        return new Result(200, "成功登出", null);
    }

}
