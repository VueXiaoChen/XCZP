<script setup lang="ts">
import axios from "axios";
import { ref, reactive,getCurrentInstance } from "vue";
import { onMounted } from "vue";
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";

const { proxy } = getCurrentInstance();
const Router = useRouter();
const validate = ref()
const seccode = ref()
const formLabelAlign = reactive({
  SESSDATA: "",
  bili_jct: "",
  DedeUserID: "",
});
//跳转页面
function loadings(){
  Router.push({name:'sumber'})
}

//申请captcha验证码
const posschallenge = ref("");
const posstoken = ref("");
const possgit = ref("");
async function GetCaptchaCode() {
  await axios
    .get("post/x/passport-login/captcha?source=main-fe-header")
    .then((res) => {
      
      posstoken.value = res.data.data.token;
      localStorage.setItem("token", posstoken.value);
      posschallenge.value = res.data.data.geetest.challenge;
      possgit.value = res.data.data.geetest.gt;
      console.log("gt="+ possgit.value);
      console.log("challenge="+posschallenge.value);
      GtCaptcha(possgit.value,posschallenge.value)
      //密码登录的函数
      GetPassportKey();

    });
}
const posshash = ref();
const posskey = ref();
//获取B站公共盐
async function GetPassportKey() {
  await axios.get("post/x/passport-login/web/key").then((res) => {
    posshash.value = res.data.data.hash;
    posskey.value = res.data.data.key.replace(/\n/g , "\\\n"); 
    console.log("Hash="+ posshash.value);
    GetEncode(posskey.value,posshash.value);
  });
}
//获取加密之后的密码
const possencode = ref("");
async function GetEncode(keys,hash) {
  await axios
    .post("encode/user/getpassword", {
      key: "-----BEGIN PUBLIC KEY-----\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDjb4V7EidX/ym28t2ybo0U6t0n\n6p4ej8VjqKHg100va6jkNbNTrLQqMCQCAYtXMXXp2Fwkk6WR+12N9zknLjf+C9sx\n/+l48mjUU8RqahiFD1XT/u2e0m2EN029OhCgkHx3Fc/KlFSIbak93EH/XlYis0w+\nXl69GV6klzgxW6d2xQIDAQAB\n-----END PUBLIC KEY-----\n",
      //key:keys,
      hash: hash,
      password: 5644566454,
      // split: "0",
    })
    .then((res) => {
      possencode.value = res.data.data;
    });
}
//登录
async function UserLoading(validate,seccode) {
  let parm = {
    username: '18571626201',
    password: possencode.value,
    source:'main-fe-header',
    // keep: 0,
    token: posstoken.value,
    challenge: posschallenge.value,
    validate: validate,
    seccode: seccode,
  };
  let parms ='username=18571626201&password="' +possencode.value +"&keep=0&token=" +posstoken.value +"&challenge=" +posschallenge.value +"&validate=2da9b87588e0e5780eb8a0fa6c23501a&seccode=2da9b87588e0e5780eb8a0fa6c23501a|jordan";
  await axios
    .post("/post/x/passport-login/web/login", proxy.$qs.stringify(parm), {
      headers: {
        "content-type": "application/x-www-form-urlencoded; charset=UTF-8",
      },
    })
    .then((res) => {
      console.log(res);
    });
}
//获取检验
// async function GtCaptcha(gt,challenge) {

//   initGeetest(
//     {
//       // 以下配置参数来自服务端 SDK
//       gt: gt,
//       challenge: challenge,
//       //offline: true,
//       //new_captcha: true,
//       //width: "100%",
//       product: "popup",
//       //https: true,
//       lang: 'zh-cn',
//     },
//     function(captchaObj) {
//       captchaObj.appendTo("#geetest");
//       captchaObj
//         .onReady(function() {
//           console.log("准备好了");
//         })
//         .onSuccess(function() {
//           let result = captchaObj.getValidate();
//           validate.value = result.geetest_validate
//           seccode.value = result.geetest_seccode
//           console.log(validate.value);
//           console.log(seccode.value);
//           //此处用的密码登录函数
//           UserLoading(validate.value,seccode.value)
//           //此处用的的短信登录函数
//           //GetPhoneSms(validate.value,seccode.value)
//         })
//         .onError(function() {
//           console.log("错误");
//         });
//     }
//   );
// }

