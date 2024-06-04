<template>
  <div class="d-flex align-items-center">
    <div>
      <RouterLink :to="{name:'home'}">
        <img class="container logoImg" src="@/assets/img/ERMP_logo.png" />
      </RouterLink>
    </div>
    <div class="header-contents p-3" v-if="login !== undefined">
      <div class="d-flex justify-content-end">
        <p>{{ JSON.parse(login).data.name }}님, 환영합니다!
          <a href="#">
            <img @click.prevent="logout" src="@/assets/img/logout_icon.png" width="30rem" height="30rem">
          </a>
        </p>
      </div>
        <nav class="container my-3 d-flex justify-content-around" v-if="login !== undefined">
          <RouterLink :to="{name: 'home'}">출석 체크</RouterLink>
          <RouterLink :to="{name: 'routine'}">운동 루틴</RouterLink>
          <RouterLink :to="{name: 'statistics'}">운동 통계</RouterLink>
        </nav>
    </div>
  </div>

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

.logoImg {
  width: 8rem;
  border-radius: 100%;
  margin: 15px;
}

.header-contents {
  width: 100%;
}

</style>