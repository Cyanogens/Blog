<template>
  <div class="login-body">
    <div class="login-main">
      <div class="bg">
        <img src="@/assets/images/loginBg.png">
      </div>
      <div class="login">
        <div class="login-logo">
          <router-link to="/">
            <svg class="icon icon-guanbi"
                 aria-hidden="true">
              <use xlink:href="#icon-fanhui"></use>
            </svg>
          </router-link>
          <span class="login-logo-txt">LOGIN IN</span>
        </div>
        <el-form :model="ruleForm"
                 status-icon
                 :rules="rules"
                 ref="ruleForm"
                 label-width="100px"
                 class="demo-ruleForm">
          <el-form-item label="用户名"
                        prop="name">
            <svg class="icon icon-name"
                 aria-hidden="true">
              <use xlink:href="#icon-kuaidiyuan_o"></use>
            </svg>
            <el-input type="txt"
                      v-model="ruleForm.name"
                      autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码"
                        prop="pass">
            <svg class="icon icon-mima"
                 aria-hidden="true">
              <use xlink:href="#icon-mima"></use>
            </svg>
            <el-input type="password"
                      v-model="ruleForm.pass"
                      autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary"
                       @click="login">登录</el-button>
          </el-form-item>
        </el-form>
        <div class="other-enter">
          <span class="forget-pass">
            <router-link to="/forget">忘记密码</router-link>
          </span>
          <span class="to-register">
            <router-link to="/register">注册</router-link>
          </span>
        </div>
      </div>
    </div>
    <footer class="footer">
      Copyright © 2022
    </footer>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Login',
  components: {

  },
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        // 发送请求到后端，然后接收数据并判断
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
      ruleForm: {
        pass: '',
        name: ''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        name: [
          { validator: validateName, trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    // ? 登录功能
    async login () {
      try {
        if (this.GLOBAL.token === 'T') {
          this.$message.success('退出成功');
          this.GLOBAL.token = 'F';
          this.$router.push('/'); // * 退出后，跳转到首页
        } else {
          const { data: res } = await axios.post('http://localhost:8080/login', {
            username: this.ruleForm.name,
            password: this.ruleForm.pass
          })

          if (res.code === 200) {
            this.GLOBAL.token = 'T'; // * 登录成功，标记
            this.$router.push('/'); // * 登录成功后回到首页
          } else {
            this.GLOABL.token = 'F'; // * 登录失败，切换标记  
            this.$message.error(res.msg);
          }
        }
      } catch (error) {
        console.log(error);
      }
    }
  }
}
</script>

<style scoped lang="less">
.login-body {
  position: absolute;
  top: 0;
  height: 100%;
  width: 100%;
  background: url(@/assets/images/login-bg2.png) center top no-repeat;
  background-size: 100% 100%;
  background-attachment: fixed;
  background-position: center top;
  overflow: auto;
}

.login-main {
  margin: 0 auto;
  width: 1000px;
  height: 93%;
  .bg {
    top: 120px;
  }
  .bg img {
    width: 560px;
  }
}

.login {
  position: absolute;
  right: 220px;
  top: 130px;
  width: 370px;
  height: 360px;
  background-color: #fff;
  // box-shadow: 0 0 10px #ccc;
  border: 1px solid #e8e8e8 !important;
  border-radius: 6px;
  .login-logo {
    text-align: center;
    padding: 20px 0;
    .icon {
      width: 63px;
      height: 54px;
      color: #000000;
    }
    .icon-guanbi {
      position: absolute;
      width: 20px;
      height: 20px;
      color: #515151;
      top: 10px;
      right: 10px;
    }
    .icon-guanbi:hover {
      box-shadow: 0 5px 5px 0 rgba(0, 0, 0, 0.25);
      transition: all 0.2s ease-in-out;
    }
  }
  .login-logo-txt {
    display: block;
    font-weight: 700;
    font-size: 20px;
    color: #000000;
  }
  .icon-name,
  .icon-mima {
    position: absolute;
    left: -95px;
    top: 10%;
    width: 32px;
    height: 32px;
  }
  .icon-mima {
    width: 23px;
    height: 23px;
    top: 20%;
    left: -90px;
  }

  .el-form-item {
    width: 63%;
    margin-left: 30px;
    margin-bottom: 20px;
  }
  .el-input {
    width: 100%;
  }
  a {
    display: inline-block;
    width: 100%;
    height: 100%;
    color: #fff;
  }
  .other-enter {
    height: 40px;
    margin-top: 90px;
    margin-left: 62px;
    width: 237px;
    span {
      display: inline-block;
      width: 70px;
      height: 28px;
      color: #fff;
      font-weight: 700;
      font-size: 14px;
      text-align: center;
      line-height: 28px;
      background-color: #6766f7;
    }
    span:hover {
      background-color: #3366ff;
      box-shadow: 0 16px 32px 0 rgba(48, 55, 66, 0.15);
      transform: translate(0, -5px);
      transition-delay: 0s !important;
    }
    .forget-pass {
      float: left;
    }
    .to-register {
      float: right;
    }
  }
}

// 底部
.footer {
  margin: 0 auto;
  text-align: center;
  height: 20px;
  line-height: 20px;
  color: rgba(255, 255, 255, 0.6);
}
</style>

<style scoped>
.demo-ruleForm >>> .el-input__inner {
  height: 25px;
  padding-left: 5px;
}
.demo-ruleForm >>> .el-form-item__label {
  font-weight: 700;
  padding: 0 0 0 40px;
  text-align: left;
}
.demo-ruleForm >>> .el-form-item__content {
  text-align: center;
}
.demo-ruleForm >>> .el-button--primary {
  position: absolute;
  left: -10px;
  top: 20px;
  width: 140px;
  height: 40px;
  font-weight: 700;
  font-size: 16px;
  background-color: #1b9752;
}
.demo-ruleForm >>> .el-button--primary:hover {
  box-shadow: 0 5px 5px 0 rgba(0, 0, 0, 0.25);
  transition: all 0.2s ease-in-out;
}
.demo-ruleForm >>> .el-input__inner {
  width: 120%;
}
.demo-ruleForm >>> .el-input__suffix {
  right: -28px;
}
.demo-ruleForm >>> .el-form-item__error {
  padding-top: 0;
}
</style>