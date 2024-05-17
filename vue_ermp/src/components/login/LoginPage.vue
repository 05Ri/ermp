<template>
  <div>
    <p>환영해요! 😀</p>
    <p>운동 목록을 관리하고 통계를 볼 수 있는 Exercise Routine Management Planner 입니다!</p>
    <div>
      <label for="id">ID</label>
      <input type="text" id="id" v-model="user.userId" />
    </div>
    <div>
      <label for="password">PW</label>
      <input type="password" id="password" v-model="user.password" />
    </div>

    <button @click="doLogin">로그인</button>
    <button @click="goToRegist">회원가입</button>
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

</style>