<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
          @click="handleClick"
      >
        <a-menu-item key="welcome">
          <router-link :to="'/'">
            <MailOutlined/>
            <span> 欢迎 </span>
          </router-link>
        </a-menu-item>
        <a-sub-menu v-for="item in level1" :key="item.id">
          <template v-slot:title>
            <span> <user-outline/> {{ item.name }} </span>
          </template>
          <a-menu-item v-for="child in item.children" :key="child.id">
            <MailOutlined/>
            <span> {{ child.name }} </span>
          </a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <div class="welcome" v-show="isShowWelcome">
        <h1> 欢迎使用知识库 </h1>
      </div>
      <a-list v-show="!isShowWelcome" item-layout="vertical" :grid="{ gutter: 20, column: 3 }" size="large"
              :data-source="ebooks">
        <template #renderItem="{ item }">
          <a-list-item key="item.name">
            <template #actions>
              <span v-for="{ type, text } in actions" :key="type">
                <component :is="type" style="margin-right: 8px"/>
                {{ text }}
              </span>
            </template>
            <a-list-item-meta :description="item.description">
              <template #title>

                <router-link :to="'/doc?ebookId='+item.id">
                  <a>{{ item.name }}</a>
                </router-link>
              </template>
              <template #avatar>
                <a-avatar :src="item.cover"/>
              </template>
            </a-list-item-meta>
          </a-list-item>
        </template>
      </a-list>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import axios from "axios";
import {Tool} from "@/util/tool";
import {message} from "ant-design-vue";

export default defineComponent({
  name: 'Home',
  setup() {
    let categoryId2 = 0;
    const ebooks = ref();
    const isShowWelcome = ref(true);
    const level1 = ref();
    onMounted(() => {
      handleQueryCategory()
      handleQueryEbook()
    })

    const handleQueryEbook = () => {
      axios.get('/ebook/list', {
        params: {
          page: 1,
          size: 1000,
          // categoryId2: categoryId2
        }
      }).then(res => {
        const data = res.data;
        ebooks.value = data.content.list;
      })
    }

    const handleQueryCategory = () => {
      axios.get("/category/all").then(res => {
        const data = res.data
        if (data.success) {
          const categorys = data.content
          level1.value = []
          level1.value = Tool.array2Tree(categorys, 0)
        } else {
          message.error(data.message)
        }
      })
    }

    const handleClick = (value: any) => {
      if (value.key === 'welcome') {
        isShowWelcome.value = true
      } else {
        categoryId2 = value.key
        isShowWelcome.value = false
        handleQueryEbook()
      }
    }

    return {
      actions: [
        {type: 'StarOutlined', text: '156'},
        {type: 'LikeOutlined', text: '156'},
        {type: 'MessageOutlined', text: '2'},
      ],
      ebooks,
      handleClick,
      level1,
      isShowWelcome
    }
  }
});
</script>
<style>
.ant-avatar {
  width: 50px;
  height: 50px;
  line-height: 50px;
  border-radius: 8%;
  margin: 5px 0;
}
</style>
