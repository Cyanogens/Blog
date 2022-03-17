<template>
  <div class="edit-body">

    <Head></Head>

    <!-- 动态添加标签 -->
    <div class="tags">
      <el-tag :key="tag"
              v-for="tag in dynamicTags"
              closable
              :disable-transitions="false"
              @close="handleClose(tag)">
        {{tag}}
      </el-tag>
      <el-input class="input-new-tag"
                v-if="inputVisible"
                v-model="inputValue"
                ref="saveTagInput"
                size="small"
                @keyup.enter.native="handleInputConfirm"
                @blur="handleInputConfirm">
      </el-input>
      <el-button v-else
                 class="button-new-tag"
                 size="small"
                 @click="showInput">+ New Tag</el-button>

    </div>

    <!-- 博客编辑部分 -->
    <div class="edit">
      <v-md-editor v-model="text"
                   height="505px"></v-md-editor>
      <div class="commit"
           @click="pushBlog">
        <span>
          发布
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue';
import axios from 'axios'

import VMdEditor from '@kangc/v-md-editor/lib/codemirror-editor';
import '@kangc/v-md-editor/lib/style/codemirror-editor.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';

// highlightjs
import hljs from 'highlight.js';

// codemirror 编辑器的相关资源
import Codemirror from 'codemirror';
// mode
import 'codemirror/mode/markdown/markdown';
import 'codemirror/mode/javascript/javascript';
import 'codemirror/mode/css/css';
import 'codemirror/mode/htmlmixed/htmlmixed';
import 'codemirror/mode/vue/vue';
// edit
import 'codemirror/addon/edit/closebrackets';
import 'codemirror/addon/edit/closetag';
import 'codemirror/addon/edit/matchbrackets';
// placeholder
import 'codemirror/addon/display/placeholder';
// active-line
import 'codemirror/addon/selection/active-line';
// scrollbar
import 'codemirror/addon/scroll/simplescrollbars';
import 'codemirror/addon/scroll/simplescrollbars.css';
// style
import 'codemirror/lib/codemirror.css';

VMdEditor.Codemirror = Codemirror;

VMdEditor.use(githubTheme, {
  Hljs: hljs,
});

Vue.use(VMdEditor);




import Head from '@/components/Head.vue'
export default {
  name: 'EditBlog',
  components: {
    Head
  },
  data () {
    return {
      text: '',
      dynamicTags: [], // ! 标签列表
      inputVisible: false,
      inputValue: ''
    };
  },
  methods: {
    handleClose (tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
    },

    showInput () {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm () {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.dynamicTags.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = '';
    },

    // ? 发布博客
    push () {
      //   console.log(this.text);
    },

    async pushBlog () {
      try {
        const { data: res } = await axios.post('http://localhost:8080/blog/insertBlog', this.txt)
      } catch (error) {
        console.log(error);
      }
    }
  }
};
</script>

<style scoped lang="less">
.edit-body {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #f5f6f7;
  // * 博客编辑部分
  .edit {
    position: relative;
    margin-top: 120px;
    padding: 0 175px;
    .commit {
      position: absolute;
      bottom: -50px;
      left: 50%;
      margin-left: -62.5px;
      span {
        display: inline-block;
        width: 125px;
        height: 32px;
        color: #fff;
        font-size: 16px;
        line-height: 30px;
        text-align: center;
        background-color: #6766f7;
      }
    }
    .commit:hover {
      cursor: pointer;
    }
  }
  // * 标签部分
  .tags {
    position: absolute;
    top: 70px;
    left: 175px;
    padding: 0 5px;
  }

  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
}
</style>