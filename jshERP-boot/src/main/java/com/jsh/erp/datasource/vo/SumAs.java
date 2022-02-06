package com.jsh.erp.datasource.vo;

import java.math.BigDecimal;

public class SumAs {

    private BigDecimal saleMoney;             // *销售金额

    private BigDecimal saleAmount;            // *销售回款

    private BigDecimal purchaseMoney;         // *采购金额

    private BigDecimal purchaseAmount;        // *付款金额

    private BigDecimal otherMoney;          // 其它费用（正：表示收入、负：表示支出）

    private BigDecimal stockCost;           // 库存成本

    public BigDecimal getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public BigDecimal getPurchaseMoney() {
        return purchaseMoney;
    }

    public void setPurchaseMoney(BigDecimal purchaseMoney) {
        this.purchaseMoney = purchaseMoney;
    }

    public BigDecimal getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(BigDecimal purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public BigDecimal getOtherMoney() {
        return otherMoney;
    }

    public void setOtherMoney(BigDecimal otherMoney) {
        this.otherMoney = otherMoney;
    }

    public BigDecimal getStockCost() {
        return stockCost;
    }

    public void setStockCost(BigDecimal stockCost) {
        this.stockCost = stockCost;
    }
}