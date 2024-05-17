<template>
  <div>
    <RouterView />
  </div>
  <div class="container">
    <VCalendar expanded :attributes="attributes" />
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router'
import { ref, onMounted } from 'vue'
import axios from 'axios'

const user = JSON.parse(sessionStorage.loginUser).data

const attributes = ref([
  {
    highlight: 'green',
    dates: []
  },
  {
    dot: 'red',
    dates: today.toLocaDateString('ko-KR')
  }
])

onMounted(()=>{
  const userId = user.userId;
  const params = {userId:userId};

  axios({
    url:"http://localhost:8080/ermp-api/attendance",
    method: "post",
    data: params
  })
  .then((response)=>{
    // console.log(response.data);
  })
  .then(()=>{
    axios.get("http://localhost:8080/ermp-api/attendance", {params})
    .then((response)=>{
      // console.log(response.data);
      attributes.value[0].dates=response.data;
    })
  })
})

</script>

<style scoped>

</style>
