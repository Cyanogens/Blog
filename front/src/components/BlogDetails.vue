<template>
  <div class="body">
    <!-- <el-empty description="暂无数据"></el-empty>
     -->

    <Head></Head>
    <div class="blog-view">
      <!-- 博客标题 -->
      <h2 class="blog-title">{{ blog.title}}</h2>
      <ul class="blog-tags">
        <li>
          <svg class="icon"
               aria-hidden="true">
            <use xlink:href="#icon-riqi"></use>
          </svg>
          {{ date }}
        </li>
        <li>
          <svg class="icon"
               aria-hidden="true">
            <use xlink:href="#icon-gaojian-zuozhe"></use>
          </svg>
          {{ author }}
        </li>
      </ul>

      <!-- 博客浏览量和点赞量 -->
      <ul class="blog-stars">
        <li>
          <el-badge :value="likeCount"
                    class="item star"
                    type="primary">
            <svg class="icon icon-star"
                 aria-hidden="true"
                 @click="star">
              <use :xlink:href="starName"></use>
            </svg>
            <span>Star</span>
          </el-badge>
        </li>
        <li>
          <el-badge :value="pageView"
                    class="item watch"
                    type="primary">
            <svg class="icon icon-star"
                 aria-hidden="true">
              <use xlink:href="#icon-yanjing_xianshi_o"></use>
            </svg>
            <span>Watch</span>
          </el-badge>
        </li>
      </ul>

      <!-- 博客内容 -->
      <v-md-preview :text="markdown"
                    class="blog-contnet">
      </v-md-preview>

    </div>
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

import Head from '@/components/Head.vue';
import axios from 'axios'
import bus from '@/components/eventBus.js'


VMdPreview.use(githubTheme, {
  Hljs: hljs,
});
Vue.use(VMdPreview);


export default {
  name: 'BlogDetails',
  components: {
    Head
  },
  data () {
    return {
      date: '', // * 发布时间
      author: '', // * 作者
      id: 1, // * 博客id
      pageView: 0, // * 浏览量
      isLiked: false, // * 是否点赞
      likeCount: 0, // * 点赞数
      categoryNames: [], // * 标签数组
      markdown: '',
      blog: {}, // * 博客对象
      starName: '#icon-star', // * 星星图标
    }
  },

  created () {
    bus.$off('getBlogId');
    bus.$on('getBlogId', id => {
      this.id = id;
      //   this.pageView = val.count;
      sessionStorage['blogId'] = id;
      sessionStorage['flag'] = 'F';
      //   console.log('拿到id了');
      //   console.log(val.count);
    });
    // ? 刷新页面重新获取数据
    window.onload = e => {
      this.id = sessionStorage['blogId'];
    }
  },
  watch: {
    id: function () {
      console.log('id变化了');
      this.getBlog();
      if (sessionStorage['flag'] === 'F') { // * flag 表示访问量只加载(调用接口）一次
        this.viewAdd();
        sessionStorage['flag'] = 'T';
      }
    }
  },
  destroyed () {
    sessionStorage['flag'] = 'F'
  },

  methods: {
    async getBlog () {
      try {
        let num = sessionStorage['blogId'];
        const { data: res } = await axios.get('http://localhost:8080/blog/' + num)
        if (res.code === 200) {
          this.markdown = res.data.mdContent;
          this.blog = res.data;
          this.id = res.data.id;
          this.title = res.data.title;
          this.author = res.data.userName;
          this.date = res.data.publishData;
          this.likeCount = res.data.likeCount;
          this.pageView = res.data.pageView;
          this.isLiked = res.data.isLiked;
        }
      } catch (error) {
        console.log(error);
      }
    },

    // ? 浏览量增加
    async viewAdd () {
      try {
        let num = sessionStorage['blogId'];
        console.log('浏览量增加');
        const { data: res } = await axios.post('http://localhost:8080/blog/changeView?blogId=' + num);
        if (res.code === 200) {
          this.pageView = res.data;
        }
      } catch (error) {
        console.log(error);
      }
    },

    // ? 点赞
    star () {
      this.isLiked = !this.isLiked; // * 点击星星，更改状态
      if (this.isLiked === false) {
        this.likeCount--; // * 未点赞，点赞数减一
        this.starName = '#icon-star'
      } else {
        this.likeCount++; // * 点赞，点赞数加一
        this.starName = '#icon-star2'
      }

    },
  },

}
</script>

