import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import 'ant-design-vue/dist/antd.css';
import Antd from 'ant-design-vue';
import * as Icons from '@ant-design/icons-vue';
import axios from "axios";

axios.defaults.baseURL = process.env.VUE_APP_SERVER


axios.interceptors.request.use((config) => {
    return config;
}, (error) => {
    return Promise.reject(error);
})

axios.interceptors.response.use((res) => {
    return res;
}, error => {
    return Promise.reject(error);
})


const app = createApp(App);
app.use(store).use(Antd).use(router).mount('#app')
const icons: any = Icons;
for (const i in icons) {
    app.component(i, icons[i])
}


console.log('环境', process.env.NODE_ENV)