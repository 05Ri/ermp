<template>
  <div class="container d-flex justify-content-center">
    <VDatePicker view="weekly" :attributes="attributes" :masks="masks" v-model.string="date" @click="getDateRoutineList" />
  </div>
  <div>
    <button @click="addRoutine">+ 루틴 추가</button>
  </div>

  <div v-if="store.routineList === null">
    <h3>등록된 루틴이 없습니다</h3>
  </div>
  <div v-else>
    <table>
      <tr>
        <th>나의 운동</th>
        <th>달성치/목표치</th>
        <th>완료/수정/삭제</th>
      </tr>
      <tr v-for="(routine, index) in store.routineList" :key="index">
        <!-- 운동 루틴 이름 -->
        <td>{{ routine.type }}</td>
        <td>
          <input type="number" /> / {{ routine.goalAmount }}{{ routine.unit }}
        </td>
        <td>
          <!-- 완료/달성 버튼 토글 시 수정 버튼 설정 달라지도록 -->
          <button type="button" class="btn btn-success">✔</button>

          <!-- 모달 수정 버튼 -->
          <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
            ✏
          </button>

          <!-- 삭제 버튼 -->
          <button @click="deleteRoutine(routine.logId)" type="button" class="btn btn-danger">✖</button>
        </td>
      </tr>
    </table>
  </div>

  <!-- 모달(바깥쪽을 클릭해도 닫히지 않음) -->
  <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">운동 루틴 수정</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <!-- 해당 데이터의 type, amount, unit 값 가져와서 수정할 수 있게 하기 -->
        <div class="modal-body">
          ...
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary">수정</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import router from '@/router'
import { useERMPStore } from '@/stores/ermp.js'


const store = useERMPStore()

const user = JSON.parse(sessionStorage.loginUser).data

let day = new Date()
const today = day.getFullYear() + '-' + (day.getMonth() + 1).toString().padStart(2, '0') + '-' + day.getDate().toString().padStart(2, '0')

const masks = ref({
  modelValue: 'YYYY-MM-DD',
})

const date = ref(today)

// 오늘 빨간색
const attributes = ref([
  {
    highlight: 'red',
    dates: today
  }
])

// 루틴 추가 페이지
const addRoutine = (() => {
  router.push({name: 'addRoutine'})
})

// 루틴 삭제 버튼
const deleteRoutine = (logId) => {
  store.deleteRoutine(logId)
}

// 날짜에 따른 루틴 가져오기
const getDateRoutineList = () => {
  store.getRoutineList(date.value, user.userId)
}

// 오늘의 루틴 불러오기
onMounted (() => {
  store.getRoutineList(today, user.userId)
})

</script>

<style scoped>

</style>