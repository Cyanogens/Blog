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
  },
  created() {
      localStorage['token'] = 'F'
  }
}
</script>

<style scoped>
</style>