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
    // console.log(user)
    axios.post(REST_API_ERMP + '/user', user)
    .then((response) => {
      sessionStorage.setItem("loginUser", JSON.stringify(response))
      router.go()
      router.replace({name: 'home'})
    })
  }

  // 루틴 만들기
  const addRoutine = function(routineList) {
    // console.log(routineList)
    axios.post(REST_API_ERMP + '/routine', routineList)
    .then(() => {
      router.push({name: 'routine'})
    })
  }

  // 루틴 리스트 가져오기
  const routineList = ref([])
  const getRoutineList = function(day, userId) {
    axios.get(REST_API_ERMP + '/routine', { params: { day: day, userId: userId }})
    .then((response) => {
      routineList.value = response.data
    })
  }

  // 루틴 삭제
  const deleteRoutine = function(logId) {
    axios.delete(REST_API_ERMP + '/routine', {data: { logId }})
    .then(() => {
      router.go()
    })
  }

  return {
    regist,
    login,
    addRoutine,
    routineList,
    getRoutineList,
    deleteRoutine,

  }
})
