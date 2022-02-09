<template>
  <div>
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
      <a
        slot="changeAmount"
        slot-scope="text, record"
        @click="openBusinessModel(0, record)"
        v-if="record.organName != '小计'"
        >{{ text }}</a
      >
      <span slot="changeAmount" slot-scope="text, record" v-else>{{ text }}</span>
      <a
        slot="totalPrice"
        slot-scope="text, record"
        @click="openBusinessModel(1, record)"
        v-if="record.organName != '小计'"
        >{{ text }}</a
      >
      <span slot="totalPrice" slot-scope="text, record" v-else>{{ text }}</span>
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
      { title: '供应商名称', dataIndex: 'organName', key: 'organName', width: '220px' },
      {
        title: '采购金额',
        dataIndex: 'totalPrice',
        key: 'totalPrice',
        scopedSlots: { customRender: 'totalPrice' },
        width: '180px',
      },
      {
        title: '付款金额',
        dataIndex: 'changeAmount',
        key: 'changeAmount',
        scopedSlots: { customRender: 'changeAmount' },
        width: '360px',
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
      ipagination: {
        pageSize: 10000,
      },
      dataSource: [],
      noRemoveStatusColumn: true,
      url: {
        list: '/analyze/list',
      },
    }
  },
  computed: {
    getDataSource() {
      if (!this.dataSource.length) return []
      if (this.dataSource.length == 1) {
        this.dataSource.push(JSON.parse(JSON.stringify(this.dataSource[0])))
        this.dataSource[1].organName = '小计'
        this.$emit('updateTotalAnalyse', {
          purchaseAccount: this.dataSource[0].totalPrice,
          purchasePayment: this.dataSource[0].changeAmount,
        })
        return this.dataSource
      }
      let obj = this.dataSource.reduce((pre, current) => {
        return {
          totalPrice: (pre.totalPrice + current.totalPrice).toFixed(2),
          changeAmount: (pre.changeAmount + current.changeAmount).toFixed(2),
          discountMoney: (pre.discountMoney + current.discountMoney).toFixed(2),
          periodDebt: (pre.periodDebt + current.periodDebt).toFixed(2),
          totalDebt: (pre.totalDebt + current.totalDebt).toFixed(2),
        }
      })
      this.$emit('updateTotalAnalyse', {
        purchaseAccount: obj.totalPrice,
        purchasePayment: obj.changeAmount,
      })
      this.dataSource.push({
        organName: '小计',
        ...obj,
      })
      return this.dataSource
    },
  },
  methods: {
    loadDataAnalyse(query) {
      this.queryParam = Object.assign(this.queryParam, query)
      this.loadData(1)
    },
    openBusinessModel(ind, record) {
      this.$emit('openBusinessModel', ind, {
        organId: record.organId,
      })
    },
    getEndTime(d) {
      return new Date(d.getFullYear(), d.getMonth() + 1, 0).getDate()
    },
  },
}
</script>
<style>
</style>
