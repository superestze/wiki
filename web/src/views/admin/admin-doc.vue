<template>
  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <p>
        <a-form layout="inline" :model="param">
          <a-form-item>
            <a-button type="primary" @click="handleQuery()">
              查询
            </a-button>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="add()">
              新增
            </a-button>
          </a-form-item>
        </a-form>
      </p>
      <a-table :columns="columns"
               :row-key="record => record.id"
               :data-source="level1"
               :loading="loading"
               :pagination="false"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar">
        </template>

        <template v-slot:action="{ text, record }">
          <a-space type="small">
            <a-button type="primary" @click="edit(record)"> 编辑</a-button>
          </a-space>
          <a-popconfirm
              title="删除后不可恢复，确认删除?"
              ok-text="Yes"
              cancel-text="No"
              @confirm="handleDelete(record.id)"
          >
            <a-button type="danger">
              删除
            </a-button>
          </a-popconfirm>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>

  <a-modal
      v-model:visible="modalVisible"
      title="文档表单"
      :confirm-loading="modalLoading"
      @ok="handleModalOk">
    <a-form :model="doc" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="名称">
        <a-input v-model:value="doc.name"/>
      </a-form-item>
      <a-form-item label="名称">
        <a-tree-select
            v-model:value="doc.parent"
            style="width: 100%"
            :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
            placeholder="请选择父文档"
            allow-clear
            tree-default-expand-all
            :tree-data="treeSelectData"
            :replaceFields="{
               title: 'name',
               key: 'id',
               value: 'id'
            }"
        >
        </a-tree-select>
      </a-form-item>
      <a-form-item label="顺序">
        <a-input v-model:value="doc.sort"/>
      </a-form-item>

    </a-form>

  </a-modal>
</template>

<script lang="ts">

import {defineComponent, onMounted, ref} from "vue";
import axios from "axios";
import {message} from "ant-design-vue";
import {Tool} from "@/util/tool";

export default defineComponent({
  name: 'AdminDoc',
  setup() {
    const treeSelectData = ref()
    treeSelectData.value = []
    const level1 = ref()
    const doc = ref({})
    const modalVisible = ref(false)
    const modalLoading = ref(false)
    const param = ref();
    param.value = {};
    const handleModalOk = () => {
      modalLoading.value = true
      axios.post('/doc/save', doc.value).then(res => {
        modalLoading.value = false
        const data = res.data
        if (data.success) {
          modalVisible.value = false
          //  重新加载列表

          handleQuery()
        } else {
          message.error(data.message)
        }
      })
    }

    const edit = (record: any) => {
      modalVisible.value = true
      doc.value = Tool.copy(record)
    }

    const handleDelete = (id: number) => {
      axios.delete(`/doc/delete/${id}`).then(res => {
        const data = res.data
        if (data.success) {
          handleQuery()
        }
      })
    }

    const add = () => {
      modalVisible.value = true
      doc.value = {};
    }

    const docs = ref()
    const columns = [

      {
        title: '名称',
        dataIndex: 'name'
      },
      {
        title: '父文档',
        key: 'parent',
        dataIndex: 'parent'
      },
      {
        title: '顺序',
        dataIndex: 'sort'
      },
      {
        title: '点赞数',
        dataIndex: 'voteCount'
      },
      {
        title: 'Action',
        key: 'action',
        slots: {customRender: 'action'}
      }
    ];

    const handleQuery = () => {
      loading.value = true
      level1.value = []
      axios.get('/doc/all').then(res => {
        loading.value = false
        const data = res.data
        if (data.success) {
          docs.value = data.content
          level1.value = []
          level1.value = Tool.array2Tree(docs.value, 0)
        } else {
          message.error(data.message)
        }
      })
    }

    onMounted(() => {
      handleQuery()
    })


    const loading = ref(false)

    return {
      columns,
      docs,
      loading,

      edit,
      add,
      handleDelete,

      modalVisible,
      modalLoading,
      handleModalOk,
      doc,
      param,
      handleQuery,
      level1
    }
  }
})

</script>
