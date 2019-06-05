import Vue from 'vue'
import Router from 'vue-router'
import login from '../components/login'
import index from '../components/index'
import chatpage from '../components/chatpage'
import twopage from '../components/twopage'
import threepage from '../components/threepage'

Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: login,
      meta: {
        title: '登录页面',
      },
    },
    {
      path: '/index',
      name: 'index',
      component: index,
      meta: {
        title: '首页',
      },
      children: [
        {
          path: '/chatpage',
          name: 'chatpage',
          component: chatpage,
          meta: {
            title: '第一页'
          }
        },
        {
          path: '/twopage',
          name: 'twopage',
          component: twopage,
          meta: {
            title: '第二页'
          }
        },
        {
          path: '/threepage',
          name: 'threepage',
          component: threepage,
          meta: {
            title: '第三页'
          }
        }
      ]
    }
  ]
})
