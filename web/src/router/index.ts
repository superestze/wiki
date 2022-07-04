import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import Home from '../views/home.vue'
import Doc from '../views/doc.vue'
import AdminEbook from '../views/admin/admin-ebook.vue'
import CategoryEbook from '../views/admin/admin-category.vue'
import AdminDoc from '../views/admin/admin-doc.vue'
import AdminUser from '../views/admin/admin-user.vue'
import store from "@/store";
import {Tool} from "@/util/tool";

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
        component: AdminEbook,
        meta: {
            loginRequire: true
        }
    }, {
        path: '/admin/category',
        name: 'CategoryEbook',
        component: CategoryEbook,
        meta: {
            loginRequire: true
        }
    }, {
        path: '/admin/doc',
        name: 'AdminDoc',
        component: AdminDoc,
        meta: {
            loginRequire: true
        }
    }, {
        path: '/doc',
        name: 'Doc',
        component: Doc,
        meta: {
            loginRequire: true
        }
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

router.beforeEach((to, from, next) => {
        if (to.matched.some(function (item) {
                return item.meta.loginRequire;
            }
        )) {
            const loginUser = store.state.user
            if (Tool.isEmpty(loginUser)) {
                next('/')
            } else {
                next()
            }
        } else {
            next()
        }
    }
)

export default router
