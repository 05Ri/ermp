<template>
  <div v-if="videoURL">
      <iframe
        width="300"
        height="200"
        :src="videoURL"
        title="YouTube video player"
        frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        referrerpolicy="strict-origin-when-cross-origin"
        allowfullscreen
      />
  </div>
</template>

<script setup>
import { ref, onMounted, defineProps, watch } from 'vue'
import axios from 'axios'

const props = defineProps({
  keyword: String,
  date: String
})

const videoURL = ref("")

// 유튜브 비디오 가져오기
const videos = ref([])
const youtubeSearch = function(keyword) {
  // console.log(keyword)
  const URL = 'https://www.googleapis.com/youtube/v3/search'
  const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY
  
  axios({
    url: URL,
    method: 'GET',
    params: {
      key: API_KEY,
      part: 'snippet',
      maxResults: 20,
      q: keyword,
      type: 'video'
    },
  })
  .then((response) => {
    console.log(response.data)
    videos.value = response.data.items
    const randomVideo = videos.value[Math.floor(Math.random() * videos.value.length)]
    videoURL.value = `https://www.youtube.com/embed/${randomVideo.id.videoId}`
  })
  .catch((e) => {
    console.log(e)
  })
}

const updateVideoURL = () => {
  youtubeSearch(props.keyword)
}

onMounted(() => {
  updateVideoURL()
})

watch(() => props.keyword, updateVideoURL)

</script>

<style scoped>

</style>