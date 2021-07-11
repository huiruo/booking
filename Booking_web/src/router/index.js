// import {createWebHistory,createRouter}  from 'vue-router'
import {createWebHashHistory,createRouter}  from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
]
/*
const router = new VueRouter({
  // mode: 'history',
  // base: process.env.BASE_URL,
  routes
})
*/

const router = createRouter({
  // history: createWebHistory(),
  history: createWebHashHistory(),
  routes
})
export default router
