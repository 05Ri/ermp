<template>
  <div>
    <TheHeader />
  </div>
  <div>
    <RouterView />
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
</style>
