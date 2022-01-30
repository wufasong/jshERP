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
      <a slot="twos" slot-scope="text" @click="openBusinessModel(2)">{{ text }}</a>
      <a slot="fours" slot-scope="text" @click="openBusinessModel(3)">{{ text }}</a>
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
      { title: '客户名称', dataIndex: 'ones', key: 'ones' },
      { title: '销售金额', dataIndex: 'twos', key: 'twos', scopedSlots: { customRender: 'twos' } },
      { title: '毛利率', dataIndex: 'threes', key: 'threes' },
      { title: '销售回款 ', dataIndex: 'fours', key: 'fours', scopedSlots: { customRender: 'fours' } },
      { title: '优惠金额', dataIndex: 'fives', key: 'fives' },
      { title: '当期欠款', dataIndex: 'sixs', key: 'sixs' },
      { title: '总欠款', dataIndex: 'events', key: 'events' },
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
.table {
  margin-bottom: 30px;
}
</style>
