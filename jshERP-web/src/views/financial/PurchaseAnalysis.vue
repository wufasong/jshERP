<template>
  <div>
    <a-table
      ref="table"
      size="middle"
      class="table"
      bordered
      rowKey="title"
      :columns="columnsList"
      :dataSource="dataSource"
      :pagination="false"
      :scroll="scroll"
      :loading="loading"
    >
      <a slot="changeAmount" slot-scope="text" @click="openBusinessModel(0)">{{ text }}</a>
      <a slot="totalPrice" slot-scope="text" @click="openBusinessModel(1)">{{ text }}</a>
    </a-table>
  </div>
</template>
<script>
import { FinancialListMixin } from './mixins/FinancialListMixin'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import moment from 'moment'
const columnsList = [
  {
    title: '采购综合分析',
    children: [
      { title: '供应商名称', dataIndex: 'organName', key: 'organName' },
      {
        title: '采购金额',
        dataIndex: 'totalPrice',
        key: 'totalPrice',
        scopedSlots: { customRender: 'totalPrice' },
      },
      {
        title: '付款金额',
        dataIndex: 'changeAmount',
        key: 'changeAmount',
        scopedSlots: { customRender: 'changeAmount' },
      },
      { title: '优惠金额 ', dataIndex: 'discountMoney', key: 'discountMoney' },
      { title: '当期欠款', dataIndex: 'periodDebt', key: 'periodDebt' },
      { title: '总欠款', dataIndex: 'totalDebt', key: 'totalDebt' },
    ],
  },
]
export default {
  mixins: [JeecgListMixin, FinancialListMixin],
  data() {
    return {
      columnsList: columnsList,
      columns: columnsList[0].children,
      queryParam: {
        beginTime: moment().format('YYYY-MM') + '-01',
        endTime: moment().format('YYYY-MM') + '-' + this.getEndTime(new Date(moment().format())),
      },
      dataSource: [],
      noRemoveStatusColumn: true,
      url: {
        list: '/analyze/list',
      },
    }
  },
  methods: {
    loadDataAnalyse(query) {
      this.queryParam = Object.assign(this.queryParam, query)
      this.loadData(1)
    },
    openBusinessModel(ind) {
      this.$emit('openBusinessModel', ind)
    },
    getEndTime(d) {
      return new Date(d.getFullYear(), d.getMonth() + 1, 0).getDate()
    },
  },
}
</script>
<style>
</style>