<style scoped lang="less">
.body {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #f5f6f7;
}
.blog-view {
  position: absolute;
  top: 100px;
  //   padding: 0 175px;
  left: 175px;
  width: 77%;
  box-shadow: 0 0 5px #ccc;
  //   height: 100%;
  //   background-color: pink;

  .blog-title {
    text-align: center;
    font-size: 28px;
    font-weight: normal;
    margin-top: 30px;
    // background-color: skyblue;
  }

  .blog-tags {
    list-style: none;
    position: absolute;
    left: calc(50% - 100px);
    margin-top: 20px;
    // background-color: skyblue;
    li {
      float: left;
      margin-right: 10px;
      padding: 0 5px;
      font-size: 14px;
    }
  }

  .blog-stars {
    list-style: none;
    position: absolute;
    right: 20px;
    top: 20px;
    li {
      float: right;
      padding: 0 5px;
      margin-left: 20px;
      border: 1px solid #ccc;
      border-radius: 10px;
      .el-badge {
        width: 90px;
        height: 25px;
      }
      .star {
        width: 90px;
      }
      .watch {
        width: 110px;
        display: flex;
        flex-wrap: nowrap;
        justify-content: center;
        align-items: center;
      }
    }
    .icon-star {
      width: 20px;
      height: 25px;
      line-height: 25px;
    }
    span {
      float: right;
      font-size: 12px;
      font-weight: 700;
      height: 25px;
      line-height: 25px;
      padding: 0 5px;
    //   margin-right: 28px;
    }
  }
}
.comment {
  position: absolute;
  left: 175px;
  top: 381px;
  //   margin-top: 50px;
  width: 77%;
  //   padding: 0 175px;
  box-shadow: 0 0 5px #ccc;
}
</style>

<style scoped>
.blog-view >>> .v-md-editor-preview {
  /* background-color: skyblue; */
  margin-top: 55px;
}

.blog-view >>> .el-badge__content--primary {
  /* top: 13px; */
  /* right: 25px; */
  background-color: #e5e7e9;
  color: #2a2935;
}

.blog-view >>> .el-badge__content.is-fixed {
    transform: none;
    position: inherit;
    right: 0;
    top: 0;
}
</style>


<style lang="less" scoped>
.my-reply {
  padding: 10px 10px;
  background-color: #fafbfc;

  .header-img {
    display: inline-block;
    vertical-align: top;
  }

  .reply-info {
    display: inline-block;
    margin-left: 5px;
    width: 90%;

    @media screen and (max-width: 1200px) {
      width: 80%;
    }

    .reply-input {
      min-height: 20px;
      line-height: 22px;
      padding: 10px 10px;
      color: #ccc;
      background-color: #fff;
      border-radius: 5px;

      &:empty:before {
        content: attr(placeholder);
      }

      &:focus:before {
        content: none;
      }

      &:focus {
        padding: 8px 8px;
        border: 2px solid blue;
        box-shadow: none;
        outline: none;
      }
    }
  }

  .reply-btn-box {
    height: 25px;
    margin: 10px 0;

    .reply-btn {
      position: relative;
      float: right;
      margin-right: 15px;
    }
  }
}

.my-comment-reply {
  margin-left: 50px;

  .reply-input {
    width: flex;
  }
}

.author-title:not(:last-child) {
  border-bottom: 1px solid rgba(178, 186, 194, 0.3);
}

.author-title {
  padding: 10px;

  .header-img {
    display: inline-block;
    vertical-align: top;
  }

  .author-info {
    display: inline-block;
    margin-left: 5px;
    width: 60%;
    height: 40px;
    line-height: 20px;

    > span {
      display: block;
      cursor: pointer;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }

    .author-name {
      color: #000;
      font-size: 18px;
      font-weight: bold;
    }

    .author-time {
      font-size: 14px;
    }
  }

  .icon-btn {
    width: 30%;
    padding: 0 !important;
    float: right;

    @media screen and (max-width: 1200px) {
      width: 20%;
      padding: 7px;
    }

    > span {
      cursor: pointer;
    }

    .iconfont {
      margin: 0 5px;
    }
  }

  .talk-box {
    margin: 0 50px;

    > p {
      margin: 0;
    }

    .reply {
      font-size: 16px;
      color: #000;
    }
  }

  .reply-box {
    margin: 10px 0 0 50px;
    background-color: #efefef;
  }
}
</style>
