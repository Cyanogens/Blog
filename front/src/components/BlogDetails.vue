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

    <!-- 评论区 -->
    <!-- <div class="comment">
      <div v-clickoutside="hideReplyBtn"
           @click="inputFocus"
           class="my-reply">
        <el-avatar class="header-img"
                   :size="40"
                   :src="myHeader"></el-avatar>
        <div class="reply-info">
          <div tabindex="0"
               contenteditable="true"
               id="replyInput"
               spellcheck="false"
               placeholder="输入评论..."
               class="reply-input"
               @focus="showReplyBtn"
               @input="onDivInput($event)">
          </div>
        </div>
        <div class="reply-btn-box"
             v-show="btnShow">
          <el-button class="reply-btn"
                     size="medium"
                     @click="sendComment"
                     type="primary">发表评论</el-button>
        </div>
      </div>
      <div v-for="(item,i) in comments"
           :key="i"
           class="author-title reply-father">
        <el-avatar class="header-img"
                   :size="40"
                   :src="item.headImg"></el-avatar>
        <div class="author-info">
          <span class="author-name">{{item.name}}</span>
          <span class="author-time">{{item.time}}</span>
        </div>
        <div class="icon-btn">
          <span @click="showReplyInput(i,item.name,item.id)"><i class="iconfont el-icon-s-comment"></i>{{item.commentNum}}</span>
          <i class="iconfont el-icon-caret-top"></i>{{item.like}}
        </div>
        <div class="talk-box">
          <p>
            <span class="reply">{{item.comment}}</span>
          </p>
        </div>
        <div class="reply-box">
          <div v-for="(reply,j) in item.reply"
               :key="j"
               class="author-title">
            <el-avatar class="header-img"
                       :size="40"
                       :src="reply.fromHeadImg"></el-avatar>
            <div class="author-info">
              <span class="author-name">{{reply.from}}</span>
              <span class="author-time">{{reply.time}}</span>
            </div>
            <div class="icon-btn">
              <span @click="showReplyInput(i,reply.from,reply.id)"><i class="iconfont el-icon-s-comment"></i>{{reply.commentNum}}</span>
              <i class="iconfont el-icon-caret-top"></i>{{reply.like}}
            </div>
            <div class="talk-box">
              <p>
                <span>回复 {{reply.to}}:</span>
                <span class="reply">{{reply.comment}}</span>
              </p>
            </div>
            <div class="reply-box">

            </div>
          </div>
        </div>
        <div v-show="_inputShow(i)"
             class="my-reply my-comment-reply">
          <el-avatar class="header-img"
                     :size="40"
                     :src="myHeader"></el-avatar>
          <div class="reply-info">
            <div tabindex="0"
                 contenteditable="true"
                 spellcheck="false"
                 placeholder="输入评论..."
                 @input="onDivInput($event)"
                 class="reply-input reply-comment-input"></div>
          </div>
          <div class=" reply-btn-box">
            <el-button class="reply-btn"
                       size="medium"
                       @click="sendCommentReply(i,j)"
                       type="primary">回复评论</el-button>
          </div>
        </div>
      </div>
    </div> -->
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




