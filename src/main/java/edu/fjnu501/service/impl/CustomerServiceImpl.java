package edu.fjnu501.service.impl;

import edu.fjnu501.domain.BankCard;
import edu.fjnu501.domain.Customer;
import edu.fjnu501.domain.Order;
import edu.fjnu501.mapper.CustomerMapper;
import edu.fjnu501.service.BankCardService;
import edu.fjnu501.service.CustomerService;
import edu.fjnu501.service.OrderService;
import edu.fjnu501.utils.MD5Password;
import edu.fjnu501.utils.RandomUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private BankCardService bankCardService;

    @Autowired
    private OrderService orderService;

    @Override
    public Customer getCustomerInfoByUid(int uid) {
        Customer customer = customerMapper.getCustomerInfoByUid(uid);
        return customer;
    }

    @Override
    public String registerAccount(Customer customer) throws ParseException {
        if (customer.getIdentityNumber() == null || "".equals(customer.getIdentityNumber())) {
            throw new RuntimeException("身份证不能为空");
        } else if (!checkIdentityNumber(customer)) {
            throw new RuntimeException("已拥有一个账号");
        }
        // 获取随机账号名(时间戳+maxId)
        String userName = RandomUsername.getRandomUserName(customerMapper.findMaxId());
        customer.setUsername(userName);
        // 密码加密存库
        String md5Pwd = MD5Password.MD5Pwd(customer);
        customer.setPassword(md5Pwd);
        customerMapper.registerAccount(customer);

        return userName;
    }

    @Override
    public void getUserInfoAfterLogin(Customer customer) {
//        Customer account = customerMapper.selectUsername(customer.getUsername());
//        if (account != null) {  // 账号存在
//            account = customerMapper.login(customer);
//            if (account == null) { // 密码错误
//                account = new Customer();
//                account.setInfo("0");
//            } else {
//                account.setInfo("1"); // 账号密码正确
//                // 获取所有银行卡信息
//                List<BankCard> bankCards = bankCardService.getBankCards(account.getUid());
//                List<Order> orders = orderService.getAllOrdersByUid(account.getUid());
//                account.setOrders(orders);
//                account.setBankCardList(bankCards);
//            }
//            return account;
//        }
//        account = new Customer();
//        account.setInfo("-1");
//        return account;

        List<BankCard> bankCards = bankCardService.getBankCards(customer.getUid());
        List<Order> orders = orderService.getAllOrdersByUid(customer.getUid());
        customer.setOrders(orders);
        customer.setBankCardList(bankCards);
    }

    @Override
    public void updateInfo(Customer customer) {
        customerMapper.updateInfo(customer);
    }

    @Override
    public boolean checkIdentityNumber(Customer customer) {
        String[] allIdentityNumber = customerMapper.getAllIdentityNumber();
        for (String identityNumber : allIdentityNumber) {
            if (identityNumber != null && identityNumber.equals(customer.getIdentityNumber())) {
                return false;
            }
        }
        return true;  // 身份证号不重复
    }

}
