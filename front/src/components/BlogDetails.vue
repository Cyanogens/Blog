<template>
  <div>
    <!-- <el-empty description="暂无数据"></el-empty>
     -->
    <v-md-preview :text="markdown"></v-md-preview>
  </div>
</template>

<script>
import Vue from 'vue'
// main.js
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
// 引入你所使用的主题 此处以 github 主题为例
import githubTheme from '@kangc/v-md-editor/lib/theme/github';
import '@kangc/v-md-editor/lib/theme/style/github.css';

// highlightjs
import hljs from 'highlight.js';

import axios from 'axios'
import bus from '@/components/eventBus.js'


VMdPreview.use(githubTheme, {
  Hljs: hljs,
});
Vue.use(VMdPreview);



export default {


  name: 'BlogDetails',
  data () {
    return {
      id: 1,
      markdown: '## Blog'
    }

  },

  methods: {
    async getBlog () {
      try {
        const { data: res } = await axios.get('http://localhost:8080/blog/' + this.id + '')
        if (res.code === 200) {
          this.markdown = res.data
        }
      } catch (error) {
        console.log(error);
      }
    },
  },
  created () {
    bus.$on('getBlogId', id => {
      this.id = id;
      //   console.log(id);
      getBlog(); // * 获取博客内容
    })
  }
}
</script>

<style>
</style>