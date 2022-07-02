import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import Home from '../views/home.vue'
import Doc from '../views/doc.vue'
import AdminEbook from '../views/admin/admin-ebook.vue'
import CategoryEbook from '../views/admin/admin-category.vue'
import AdminDoc from '../views/admin/admin-doc.vue'
import AdminUser from '../views/admin/admin-user.vue'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'home',
        component: Home
    },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/about.vue')
    },
    {
        path: '/admin/ebook',
        name: 'AdminEbook',
        component: AdminEbook
    }, {
        path: '/admin/category',
        name: 'CategoryEbook',
        component: CategoryEbook
    }, {
        path: '/admin/doc',
        name: 'AdminDoc',
        component: AdminDoc
    }, {
        path: '/doc',
        name: 'Doc',
        component: Doc
    }, {
        path: '/admin/user',
        name: 'AdminUser',
        component: AdminUser
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
