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
      <SaleAnalyse @openBusinessModel="openBusinessModel" ref="SaleAnalyse" />
      <PurchaseAnalysis @openBusinessModel="openBusinessModel" ref="PurchaseAnalysis" />
      <OutherAnalyse @openBusinessModel="openBusinessModel" ref="OutherAnalyse" />
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
export default {
  components: {
    BusinessModel,
    PurchaseAnalysis,
    SaleAnalyse,
    OutherAnalyse,
  },
  data() {
    return {
      monthFormat: 'YYYY-MM',
      currentMonth: moment().format('YYYY-MM'),
      queryParam: {
        beginTime: moment().format('YYYY-MM') + '-01',
        endTime: moment().format('YYYY-MM') + '-' + this.getEndTime(new Date(moment().format())),
      },
    }
  },
  created() {},
  methods: {
    moment,
    openBusinessModel(e, params) {
      this.$refs.BusinessModel.open(e, {
        beginTime: this.queryParam.beginTime,
        endTime: this.queryParam.endTime,
        ...params,
      })
    },
    onChange: function (value, dateString) {
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
@import '~@assets/less/common.less';
</style>
