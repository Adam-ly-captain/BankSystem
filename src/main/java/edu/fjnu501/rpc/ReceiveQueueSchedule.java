package edu.fjnu501.rpc;

import edu.fjnu501.bankenum.ResultCodeState;
import edu.fjnu501.rpc.domain.BankTrade;
import edu.fjnu501.rpc.domain.Result2;
import edu.fjnu501.rpc.RPCProtocol;
import edu.fjnu501.service.OrderService;
import edu.fjnu501.utils.Schedule;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ReceiveQueueSchedule implements RPCProtocol {

    private OrderService orderService;

    public ReceiveQueueSchedule() throws IOException {
        RPC.Server server = new RPC.Builder(new Configuration())
                .setBindAddress("localhost")
                .setPort(9871)
                .setProtocol(RPCProtocol.class)
                .setInstance(new ReceiveQueueSchedule(1))
                .build();
        server.start();
    }

    public ReceiveQueueSchedule(int i) {}

    @Override
    public void receiveTrade(BankTrade bankTrade) throws IOException, InterruptedException {
//        Thread.sleep(20000);
        if (!Schedule.orderService.checkTransferTradeInfo(bankTrade)) {
            Schedule.orderService.addTransferTradeInfo(bankTrade);
        }
        Result2 result = new Result2();
        result.setCode(ResultCodeState.SUCCESS.getState());
        result.setMsg(bankTrade.getTradeId());
        RPCProtocol protocol = RPC.getProxy(RPCProtocol.class, RPCProtocol.versionID, new InetSocketAddress("localhost", 3000), new Configuration());
        protocol.responseTrade(result);
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
        Schedule.orderService = orderService;
    }

}
