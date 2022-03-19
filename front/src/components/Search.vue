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
              <!-- 预览图片 -->
              <img src="@/assets/images/blog-preview.png"
                   alt="item.title">
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
                     class="pages"
                     @current-change="change">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import Head from '@/components/Head.vue'
export default {
  name: 'Search',
  components: {
    Head
  },

  data () {
    return {
      // 博客总数目
      total: 0,
      // 当前页
      pageNo: 1,
      // 每页展示的数目
      pageSize: 10,
      color: ['', 'success', 'warning', 'danger'],
      list: [{ title: 'ss', mdContent: 'ss', id: '2', categoryNames: ['22'] }]
    };
  },

  mounted () {

  },

  methods: {

  },
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
    margin-left: 176px;
    margin-top: 60px;
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
        left: 225px;
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