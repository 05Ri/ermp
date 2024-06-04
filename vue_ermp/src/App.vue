<template>
  <div>
    <TheHeader />
  </div>
  <div class="background">
    <div class="content">
      <RouterView />
    </div>
  </div>
</template>

<script setup>
import TheHeader from '@/components/common/TheHeader.vue'
import { RouterView } from 'vue-router'

</script>


<style>
@font-face {
  font-family: 'SKYBORI';
  src: url(assets/font/SKYBORI.ttf);
  font-weight: 400;
}

* {
  font-family: 'SKYBORI';
}

a {
  text-decoration: none;
  color:black;
}

.background {
  background: url('@/assets/img/background/bg_3.jpg') no-repeat center center fixed;
  background-size: cover;
  width: 100vw;
  min-height: 85.5vh;
  height: auto;
  display: flex;
  align-items: center;
  justify-content: center;
}

.content {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 10px;
  margin: auto 10rem;
  margin-top: 10rem;
}
</style>
