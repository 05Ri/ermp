<template>
  <div>
    <h3>환영해요! 😀</h3>
    <p>운동 목록을 관리하고 통계를 볼 수 있는 Exercise Routine Management Planner 입니다!</p>
    <div class="loginbox container d-flex flex-column align-items-center row p-4">
      <div>
        <input class="form-control" type="text" id="id" v-model="user.userId" placeholder="아이디" @keyup.enter="doLogin" />
      </div>
      <div>
        <input class="form-control mb-3" type="password" id="password" v-model="user.password" placeholder="비밀번호" @keyup.enter="doLogin" />
      </div>
      
      <button class="btn btn-primary" @click="doLogin">로그인</button>
      <button class="btn btn-light" @click="goToRegist">회원가입</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useERMPStore } from '@/stores/ermp'

const router = useRouter()
const store = useERMPStore()

const user = ref({
  userId: "",
  password: ""
})

const doLogin = () => {
  store.login(user.value)
}

const goToRegist = () => {
  router.push({name: "regist"})
}
</script>

<style scoped>
/* .loginbox {
  background-color: aliceblue;
  width: 20rem;
} */
</style>