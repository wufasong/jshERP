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
      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="title"
        :columns="columnList[0]"
        :dataSource="dataSource"
        :pagination="ipagination"
        :scroll="scroll"
        :loading="loading"
        @change="handleTableChange"
      >
      </a-table>
      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="title"
        :columns="columnList[1]"
        :dataSource="dataSource"
        :pagination="ipagination"
        :scroll="scroll"
        :loading="loading"
        @change="handleTableChange"
      >
      </a-table>
      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="title"
        :columns="columnList[2]"
        :dataSource="dataSource"
        :pagination="ipagination"
        :scroll="scroll"
        :loading="loading"
        style="width: 350px"
        @change="handleTableChange"
      >
      </a-table>
    </a-card>
  </div>
</template>
<script>
import { FinancialListMixin } from './mixins/FinancialListMixin'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import moment from 'moment'
const columns = [
  [
    {
      title: '销售分析',
      children: [
        { title: '客户名称', dataIndex: 'ones', key: 'ones' },
        { title: '销售金额', dataIndex: 'twos', key: 'twos' },
        { title: '毛利率', dataIndex: 'threes', key: 'threes' },
        { title: '销售回款 ', dataIndex: 'fours', key: 'fours' },
        { title: '优惠金额', dataIndex: 'fives', key: 'fives' },
        { title: '当期欠款', dataIndex: 'sixs', key: 'sixs' },
        { title: '总欠款', dataIndex: 'events', key: 'events' },
      ],
    },
  ],
  [
    {
      title: '采购综合分析',
      children: [
        { title: '供应商名称', dataIndex: 'organName', key: 'organName' },
        { title: '采购金额', dataIndex: 'totalPrice', key: 'totalPrice' },
        { title: '付款金额', dataIndex: 'changeAmount', key: 'changeAmount' },
        { title: '优惠金额 ', dataIndex: 'discountMoney', key: 'discountMoney' },
        { title: '当期欠款', dataIndex: 'periodDebt', key: 'periodDebt' },
        { title: '总欠款', dataIndex: 'totalDebt', key: 'totalDebt' },
      ],
    },
  ],
  [
    {
      title: '其他收支分析',
      children: [
        { title: '收支项目', dataIndex: 'szh', key: 'szh' },
        { title: '金额', dataIndex: 'hes', key: 'hes' },
      ],
    },
  ],
]
export default {
  mixins: [JeecgListMixin, FinancialListMixin],
  data() {
    return {
      monthFormat: 'YYYY-MM',
      currentMonth: moment().format('YYYY-MM'),
      queryParam: {
        beginTime: moment().format('YYYY-MM') + '-01',
        endTime: moment().format('YYYY-MM') + '-' + this.getEndTime(new Date(moment().format())),
      },
      // 表头
      columnList: columns,
      columns: columns[0][0].children.concat(columns[1][0].children).concat(columns[2][0].children),
      dataSource: [
        {
          organName: 'Leo',
          totalPrice: '120亿',
          changeAmount: '10亿',
          discountMoney: '50亿',
          periodDebt: '100万',
          totalDebt: '1.1亿',
          ones: '12.8亿',
          twos: '12.8亿',
          threes: '12.8亿',
          fours: '12.8亿',
          fives: '1111.亿',
          sixs: '12.8亿',
          events: '12.8亿',
          szh: '111万',
          hes: '123万',
        },
      ],
      url: {
        list: '/analyze/list',
      },
    }
  },
  created() {
    console.log(this.columns)
  },
  methods: {
    moment,
    onChange: function (value, dateString) {
      this.queryParam.endTime = dateString + '-' + this.getEndTime(new Date(value.format()))
      this.queryParam.beginTime = dateString + '-01'
      console.log(this.queryParam)
    },
    getEndTime(d) {
      return new Date(d.getFullYear(), d.getMonth() + 1, 0).getDate()
    },
    searchQuery() {
      if (this.queryParam.beginTime == '') {
        this.$message.warning('请选择月份！')
      } else {
        this.loadData(1)
      }
    },
    handleTableChange() {},
  },
}
</script>
<style scoped>
.business {
}
@import '~@assets/less/common.less';
</style>
