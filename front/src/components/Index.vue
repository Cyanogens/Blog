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
          <li class="tag"  v-for="(it, index) in item.tag" :key="index" :style="{'background-color': color[index]}" >
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
      color: ['#ffefef','#B0E0E6', '#FFFF00', '#F5DEB3'],
      list: [
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 1, tag: ['IDEA', 'JAVA', 'Vue', '数据结构'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 2, tag: ['IDEA', 'JAVA', 'Vue', '数据结构'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 3, tag: ['IDEA', 'JAVA', 'Vue', '数据结构'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 4, tag: ['IDEA', 'JAVA', 'Vue', '数据结构'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 5, tag: ['IDEA', 'JAVA', 'Vue', '数据结构'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 6, tag: ['IDEA', 'JAVA', 'Vue', '数据结构'] },
      ]
    }
  }
}
</script>

<style scoped lang="less">
.body {
  position: absolute;
  top: 0;
  height: 100%;
  width: 100%;
  background: url(@/assets/images/background-1.jpg) no-repeat;
  background-size: 100% 100%;
  background-attachment: fixed;
  background-position: center top;
  overflow: auto;
}
.blog-preview-list {
  list-style: none;
  margin-left: 170px;
  margin-top: 60px;
  .blog-preview {
    position: relative;
    display: block;
    width: 667px;
    height: 190px;
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
    // 内容
    .blog-article {
      font-size: 14px;
      line-height: 1.7;
      color: #bdbdbd;
      padding: 0 10px;
      width: 445px;
      height: 115px;
      text-indent: 2em;
      overflow: hidden;
      text-overflow:ellipsis;
      overflow: hidden;
      display:-webkit-box;
      -webkit-line-clamp: 5;
      -webkit-box-orient:vertical;
    }
    // 标签
    .tag-list {
      position: absolute;
      bottom: 3px;
      left: 225px;
      .tag {
        float: left;
        padding:  0 10px;
        margin-right: 10px;
        height: 25px;
        // background-color: #ffefef;
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