const clickoutside = {
  // 初始化指令
  bind (el, binding, vnode) {
    function documentHandler (e) {
      // 这里判断点击的元素是否是本身，是本身，则返回
      if (el.contains(e.target)) {
        return false;
      }
      // 判断指令中是否绑定了函数
      if (binding.expression) {
        // 如果绑定了函数 则调用那个函数，此处binding.value就是handleClose方法
        binding.value(e);
      }
    }
    // 给当前元素绑定个私有变量，方便在unbind中可以解除事件监听
    el.vueClickOutside = documentHandler;
    document.addEventListener('click', documentHandler);
  },
  update () { },
  unbind (el, binding) {
    // 解除事件监听
    document.removeEventListener('click', el.vueClickOutside);
    delete el.vueClickOutside;
  },
};


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

      btnShow: false,
      index: '0',
      replyComment: '',
      myName: 'Lana Del Rey',
      myHeader: 'https://ae01.alicdn.com/kf/Hd60a3f7c06fd47ae85624badd32ce54dv.jpg',
      myId: 19870621,
      to: '',
      toId: -1,
      comments: [
        {
          name: 'Lana Del Rey',
          id: 19870621,
          headImg: 'https://ae01.alicdn.com/kf/Hd60a3f7c06fd47ae85624badd32ce54dv.jpg',
          comment: '我发布一张新专辑Norman Fucking Rockwell,大家快来听啊',
          time: '2019年9月16日 18:43',
          commentNum: 2,
          like: 15,
          inputShow: false,
          reply: [
            {
              from: 'Taylor Swift',
              fromId: 19891221,
              fromHeadImg: 'https://ae01.alicdn.com/kf/H94c78935ffa64e7e977544d19ecebf06L.jpg',
              to: 'Lana Del Rey',
              toId: 19870621,
              comment: '我很喜欢你的新专辑！！',
              time: '2019年9月16日 18:43',
              commentNum: 1,
              like: 15,
              inputShow: false
            },
            {
              from: 'Ariana Grande',
              fromId: 1123,
              fromHeadImg: 'https://ae01.alicdn.com/kf/Hf6c0b4a7428b4edf866a9fbab75568e6U.jpg',
              to: 'Lana Del Rey',
              toId: 19870621,
              comment: '别忘记宣传我们的合作单曲啊',
              time: '2019年9月16日 18:43',
              commentNum: 0,
              like: 5,
              inputShow: false

            }
          ]
        },
      ]
    }
  },


  directives: { clickoutside },
  created () {
    bus.$on('getBlogId', id => {
      this.id = id;
      //   console.log(id);
      this.viewAdd(); // * 浏览量增加
      this.getBlog(); // * 获取博客内容
    })
    window.onbeforeunload = function () {
      console.log(22);
    }
  },

  methods: {
    async getBlog () {
      try {
        const { data: res } = await axios.get('http://localhost:8080/blog/' + this.id + '')
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
        const { data: res } = axios.post('http://localhost:8080/blog/changeView?blogId=' + this.id)
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
      if (this.isLiked == false) {
        this.likeCount--; // * 未点赞，点赞数减一
        this.starName = '#icon-star'
      } else {
        this.likeCount++; // * 点赞，点赞数加一
        this.starName = '#icon-star2'
      }

    },


    // inputFocus () {
    //   var replyInput = document.getElementById('replyInput');
    //   replyInput.style.padding = "8px 8px"
    //   replyInput.style.border = "2px solid blue"
    //   replyInput.focus()
    // },
    // showReplyBtn () {
    //   this.btnShow = true
    // },
    // hideReplyBtn () {
    //   this.btnShow = false
    //   replyInput.style.padding = "10px"
    //   replyInput.style.border = "none"
    // },
    // showReplyInput (i, name, id) {
    //   this.comments[this.index].inputShow = false
    //   this.index = i
    //   this.comments[i].inputShow = true
    //   this.to = name
    //   this.toId = id
    // },
    // _inputShow (i) {
    //   return this.comments[i].inputShow
    // },
    // sendComment () {
    //   if (!this.replyComment) {
    //     this.$message({
    //       showClose: true,
    //       type: 'warning',
    //       message: '评论不能为空'
    //     })
    //   } else {
    //     let a = {}
    //     let input = document.getElementById('replyInput')
    //     let timeNow = new Date().getTime();
    //     let time = this.dateStr(timeNow);
    //     a.name = this.myName
    //     a.comment = this.replyComment
    //     a.headImg = this.myHeader
    //     a.time = time
    //     a.commentNum = 0
    //     a.like = 0
    //     this.comments.push(a)
    //     this.replyComment = ''
    //     input.innerHTML = '';

    //   }
    // },
    // sendCommentReply (i, j) {
    //   if (!this.replyComment) {
    //     this.$message({
    //       showClose: true,
    //       type: 'warning',
    //       message: '评论不能为空'
    //     })
    //   } else {
    //     let a = {}
    //     let timeNow = new Date().getTime();
    //     let time = this.dateStr(timeNow);
    //     a.from = this.myName
    //     a.to = this.to
    //     a.fromHeadImg = this.myHeader
    //     a.comment = this.replyComment
    //     a.time = time
    //     a.commentNum = 0
    //     a.like = 0
    //     this.comments[i].reply.push(a)
    //     this.replyComment = ''
    //     document.getElementsByClassName("reply-comment-input")[i].innerHTML = ""
    //   }
    // },
    // onDivInput: function (e) {
    //   this.replyComment = e.target.innerHTML;
    // },
    // dateStr (date) {
    //   //获取js 时间戳
    //   var time = new Date().getTime();
    //   //去掉 js 时间戳后三位，与php 时间戳保持一致
    //   time = parseInt((time - date) / 1000);
    //   //存储转换值 
    //   var s;
    //   if (time < 60 * 10) {//十分钟内
    //     return '刚刚';
    //   } else if ((time < 60 * 60) && (time >= 60 * 10)) {
    //     //超过十分钟少于1小时
    //     s = Math.floor(time / 60);
    //     return s + "分钟前";
    //   } else if ((time < 60 * 60 * 24) && (time >= 60 * 60)) {
    //     //超过1小时少于24小时
    //     s = Math.floor(time / 60 / 60);
    //     return s + "小时前";
    //   } else if ((time < 60 * 60 * 24 * 30) && (time >= 60 * 60 * 24)) {
    //     //超过1天少于30天内
    //     s = Math.floor(time / 60 / 60 / 24);
    //     return s + "天前";
    //   } else {
    //     //超过30天ddd
    //     var date = new Date(parseInt(date));
    //     return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate();
    //   }
    // }
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
      margin-right: 28px;
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
  top: 13px;
  right: 25px;
  background-color: #e5e7e9;
  color: #2a2935;
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