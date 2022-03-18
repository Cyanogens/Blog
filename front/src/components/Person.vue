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

        <el-form-item label="手机号"
                      prop="phone">
          <el-input type="tel"
                    oninput="value=value.replace(/[^\d]/g,'')"
                    v-model.number="ruleForm.phone"></el-input>
        </el-form-item>
        <el-form-item class="save">
          <el-button type="primary"
                     @click="pushInfo">保存信息</el-button>
        </el-form-item>
      </el-form>
      <el-button type="primary"
                 class="changeInfo"
                 :disabled="!edit"
                 @click="change">修改信息</el-button>

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
    var validatePhone = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('电话不能为空'));
      } else {
        if (String(value).length != 11) {
          callback(new Error('请输入11位的电话'))
        }
        callback();
      }
    };
    var validateName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    return {
      id: 1, // * 用户id
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
        phone: [
          { validator: validatePhone, trigger: 'blur' }
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
          this.$message({
            message: '修改成功',
            type: 'success'
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm (formName) {
      this.$refs[formName].resetFields();
    },
    change () {
      this.edit = !this.edit;
    },
    async pushInfo () {
      if (this.ruleForm.userName === '' || this.ruleForm.phone === '') {
        this.$message({
          showClose: true,
          message: '用户名和电话不能为空哦~',
          type: 'error'
        });
        return;
      }
      try {
        const { data: res } = axois.post('http://localhost:8080/user/change', {
          id: '', // todo 接口传参
        })
        if (res.code === 200) {
          // * 请求成功，即更改信息成功
          this.$message({
            message: '修改成功',
            type: 'success'
          });
        }
      } catch (error) {
        console.log(error);
      }
    },
    // ? 获取用户信息
    async getInfo () {
      try {
        const { data: res } = axios.get('http://localhost:8080/user/id') // todo 这里的id记得改
        if (res.code === 200) {
          this.id = res.id;
          this.ruleForm.userName = res.username,
            this.sex = res.sex
        }
      } catch (error) {
        console.log(error);
      }
    }
  },
  created () {
    this.getInfo();
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
</style>