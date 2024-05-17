<template>
  <div class="d-flex">
    <h1><RouterLink :to="{name:'home'}">ERMP</RouterLink></h1>
    <div v-if="login !== undefined">
      <p>{{ JSON.parse(login).data.name }}님, 환영합니다!
        <img @click="logout" src="@/assets/img/logout_icon.png" width="30rem" height="30rem">
      </p>
    </div>
  </div>
  <nav class="container my-3" v-if="login !== undefined">
    <RouterLink :to="{name: 'home'}">출석 체크</RouterLink>
    <RouterLink :to="{name: 'routine'}">운동 루틴</RouterLink>
    <RouterLink :to="{name: 'statistics'}">운동 통계</RouterLink>
  </nav>

</template>

<script setup>
import { RouterLink, useRouter } from 'vue-router'

const login = sessionStorage.loginUser

const router = useRouter()

const logout= function() {
  sessionStorage.removeItem('loginUser')
  router.go()
}

</script>

<style scoped>
nav {
  display: flex;
  justify-content: space-around;
}
nav .router-link-exact-active {
  color: purple;
}

</style>