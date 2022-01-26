package com.jsh.erp.datasource.vo;


import java.math.BigDecimal;

public class DepotItemVo4StockCost {

    // 库存成本
    private BigDecimal inCostTotal;
    private BigDecimal outCostTotal;
    private BigDecimal transfInCostTotal;
    private BigDecimal transfOutCostTotal;
    private BigDecimal assemInCostTotal;
    private BigDecimal assemOutCostTotal;
    private BigDecimal disAssemInCostTotal;
    private BigDecimal disAssemOutCostTotal;

    public BigDecimal getInCostTotal() {
        return inCostTotal;
    }

    public void setInCostTotal(BigDecimal inCostTotal) {
        this.inCostTotal = inCostTotal;
    }

    public BigDecimal getOutCostTotal() {
        return outCostTotal;
    }

    public void setOutCostTotal(BigDecimal outCostTotal) {
        this.outCostTotal = outCostTotal;
    }

    public BigDecimal getTransfInCostTotal() {
        return transfInCostTotal;
    }

    public void setTransfInCostTotal(BigDecimal transfInCostTotal) {
        this.transfInCostTotal = transfInCostTotal;
    }

    public BigDecimal getTransfOutCostTotal() {
        return transfOutCostTotal;
    }

    public void setTransfOutCostTotal(BigDecimal transfOutCostTotal) {
        this.transfOutCostTotal = transfOutCostTotal;
    }

    public BigDecimal getAssemInCostTotal() {
        return assemInCostTotal;
    }

    public void setAssemInCostTotal(BigDecimal assemInTotal) {
        this.assemInCostTotal = assemInCostTotal;
    }

    public BigDecimal getAssemOutCostTotal() {
        return assemOutCostTotal;
    }

    public void setAssemOutCostTotal(BigDecimal assemOutCostTotal) {
        this.assemOutCostTotal = assemOutCostTotal;
    }

    public BigDecimal getDisAssemInCostTotal() {
        return disAssemInCostTotal;
    }

    public void setDisAssemInCostTotal(BigDecimal disAssemInCostTotal) {
        this.disAssemInCostTotal = disAssemInCostTotal;
    }

    public BigDecimal getDisAssemOutCostTotal() {
        return disAssemOutCostTotal;
    }

    public void setDisAssemOutCostTotal(BigDecimal disAssemOutCostTotal) {
        this.disAssemOutCostTotal = disAssemOutCostTotal;
    }
}
