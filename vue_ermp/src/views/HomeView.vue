<template>
  <div>
    <RouterView />
  </div>
  <div class="attendance-board container text-center d-flex flex-column justify-content-around">
    <h2>출석체크 판</h2>
    <VCalendar expanded :attributes="attributes" />
    <p>매일매일 힘내보아요</p>
    <p>「 {{  advice }} 」</p>
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router'
import { ref, onMounted } from 'vue'
import axios from 'axios'

const user = JSON.parse(sessionStorage.loginUser).data
let today = new Date()

const attributes = ref([
  {
    highlight: 'green',
    dates: []
  },
  {
    highlight: 'red',
    dates: today.toLocaleDateString()
  }
])

const advice = ref("");

onMounted(()=>{
  const userId = user.userId;
  const params = {userId:userId};
  

  // 출석 체크 및 출석판 불러오기
  axios({
    //출석체크
    url:"http://localhost:8080/ermp-api/attendance",
    method: "post",
    data: params
  })
  .then(()=>{
    //출석판 불러오기
    axios.get("http://localhost:8080/ermp-api/attendance", {params})
    .then((response)=>{
      // console.log(response.data);
      attributes.value[0].dates=response.data;
    })
  })

  // 명언 불러오기
  axios.get("https://api.adviceslip.com/advice")
  .then((response)=>{
    advice.value = response.data.slip.advice;
  }
)
})

</script>

<style scoped>
  .attendance-board {
    width: 40rem;
    height: 30rem;
  }
</style>
