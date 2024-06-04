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
    .catch(() => {
      alert("다시 한번 확인해주세요!")
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
      routineList.value.forEach(function(routine) {
        return routine.isComplete = routine.achieveAmount > 0
      })
    })
  }

  // 루틴 완료 & 성취 취소
  const sendAmount = function(routine) {
    axios.put(REST_API_ERMP + '/routine/check', routine)
    .then(() => {

    })
  }

  // 루틴 수정
  const modifyRoutine = function(routine, day, userId) {
    // console.log(routine)
    axios.put(REST_API_ERMP + '/routine', routine)
    .then(() => {
      getRoutineList(day, userId)
    })
  }

  // 루틴 삭제
  const deleteRoutine = function(logId) {
    axios.delete(REST_API_ERMP + '/routine', {data: { logId }})
    .then(() => {
      router.go()
    })
  }


  // 차트데이터 가져오기
  const chartData = ref({
    // list of dates, from startDate to endDate
    labels: ['일-7', '일-6', '일-5', '일-4', '일-3', '일-2', '일-1', '일'],
    
    datasets: [
      {
        label: '운동타입 1',
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        data: [65, 59, 80, 81, 56, 55, 40]
      },
      {
        label: '운동타입 2',
        backgroundColor: 'rgba(153, 102, 255, 0.2)',
        borderColor: 'rgba(153, 102, 255, 1)',
        borderWidth: 1,
        data: [28, 48, 40, 19, 86, 27, 90]
      }
    ]
  })
  const getChartData = function(startDate, endDate, userId) {
    // console.log(userId);
    axios.get(REST_API_ERMP + '/statistics', {params:{startDate, endDate, userId}})
    .then((response)=> {
      // console.log(response.data)
      chartData.value = response.data;
      // console.log(chartData.value)
    })
  }


  return {
    regist,
    login,
    addRoutine,
    routineList,
    getRoutineList,
    sendAmount,
    modifyRoutine,
    deleteRoutine,
    getChartData,
    chartData,

  }
})
