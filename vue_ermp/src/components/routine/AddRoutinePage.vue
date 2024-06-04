<template>
  <div>
    <h2>루틴 추가</h2>
  </div>

  <div>
    <table class="text-center">
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
          <select id="unit" v-model="routine.unit" class="select-unit">
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
          <div class="d-flex">
            <input class="period-input" type="number" id="period" v-model="routine.period" value="1" min='1' />
            <span>일</span>
          </div>
        </td>
        <td>
          <button class="btn btn-sm btn-outline-danger" @click="deleteRow(index)">✖</button>
        </td>
      </tr>
    </table>

    <div>
      <button class="btn btn-sm btn-outline-success m-1" @click="addRow">+ 행 추가</button>
    </div>
  </div>
  <div class="d-flex justify-content-end">
    <button class="btn btn-sm btn-warning" @click="getGPTList" :disabled = "gptLoadDisable">AI 추천 리스트 불러오기</button>
    <button class="btn btn-sm btn-primary mx-1" @click="addRoutine">루틴 등록</button>
    <button class="btn btn-sm btn-secondary" @click="goBack">돌아가기</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
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

// 오늘 날짜
const day = new Date()
const today = changeDateForm(day)

// n일뒤 날짜 구하기
const nDaysAfter = function(n) {
  const day = new Date();
  day.setDate(day.getDate() + n);
  return day;
}

// 유저 정보
const user = JSON.parse(sessionStorage.getItem("loginUser")).data;

// 루틴 리스트
const routineList = ref([{
  userId: user.userId,
  type: "",
  goalAmount: 1,
  unit: "select",
  startDate: changeDateForm(new Date()),
  endDate: changeDateForm(new Date()),
  period: 1
}])

// 루틴 추가
const addRoutine = () => {
  if (checkList()) {
    store.addRoutine(routineList.value)
  } else {
    alert(msg.value)
  }
}

// 루틴값들이 제대로 입력되었는지 확인하는 메소드
const checkList = function() {
  console.log(routineList.value);
  for(let i = 0; i<routineList.value.length; i++) {
    const routine = routineList.value[i];
    console.log(routine);
    if (routine.type === "") {
      msg.value = "운동 이름을 입력해주세요"
      return false;
    }
    if (routine.goalAmount < 1) {
      msg.value = "목표 횟수를 1 이상 입력해주세요"
      return false
    }
    if (!routine.unit || routine.unit === 'select') {
      msg.value = "단위를 선택해주세요"
      return false
    }
    if (routine.period < 1) {
      msg.value = "주기를 1회 이상 입력해주세요"
      return false
    }
  }
  return true  
}


// 루틴 행 추가 메소드
const addRow = () => {
  routineList.value.push({
  userId: user.userId,
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
    routineList.value.splice(index, 1);
  }
}

// 돌아가기 버튼 메소드
const goBack = () => {
  router.go(-1)
}

//gpt 추천 운동 리스트
const gptList = [{
  userId: user.userId,
  type: "달리기",
  goalAmount: 30,
  unit: "분",
  startDate: changeDateForm(nDaysAfter(1)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "실내사이클",
  goalAmount: 30,
  unit: "분",
  startDate: changeDateForm(nDaysAfter(1)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "벤치프레스",
  goalAmount: 30,
  unit: "회",
  startDate: changeDateForm(nDaysAfter(2)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "숄더프레스",
  goalAmount: 30,
  unit: "회",
  startDate: changeDateForm(nDaysAfter(2)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "랫풀다운",
  goalAmount: 30,
  unit: "회",
  startDate: changeDateForm(nDaysAfter(2)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "푸시업",
  goalAmount: 30,
  unit: "회",
  startDate: changeDateForm(nDaysAfter(2)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "수영",
  goalAmount: 30,
  unit: "분",
  startDate: changeDateForm(nDaysAfter(3)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "파워워킹",
  goalAmount: 60,
  unit: "분",
  startDate: changeDateForm(nDaysAfter(3)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "스쿼트",
  goalAmount: 30,
  unit: "회",
  startDate: changeDateForm(nDaysAfter(4)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "데드리프트",
  goalAmount: 30,
  unit: "회",
  startDate: changeDateForm(nDaysAfter(4)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "레그프레스",
  goalAmount: 30,
  unit: "회",
  startDate: changeDateForm(nDaysAfter(4)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "레그컬",
  goalAmount: 30,
  unit: "회",
  startDate: changeDateForm(nDaysAfter(4)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "요가",
  goalAmount: 60,
  unit: "분",
  startDate: changeDateForm(nDaysAfter(5)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "줄넘기",
  goalAmount: 30,
  unit: "분",
  startDate: changeDateForm(nDaysAfter(5)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "풀업",
  goalAmount: 30,
  unit: "회",
  startDate: changeDateForm(nDaysAfter(6)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "푸시업",
  goalAmount: 30,
  unit: "회",
  startDate: changeDateForm(nDaysAfter(6)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "스쿼트",
  goalAmount: 30,
  unit: "회",
  startDate: changeDateForm(nDaysAfter(6)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
{
  userId: user.userId,
  type: "데드리프트",
  goalAmount: 30,
  unit: "회",
  startDate: changeDateForm(nDaysAfter(6)),
  endDate: changeDateForm(nDaysAfter(28)),
  period: 7
},
]

const gptLoadDisable = ref(false);

const getGPTList = ()=> {
  routineList.value = gptList;
  gptLoadDisable.value = true;
}



</script>

<style scoped>
.select-unit, .select-unit > option , input {
  height: 2rem;
}

</style>