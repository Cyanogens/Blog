<template>
  <div class="body">
    <!-- 博客预览列表 -->
    <ol class="blog-preview-list">
      <li v-for="item in list" :key="item.id" class="blog-preview">
        <!-- 预览图片 -->
        <img src="@/assets/images/blog-preview.png" alt="item.title">
        <!-- 预览内容 -->
        <h2 class="blog-title">{{ item.title }}</h2>
        <!-- 文章预览内容 -->
        <p class="blog-article">{{ item.txt }}</p>
        <!-- 标签列表 -->
        <ul class="tag-list">
          <li class="tag"  v-for="it in item.tag" :key="it.index">
            <span>{{ it }}</span>
          </li>
        </ul>
      </li>
    </ol>
  </div>
</template>

<script>
import Head from '@/components/Head.vue'
export default {
  name: 'Index',
  components: {
    Head
  },
  data() {
    return {
      list: [
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 1, tag: ['IDEA', 'JAVA'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 1, tag: ['IDEA', 'JAVA'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 1, tag: ['IDEA', 'JAVA'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 1, tag: ['IDEA', 'JAVA'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 1, tag: ['IDEA', 'JAVA'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 1, tag: ['IDEA', 'JAVA'] },
      ]
    }
  }
}
</script>

<style scoped lang="less">
.blog-preview-list {
  list-style: none;
  margin-left: 170px;
  margin-top: 10px;
  .blog-preview {
    position: relative;
    display: block;
    width: 667px;
    height: 144px;
    margin-bottom: 10px;
    padding: 10px;
    background-color: #fff;
    // 图片
    img {
      float: left;
      width: 200px;
      height: 100%;
    }
    // 标题
    .blog-title {
      display: inline-block;
      font-size: 20px;
      color: #4f4f52;
      margin-left: 20px;
    }
    // 标签
    .tag-list {
      position: absolute;
      bottom: 10px;
      left: 225px;
      .tag {
        float: left;
        padding:  0 10px;
        margin-right: 10px;
        height: 25px;
        background-color: #ffefef;
        font-size: 12px;
        color: #ff8282;
        font-weight: 700;
        text-align: center;
        border-radius: 5px;
        line-height: 25px;
      }
    }
    
  }
}
</style>