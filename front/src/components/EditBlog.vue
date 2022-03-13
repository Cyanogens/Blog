<template>
	<div class="edit-body">
		<Head></Head>
		<div class="edit">
			<v-md-editor v-model="text" height="505px"></v-md-editor>
			<div class="commit">
				<span>
					发布
				</span>
			</div>
		</div>
	</div>
</template>

<script>
import Head from '@/components/Head.vue'
	export default {
		name: 'EditBlog',
		components: {
			Head
		},
		data () {
			return {
				text: '',
			};
		},
	};
</script>

<style scoped lang="less">
	.edit-body {
		position: absolute;
		width: 100%;
		height: 100%;
		background-color: #f5f6f7;
		.edit {
			position: relative;
			margin-top: 70px;
			padding: 0 175px;
			.commit {
				position: absolute;
				bottom: -50px;
				left: 50%;
				margin-left: -62.5px;
				span {
					display: inline-block;
					width: 125px;
					height: 32px;
					color: #fff;
					font-size: 16px;
					line-height: 30px;
					text-align: center;
					background-color: #6766f7;
				}
			}
		}
	}
</style>