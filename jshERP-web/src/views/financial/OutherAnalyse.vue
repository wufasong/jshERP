<template>
  <div class="outher-analyse">
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
      <a slot="changeAmount" slot-scope="text, record" @click="openBusinessModel(record)">{{ text }}</a>
    </a-table>
  </div>
</template>
<script>
import { FinancialListMixin } from './mixins/FinancialListMixin'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import moment from 'moment'
const columnsList = [
  {
    title: '其他收支分析',
    children: [
      { title: '收支项目', dataIndex: 'clientName', key: 'clientName' },
      { title: '金额', dataIndex: 'changeAmount', key: 'changeAmount', scopedSlots: { customRender: 'changeAmount' } },
    ],
  },
]
export default {
  mixins: [JeecgListMixin, FinancialListMixin],
  data() {
    return {
      columnsList: columnsList,
      columns: columnsList[0].children,
      noRemoveStatusColumn: true,
      queryParam: {
        beginTime: moment().format('YYYY-MM') + '-01',
        endTime: moment().format('YYYY-MM') + '-' + this.getEndTime(new Date(moment().format())),
        as: 'other',
      },
      ipagination: {
        pageSize: 10000,
      },
      dataSource: [{ szh: 1, hes: 1 }],
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
    openBusinessModel(record) {
      this.$emit('openBusinessModel', record.itemType == '收入' ? 4 : 5, {
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
.outher-analyse {
  width: 500px;
}
</style>
