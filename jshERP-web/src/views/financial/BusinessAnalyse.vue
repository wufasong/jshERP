<template>
  <div class="business">
    <a-card :bordered="false">
      <div class="table-page-search-wrapper">
        <!-- 搜索区域 -->
        <a-form layout="inline" @keyup.enter.native="searchQuery">
          <a-row :gutter="24">
            <a-col :md="6" :sm="24">
              <a-form-item label="日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
                <a-range-picker
                  style="width: 100%"
                  v-model="queryParam.createTimeRange"
                  format="YYYY-MM-DD"
                  :placeholder="['开始时间', '结束时间']"
                  @change="onDateChange"
                  @ok="onDateOk"
                />
              </a-form-item>
            </a-col>
            <span style="float: left; overflow: hidden" class="table-page-search-submitButtons">
              <a-col :md="6" :sm="24">
                <a-button type="primary" @click="searchQuery">查询</a-button>
                <a-button style="margin-left: 8px" @click="searchReset">重置</a-button>
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
        { title: '供应商名称', dataIndex: 'one', key: 'one' },
        { title: '采购金额', dataIndex: 'two', key: 'two' },
        { title: '付款金额', dataIndex: 'three', key: 'three' },
        { title: '优惠金额 ', dataIndex: 'four', key: 'four' },
        { title: '当期欠款', dataIndex: 'five', key: 'five' },
        { title: '总欠款', dataIndex: 'six', key: 'six' },
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
      // 表头
      columnList: columns,
      dataSource: [
        {
          one: 'Leo',
          two: '120亿',
          three: '10亿',
          four: '50亿',
          five: '100万',
          six: '1.1亿',
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
        list: '1',
      },
    }
  },
  created() {},
  methods: {
    handleTableChange() {},
  },
}
</script>
<style scoped>
.business {
}
@import '~@assets/less/common.less';
</style>
