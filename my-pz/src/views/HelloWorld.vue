<script setup lang="ts">
  import { reactive, ref } from "vue"
  import { User, Lock } from '@element-plus/icons-vue'
  import WebSocket from '../utils/WebSocket';
  import request from '../service/'
  import { Tool } from "../utils/tool"
  import { ElMessage } from 'element-plus'
  import { useRouter } from "vue-router"
  import { useUserStore } from "@/store/modules/user"
  const router = useRouter()
  const user = useUserStore()
  const radio = ref(1)

  const loginFormRef = ref(null)

  const loginFormRules:any =reactive( {
    username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
    password: [
      { required: true, message: "请输入密码", trigger: "blur" },
      { min: 1, max: 16, message: "长度在 1 到 16 个字符", trigger: "blur" }
    ],
  })

  const loginFormData = reactive({
    username: "111",
    password: "23333",
  })




async function loading(){
  await request({
    url: "/api/user/loading",
    method: 'POST',
    data:loginFormData,
    interceptors: {
      requestInterceptors(res) {
        console.log('接口请求拦截')
        return res
      },
      responseInterceptors(result) {
        console.log('接口响应拦截')
        return result
      },
    },
  }).then((res:any)=>{
    if(res){
      console.log(res);
      user.userid = res.data.userid
      ElMessage({
      message:res.message,
      type: 'success',
      })
      router.push({ path: "/homes" })
    }
    
  })
}


</script>


<template>
     <div class="login-container">
    
    <div class="login-card">
      <div class="title">
        <h2>登录</h2>
      </div>
      <div class="content">
        <el-form ref="loginFormRef" :model="loginFormData" :rules="loginFormRules" >
          <el-form-item prop="username">
            <el-input
              v-model="loginFormData.username"
              placeholder="用户名"
              type="text"
              tabindex="1"
              size="large"
              :prefix-icon="User"
              
            />
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="loginFormData.password"
              placeholder="密码"
              type="password"
              tabindex="2"
              size="large"
              show-password
              :prefix-icon="Lock"
            >
            
          </el-input>
            
          </el-form-item>     
          <el-form-item prop="password">
            <el-radio-group v-model="radio" >
              <el-radio :value="1">学生</el-radio>
              <el-radio :value="2">老师</el-radio>
              <el-radio :value="3">管理员</el-radio>
            </el-radio-group>
          </el-form-item>       
          <el-button  type="primary" size="large" @click="loading">登 录</el-button>
        </el-form>
      </div>
    </div>
  </div>
</template>
   
   
<style scoped lang="scss">
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  min-height: 100%;
  .login-card {
    width: 480px;
    border-radius: 20px;
    box-shadow: 0 0 10px #dcdfe6;
    background-color: #fff;
    overflow: hidden;
    .title {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 150px;
      img {
        height: 100%;
      }
    }
    .content {
      padding: 20px 50px 50px 50px;
      :deep(.el-input-group__append) {
        padding: 0;
        overflow: hidden;
        .el-image {
          width: 100px;
          height: 40px;
          border-left: 0px;
          user-select: none;
          cursor: pointer;
          text-align: center;
        }
      }
      .el-button {
        width: 100%;
        margin-top: 10px;

      }
      .el-input {
        font-size: 1rem
      }
    }
  }
}
</style>