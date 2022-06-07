package edu.fjnu501.service;

import edu.fjnu501.domain.Order;
import edu.fjnu501.domain.TransferOrder;
import edu.fjnu501.rpc.domain.BankTrade;

import java.util.List;

public interface OrderService {

    void addOrderInfo(Order order);

    List<Order> getAllOrdersByUid(int uid);

    void transferBalance(TransferOrder transferOrder);

    boolean checkTransferTradeInfo(BankTrade bankTrade);

    void addTransferTradeInfo(BankTrade bankTrade);

}
