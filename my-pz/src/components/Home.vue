

<script setup lang="ts">
  import { reactive, ref,onMounted} from "vue"
  import WebSocket from '../utils/WebSocket';
  import { useUserStore } from "@/store/modules/user"
  import { ElMessage } from 'element-plus'


  const user = useUserStore()
  console.log(user);
  
  function JoinRedis(){
    const token = user.userid
      const url = "ws://127.0.0.1:8080/ws/" + token
      const options = {
        url: url,
        onmessage: (res) => {
          // 接收数据后回调
          ElMessage({
            message:res.data,
            type: 'success',
          })
        },
          // 保活周期 10s
        timer: 10000,
        // 断线重连
        reconnect: true,
      }
      const socket = new WebSocket(options);
  }
  onMounted(async () => {
    JoinRedis()
  })
</script>


<template>
    <div class="div">123123</div>
</template>
   
   
<style scoped lang="scss">

</style>