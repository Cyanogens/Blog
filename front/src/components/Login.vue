<template>
	<div class="body">
		<el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
			<el-form-item label="用户名" prop="name">
				<el-input type="txt" v-model="ruleForm.pass" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="密码" prop="pass">
				<el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
				<el-button @click="resetForm('ruleForm')">重置</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		name: 'Login',
		data () {
			var validatePass = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请输入密码'));
				} else {
					if (this.ruleForm.checkPass !== '') {
						this.$refs.ruleForm.validateField('pass');
					}
					callback();
				}
			};
			var validateName = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('请输入用户名'));
				} else {
					if (this.ruleForm.checkPass !== '') {
						this.$refs.ruleForm.validateField('name');
					}
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
					]
				}
			};
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
		}
	}
</script>

<style scoped lang="less">
	.body {
		position: absolute;
		top: 0;
		height: 100%;
		width: 100%;
		background: url(@/assets/images/background-login-register.png) no-repeat;
		background-size: 100% 100%;
		background-attachment: fixed;
		background-position: center top;
		overflow: auto;
	}
	.demo-ruleForm {
		position: absolute;
		left: 486px;
		top: 204px;
		width: 370px;
		height: 310px;
		background-color: skyblue;
		.el-form-item {
			width: 63%;
			margin-left: 45px;
			background-color: pink;
		}
		.el-input {
			width: 100%;
		}
	}
</style>
<style scoped>
	.demo-ruleForm >>> .el-input__inner {
		height: 25px;
	}
</style>