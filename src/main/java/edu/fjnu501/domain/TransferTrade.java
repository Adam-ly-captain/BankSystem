package edu.fjnu501.domain;

public class TransferTrade {

    private int tradeId;
    private int transferId;
    private double amount;
    private int type;
    private int cid;

    public TransferTrade() {}

    public TransferTrade(int transferId, double amount, int type, int cid) {
        this.transferId = transferId;
        this.amount = amount;
        this.type = type;
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "TransferTrade{" +
                "tradeId=" + tradeId +
                ", transferId=" + transferId +
                ", amount=" + amount +
                ", type=" + type +
                ", cid=" + cid +
                '}';
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
