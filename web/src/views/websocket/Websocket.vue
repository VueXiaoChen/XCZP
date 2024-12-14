<script setup lang="ts">
import { onBeforeUnmount, ref, shallowRef, onMounted ,reactive} from 'vue'
import axios from "axios";
import { Tool } from "../../utils/tool"
import { ElMessage } from 'element-plus'
import { NotifyStore } from "../../store/modules/Notify"
import WebSocket from '@/utils/websocket';
let token: any;
const Notifys:any = NotifyStore()
const user = reactive(JSON.parse(sessionStorage.getItem("user")))
token = "userid"+user.userid
// 连接地址：ws://127.0.0.1:8080/ws/xxx
const url = "ws://127.0.0.1:8080/ws/" + token
const options = {
   url: url,
   onmessage: (res) => {
    // 接收数据后回调
    //console.log('WebSocket收到消息：', res.data);
    // ElMessage({
    //     message:res.data,
    //     type: 'success',
    // })

    const data = JSON.parse(res.data)
    if(data.type==="2"){
        Notifys.messageData.push({
        avatar: "https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png",
        title: "新的消息",
        description: data.comment,
        datetime: "2000-06-05"
        })
    }else if(data.type==="1"){
        Notifys.notifyData.push({
        avatar: "https://gw.alipayobjects.com/zos/rmsportal/OKJXDXrmkNshAMvwtvhu.png",
        title: "通知",
        datetime: "现在",
        description:data.comment
        })
        
    }
  },
  // 保活周期 10s
  timer: 10000,
 // 断线重连
 reconnect: true,
 };
 const socket = new WebSocket(options);

//web发送消息
function ceshi(){
    socket.send("你好胖啊")
}

// let websocket: any;
// let token: any;

// const Notifys:any = NotifyStore()

// const user = reactive(JSON.parse(sessionStorage.getItem("user")))
// const onOpen = () => {
//     console.log('WebSocket连接成功，状态码：', websocket.readyState)
// };
// const onMessage = (event: any) => {
//     console.log('WebSocket收到消息：', event.data);
//     ElMessage({
//         message:event.data,
//         type: 'success',
//     })
// };
// const onError = () => {
//     console.log('WebSocket连接错误，状态码：', websocket.readyState)
// };
// const onClose = () => {
//     console.log('WebSocket连接关闭，状态码：', websocket.readyState)
// };
// const initWebSocket = () => {
//     // 连接成功
//     websocket.onopen = onOpen;
//     // 收到消息的回调
//     websocket.onmessage = onMessage;
//     // 连接错误
//     websocket.onerror = onError;
//     // 连接关闭的回调
//     websocket.onclose = onClose;
// };
// onMounted(async() => {
//     // WebSocket
//     if ('WebSocket' in window) {
//         //token = Tool.uuid(10);
//         token = "userid"+user.userid
//         // 连接地址：ws://127.0.0.1:8080/ws/xxx
//         const url = "ws://127.0.0.1:8080/ws/" + token
//         websocket = new WebSocket(url);
//         await initWebSocket()
//         // 关闭
//         // websocket.close();s
//     } else {
//         alert('当前浏览器 不支持')
//     }
    
    
// })

</script>

<template>
    <div class="comment">
        <div class="comment-btn">
            <div class="btn">
                <button v-onceClick @click="ceshi">点赞</button>
                <button v-onceClick @click="">关注</button>
                <button v-onceClick @click="">评论</button>
                <button v-onceClick @click="">收藏</button>
            </div>
        </div>
    </div>
</template>

<style scoped>
.comment {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
}

.comment .comment-btn {
    width: 100%;
    height: 10%;
    display: flex;
    justify-content: center;
    align-items: center;
}

.comment .comment-btn .btn {
    width: 80%;
    height: 100%;
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    align-items: center;
    /* background: rgba(255, 255, 255, 0.2); */
}

.comment .comment-btn .btn button {
    position: relative;
    /* top: 25px; */
    padding: 8px 30px;
    border: none;
    outline: none;
    background: #03a9f4;
    border-radius: 30px;
    color: #fff;
    font-size: 1rem;
    letter-spacing: 0.2rem;
    text-transform: uppercase;
    font-weight: 500;
    cursor: pointer;
    border: 5px solid #083d41;
    box-shadow: 0 0 0 1px #fff;
    transition: 0.5s;
}

.comment .comment-btn .btn button:hover {
    letter-spacing: 0.5rem;
    background: #ff3d7f;
}
</style>
