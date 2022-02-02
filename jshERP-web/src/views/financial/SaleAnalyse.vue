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
      <span slot="discountLastMoney" slot-scope="text, record"
        >{{
          (
            (record.discountLastMoney + record.otherMoney - record.stockCost) /
            (record.discountLastMoney + record.otherMoney)
          ).toFixed(2)
        }}%</span
      >
      <a slot="totalPrice" slot-scope="text, record" @click="openBusinessModel(2, record)">{{ text }}</a>
      <a slot="changeAmount" slot-scope="text, record" @click="openBusinessModel(3, record)">{{ text }}</a>
    </a-table>
  </div>
</template>
<script>
import { FinancialListMixin } from './mixins/FinancialListMixin'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import moment from 'moment'
const columnsList = [
  {
    title: '销售分析',
    children: [
      { title: '客户名称', dataIndex: 'clientName', key: 'clientName' },
      { title: '销售金额', dataIndex: 'totalPrice', key: 'totalPrice', scopedSlots: { customRender: 'totalPrice' } },
      {
        title: '毛利率',
        dataIndex: 'discountLastMoney',
        key: 'discountLastMoney',
        scopedSlots: { customRender: 'discountLastMoney' },
      },
      {
        title: '销售回款 ',
        dataIndex: 'changeAmount',
        key: 'changeAmount',
        scopedSlots: { customRender: 'changeAmount' },
      },
      { title: '优惠金额', dataIndex: 'discountMoney', key: 'discountMoney' },
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
        as: 'sale',
      },
      ipagination: {
        pageSize: 10000,
      },
      noRemoveStatusColumn: true,
      dataSource: [{ ones: 1, twos: '123', threes: 3, fours: 4, fives: 5, sixs: 6, events: 7 }],
      url: {
        list: '/analyze/list',
      },
    }
  },
  created() {
    console.log(this.columnsList)
  },
  methods: {
    loadDataAnalyse(query) {
      this.queryParam = Object.assign(this.queryParam, query)
      this.loadData(1)
    },
    openBusinessModel(ind, record) {
      this.$emit('openBusinessModel', ind, {
        organId: record.clientId,
      })
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
</style>
