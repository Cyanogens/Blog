const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // devServer: {
  //   host: 'localhost', // 本地主机
  //   port: 8081, // 端口号的配置
  //   open: true, // 自动打开浏览器
  //   proxy: {
  //     '/api': {
  //       target: 'http://localhost:8080',
  //       changeOrigin: true,
  //       pathRewrite: {
  //         '^/api': '/',
  //       },
  //     },
  // },
  // },
})
