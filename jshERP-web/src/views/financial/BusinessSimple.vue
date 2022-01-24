<template>
  <div class="business">
    <a-card :style="cardStyle" :bordered="false">
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
        :columns="columnList"
        :dataSource="dataSource"
        :pagination="ipagination"
        :scroll="scroll"
        :loading="loading"
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
  { title: '客户名称', dataIndex: 'ones', key: 'ones' },
  { title: '销售金额', dataIndex: 'twos', key: 'twos' },
  { title: '毛利率', dataIndex: 'threes', key: 'threes' },
  { title: '销售回款 ', dataIndex: 'fours', key: 'fours' },
  { title: '优惠金额', dataIndex: 'fives', key: 'fives' },
  { title: '当期欠款', dataIndex: 'sixs', key: 'sixs' },
]
export default {
  mixins: [JeecgListMixin, FinancialListMixin],
  data() {
    return {
      // 表头
      columnList: columns,
      dataSource: [
        {
          ones: '12.8亿',
          twos: '12.8亿',
          threes: '12.8亿',
          fours: '12.8亿',
          fives: '1111.亿',
          sixs: '12.8亿',
          events: '12.8亿',
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
