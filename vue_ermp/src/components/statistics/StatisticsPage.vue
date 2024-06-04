<template>
  <div class="container text-center d-flex flex-column">
    <div class="py-4">
      <input class="w-25" type="date" id="startDate" v-model="startDate" :min="today" :max="endDate" />
      <span class="mx-3">~</span>
      <input class="w-25" type="date" id="endDate" v-model="endDate" :min="startDate" />
      <input class="mx-3" type="submit" value="새 통계 불러오기" @click="getChartData()">
    </div>
    <ChartItem :chart-data="store.chartData" :chart-options="chartOptions" />
    <span class="text-end text-secondary">(가중치: 회 - 1, 분 - 5, km - 30)</span>
  </div>
</template>

<script setup>
  import { ref, onMounted } from 'vue';
  import { useERMPStore } from '@/stores/ermp';
  import ChartItem from './ChartItem.vue';

  const store = useERMPStore();
  const user = JSON.parse(sessionStorage.loginUser).data
  

  const sevenDaysAgo = new Date();
  sevenDaysAgo.setDate(sevenDaysAgo.getDate() - 7);
  const today = new Date();

  const changeDateForm = function(date) {
    const result = date.getFullYear() 
    + '-' + (date.getMonth() + 1).toString().padStart(2, '0') 
    + '-' + date.getDate().toString().padStart(2, '0');
    return result
  }
  
  const startDate = ref(changeDateForm(sevenDaysAgo));
  const endDate = ref(changeDateForm(today));

  const chartOptions = ref({
    responsive: true,
    maintainAspectRatio: false,
  });


  onMounted(()=>{
    // console.log(user);
    store.getChartData(startDate.value, endDate.value, user.userId);
  })

  const getChartData = () =>{
    store.getChartData(startDate.value, endDate.value, user.userId);
  }
  
</script>

<style scoped>

</style>