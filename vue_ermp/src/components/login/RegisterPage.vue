<template>
  <div>
    <h2>회원가입</h2>
    <div>
      <label for="id">ID</label>
      <input type="text" id="id" v-model="user.userId"/>
    </div>
    <div>
      <label for="password">PW</label>
      <input type="password" id="password" v-model="user.password" />
    </div>
    <div>
      <label for="name">이름</label>
      <input type="text" id="name" v-model="user.name" />
    </div>
    <div>
      <label for="email">이메일</label>
      <input type="email" id="email" v-model="user.email" />
    </div>
  </div>

  <button @click="regist">가입하기</button>
  <button @click="back">취소</button>
</template>

<script setup>
import { ref } from 'vue'
import { useERMPStore } from '@/stores/ermp'
import router from '@/router'

const store = useERMPStore()

const user = ref({
  userId: "",
  password: "",
  name: "",
  email: ""
})

const regist = function() {
  store.regist(user.value)
}

const back = function() {
  router.go(-1)
}
</script>

<style scoped>

</style>