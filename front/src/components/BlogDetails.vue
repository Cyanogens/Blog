<template>
  <div>
    <!-- <el-empty description="暂无数据"></el-empty>
     -->
     <v-md-editor v-model="text" height="400px"></v-md-editor>
  </div>
</template>

<script>

import Vue from 'vue';
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';

VueMarkdownEditor.use(vuepressTheme);

Vue.use(VueMarkdownEditor);

export default {

    
  name: 'BlogDetails',
  data () {
      return {
          text: '', 
      }

  }
}
</script>

<style>

</style>