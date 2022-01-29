package com.jsh.erp.datasource.vo;

import java.math.BigDecimal;

public class PurchaseAs {

    private Long organId;

    private String organName;

    private BigDecimal totalPrice;          // 商品总价

    private BigDecimal discountMoney;       // *优惠金额

    private BigDecimal discountLastMoney;   // 优惠后金额

    private BigDecimal otherMoney;          // 其它费用

    private BigDecimal needInMoney;         // *采购金额 totalPrice + otherMoney - discountMoney

    private BigDecimal changeAmount;        // *付款金额（已付部分）

    private BigDecimal periodDebt;          // *当期欠款 needInMoney - changeAmount

    private BigDecimal totalDebt;           // *总欠款 - 从开始计算的所有欠款

    public Long getOrganId() {
        return organId;
    }

    public void setOrganId(Long organId) {
        this.organId = organId;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public BigDecimal getNeedInMoney() {
        return needInMoney;
    }

    public void setNeedInMoney(BigDecimal needInMoney) {
        this.needInMoney = needInMoney;
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public BigDecimal getPeriodDebt() {
        return periodDebt;
    }

    public void setPeriodDebt(BigDecimal periodDebt) {
        this.periodDebt = periodDebt;
    }

    public BigDecimal getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(BigDecimal totalDebt) {
        this.totalDebt = totalDebt;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public BigDecimal getDiscountLastMoney() {
        return discountLastMoney;
    }

    public void setDiscountLastMoney(BigDecimal discountLastMoney) {
        this.discountLastMoney = discountLastMoney;
    }

    public BigDecimal getOtherMoney() {
        return otherMoney;
    }

    public void setOtherMoney(BigDecimal otherMoney) {
        this.otherMoney = otherMoney;
    }
}