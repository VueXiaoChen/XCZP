<script setup lang="ts">
import { ref, onMounted,watch,reactive } from "vue";
import { Base64 } from 'js-base64'
import axios from 'axios'
import { useRouter } from "vue-router";



const router = useRouter();

const form = ref()
// const str = 'e:/we/英雄联盟/LeagueClient/LeagueClientUx.exe "--riotclient-auth-token=nWSilvDm91_Xh_V8ECDU0g" "--riotclient-app-port=49298" "--riotclient-tencent" "--no-rads" "--disable-self-update" "--region=TENCENT" "--locale=zh_CN" "--t.lcdshost=hn10-cloud-feapp.lol.qq.com" "--t.chathost=hn10-cloud-ejabberd.lol.qq.com" "--t.lq=https://hn10-cloud-login.lol.qq.com:8443" "--t.storeurl=https://hn10-cloud-sr.lol.qq.com:8443" "--t.rmsurl=wss://hn10-cloud-rms.lol.qq.com:443" "--rso-auth.url=https://prod-rso.lol.qq.com:3000" "--rso_platform_id=HN10" "--rso-auth.client=lol" "--t.location=loltencent.gz2.HN10" "--tglog-endpoint=https://tglogsz.datamore.qq.com/lolcli/report/" "--ccs=https://hn10-cloud-cc.lol.qq.com:8093" "--entitlements-url=https://hn10-cloud-entitlements.lol.qq.com:28088/api/token/v1" "--dradis-endpoint=http://some.url" "--remoting-auth-token=J07oKpgu9rtTCRb7SlFWpw" "--app-port=49359" "--install-directory=e:\we\鑻遍泟鑱旂洘\LeagueClient" "--app-name=LeagueClient" "--ux-name=LeagueClientUx" "--ux-helper-name=LeagueClientUxHelper" "--log-dir=LeagueClient Logs" "--crash-reporting=" "--crash-environment=HN10" "--app-log-file-path=e:/we/英雄联盟/LeagueClient/../Game/Logs/LeagueClient Logs/2023-09-01T06-25-38_10332_LeagueClient.log" "--app-pid=10332" "--output-base-dir=e:/we/鑻遍泟鑱旂洘/LeagueClient/../Game" "--no-proxy-server" "--ignore-certificate-errors"'
const str = ref('')
const reg = /"([^"]*)"/g;




// axios.get('/api/lol-chat/v1/me',{
// }).then((res)=>{
//     console.log(res);
// })

function Loading(){
  if(sessionStorage.getItem("port")){
    router.push({"name":"Lolchat"})
  }else{
    const result = str.value.match(reg)
    const remotingauthtoken = result[20].replace(/\"/g, "").split("--remoting-auth-token=")[1]
    const port = result[21].replace(/\"/g, "").split("--app-port=")[1]
    console.log("Token : " + remotingauthtoken);
    const Authorization  = "Basic " + Base64.encode("riot:" + remotingauthtoken)
    localStorage.setItem("Authorization",Authorization)
    localStorage.setItem("port",port)
    console.log(localStorage.getItem("Authorization"));
    console.log("端口号 ：" + port);
    //axios.defaults.headers.common['Authorization'] = Authorization;
    //axios.defaults.headers.post['Content-Type'] = 'application/json';
    router.push({"name":"LOLFind"})
  }
}

onMounted(() => {

});
</script>

<template>
  <el-form :model="form" label-width="120px">
    <el-form-item label="请输入字符串">
      <el-input v-model="str" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="Loading">进入</el-button>
      <el-button>清空</el-button>
    </el-form-item>
  </el-form>
</template>

<style scoped>

</style>
