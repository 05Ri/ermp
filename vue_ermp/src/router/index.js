import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

import RegisterPage from '../components/login/RegisterPage.vue'
import LoginPage from '../components/login/LoginPage.vue'
import RoutinePage from '@/components/routine/RoutinePage.vue'
import StatisticsPage from '@/components/statistics/StatisticsPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/regist',
      name: 'regist',
      component: RegisterPage,
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPage,
    },
    {
      path: '/routine',
      name: 'routine',
      component: RoutinePage,
    },
    {
      path: '/statistics',
      name: 'statistics',
      component: StatisticsPage,
    },
  ]
})

router.beforeEach((to, from) => {

  if (sessionStorage.getItem('loginUser') !== null && (to.name === 'regist' || to.name === 'login')) {
    return { name: 'home' }
  }

  if (to.name !== 'regist' && sessionStorage.getItem('loginUser') === null && to.name !== 'login') {
    return { name: 'login' }
  }


})
export default router
