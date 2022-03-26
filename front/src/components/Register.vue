<template>
  <div class="body">
    <div class="register">
      <div class="register-logo">
        <router-link to="/login">
          <svg class="icon icon-guanbi"
               aria-hidden="true">
            <use xlink:href="#icon-fanhui"></use>
          </svg>
        </router-link>
        <div class="logo">
          <img src="@/assets/images/register-logo.png">
        </div>
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
          <el-input type="txt"
                    v-model="ruleForm.name"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称"
                      prop="nickName"
                      class="nickName">
          <el-input type="txt"
                    v-model="ruleForm.nickName"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密保问题"
                      prop="question"
                      class="question">
          <el-input type="txt"
                    v-model="ruleForm.question"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密保问题答案"
                      prop="answer"
                      class="answer">
          <el-input type="txt"
                    v-model="ruleForm.answer"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码"
                      prop="pass"
                      class="pass">
          <el-input type="password"
                    v-model="ruleForm.pass"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码"
                      prop="checkPass"
                      class="checkPass">
          <el-input type="password"
                    v-model="ruleForm.checkPass"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item class="btn">
          <el-button type="primary"
                     @click="register">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Register',
  data () {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    var checkName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入用户名'));
        // 根据后端返回结果显示是显示“请输入用户名还是，用户名重复”
      } else {
        this.check();
        callback();
      }
    };
    var checkNickName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入昵称'));
      } else {
        callback();
      }
    };
    var checkQuestion = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入密保问题'));
      } else {
        callback();
      }
    };
    var checkAnswer = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入密保问题答案'));
      } else {
        callback();
      }
    };
    return {
      status: 0, // * 能否注册
      ruleForm: {
        pass: '',
        name: '',
        checkPass: '',
        nickName: '',
        question: '',
        answer: ''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        name: [
          { validator: checkName, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        nickName: [
          { validator: checkNickName, trigger: 'blur' }
        ],
        question: [
          { validator: checkQuestion, trigger: 'blur' }
        ],
        answer: [
          { validator: checkAnswer, trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid) {
          console.log('error submit!!');
          return false;
        }
      });
    },
    register () {
      // if (this.status === 1) {
      this.register_push();
      // }
    },

    async check () {
      try {
        const { data: res } = await axios.get('http://localhost:8080/checkAccount?username=' + this.ruleForm.name)
        if (res.code === 2008) {
          this.status = 0
          this.$message.error('该用户名已存在')
        } else {
          this.status = 1
        }
      } catch (error) {
        console.log(error);
      }
    },

    async register_push () {
      try {
        const { data: res } = await axios.post('http://localhost:8080/register', {
          username: this.ruleForm.name,
          password: this.ruleForm.pass,
          problem: this.ruleForm.question,
          ans: this.ruleForm.answer,
          nickName:this.ruleForm.nickName
        })
        if (res.code === 200) {
          this.$message.success('注册成功')
          this.$router.push('/')
        }
      } catch (error) {
        console.log(error);
      }
    }
  }
}
</script>

<style lang="less" scoped>
.body {
  position: absolute;
  top: 0;
  height: 100%;
  width: 100%;
  background: url(@/assets/images/bg1.jpg) no-repeat;
  background-size: 100% 100%;
  background-attachment: fixed;
  background-position: center top;
  overflow: auto;
}
.register {
  margin: 135px auto;
  width: 423px;
  height: 380px;
  background-color: rgba(29, 30, 30, 0.5);
  // background-color: pink;
  .register-logo {
    position: relative;
    height: 90px;
    // background-color: purple;
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
      // color: #515151;
      color: #fff;
      top: 10px;
      right: 10px;
    }
    .icon-guanbi:hover {
      box-shadow: 0 0 4px 0 rgba(255, 255, 255, 0.25);
      transition: all 0.2s ease-in-out;
    }
    .logo {
      width: 238px;
      height: 52px;
      margin: 0 auto;
      img {
        width: 100%;
        height: 100%;
        border: none;
      }
    }
  }
  .demo-ruleForm {
    position: relative;
    left: 30px;
    width: 360px;
    height: 210px;
    // background-color: skyblue;
    .name {
      right: 0;
      top: 0;
    }
    .el-input__inner {
      height: 25px;
    }
    .el-form-item {
      position: absolute;
      width: 155px;
      height: 55px;
      margin-bottom: 0;
    }
    .name {
      left: 0;
      top: 0;
    }
    .nickName {
      right: 0;
      top: 0;
    }
    .question {
      left: 0;
      bottom: 75px;
    }
    .answer {
      right: 0;
      bottom: 75px;
    }
    .pass {
      left: 0;
      bottom: 0;
    }
    .checkPass {
      right: 0;
      bottom: 0;
    }
    .btn {
      bottom: -55px;
      left: 105px;
      width: 128px;
      height: 30px;
    }
  }
}
</style>

<style scoped>
.demo-ruleForm >>> .el-input__inner {
  height: 25px;
  padding-left: 5px;
}
.demo-ruleForm >>> .el-input__suffix {
  right: 0;
  top: -7px;
}
.demo-ruleForm >>> .el-form-item__label {
  float: none;
  text-align: left;
  padding-left: 5px;
  height: 20px;
  line-height: 20px;
  color: #fff;
  padding-right: 10px;
}
.demo-ruleForm >>> .el-input {
  line-height: 25px;
}
.demo-ruleForm >>> .el-form-item__content {
  line-height: 30px;
  margin-left: 0 !important;
}
.demo-ruleForm >>> .el-button--primary {
  width: 100%;
  height: 100%;
  padding: 0;
  background-color: #b49c62;
  border: none;
}
.demo-ruleForm >>> .el-button--primary:hover {
  box-shadow: 0 0 20px 0 rgba(255, 255, 255, 0.25);
  transition: all 0.2s ease-in-out;
}
.demo-ruleForm >>> .el-button--primary span {
  display: inline-block;
  width: 100%;
  line-height: 35px;
  font-size: 18px;
  color: #474747;
  font-weight: 700;
}
</style>
