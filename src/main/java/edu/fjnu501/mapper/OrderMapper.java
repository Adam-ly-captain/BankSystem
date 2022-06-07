package edu.fjnu501.mapper;

import edu.fjnu501.domain.Order;
import edu.fjnu501.domain.TransferTrade;
import edu.fjnu501.rpc.domain.BankTrade;

import java.util.List;

public interface OrderMapper {

    void addOrderInfo(Order order);

    List<Order> getAllOrdersByUid(int uid);

    TransferTrade checkTransferTradeInfo(int id);

    void addTransferTradeInfo(TransferTrade trade);

}
