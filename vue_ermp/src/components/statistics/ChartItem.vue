<template>
  <div class="chart-container">
    <Bar :data="chartData" :options="chartOptions"></Bar>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';
import { Bar } from 'vue-chartjs';
import { Chart as ChartJS, registerables } from 'chart.js';

// Chart.js 컴포넌트 등록
ChartJS.register(...registerables);

// Props 정의
const props = defineProps({
  chartData: {
    type: Object,
    required: true
  },
  chartOptions: {
    type: Object,
    required: true
  }
});

</script>

<style scoped>
.chart-container {
  margin: auto;
  height: 40vh;
  width: 60vw;
}
</style>