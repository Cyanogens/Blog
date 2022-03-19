<template>
  <div id="app"
       class="body">
    <!-- <Head></Head> -->
    <router-view />
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
</style>