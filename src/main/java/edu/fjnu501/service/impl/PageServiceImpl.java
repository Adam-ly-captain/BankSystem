package edu.fjnu501.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fjnu501.domain.BankCard;
import edu.fjnu501.domain.Order;
import edu.fjnu501.domain.Page;
import edu.fjnu501.mapper.BankCardMapper;
import edu.fjnu501.mapper.OrderMapper;
import edu.fjnu501.service.OrderService;
import edu.fjnu501.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pageService")
public class PageServiceImpl implements PageService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public Page getOrderInfoByPage(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
//        List<Order> allOrdersByUid = orderMapper.getAllOrdersByUid(page.getUid());
        List<Order> allOrdersByUid = orderService.getAllOrdersByUid(page.getUid());
        PageInfo<Order> pageInfo = new PageInfo<>(allOrdersByUid);

        page.setData(allOrdersByUid);
        page.setPages(pageInfo.getPages());
        return page;
    }

    @Override
    public Page getCardInfoByPage(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<BankCard> bankCardsByUid = bankCardMapper.findBankCardsByUid(page.getUid());
        PageInfo<BankCard> pageInfo = new PageInfo<>(bankCardsByUid);

        page.setData(bankCardsByUid);
        page.setPages(pageInfo.getPages());
        return page;
    }
}
