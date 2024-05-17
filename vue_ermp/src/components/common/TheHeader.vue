<template>
  <div class="d-flex">
    <h1><RouterLink :to="{name:'home'}">ERMP</RouterLink></h1>
    <div>
      <p>{{ user.name }}님, 환영합니다!
        <img @click="logout" src="@/assets/img/logout_icon.png" width="30rem" height="30rem">
      </p>
    </div>
  </div>
  <nav>
    <RouterLink :to="{}">출석 체크</RouterLink>
    <RouterLink :to="{}">운동 계획</RouterLink>
    <RouterLink :to="{}">운동 통계</RouterLink>

  </nav>
</template>

<script setup>
import { RouterLink } from 'vue-router'
const user = JSON.parse(sessionStorage.loginUser).data

const logout= function() {
  
}

</script>

<style scoped>

</style>