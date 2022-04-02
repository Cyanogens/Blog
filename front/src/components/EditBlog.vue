<template>
  <div class="edit-body">

    <Head></Head>

    <!-- 标题 -->

    <div class="title">
      <span class="title-txt">
        标题
      </span>
      <el-input v-model="title"
                placeholder="请输入标题"
                class="title-ipt">
      </el-input>

    </div>
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

    <!-- 博客编辑部分
    <div class="edit">
      <v-md-editor v-model="text"
                   height="505px"
                   :disabled-menus="[]"
                   @upload-image="handleUploadImage"></v-md-editor> -->
    <div class="edit">
      <!-- style="height: 500px;" -->
      <mavon-editor style="height: 100%;width: 100%;"
                    ref=md
                    @imgAdd="imgAdd"
                    @imgDel="imgDel"
                    v-model="text"
                    :ishljs="true" />

      <div class="commit"
           @click="push">
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
import bus from '@/components/eventBus.js'

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
      imgUrlFromServer: '#',
      base64: '', // ! 图片base64字符串
      text: '',
      title: '',
      dynamicTags: [], // ! 标签列表
      inputVisible: false,
      inputValue: '',
      blog: { // ! 博客内容
        mdContent: '', // * 博客内容--Markdown格式
        htmlContent: '', // * 博客内容--HTML格式
        categoryNames: [], // * 标签数组
        userName: '', // * 用户名--未完成登录等功能，默认值
        // publishData: '', // * 发布日期
        title: '', // * 博客标题
        summary: '',

      },
    };
  },
  mounted() {
    //   window.onbeforeunload = () =>{
    //       sessionStorage['blog-title'] = this.blog.title;
    //       sessionStorage['blog-categoryNames'] = this.blog.categoryNames;
    //       sessionStorage['blog-mdContent'] = this.blog.mdContent;
    //       return null
    //   }
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
      this.blog.categoryNames = this.dynamicTags;
      this.blog.mdContent = this.text;
      // * 将Markdown格式转换为html格式
      let MarkdownIt = require('markdown-it'), md = new MarkdownIt();
      let result = md.render(this.text);
      this.blog.htmlContent = result;
      this.blog.userName = localStorage['username'];
      this.blog.title = this.title;
      this.pushBlog();
        console.log(this.blog);
    },

    async pushBlog () {
      try {
        if (this.blog.title === '' || this.blog.categoryNames.length === 0 || this.text === '') {
          this.$message({
            showClose: true,
            message: '必须输入标题，标签，内容才能发布博客哦~',
            type: 'error'
          });
        }
        else {
          const { data: res } = await axios.post('http://localhost:8080/blog/insertBlog', {
            userName: this.blog.userName,
            categoryNames: this.blog.categoryNames,
            mdContent: this.text,
            title: this.blog.title,
            htmlContent: this.blog.htmlContent
          })
          // console.log(this.blog);
          if (res.code === 200) {
            bus.$emit('getBlogId', res.data); // * 发送博客id
            // ! 跳转路由
            this.$router.push('/blog')
          } else {
            if (res.code === 2001) {
              this.$message.error('请先完成登录才能发布博客哦~')
            }
          }
        }
        // console.log(this.blog);
      } catch (error) {
        console.log(error);
      }
    },

    async imgAdd (pos, $file) {

      var _this = this;
      // 第一步.将图片上传到服务器.
      var formdata = new FormData();
      formdata.append('image', $file);
      this.uploadFileRequest("http://localhost:8080/uploadImg", formdata).then(resp => {
        var json = resp.data;
        if (json.code === 200) {
          //            _this.$refs.md.$imgUpdateByUrl(pos, json.msg)
          _this.$refs.md.$imglst2Url([[pos, json.data]])
        } else {
          _this.$message({ type: json.code, message: json.msg });
        }
      });
    },

    uploadFileRequest (url, params) {
      return axios({
        method: 'post',
        url: `${url}`,
        data: params,
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
    },
    imgDel (pos) {

    },

    handleRemove (file) {
      console.log(file);
    },
    handlePictureCardPreview (file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleDownload (file) {
      console.log(file);
    },
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
    margin-top: 140px;
    padding: 0 175px;
    z-index: 9;
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
    top: 90px;
    left: 550px;
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

  // * 标题部分
  .title {
    position: absolute;
    top: 80px;
    left: 165px;
    height: 50px;
    line-height: 50px;
    span {
      display: inline-block;
      padding: 0 10px;
      font-size: 14px;
    }
    .title-ipt {
      position: absolute;
      left: 50px;
      width: 310px;
    }
  }
}
</style>

<style scoped>
.title >>> .el-input__inner {
  display: inline-block;
}
</style>
