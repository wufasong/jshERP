package com.jsh.erp.service.analyze;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsh.erp.constants.BusinessConstants;
import com.jsh.erp.datasource.entities.*;
import com.jsh.erp.datasource.vo.PurchaseAs;
import com.jsh.erp.datasource.mappers.DepotHeadMapperEx;
import com.jsh.erp.exception.JshException;
import com.jsh.erp.service.depot.DepotService;
import com.jsh.erp.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyzeService {
    private Logger logger = LoggerFactory.getLogger(AnalyzeService.class);

    @Resource
    private DepotHeadMapperEx depotHeadMapperEx;
    @Resource
    private DepotService depotService;

    /**
     * 采购分析
     * 获取指定时间段（月份）- 供应商、采购金额、付款金额、优惠金额、当期欠款
     * @param beginTime
     * @param endTime
     * @param depotId
     * @return
     * @throws Exception
     */
    public List<PurchaseAs> getPurchaseAs(String beginTime, String endTime, Long depotId) throws Exception {
        List<PurchaseAs> resList = new ArrayList<>();
        try{
            List<Long> depotList = new ArrayList<>();
            if(depotId != null) {
                depotList.add(depotId);
            } else {
                //未选择仓库时默认为当前用户有权限的仓库
                JSONArray depotArr = depotService.findDepotByCurrentUser();
                for(Object obj: depotArr) {
                    JSONObject object = JSONObject.parseObject(obj.toString());
                    depotList.add(object.getLong("id"));
                }
            }

            beginTime = Tools.parseDayToTime(beginTime,BusinessConstants.DAY_FIRST_TIME);
            endTime = Tools.parseDayToTime(endTime,BusinessConstants.DAY_LAST_TIME);
            List<PurchaseAs> list=depotHeadMapperEx.selectByConditionDepotHeadGroupBySup("入库", "采购", null, beginTime, endTime, depotList);
            if (null != list) {
                for (PurchaseAs dh : list) {
                    dh.setTotalDebt(depotHeadMapperEx.getPurchaseAsSupTotalDebt("入库", "采购", null, endTime, depotList, dh.getOrganId()));

                    resList.add(dh);
                }
            }
        }catch(Exception e){
            JshException.readFail(logger, e);
        }
        return resList;
    }

}
