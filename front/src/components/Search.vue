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
}
</style>