function SetCookie(){
  localStorage.setItem("SESSDATA",'SESSDATA='+formLabelAlign.SESSDATA)
  localStorage.setItem("bili_jct",'bili_jct='+formLabelAlign.bili_jct)
  localStorage.setItem("DedeUserID",'DedeUserID='+formLabelAlign.DedeUserID)
  localStorage.setItem("SESSDATAs",formLabelAlign.SESSDATA)
  localStorage.setItem("bili_jcts",formLabelAlign.bili_jct)
  localStorage.setItem("DedeUserIDs",formLabelAlign.DedeUserID)
  Router.push({name:'Ceshi'})
}

function RestCookie(){
  formLabelAlign.SESSDATA=''
  formLabelAlign.bili_jct=''
  formLabelAlign.DedeUserID=''
}
//判断是否存在localStorage
function LoadingOrlocalStorage(){
  if(localStorage.getItem('SESSDATAs')){
    formLabelAlign.SESSDATA = localStorage.getItem('SESSDATAs')
  }
  if(localStorage.getItem('bili_jcts')){
    formLabelAlign.bili_jct = localStorage.getItem('bili_jcts')
  }
  if(localStorage.getItem('DedeUserIDs')){
    formLabelAlign.DedeUserID = localStorage.getItem('DedeUserIDs')
  }
}


//申请二维码
async function GetQrcodeGenerate(){
  await axios.get("post/x/passport-login/web/qrcode/generate").then((res) => {
    console.log(res);
    window.location.href = res.data.data.url
    //QrcodeGenerateLoading(res.data.data.qrcode_key)
  });
}
//二维码登录
async function QrcodeGenerateLoading(qrcode_key){
  await axios.get("post/x/passport-login/web/qrcode/poll?qrcode_key="+qrcode_key).then((res) => {
    console.log(res);
  });
}


//发送手机验证码验证码
async function GetPhoneSms(validate,seccode){
  let parm = {
    cid:1,
    tel:'18571626201',
    source:'main_mini',
    token:posstoken.value,
    challenge:posschallenge.value,
    validate:validate,
    seccode:seccode,
  }
  await axios.post("post/x/passport-login/web/sms/send",proxy.$qs.stringify(parm),{
    headers: {
        "content-type": "application/x-www-form-urlencoded; charset=UTF-8",
      },
  }).then((res) => {
    console.log(res);
  });
}

onMounted(() => {
  LoadingOrlocalStorage()
  //GetQrcodeGenerate()
  //GetCaptchaCode()
  
});
</script>
  <template>
  <div class="Sessdata">
  <div id="geetest"></div>
  <div style="margin: 20px" />
  <el-form
    label-width="100px"
    :model="formLabelAlign"
    style="max-width: 460px"
  >
    <el-form-item label="SESSDATA:">
      <el-input v-model="formLabelAlign.SESSDATA" />
    </el-form-item>
    <el-form-item label="bili_jct:">
      <el-input v-model="formLabelAlign.bili_jct" />
    </el-form-item>
    <el-form-item label="DedeUserID:">
      <el-input v-model="formLabelAlign.DedeUserID" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="SetCookie" v-onceClick>
        设置Cookie
      </el-button>
      <el-button  type="primary" @click="RestCookie" >清空</el-button>
      <el-button type="primary" @click="loadings" v-onceClick>登录</el-button>
    </el-form-item>
  </el-form>
</div>
</template>


<style >
.Sessdata{
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>


