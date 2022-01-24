package com.jsh.erp.datasource.entities;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Description
 *
 * @Author: cjl
 * @Date: 2019/2/25 11:40
 */
@Data
public class DepotEx extends Depot{
    //负责人名字
    private String principalName;

    /**
     * 只有上面的字段来自 depot
     * 下面信息只能来自 material_initial_stock
     */
    private BigDecimal initStock;

    private BigDecimal initStockCost;

    private BigDecimal currentStock;

    private BigDecimal lowSafeStock;

    private BigDecimal highSafeStock;

}
