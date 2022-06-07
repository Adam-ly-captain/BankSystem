package edu.fjnu501.service.impl;

import edu.fjnu501.bankenum.Trade;
import edu.fjnu501.domain.BankCard;
import edu.fjnu501.domain.Order;
import edu.fjnu501.mapper.BankCardMapper;
import edu.fjnu501.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bankCardService")
public class BankCardServiceImpl implements BankCardService {

    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public List<BankCard> getBankCards(int uid) {
        List<BankCard> bankCards = bankCardMapper.findBankCardsByUid(uid);
        return bankCards;
    }

    public void addBankCard(BankCard bankCard) {
        bankCardMapper.addBankCard(bankCard);
    }

    @Override
    public void updateBalance(Order order) {
        if (Trade.withdraw.getType().equals(order.getType()) || Trade.WITHDRAW.getType().equals(order.getType())) {  // 取钱
            double balance = findBalance(order.getCid());
            if (balance < order.getAmount()) {
                throw new RuntimeException("余额不足");
            }
            double amount = - order.getAmount();
            order.setAmount(amount);
        }
        bankCardMapper.updateBalance(order);
    }

    @Override
    public void deleteCard(int cid) {
        bankCardMapper.deleteCard(cid);
    }

    @Override
    public double findBalance(int cid) {
        double balance = bankCardMapper.findBalance(cid);
        return balance;
    }

    @Override
    public int findUidByCid(int cid) {
        int uid = bankCardMapper.findUidByCid(cid);
        return uid;
    }

}
