<template>
  <!-- <div class="H"> -->
  <div class="head">
    <!-- logo -->
    <div class="logo">
      <router-link to="/">
        <img src="@/assets/images/logo1.jpg"
             title="s'pirate" />
      </router-link>
    </div>
    <!-- 搜索框 -->
    <div class="search">
      <el-input class="search-ipt"
                placeholder="请输入内容"
                v-model="input"
                clearable> </el-input>
      <router-link to="/search">
        <span class="search-btn">
          <svg class="icon"
               aria-hidden="true">
            <use xlink:href="#icon-sousuo"></use>
          </svg>
          搜索
        </span>
      </router-link>
    </div>

    <!-- 导航栏 -->
    <ul class="nav">
      <li>
        <router-link to="/">
          <svg class="icon"
               aria-hidden="true">
            <use xlink:href="#icon-shouye1"></use>
          </svg>
          首页
        </router-link>
      </li>
      <li>
        <router-link to="/QA">
          <svg class="icon"
               aria-hidden="true">
            <use xlink:href="#icon-fabuwenda"></use>
          </svg>
          问答
        </router-link>
      </li>
      <li>
        <router-link to="/hot">
          <svg class="icon"
               aria-hidden="true">
            <use xlink:href="#icon-redu"
                 style="color: #fc3847"></use>
          </svg>
          热榜
        </router-link>
      </li>
      <li>
        <router-link to="/special">
          <svg class="icon"
               aria-hidden="true">
            <use xlink:href="#icon-biaoqianA01_zhuanqu-53"></use>
          </svg>
          专区
        </router-link>
      </li>
      <li class="li-blog">
        <!-- 发布博客 -->
        <router-link to="/editBlog"
                     class="blog">
          <svg class="icon"
               aria-hidden="true">
            <use xlink:href="#icon-tubiaozhizuomoban-"></use>
          </svg>
          发布博客
        </router-link>
      </li>
    </ul>

    <!-- 头像&下拉菜单 -->
    <div class="menu">
      <ul>
        <li class="user"><img src="@/assets/images/user.jpg"
               alt="" /></li>
        <div class="menu-hid">
          <li>
            <router-link to="/person">个人中心</router-link>
          </li>
          <li>
            <router-link @click.native="toLogin"
                         :to="path">{{txt}}</router-link>
          </li>
        </div>
      </ul>
    </div>
  </div>
  <!-- </div> -->
</template>

<script>
import axios from 'axios'
export default {
  name: 'Head',
  data () {
    return {
      input: '',
      txt: '登录',
      path: '/login'
    }
  },
  methods: {
    toLogin () {
      if (this.GLOBAL.token === 'T') { // * 当前状态为已登录，点击退出
        this.logout();
      } else {
        // * 当前状态为未登录，点击跳转到登录界面
        this.path = '/login'
      }
    },
    async logout () {
      try {
        const { data: res } = await axios.post('http://localhost:8080/logout')
        if (res.code == 200) {
          this.$message.success('退出成功');
          this.GLOBAL.token = 'F';
          this.path = '/'; // * 退出后，跳转到首页
        }
      } catch (error) {
        console.log(error);
      }
    }
  },
  watch: { // * 监听路由的变化，只要有变化，就实时调整登录/退出的切换
    $route: {
      handler () {
        if (this.GLOBAL.token === 'T') {
          this.txt = '退出'
        } else {
          this.txt = '登录'
        }
      }
    }
  },
  created () {
    if (this.GLOBAL.token === 'T') {
      this.txt = '退出'
    } else {
      this.txt = '登录'
    }
  }
}
</script>

<style scoped lang="less">
/* 头部整体区域 */
.head {
  position: relative;
  position: fixed;
  top: 0;
  width: 100%;
  height: 60px;
  margin-bottom: 10px;
  box-shadow: 0px 0px 1px;
  background-color: #fff;
  z-index: 99;
  border-color: #e7e7e7;
  box-shadow: 0px 3px 10px rgb(102 102 102 / 3%);
}
/* logo区域 */
.logo {
  position: absolute;
  left: 175px;
  //   width: 167px;
  width: 70px;
  height: 100%;
}

.logo a {
  padding: 0;
}

.logo img {
  //   width: 100%;
  height: 100%;
}

/* 搜索框 */
.search {
  // display: inline-block;
  width: 284px;
  height: 30px;
  // margin-left: 140px;
  //   margin: 10px 0 10px 910px;
  margin: 15px 0 15px 665px;
  // margin-top: 10px;
  a {
    display: inline;
    padding: 0;
  }
}

.search .search-ipt {
  width: 214px;
  height: 100%;
  float: left;
}

.search .search-btn {
  float: right;
  width: 70px;
  height: 100%;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  color: #fff;
  font-size: 14px;
  line-height: 30px;
  text-align: center;
  background-color: #fc5632;
  border-radius: 0 16px 16px 0;
  font-family: "PingFang SC", "SF Pro Display", "Microsoft YaHei", Roboto, Noto,
    Arial, sans-serif;
}

.search .search-btn:hover {
  background-color: #fc1944;
}
/* 导航栏 */
.nav {
  position: absolute;
  top: 0;
  //   left: 345px;
  left: 260px;
  list-style: none;
}
.nav li {
  height: 60px;
  float: left;
  text-align: center;
  line-height: 60px;
  color: #343434;
  font-size: 14px;
}

.nav li:hover {
  background-color: #cccccc;
  color: #3e3e3e;
  font-weight: 700;
}

/* 头像&菜单栏 */
.menu {
  position: absolute;
  top: 0;
  right: 175px;
  width: 80px;
  height: 100%;
}

.menu .menu-hid {
  box-shadow: 0 0 10px rgb(204 204 204 / 60%);
}
.user img {
  width: 100%;
  height: 100%;
  border-radius: 50px;
}

.menu ul {
  list-style: none;
  width: 80px;
  height: 60px;
  overflow: hidden;
  transition: height 0.5s;
}

.menu ul:hover {
  height: 150px;
}

.menu ul li {
  height: 35px;
  line-height: 35px;
  text-align: center;
  z-index: 9999;
}

.menu ul .menu-hid li {
  background-color: #fff;
  // box-shadow: 0 0 10px rgb(204 204 204 / 60%);
}

.menu .user {
  padding: 0;
  width: 100%;
  height: 60px;
}

.menu ul li:hover {
  background-color: #cccccc;
}

.menu ul li a {
  display: inline-block;
  width: 100%;
  height: 100%;
  color: #343434;
}

/* 发布博客 */
// .nav .li-blog {
// 	padding: 0;
// }

.blog {
  position: relative;
  width: 100%;
  height: 100%;
  display: inline-block;
  height: 100%;
  width: 100%;
  text-align: center;
  font-size: 14px;
  line-height: 60px;
  color: #343434;
}

// .blog::before {
// 	position: absolute;
// 	left: 2px;
// }
a {
  display: inline-block;
  width: 100%;
  height: 100%;
  padding: 0 10px;
}

a:hover {
  background-color: #f0f0f5;
  color: #343434;
  font-weight: 700;
}

/* 设置路由的样式 */
</style>

<style scoped>
.search .search-ipt >>> .el-input__inner {
  padding: 0 8px;
  height: 100%;
  outline: #ccc;
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  border-radius: 16px 0 0 16px;
}
</style>