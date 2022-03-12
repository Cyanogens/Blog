import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '@/components/Index.vue'
import Login from '@/components/Login.vue'
import Register from '@/components/Register.vue'
import Forget from '@/components/Forget.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: 'forget',
    name: 'Forget',
    component: Forget
  }
]

const router = new VueRouter({
  routes
})

export default router
