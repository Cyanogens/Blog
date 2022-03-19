<template>
  <div class="body">
    <div class="login">
      <div class="login-logo">
        <router-link to="/">
          <svg class="icon icon-guanbi"
               aria-hidden="true">
            <use xlink:href="#icon-fanhui"></use>
          </svg>
        </router-link>
        <span class="login-logo-txt">忘记密码</span>
      </div>
      <el-form :model="ruleForm"
               status-icon
               :rules="rules"
               ref="ruleForm"
               label-width="100px"
               class="demo-ruleForm">
        <el-form-item label="用户名"
                      prop="name"
                      class="name">
          <svg class="icon icon-name"
               aria-hidden="true">
            <use xlink:href="#icon-kuaidiyuan_o"></use>
          </svg>
          <el-input type="txt"
                    v-model="ruleForm.name"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密保问题"
                      prop="question">
          <svg class="icon icon-mima"
               aria-hidden="true">
            <use xlink:href="#icon-ziyuan28"></use>
          </svg>
          <el-input type="text"
                    v-model="ruleForm.question"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密保答案"
                      prop="answer">
          <svg class="icon icon-mima"
               aria-hidden="true">
            <use xlink:href="#icon-zanweihuida"></use>
          </svg>
          <el-input type="text"
                    v-model="ruleForm.answer"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary"
                     @click="login;submitForm('ruleForm')">找回密码</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Forget',
  data () {

    var validateQ = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密保问题'));
      } else {
        callback();
      }
    };
    var validateA = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密保答案'));
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
      ruleForm: {
        question: '',
        answer: '',
        name: ''
      },
      rules: {
        question: [
          { validator: validateQ, trigger: 'blur' }
        ],
        answer: [
          { validator: validateA, trigger: 'blur' }
        ],
        name: [
          { validator: validateName, trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    // ? 发送请求
    async find () {
      try {
        const { data: res } = axios.get('http://localhost:8080/verifyAnswer', {
          params: {
            answer: this.ruleForm.answer,
            username: this.ruleForm.name
          }
        })
      } catch (error) {
        console.log(error);
      }
    },
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid) {
          console.log('error submit!!');
          return false;
        }
      });
    },
  }
}
</script>

<style lang="less" scoped>
.body {
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
  background: url("../assets/images/background-login-register.png") top center
    no-repeat;
  background-size: 100% 100%;
}

.login {
  margin: 150px auto;
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
}
</style>
<style scoped>
.demo-ruleForm >>> .el-input__inner {
  height: 25px;
  padding-left: 5px;
}
.demo-ruleForm >>> .el-form-item__label {
  font-weight: 700;
  padding: 0 0 0 35px;
  text-align: left;
}

.demo-ruleForm .name >>> .el-form-item__label {
  padding: 0 0 0 40px;
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