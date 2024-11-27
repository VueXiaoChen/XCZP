import { defineConfig } from 'vite'
import { type ConfigEnv, type UserConfigExport, loadEnv } from "vite"
import path, { resolve } from "path"
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
/** 配置项文档：https://cn.vitejs.dev/config */
export default (configEnv: ConfigEnv): UserConfigExport => {
  const viteEnv = loadEnv(configEnv.mode, process.cwd()) as ImportMetaEnv
  const { VITE_PUBLIC_PATH } = viteEnv
  return {
    /** 打包时根据实际情况修改 base */
    base: VITE_PUBLIC_PATH,
    resolve: {
      alias: {
        /** @ 符号指向 src 目录 */
        "@": resolve(__dirname, "./src")
      }
    },
    server: {
      /** 是否开启 HTTPS */
      https: false,
      /** 设置 host: true 才可以使用 Network 的形式，以 IP 访问项目 */
      host: true, // host: "0.0.0.0"
      /** 端口号 */
      port: 5173,
      /** 是否自动打开浏览器 */
      open: false,
      /** 跨域设置允许 */
      cors: true,
      /** 端口被占用时，是否直接退出 */
      strictPort: false,
      /** 接口代理 */
      proxy: {
        '/api': {
          target: 'http://localhost:8080',
          changeOrigin: true,
          secure:false,//解决自签名证书错误
          rewrite: (path) => path.replace(/^\/api/, '')
        },
        '/sumber': {
          target: 'https://api.bilibili.com',
          changeOrigin: true,
          secure:false,//解决自签名证书错误
          configure: (proxy, options) => {
            proxy.on('proxyReq', function (proxyReq, req, res) {
              proxyReq.removeHeader('referer')  //移除请求头
              proxyReq.removeHeader('origin') //移除请求头
              //proxyReq.setHeader('host','api.bilibili.com/') //添加请求头
            });
            proxy.on('proxyRes', function (proxyRes, req, res) {
              /*添加或删除响应头有两种写法，第一种是操作 proxyRes 参数*/
              delete proxyRes.headers['set-cookie']
              //proxyRes.headers['cookie'] = 'SESSDATA=4e3dedeb%2C1703865426%2C4538b%2A71sf_6Hf4wW_T6R3v9tSesDllgpKAe2Awag0wXqrm67iSXb9AoqYItAB9_7IDrDlJGYvSzXgAAXgA;bili_jct=9b500afa69ee832587ec8f560c364404;DedeUserID=235576894';
              //proxyRes.headers['set-cookie'] = 'SESSDATA=1c3d9b6a%2C1703795894%2C951d8%2A71DHs_AsMbrQPFi-_pgf9g797y2NebhruR4Ktifyz0e-hAbs5P7gYmTA5BOgxK8tpLVBknlwAAXgA';
              /*第二种方法是操作 res 参数*/
              // res.removeHeader("Access-Control-Allow-Origin");
              // res.setHeader("Access-Control-Allow-Origin", req.headers.origin);
              // res.setHeader("Access-Control-Allow-Credentials", 'true');
              // res.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
              // res.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
            });
          },
          rewrite: (path) => path.replace(/^\/sumber/, '')
        },
      }
    },
    build: {
      /** 消除打包大小超过 500kb 警告 */
      chunkSizeWarningLimit: 2000,
      /** Vite 2.6.x 以上需要配置 minify: "terser", terserOptions 才能生效 */
      minify: "terser",
      /** 在打包代码时移除 console.log、debugger 和 注释 */
      terserOptions: {
        compress: {
          drop_console: false,
          drop_debugger: true,
          pure_funcs: ["console.log"]
        },
        format: {
          /** 删除注释 */
          comments: false
        }
      },
      /** 打包后静态资源目录 */
      assetsDir: "static"
    },
    /** Vite 插件 */
    plugins: [
      vue(),
      //vueJsx(),
      /** 将 SVG 静态图转化为 Vue 组件 */
      //svgLoader({ defaultImport: "url" }),
      /** SVG */
      // createSvgIconsPlugin({
      //   iconDirs: [path.resolve(process.cwd(), "src/icons/svg")],
      //   symbolId: "icon-[dir]-[name]"
      // }),
      // /** UnoCSS */
      // UnoCSS()
    ],
    /** Vitest 单元测试配置：https://cn.vitest.dev/config */
    test: {
      include: ["tests/**/*.test.ts"],
      environment: "jsdom"
    }
  }
}
