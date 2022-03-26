<template>
  <div class="body">

    <Head></Head>
    <!-- 博客预览列表 -->
    <div class="blog-list">
      <ol class="blog-preview-list">
        <li v-for="item in list"
            :key="item.id"
            class="blog-preview"
            :id="item.id"
            @click.stop="enterDetails($event)">
          <router-link to="/blog"
                       class="link">
            <div class="pre">
              <!--            &lt;!&ndash; 预览图片 &ndash;&gt;-->
              <!--            <img src="@/assets/images/blog-preview.png"-->
              <!--                 alt="item.title">-->
              <!-- 预览内容 -->
              <h2 class="blog-title">{{ item.title }}</h2>
              <!-- 文章预览内容 -->
              <p class="blog-article">{{ item.mdContent }}</p>
              <!-- 标签列表 -->
              <ul class="tag-list">
                <li class="tag"
                    v-for="(it, index) in item.categoryNames.slice(0, 4)"
                    :key="index">
                  <el-tag :type="color[index]">{{ it }}</el-tag>
                </li>
              </ul>
            </div>
          </router-link>
        </li>
      </ol>
      <!-- 分页 -->
      <el-pagination background
                     layout="prev, pager, next"
                     :total="total"
                     :current-page="pageNo"
                     :page-size="pageSize"
                     class="pages"
                     @current-change="change">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import Head from '@/components/Head.vue'
import bus from '@/components/eventBus.js'
import axios from 'axios'
export default {
  name: 'Search',
  components: {
    Head
  },

  data () {
    return {
      txt: '', // * 搜索关键字
      // 博客总数目
      total: 0,
      // 当前页
      pageNo: 1,
      // 每页展示的数目
      pageSize: 5,
      color: ['', 'success', 'warning', 'danger'],
      list: []
    };
  },

  methods: {
    change (e) {
      this.pageNo = e; // ! e即为当前页数
      // console.log(this.pageNo);
      this.getBlog()
    },
    async getBlog () {
      try {
        const { data: res } = await axios.get('http://localhost:8080/blog/search', {
          params: {
            pageNo: this.pageNo,
            pageSize: this.pageSize,
            key: this.txt
          }
        })
        if (res.code === 200) { // * 返回成功
          this.list = res.data.list;
          this.total = res.data.total;
          //   console.log(this.list);
        }
      } catch (error) {
        console.log(error);
      }
    },
    enterDetails (e) {
      //   console.log(e.currentTarget.id);
      const id = e.currentTarget.id;
      bus.$emit('getBlogId', id);
    }
  },
  // ? 进入博客详情页，向博客详情页传值
  created () {
    bus.$on('getSearchTxt', txt => {
      this.txt = txt;
      this.getBlog(); // * 获取博客内容
    })
  }

};
</script>

<style lang="less" scoped>
.body {
  position: absolute;
  top: 0;
  height: 100%;
  width: 100%;
  background-color: #f5f6f7;
  overflow: auto;
  .blog-preview-list {
    list-style: none;
    margin-left: 25%;
    margin-top: 80px;
    .blog-preview {
      position: relative;
      display: block;
      width: 667px;
      height: 150px;
      margin-bottom: 10px;
      // padding: 10px;
      background-color: #fff;
      .pre {
        width: 100%;
        height: 100%;
        padding: 10px;
        z-index: 9;
      }
      .link {
        display: block;
        width: 100%;
        height: 100%;
      }
      // 图片
      img {
        float: left;
        width: 200px;
        height: 100%;
      }
      // 标题
      .blog-title {
        display: inline-block;
        font-size: 18px;
        color: #333;
        margin-left: 20px;
        margin-top: 10px;
        font-family: -apple-system, "Helvetica Neue", Helvetica, Arial,
        "PingFang SC", "Hiragino Sans GB", "WenQuanYi Micro Hei",
        "Microsoft Yahei", sans-serif;
      }
      // 内容
      .blog-article {
        font-size: 14px;
        line-height: 1.7;
        color: #6b7486;
        padding: 10px 0 0 20px;
        width: 440px;
        overflow: hidden;
        text-overflow: ellipsis;
        overflow: hidden;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
      }
      // 标签
      .tag-list {
        position: absolute;
        bottom: 7px;
        left: 20px;
        .tag {
          float: left;
          margin-right: 10px;
          height: 25px;
        }
      }
      .el-tag {
        // width: 35px;
        padding: 0 5px;
        height: 20px;
        line-height: 20px;
      }
    }
    .blog-preview:hover {
      box-shadow: 0 0 5px #ccc;
    }

    .pages {
      text-align: center;
      z-index: 9;
    }
  }
}
</style>

<style scoped>
.blog-list >>> .el-pagination {
  text-align: center;
}
</style>
