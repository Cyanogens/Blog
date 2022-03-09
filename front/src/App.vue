<template>
  <div id="app" class="body">
    <Head></Head>
    <router-view/>
  </div>
</template>

<script>
import Head from '@/components/Head.vue'
export default {
  components: {
    Head
  }
}
</script>

<style scoped>
.body {
  position: absolute;
  height: 96%;
  width: 100%;
  background: url(@/assets/images/background-1.jpg) no-repeat;
  background-size: 100% 100%;
}
</style>