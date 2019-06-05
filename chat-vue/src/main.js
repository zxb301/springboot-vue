import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
Vue.use(router)
//引入elementui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)
//显示网页标题
import VueTitle from 'vue-wechat-title'
Vue.use(VueTitle)
// 引入富文本编辑器
import  VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
Vue.use(VueQuillEditor)

Vue.config.productionTip = false
Vue.prototype.$basePath = "http://127.0.0.1:8089"
Vue.prototype.$ajax = axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
