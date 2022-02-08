<template>
  <div class="business">
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <!-- 搜索区域 -->
        <a-form layout="inline" @keyup.enter.native="searchQuery">
          <a-row :gutter="24">
            <a-col :md="6" :sm="24">
              <a-form-item label="日期">
                <a-month-picker
                  placeholder="请选择月份"
                  :default-value="moment(currentMonth, monthFormat)"
                  style="width: 100%"
                  :format="monthFormat"
                  @change="onChange"
                />
              </a-form-item>
            </a-col>
            <span style="float: left; overflow: hidden" class="table-page-search-submitButtons">
              <a-col :md="6" :sm="24">
                <a-button type="primary" @click="searchQuery">查询</a-button>
              </a-col>
            </span>
          </a-row>
        </a-form>
      </div>
      <SaleAnalyse @openBusinessModel="openBusinessModel" ref="SaleAnalyse" @updateTotalAnalyse="updateTotalAnalyse" />
      <PurchaseAnalysis
        @openBusinessModel="openBusinessModel"
        ref="PurchaseAnalysis"
        @updateTotalAnalyse="updateTotalAnalyse"
      />
      <div class="bottom">
        <OutherAnalyse
          @openBusinessModel="openBusinessModel"
          ref="OutherAnalyse"
          @updateTotalAnalyse="updateTotalAnalyse"
        />
        <div class="outher-total">
          <p>当期毛利：{{ (totalAnalyse.saleAccount - totalAnalyse.purchaseAccount).toFixed(2) }}</p>
          <p>
            毛利率：{{
              (
                ((totalAnalyse.saleAccount - totalAnalyse.purchaseAccount) / totalAnalyse.purchaseAccount) *
                100
              ).toFixed(2)
            }}%
          </p>
          <p>
            当期纯利：{{
              (totalAnalyse.returnPayment - totalAnalyse.purchasePayment + totalAnalyse.outherAmount).toFixed(2)
            }}
          </p>
          <p>库存金额：{{ totalAnalyse.stockCost.toFixed(2) }}</p>
        </div>
      </div>
    </a-card>
    <BusinessModel ref="BusinessModel" />
  </div>
</template>
<script>
import BusinessModel from './modules/BusinessModel.vue'
import moment from 'moment'
import PurchaseAnalysis from './PurchaseAnalysis.vue'
import SaleAnalyse from './SaleAnalyse.vue'
import OutherAnalyse from './OutherAnalyse.vue'
import { getCurrentStockCost } from '@/api/api'
export default {
  components: {
    BusinessModel,
    PurchaseAnalysis,
    SaleAnalyse,
    OutherAnalyse,
  },
  data() {
    return {
      totalAnalyse: {
        //库存金额
        stockCost: 0,
        //销售金额
        saleAccount: 0,
        //采购金额
        purchaseAccount: 0,
        //销售回款
        returnPayment: 0,
        //采购付款
        purchasePayment: 0,
        //其他收支
        outherAmount: 0,
      },
      monthFormat: 'YYYY年MM月',
      currentMonth: moment().format('YYYY-MM'),
      queryParam: {
        beginTime: moment().format('YYYY-MM') + '-01',
        endTime: moment().format('YYYY-MM') + '-' + this.getEndTime(new Date(moment().format())),
      },
    }
  },
  created() {
    getCurrentStockCost({}).then((res) => {
      if (res && res.code === 200) {
        this.totalAnalyse.stockCost = res.data.stockCost
      }
    })
  },
  methods: {
    moment,
    updateTotalAnalyse(obj) {
      this.totalAnalyse = Object.assign(this.totalAnalyse, obj)
      console.log(this.totalAnalyse)
    },
    openBusinessModel(e, params) {
      this.$refs.BusinessModel.open(e, {
        beginTime: this.queryParam.beginTime,
        endTime: this.queryParam.endTime,
        ...params,
      })
    },
    onChange: function (value, dateString) {
      dateString = value.format('YYYY-MM')
      this.queryParam.endTime = dateString + '-' + this.getEndTime(new Date(value.format()))
      this.queryParam.beginTime = dateString + '-01'
    },
    getEndTime(d) {
      return new Date(d.getFullYear(), d.getMonth() + 1, 0).getDate()
    },
    searchQuery() {
      if (this.queryParam.beginTime == '') {
        this.$message.warning('请选择月份！')
      } else {
        this.$refs.SaleAnalyse.loadDataAnalyse(this.queryParam)
        this.$refs.PurchaseAnalysis.loadDataAnalyse(this.queryParam)
        this.$refs.OutherAnalyse.loadDataAnalyse(this.queryParam)
      }
    },
    handleTableChange() {},
  },
}
</script>
<style scoped>
.business {
}
.table {
  margin-bottom: 30px;
}
.bottom {
  display: flex;
}
.outher-total {
  margin-left: 50px;
  font-weight: bold;
}
@import '~@assets/less/common.less';
</style>
