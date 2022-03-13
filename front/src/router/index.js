import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '@/components/Index.vue'
import Login from '@/components/Login.vue'
import Register from '@/components/Register.vue'
import Forget from '@/components/Forget.vue'
import EditBlog from '@/components/EditBlog.vue'
import QA from '@/components/QA.vue'
import Hot from '@/components/Hot.vue'
import Special from '@/components/Special.vue'
import BlogDetails from '@/components/BlogDetails.vue';
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index,
    meta: {
      title: '首页',
    },
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: {
      title: '登录',
    },
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
    meta: {
      title: '注册',
    },
  },
  {
    path: '/forget',
    name: 'Forget',
    component: Forget,
    meta: {
      title: '忘记密码',
    },
  },
  {
    path: '/editBlog',
    name: 'EditBlog',
    component: EditBlog,
    meta: {
      title: '撰写博客',
    },
  },
  {
    path: '/QA',
    name: 'QA',
    component: QA,
    meta: {
      title: '问答',
    },
  },
  {
    path: '/hot',
    name: 'Hot',
    component: Hot,
    meta: {
      title: '热榜',
    },
  },
  {
    path: '/special',
    name: 'Special',
    component: Special,
    meta: {
      title: '专区',
    },
  },{
    path: '/blog',
    name: 'BlogDetails',
    component: BlogDetails,
    meta: {
      title: '专区',
    },
  },
]

const router = new VueRouter({
  routes,
})

export default router
