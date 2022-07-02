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
      title="分类表单"
      :confirm-loading="modalLoading"
      @ok="handleModalOk">
    <a-form :model="category" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="名称">
        <a-input v-model:value="category.name"/>
      </a-form-item>
      <a-form-item label="父分类">
        <a-select
            ref="select"
            v-model:value="category.parent"
            style="width: 100%"
        >
          <a-select-option value="0">无</a-select-option>
          <a-select-option :disabled="category.id === c.id" v-for="c in level1" :key="c.id" :value="c.id">{{
              c.name
            }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="顺序">
        <a-input v-model:value="category.sort"/>
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
  name: 'AdminCategory',
  setup() {
    const level1 = ref()
    const category = ref()
    const modalVisible = ref(false)
    const modalLoading = ref(false)
    const param = ref();
    param.value = {};
    const handleModalOk = () => {
      modalLoading.value = true
      axios.post('/category/save', category.value).then(res => {
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
      category.value = Tool.copy(record)
    }

    const handleDelete = (id: number) => {
      axios.delete(`/category/delete/${id}`).then(res => {
        const data = res.data
        if (data.success) {
          handleQuery()
        }
      })
    }

    const add = () => {
      modalVisible.value = true
      category.value = {};
    }

    const categorys = ref()
    const columns = [

      {
        title: '名称',
        dataIndex: 'name'
      },
      {
        title: '父分类',
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
      axios.get('/category/all').then(res => {
        loading.value = false
        const data = res.data
        if (data.success) {
          categorys.value = data.content
          level1.value = []
          level1.value = Tool.array2Tree(categorys.value, 0)
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
      categorys,
      loading,

      edit,
      add,
      handleDelete,

      modalVisible,
      modalLoading,
      handleModalOk,
      category,
      param,
      handleQuery,
      level1
    }
  }
})

</script>
