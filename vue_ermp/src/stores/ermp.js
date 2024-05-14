import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_API_ERMP = 'http://localhost:8080/ermp-api'

export const useERMPStore = defineStore('ermp', () => {

  // 회원가입
  const regist = function(user) {
    axios.post(REST_API_ERMP + '/user/regist', user)
    .then(() => {
      router.replace({name: 'home'})
    })
    .catch(() => {
      // console.log(user)
    })
  }

  // 로그인
  const login = function(user) {
    console.log(user)
    axios.post(REST_API_ERMP + '/user', user)
    .then((response) => {
      sessionStorage.setItem("loginUser", JSON.stringify(response))
      router.replace({name: 'home'})
    })
  }

  return {
    regist,
    login,
  }
})
