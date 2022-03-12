<template>
	<div class="body">
		<div class="login">
			<div class="login-logo">
				<router-link to="/">
					<svg class="icon icon-guanbi" aria-hidden="true">
						<use xlink:href="#icon-guanbi"></use>
					</svg>
				</router-link>
				<span class="login-logo-txt">LOGIN IN</span>
			</div>
			<el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
				<el-form-item label="用户名" prop="name">
					<svg class="icon icon-name" aria-hidden="true">
						<use xlink:href="#icon-kuaidiyuan_o"></use>
					</svg>
					<el-input type="txt" v-model="ruleForm.name" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="pass">
					<svg class="icon icon-mima" aria-hidden="true">
						<use xlink:href="#icon-mima"></use>
					</svg>
					<el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
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
</template>

<script>
	export default {
		name: 'Login',
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

	.login {
		position: absolute;
		left: 500px;
		top: 163px;
		width: 370px;
		height: 310px;
		box-shadow: 0 0 10px #ccc;
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
				width: 18px;
				height: 18px;
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
			margin-bottom: 10px;
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
			margin-top: 85px;
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