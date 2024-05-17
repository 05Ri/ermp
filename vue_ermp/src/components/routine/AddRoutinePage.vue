<template>
  <div>
    <h2>루틴 추가</h2>
  </div>

  <div>
    <table>
      <tr>
        <th>운동 이름</th>
        <th>목표 횟수</th>
        <th>단위</th>
        <th>시작 날짜</th>
        <th>종료 날짜</th>
        <th>주기</th>
      </tr>
      <tr v-for="(routine, index) in routineList" :key="index">
        <td>
          <input type="text" id="type" v-model="routine.type" />
        </td>
        <td>
          <input type="number" id="goalAmount" v-model="routine.goalAmount" value="1" />
        </td>
        <td>
          <select id="unit" v-model="routine.unit">
            <option value="select">단위를 선택하세요.</option>
            <option value="회">회</option>
            <option value="분">분</option>
            <option value="km">km</option>
          </select>
        </td>
        <td>
          <input type="date" id="startDate" v-model="routine.startDate" :min="today" :max="routine.endDate" />
        </td>
        <td>
          <input type="date" id="endDate" v-model="routine.endDate" :min="routine.startDate" />
        </td>
        <td>
          <input type="number" id="period" v-model="routine.period" value="1" min='1' />
          <span>일</span>
        </td>
        <td>
          <button @click="deleteRow(index)">루틴 삭제</button>
        </td>
      </tr>
    </table>

    <div>
      <button @click="addRow">+ 루틴 추가</button>
    </div>
  </div>

  <div>
    <button @click="addRoutine">루틴 등록</button>
    <button @click="goBack">돌아가기</button>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useERMPStore } from '@/stores/ermp.js'
import router from '@/router'

// yyyy-mm-dd 형식으로 바꿔주는 메소드
const changeDateForm = function(date) {
  const result = date.getFullYear() + '-' + (date.getMonth() + 1).toString().padStart(2, '0') + '-' + date.getDate().toString().padStart(2, '0')
  return result
}

// 출력할 메세지
const msg = ref("")

// 피니아 저장소
const store = useERMPStore()

// 오늘 날짜 구하기
const day = new Date()
const today = changeDateForm(day)

// 루틴 객체
const routine = ref({
  userId: JSON.parse(sessionStorage.getItem("loginUser")).data.userId,
  type: "",
  goalAmount: 1,
  unit: "select",
  startDate: changeDateForm(new Date()),
  endDate: changeDateForm(new Date()),
  period: 1
})

// 루틴 리스트
const routineList = ref([routine.value])

// 루틴 추가
const addRoutine = () => {
  if (checkOne()) {
    store.addRoutine(routineList.value)
  } else {
    alert(msg.value)
  }
}

// 루틴값들이 제대로 입력되었는지 확인하는 메소드
const checkOne = function() {
  if (routine.value.type === "") {
    msg.value = "운동 이름을 입력해주세요"
    return false
  }
  if (routine.value.goalAmount < 1) {
    msg.value = "목표 횟수를 1 이상 입력해주세요"
    return false
  }
  if (!routine.value.unit || routine.value.unit === 'select') {
    msg.value = "단위를 선택해주세요"
    return false
  }
  if (routine.value.period < 1) {
    msg.value = "주기를 1회 이상 입력해주세요"
    return false
  }

  return true
}

// 루틴 행 추가 메소드
const addRow = () => {
  routineList.value.push({
  userId: JSON.parse(sessionStorage.getItem("loginUser")).data.userId,
  type: "",
  goalAmount: 1,
  unit: "select",
  startDate: changeDateForm(new Date()),
  endDate: changeDateForm(new Date()),
  period: 1
})
}

// 루틴 행 제거 메소드
const deleteRow = (index) => {
  // console.log(routineList.value.length)
  if (routineList.value.length <= 1) {
    alert("행이 하나는 있어야 합니다!")
  } else {
    routineList.value.pop(index)
  }
}

// 돌아가기 버튼 메소드
const goBack = () => {
  router.go(-1)
}

</script>

<style scoped>

</style>