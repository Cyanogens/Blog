<template>
  <div class="body">

    <Head></Head>
    <vue-particles color="#00a6ac"
                   :particleOpacity="0.7"
                   :particlesNumber="80"
                   shapeType="circle"
                   :particleSize="2"
                   linesColor="#409EFF"
                   :linesWidth="1"
                   :lineLinked="true"
                   :lineOpacity="0.4"
                   :linesDistance="150"
                   :moveSpeed="5"
                   :hoverEffect="true"
                   hoverMode="grab"
                   :clickEffect="true"
                   clickMode="push">
    </vue-particles>
    <!-- 博客预览列表 -->
    <ol class="blog-preview-list">
      <li v-for="item in list"
          :key="item.id"
          class="blog-preview">
        <router-link to="/blog"
                     class="link">
          <div class="pre">
            <!-- 预览图片 -->
            <img src="@/assets/images/blog-preview.png"
                 alt="item.title">
            <!-- 预览内容 -->
            <h2 class="blog-title">{{ item.title }}</h2>
            <!-- 文章预览内容 -->
            <p class="blog-article">{{ item.txt }}</p>
            <!-- 标签列表 -->
            <ul class="tag-list">
              <li class="tag"
                  v-for="(it, index) in item.tag.slice(0, 4)"
                  :key="index">
                <el-tag :type="color[index]">{{ it }}</el-tag>
              </li>
            </ul>
          </div>
        </router-link>
      </li>
    </ol>

    <!-- 近期发布 -->
    <ul class="tagAll-list">
      <li v-for="(item,index) in tag_list"
          :key=index>
        <el-tag :type="color[index % 4]">{{ item }}</el-tag>
      </li>
    </ul>

    <!-- 分页 -->
    <el-pagination background
                   layout="prev, pager, next"
                   :total="total"
                   :current-page="pageNo"
                   class="pages"
                   @current-change="change">
    </el-pagination>
  </div>
</template>

<script>
import Head from '@/components/Head.vue'
import axios from 'axios'
export default {
  name: 'Index',
  components: {
    Head
  },
  data () {
    return {
      // 所有标签
      tag_list: [
        'IDEA', 'JAVA', 'Vue', '数据结构', 'IDEA', 'JAVA', 'Vue', '数据结构', 'IDEA', 'JAVA', 'Vue', '数据结构',
        'IDEA', 'JAVA', 'Vue', '数据结构', 'IDEA', 'JAVA', 'Vue', '数据结构',
      ],
      // 博客总数目
      total: 100,
      // 当前页
      pageNo: 1,
      // 每页展示的数目
      pageSize: 10,
      color: ['', 'success', 'warning', 'danger'],
      list: [
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 1, tag: ['IDEA', 'JAVA', 'Vue', '数据结构', '数据结构'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 2, tag: ['IDEA', 'JAVA', 'Vue', '数据结构'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 3, tag: ['IDEA', 'JAVA', 'Vue', '数据结构'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 4, tag: ['IDEA', 'JAVA', 'Vue', '数据结构'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 5, tag: ['IDEA', 'JAVA', 'Vue', '数据结构'] },
        { img: 'img', title: '好用的IDEA快捷键大全', txt: '个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的个人提取示范点水电费水电费水电费圣诞福利就从VB都发了的', date: 'now', id: 6, tag: ['IDEA', 'JAVA', 'Vue', '数据结构'] },
      ]
    }
  },
  methods: {
    // ? 分页，当期页数改变
    change (e) {
      this.pageNo = e; // ! e即为当前页数
      // console.log(this.pageNo);
      // getList();
    },

    // ? 数组去重
    unique (arr) {
      return Array.from(new Set(arr))
    },

    // ? 获取所有博客列表
    async getBlogList () {
      const { data: res } = await axios.post('http://localhost:8080/blog/random', {
        pageNo: this.pageNo,
        pageSize: this.pageSize
      })
      // console.log(res);
      //   console.log('11');
      if (res.errorCode === 200) {
        this.list = res.list;
        console.log(this.list);
      }
    },

    // ? 获取所有标签列表
    async getTagList () {
      const { data: res } = await axios.get('http://localhost:8080/category/getAll')
      //   console.log(res);
      if (res.errorCode === 200) {
        this.tag_list = this.unique(this.tag_list);
        console.log(this.tag_list);
      }
    }
  },
  created () {
    // return this.getList();
    this.getTagList() // todo 获取所有标签
    this.getBlogList() // todo 获取所有博客
    // console.log();

  }
}
</script>

<style scoped lang="less">
.body {
  position: absolute;
  top: 0;
  height: 100%;
  width: 100%;
  // background: url(@/assets/images/background-1.jpg) no-repeat;
  // background-size: 100% 100%;
  // background-attachment: fixed;
  // background-position: center top;
  background-color: #f5f6f7;
  overflow: auto;
  #particles-js {
    width: 100%;
    height: 100%;
    position: absolute;
  }
}
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
  }
}

// 所有标签
.tagAll-list {
  position: absolute;
  right: 400px;
  top: 180px;
  width: 205px;
  //   padding: 10px 0 20px 0;
  background-color: #fff;
  box-shadow: 0 0 1px #ccc;
  li {
    float: left;
    margin: 10px;
  }
  li:hover {
    box-shadow: 0 0 6px #ccc;
    cursor: pointer;
  }
}
</style>

<style scoped>
.el-pagination {
  text-align: center;
  margin: 20px 0;
}
</style>