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

import Vue from 'vue';
import VMdEditor from '@kangc/v-md-editor/lib/codemirror-editor';
import '@kangc/v-md-editor/lib/style/codemirror-editor.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';

// highlightjs
import hljs from 'highlight.js';

// codemirror 编辑器的相关资源
import Codemirror from 'codemirror';
// mode
import 'codemirror/mode/markdown/markdown';
import 'codemirror/mode/javascript/javascript';
import 'codemirror/mode/css/css';
import 'codemirror/mode/htmlmixed/htmlmixed';
import 'codemirror/mode/vue/vue';
// edit
import 'codemirror/addon/edit/closebrackets';
import 'codemirror/addon/edit/closetag';
import 'codemirror/addon/edit/matchbrackets';
// placeholder
import 'codemirror/addon/display/placeholder';
// active-line
import 'codemirror/addon/selection/active-line';
// scrollbar
import 'codemirror/addon/scroll/simplescrollbars';
import 'codemirror/addon/scroll/simplescrollbars.css';
// style
import 'codemirror/lib/codemirror.css';

VMdEditor.Codemirror = Codemirror;

VMdEditor.use(githubTheme, {
  Hljs: hljs,
});

Vue.use(VMdEditor);




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