<template>
  <div class="body">

    <Head></Head>
    <!-- <el-empty description="暂无数据"></el-empty> -->
    <div class="info">
      <!-- 头像 -->
      <div class="img">
        <img src="../assets/images/user.jpg">
      </div>
      <!-- 基本信息框 -->
      <el-form :model="ruleForm"
               status-icon
               :rules="rules"
               ref="ruleForm"
               label-width="100px"
               class="demo-ruleForm"
               :disabled="edit">
        <el-form-item label="用户名"
                      prop="userName">
          <el-input type="text"
                    v-model="ruleForm.userName"
                    autocomplete="off"></el-input>
        </el-form-item>
        <!-- 性别 -->
        <span class="sex">性别</span>
        <input type="radio"
               class="ipt1"
               id="boy"
               value="男"
               v-model="sex"
               name="sex"
               :disabled="edit"> <label for="boy">男</label>
        <input type="radio"
               class="ipt2"
               id="girl"
               value="女"
               v-model="sex"
               name="sex"
               :disabled="edit"> <label for="girl">女</label>

        <el-form-item label="密码"
                      prop="pass">
          <el-input type="tel"
                    oninput="value=value.replace(/[^\d]/g,'')"
                    v-model.number="ruleForm.pass"></el-input>
        </el-form-item>
        <el-form-item class="save">
          <el-button type="primary"
                     @click="pushInfo">保存信息</el-button>
        </el-form-item>
      </el-form>
      <el-button type="primary"
                 class="changeInfo"
                 :disabled="!edit"
                 @click="changeEdit">修改信息</el-button>

    </div>

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
              <!-- <img src="@/assets/images/blog-preview.png"
                 alt="item.title"> -->
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
import axios from 'axios';
export default {
  name: 'Person',
  components: {
    Head
  },
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('密码不能为空'));
      } else {
        callback();
      }
    };
    var validateName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'));
      } else {
        callback();
      }
    };
    return {
      // 用户登录标志
      token: this.GLOBAL.token,
      // 所有标签
      tag_list: [],
      // 博客总数目
      total: 0,
      // 当前页
      pageNo: 1,
      // 每页展示的数目
      pageSize: 4,
      color: ['', 'success', 'warning', 'danger'],
      list: [],
      id: this.GLOBAL.id, // * 用户id
      password: 1,
      edit: true,
      sex: '男', // * 性别
      ruleForm: {
        userName: '',
        phone: '',
      },
      rules: {
        userName: [
          { validator: validateName, trigger: 'blur' }
        ],
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ]
      }
    };
  },

  methods: {
    changeEdit () {
      this.edit = !this.edit;
    },
    async pushInfo () {
      try {
        if (this.ruleForm.userName === '' || this.ruleForm.pass === '') {
          this.$message({
            showClose: true,
            message: '用户名和密码不能为空哦~',
            type: 'error'
          });
          return;
        }
        const { data: res } = await axois.post('http://localhost:8080/user/change', {
          id: this.GLOBAL.id,
        })
        if (res.code === 200) {
          // * 请求成功，即更改信息成功
          this.$message({
            message: '修改成功',
            type: 'success'
          });
          this.getInfo(); // * 保存更改后，重新获取信息
        }
      } catch (error) {
        console.log(error);
      }
    },
    // ? 获取用户信息
    async getInfo () {
      try {
        const { data: res } = await axios.get('http://localhost:8080/user/' + this.GLOBAL.id)
        if (res.code === 200) {
          this.GLOBAL.id = res.data.id;
          this.ruleForm.userName = res.data.username,
            this.sex = res.data.sex
        } else {
          if (res.code === 2001) {
            this.$message.error('请先登录');
            this.$router.push('/login')
          }
        }
      } catch (error) {
        console.log(error);
      }
    },

    // ? 分页，当期页数改变
    change (e) {
      this.pageNo = e; // ! e即为当前页数
      // console.log(this.pageNo);
      this.getBlogList() // * 获取所有博客
    },

    // ? 获取用户发布的所有博客列表
    async getBlogList () {
      try {
        const { data: res } = await axios.get('http://localhost:8080/user/userBlogs/' + this.GLOBAL.id + '', {
          params: {
            pageNo: this.pageNo,
            pageSize: this.pageSize
          }
        })
        // console.log(res);
        //   console.log('11');
        if (res.code === 200) { // ! 返回成功
          this.list = res.data.list;
          this.total = res.data.total,
            console.log(this.list);
        }
      } catch (error) {
        console.log(error);
      }
    },
  },
  created () {
    this.getInfo();
    this.getBlogList();
  },
};
</script>

<style lang="less" scoped>
.body {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #f5f6f7;
  overflow: auto;

  .info {
    position: relative;
    top: 80px;
    left: 175px;
    width: 300px;
    height: 372px;
    // background: pink;
    box-shadow: 0 0 5px #ccc;

    // 头像部分
    .img {
      width: 100px;
      height: 100px;
      margin: 0 auto;
      img {
        border-radius: 50px;
        height: 100%;
      }
    }

    // 基本信息部分
    .demo-ruleForm {
      margin-top: 20px;
      .sex {
        display: inline-block;
        margin-left: 40px;
        font-size: 14px;
        padding: 10px;
        letter-spacing: 10px;
      }
      .ipt1 {
        margin-left: 20px;
        vertical-align: middle;
        zoom: 1.3;
      }
      .ipt2 {
        margin-left: 10px;
        vertical-align: middle;
        zoom: 1.3;
      }
    }
    .changeInfo {
      position: absolute;
      margin-left: 30px;
      margin-top: -50px;
    }
  }

  // ? 博客预览列表
  .blog-list {
    position: absolute;
    right: 240px;
    top: 80px;
  }

  .blog-preview-list {
    list-style: none;
    position: relative;
    // top: 80px;
    // right: 240px;
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

<style scoped>
.demo-ruleForm >>> .el-input__inner {
  width: 160px;
  height: 30px;
  padding: 0 7px;
}

.demo-ruleForm >>> .el-form-item {
  margin-bottom: 10px;
}

.demo-ruleForm >>> .el-form-item__label {
  letter-spacing: 2px;
}

.demo-ruleForm .save >>> .el-form-item__content {
  margin-left: 164px !important;
  margin-top: 25px;
}

.body >>> .el-pagination {
  text-align: right;
  margin: 20px 0;
  margin-right: 285px;
}
</style>