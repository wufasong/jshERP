<template>
  <div>
    <a-modal
      v-model="modelVisible"
      :title="showComponentList[showComponentInd].title"
      @ok="modelVisible = false"
      width="1300px"
    >
      <component :is="showComponentList[showComponentInd].name" :showType="false" v-if="modelVisible"></component>
    </a-modal>
  </div>
</template>
<script>
import MoneyOut from '../MoneyOutList.vue'
import InDetail from '../../report/InDetail.vue'
import OutDetail from '../../report/OutDetail.vue'
import MoneyIn from '../MoneyInList.vue'
import ItemIn from '../ItemInList.vue'
import ItemOut from '../ItemOutList.vue'
export default {
  components: {
    MoneyOut,
    InDetail,
    OutDetail,
    MoneyIn,
    ItemIn,
    ItemOut,
  },
  data() {
    return {
      modelWidth: '1300px',
      modelVisible: false,
      showComponentInd: 0,
      showComponentList: [
        { name: 'MoneyOut', title: '付款单', extraData: {} },
        { name: 'InDetail', title: '入库明细', extraData: {} },
        { name: 'OutDetail', title: '出库明细', extraData: {} },
        { name: 'MoneyIn', title: '收款单', extraData: {} },
        { name: 'ItemIn', title: '收入单', extraData: {} },
        { name: 'ItemOut', title: '支出单', extraData: {} },
      ],
    }
  },
  methods: {
    open(ind) {
      this.showComponentInd = ind
      this.modelVisible = true
    },
  },
}
</script>