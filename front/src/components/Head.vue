<template>
  <div class="head">
    <!-- logo -->
    <div class="logo">
      <router-link to="/">
        <img src="@/assets/images/logo.png" title="山本仙人酋" />
      </router-link>
    </div>
    <!-- 搜索框 -->
    <div class="search">
      <el-input class="search-ipt" placeholder="请输入内容" v-model="input" clearable> </el-input>
      <span class="search-btn">搜索</span>
    </div>

    <!-- 导航栏 -->
    <ul class="nav">
      <li>首页</li>
      <li>留言</li>
    </ul>

    <!-- 头像&下拉菜单 -->
    <div class="menu">
      <ul>
        <li class="user"><img src="@/assets/images/user.jpg" alt="" /></li>
        <li>
          <router-link to="/person">个人中心</router-link>
        </li>
        <li>
          <router-link to="/login">登录</router-link>
        </li>
      </ul>
    </div>
    <!-- 发布博客 -->
    <div class="edit-blog">
      <span class="el-icon-edit-outline">发布博客</span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      input: '',
    }
  },
  name: 'Head'
}
</script>

<style scoped>
/* 头部整体区域 */
.head {
  position: relative;
  position: fixed;
  top: 0;
  left: 170px;
  width: 1042px;
  height: 51px;
  margin-bottom: 10px;
  box-shadow: 0px 0px 1px;
  background-color: #fff;
  z-index: 99;
}
/* logo区域 */
.logo {
  float: left;
  width: 167px;
  height: 100%;
}

.logo img {
  width: 100%;
  height: 100%;
}

/* 搜索框 */
.search {
  display: inline-block;
  width: 272px;
  height: 30px;
  margin-left: 140px;
  margin-top: 10px;
}

.search .search-ipt {
  width: 214px;
  height: 100%;
  float: left;
}

.search .search-ipt >>> .el-input__inner {
  padding: 0 8px;
  height: 100%;
  outline: #ccc;
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
}

.search .search-btn {
  float: right;
  width: 58px;
  height: 100%;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  color: #fff;
  font-size: 14px;
  line-height: 30px;
  text-align: center;
  background-color: #fc5632;
}

/* 导航栏 */
.nav {
  position: absolute;
  top: 0;
  right: 310px;
  list-style: none;
}
.nav li {
  padding: 0 10px;
  height: 50px;
  float: left;
  text-align: center;
  line-height: 50px;
  color: #343434;
  font-size: 16px;
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
  right: 170px;
  width: 80px;
  height: 100%;
}

.user img {
  width: 100%;
  height: 100%;
  border-radius: 50px;
}

/* 头像部分下弹菜单 */
.menu {
  /* overflow: hidden; */
  right: 178px;
  /* transition: all 1s; */
}

.menu ul {
  list-style: none;
  width: 80px;
  height: 51px;
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
}

.menu ul li:not(:first-child) {
  background-color: #fff;
}


.menu .user {
  padding: 0;
  width: 100%;
  height: 51px;
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
.edit-blog {
  position: absolute;
  right: 60px;
  top: 0;
  font-size: 16px;
  height: 100%;
  padding: 0 10px;
  text-align: center;
}


.edit-blog .el-icon-edit-outline::before {
  font-size: 16px;
}

.edit-blog span {
  display: inline-block;
  height: 100%;
  text-align: center;
  line-height: 50px;
  font-size: 16px;
}
</style>