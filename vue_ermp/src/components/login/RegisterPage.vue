<template>
  <div>
    <h2>회원가입</h2>
    <div class="form-box container">
      <div>
      <label class="form-label" for="id">ID</label>
        <input class="form-control" type="text" id="id" v-model="user.userId" @input="doubleCheck = false" aria-describedby="basic-addon2" />
      </div>
      <div>
        <label class="form-label" for="password">PW</label>
        <input class="form-control" type="password" id="password" v-model="user.password" />
      </div>
      <div>
        <label class="form-label" for="name">이름</label>
        <input class="form-control" type="text" id="name" v-model="user.name" />
      </div>
      <div>
        <label class="form-label" for="email">이메일</label>
        <input class="form-control" type="email" id="email" v-model="user.email" @input="doubleCheck = false" />
      </div>
    </div>
    
    <div class="btn-box container">
      <button class="btn btn-warning" @click="check" :disabled="doubleCheck">중복확인</button>
      <button class="btn btn-success" @click="regist" :disabled="!doubleCheck">가입하기</button>
      <button class="btn btn-danger" @click="back">취소</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useERMPStore } from '@/stores/ermp'
import router from '@/router'
import axios from 'axios'

const store = useERMPStore()

const doubleCheck = ref(false)

const user = ref({
  userId: "",
  password: "",
  name: "",
  email: ""
})

// 중복 체크
const check = () => {
  if (nullCheck()) {
    axios.post(`http://localhost:8080/ermp-api/user/check`, user.value)
    .then(() => {
      doubleCheck.value = true
      alert("사용가능한 아이디, 이메일입니다")
    })
    .catch(() => {
      alert("중복된 아이디 또는 이메일입니다")
    })
  }
}

// 공백 체크
const nullCheck = () => {
  if (user.value.userId == "") {
    alert("아이디를 입력해주세요!")
    return false
  }
  if (user.value.password == "") {
    alert("비밀번호를 입력해주세요!")
    return false
  }
  if (user.value.name == "") {
    alert("이름을 입력해주세요!")
    return false
  }
  if (user.value.email == "") {
    alert("이메일을 입력해주세요!")
    return false
  }

  return true
}

const regist = function() {
  if (nullCheck())
    store.regist(user.value)
}

const back = function() {
  router.go(-1)
}
</script>

<style scoped>
.form-box {
  padding: 40px;
}

.form-box > div {
  margin: 10px;
}

.btn-box {
  margin: 0 auto;
  text-align: center;
}

.btn-box > button {
  margin: 5px;
}
</style>