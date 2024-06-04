<template>
  <div class="container routine-container">
    <div class="text-center">
      <h2>나의 루틴</h2>
    </div>
    <div class="d-flex justify-content-center">
      <VDatePicker view="weekly" :attributes="attributes" :masks="masks" v-model.string="date" @click="getDateRoutineList" />
    </div>
    <div>
      <button class="add-routine btn btn-primary" @click="addRoutine">+ 루틴 추가</button>
    </div>

    <div v-if="store.routineList == ''">
      <h3>등록된 루틴이 없습니다...</h3>
    </div>
    <div v-else>
      <table class="routine-table">
        <tr>
          <th>나의 운동</th>
          <th>달성치/목표치</th>
          <th>완료/수정/삭제</th>
        </tr>
        <tr v-for="(routine, index) in store.routineList" :key="index">
          <td>{{ routine.type }}</td>
          <td>
            <input type="number" :disabled="routine.isComplete" v-model="routine.achieveAmount" /> / {{ routine.goalAmount }}{{ routine.unit }}
          </td>
          <td>
            <!-- 완료/달성 버튼 토글 시 수정 버튼 설정 달라지도록 -->
            <button
              v-if="!routine.isComplete"
              type="button"
              class="btn btn-success"
              @click="[toggleRoutine(routine), sendAmount(routine.logId)]"
            >✔</button>
            <button
              v-else
              type="button"
              class="btn btn-warning"
              @click="[toggleRoutine(routine), sendAmount(routine.logId)]"
            >🏆</button>

            <!-- 모달 수정 버튼 -->
            <button
              type="button"
              :class="['btn', routine.isComplete ? 'btn-secondary' : 'btn-info']"
              :disabled="routine.isComplete"
              data-bs-toggle="modal"
              data-bs-target="#staticBackdrop"
              @click="setCurrentRoutine(routine)"
            >✏</button>

            <!-- 삭제 버튼 -->
            <button
              @click="deleteRoutine(routine.logId)"
              type="button"
              class="btn btn-danger"
            >✖</button>
          </td>

        </tr>
      </table>
      <h5 v-if="store.routineList && isntAllComplete">이 영상은 어떠신가요? 운동 이름으로 검색해봤어요 😎</h5>
      <div class="container d-flex justify-content-around flex-sm-wrap ">
        <YoutubeVideoRecommend v-for="(routine, index) in store.routineList" :key="index"  :hidden="routine.isComplete" :keyword="routine.type" :date="date" />
      </div>
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
          <div class="modal-body text-center">
            <div class = "row align-items-center">
              <label class = "col-4" for="type">운동 이름</label>
              <input class = "col-auto" type="text" id="type" v-model="currentRoutine.type" />
            </div>
            <div class = "row align-items-center my-2">
              <label class = "col-4" for="amount">목표 횟수</label>
              <input class = "col-auto" type="number" id="amount" v-model="currentRoutine.goalAmount" />
            </div>
            <div class = "row align-items-center">
              <label class = "col-4" for="unit">단위</label>
              <select class = "col-auto select-unit" for="unit" id="unit" v-model="currentRoutine.unit">
                <option value="회">회</option>
                <option value="분">분</option>
                <option value="km">km</option>
              </select>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" @click="modifyRoutine">수정</button>
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue'
import router from '@/router'
import { useERMPStore } from '@/stores/ermp.js'
import YoutubeVideoRecommend from '@/components/routine/YoutubeVideoRecommend.vue'

const store = useERMPStore()

const user = JSON.parse(sessionStorage.loginUser).data

let day = new Date()
const today = day.getFullYear() + '-' + (day.getMonth() + 1).toString().padStart(2, '0') + '-' + day.getDate().toString().padStart(2, '0')

const masks = ref({
  modelValue: 'YYYY-MM-DD',
})

const date = ref(today)


// 오늘 날짜를 빨간색으로 표시
const attributes = ref([
  {
    highlight: 'red',
    dates: today
  }
])

// 완료 개수에 따른 cnt
const isntAllComplete = computed(()=>{
  return store.routineList.filter((routine)=>!routine.isComplete).length > 0;
})

// 루틴 추가 페이지
const addRoutine = (() => {
  router.push({name: 'addRoutine'})
})

// 루틴 완료 버튼 누르면 성취 버튼으로 바뀜
const toggleRoutine = ((routine) => {
  routine.isComplete = !routine.isComplete
  sendAmount(routine)
})

// 루틴 완료 시 보낼 값 & 취소 시 보낼 값
const sendAmount = (routine) => {
  const sendData = {
    logId: routine.logId,
    achieveAmount: routine.isComplete ? routine.achieveAmount : 0,
  }
  store.sendAmount(sendData)
}

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

// 현재 루틴 값 바인딩
const currentRoutine = ref({
  type: "",
  goalAmount: 0,
  unit: "",
})

// 수정할 루틴 객체 모달로 보낼거 저장시키기
const setCurrentRoutine = (routine) => {
  currentRoutine.value = {
    type: routine.type,
    goalAmount: routine.goalAmount,
    unit: routine.unit,
    logId: routine.logId,
    isComplete: routine.isComplete
  }
}

// 루틴 수정
const modifyRoutine = () => {
  store.modifyRoutine(currentRoutine.value, date.value, user.userId)
  document.querySelector('.btn-close').click()
}

</script>

<style scoped>
.routine-table {
  margin: 10px auto;
  text-align: center;
}

.routine-table > tr, th, td {
  border-right: 1px solid rgb(200, 200, 200);
  border-left: 1px solid rgb(200, 200, 200);
  padding-right: 3rem;
  padding-left: 3rem;
  padding-bottom: 1rem;
}

.add-routine {
  margin: 10px;
  text-align: right;
}

.select-unit, .select-unit > option , input {
  height: 2rem;
}

.routine-container {
  min-width: 755px;
}

</style>