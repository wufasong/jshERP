<template>
  <div>
    <a-card :bordered="false">
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
      <a-table
        ref="table"
        size="middle"
        class="table"
        bordered
        rowKey="title"
        :columns="columnsList"
        :dataSource="getDataSource"
        :pagination="false"
        :scroll="scroll"
        :loading="loading"
      >
        <span slot="discountLastMoney" slot-scope="text, record" v-if="record.clientName != '小计'"
          >{{ (((record.totalPrice - record.stockCost) / record.stockCost) * 100).toFixed(2) }}%</span
        >
        <span slot="discountLastMoney" slot-scope="text" v-else></span>
        <a
          slot="totalPrice"
          slot-scope="text, record"
          @click="openBusinessModel(2, record)"
          v-if="record.clientName != '小计'"
          >{{ text }}</a
        >
        <span slot="totalPrice" slot-scope="text, record" v-else>{{ text }}</span>
        <a
          slot="changeAmount"
          slot-scope="text, record"
          @click="openBusinessModel(3, record)"
          v-if="record.clientName != '小计'"
          >{{ text }}</a
        >
        <span slot="changeAmount" slot-scope="text, record" v-else>{{ text }}</span>
      </a-table>
    </a-card>
  </div>
</template>
<script>
import { FinancialListMixin } from './mixins/FinancialListMixin'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import moment from 'moment'
const columnsList = [
  { title: '客户名称', dataIndex: 'clientName', key: 'clientName', width: '220px' },
  {
    title: '销售金额',
    dataIndex: 'totalPrice',
    key: 'totalPrice',
    scopedSlots: { customRender: 'totalPrice' },
    width: '180px',
  },
  {
    title: '销售回款 ',
    dataIndex: 'changeAmount',
    key: 'changeAmount',
    scopedSlots: { customRender: 'changeAmount' },
    width: '180px',
  },
  {
    title: '毛利率',
    dataIndex: 'discountLastMoney',
    key: 'discountLastMoney',
    scopedSlots: { customRender: 'discountLastMoney' },
  },

  { title: '优惠金额', dataIndex: 'discountMoney', key: 'discountMoney' },
  { title: '当期欠款', dataIndex: 'periodDebt', key: 'periodDebt' },
]
export default {
  mixins: [JeecgListMixin, FinancialListMixin],
  data() {
    return {
      columnsList: columnsList,
      columns: columnsList,
      queryParam: {
        beginTime: moment().format('YYYY-MM') + '-01',
        endTime: moment().format('YYYY-MM') + '-' + this.getEndTime(new Date(moment().format())),
        as: 'sale',
      },
      ipagination: {
        pageSize: 10000,
      },
      noRemoveStatusColumn: true,
      dataSource: [],
      url: {
        list: '/analyze/list',
      },
      monthFormat: 'YYYY年MM月',
      currentMonth: moment().format('YYYY-MM'),
    }
  },
  computed: {
    getDataSource() {
      if (!this.dataSource.length) return []
      if (this.dataSource.length == 1) {
        this.dataSource.push(JSON.parse(JSON.stringify(this.dataSource[0])))
        this.dataSource[1].clientName = '小计'
        return this.dataSource
      }
      let obj = this.dataSource.reduce((pre, current) => {
        return {
          totalPrice: (pre.totalPrice + current.totalPrice).toFixed(2),
          changeAmount: (pre.changeAmount + current.changeAmount).toFixed(2),
          discountMoney: (pre.discountMoney + current.discountMoney).toFixed(2),
          periodDebt: (pre.periodDebt + current.periodDebt).toFixed(2),
          discountLastMoney: (pre.discountLastMoney + current.discountLastMoney).toFixed(2),
          stockCost: (pre.stockCost + current.stockCost).toFixed(2),
        }
      })
      this.dataSource.push({
        clientName: '小计',
        ...obj,
      })
      return this.dataSource
    },
  },
  methods: {
    moment,
    loadDataAnalyse(query) {
      this.queryParam = Object.assign(this.queryParam, query)
      this.loadData(1)
    },
    openBusinessModel(ind, record) {
      this.$emit('openBusinessModel', ind, {
        organId: record.clientId,
      })
    },
    onChange: function (value, dateString) {
      dateString = value.format('YYYY-MM')
      this.queryParam.endTime = dateString + '-' + this.getEndTime(new Date(value.format()))
      this.queryParam.beginTime = dateString + '-01'
    },
    searchQuery() {
      if (this.queryParam.beginTime == '') {
        this.$message.warning('请选择月份！')
      } else {
        this.loadDataAnalyse(this.queryParam)
      }
    },
    getEndTime(d) {
      return new Date(d.getFullYear(), d.getMonth() + 1, 0).getDate()
    },
  },
}
</script>
<style>
.table {
  margin-bottom: 30px;
}
.ant-card {
  min-height: 600px;
}
</style>
