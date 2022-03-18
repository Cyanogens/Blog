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
               class="demo-ruleForm">
        <el-form-item label="用户名"
                      prop="userName">
          <el-input type="text"
                    v-model="ruleForm.pass"
                    autocomplete="off"></el-input>
        </el-form-item>
        <!-- 性别 -->
        <span class="sex">性别</span>
        <input type="radio"
               class="ipt1"
               id="boy"
               v-model="sex"
               name="sex"> <label for="boy">男</label>
        <input type="radio"
               class="ipt2"
               id="girl"
               v-model="sex"
               name="sex"> <label for="girl">女</label>

        <el-form-item label="手机号"
                      prop="phone">
          <el-input v-model.number="ruleForm.age"></el-input>
        </el-form-item>
        <!-- <el-form-item>
          <el-button type="primary"
                     @click="submitForm('ruleForm')">保存信息</el-button>
        </el-form-item> -->
      </el-form>

    </div>
  </div>
</template>

<script>
import Head from '@/components/Head.vue'
export default {
  name: 'Person',
  components: {
    Head
  },
  data () {
    var checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('年龄不能为空'));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字值'));
        } else {
          if (value < 18) {
            callback(new Error('必须年满18岁'));
          } else {
            callback();
          }
        }
      }, 1000);
    };
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
    return {

      sex: 'boy', // * 性别

      ruleForm: {
        pass: '',
        checkPass: '',
        age: '',
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        age: [
          { validator: checkAge, trigger: 'blur' }
        ]
      }
    };
  },

  mounted () {

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
    resetForm (formName) {
      this.$refs[formName].resetFields();
    }
  },
};
</script>

<style lang="less" scoped>
.body {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #f5f6f7;

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
      }
      .ipt1 {
        margin-left: 40px;
        vertical-align: middle;
        zoom: 1.3;
      }
      .ipt2 {
        margin-left: 10px;
        vertical-align: middle;
        zoom: 1.3;
      }
    }
  }
}
</style>

<style scoped>
.demo-ruleForm >>> .el-input__inner {
  width: 160px;
  height: 30px;
}

.demo-ruleForm >>> .el-form-item {
  margin-bottom: 10px;
}
</style>