<template>
  <div class="body">
    <Head></Head>
  </div>
</template>

<script>
import Head from '@/components/Head.vue'
export default {
  name: 'Index',
  components: {
    Head
  }
}
</script>

<style scoped>
.body {
  position: absolute;
  height: 100%;
  width: 100%;
  background: url(@/assets/images/background-1.jpg) no-repeat;
  background-size: 100% 100%;
}
</style>