import Vue from 'vue'
import App from './App.vue'
import router from './router'
import '@/styles/base.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/icon/iconfont'
import axios from 'axios'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
Vue.use(mavonEditor)

import VueParticles from 'vue-particles'
Vue.use(VueParticles)

Vue.config.productionTip = false
Vue.use(ElementUI)

Vue.prototype.axios = axios
axios.defaults.baseURL = '/api'

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

new Vue({
  router,
  render: (h) => h(App),
}).$mount('#app')
