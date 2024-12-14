<script setup lang="ts">
import axios from "axios";
import { ref, getCurrentInstance } from "vue";
import { onMounted } from "vue";
import { loadRouteLocation, useRouter } from "vue-router";
import { ElMessage } from "element-plus";
import { Md5 } from "ts-md5";
import { Header } from "element-plus/es/components/table-v2/src/components";

const { proxy } = getCurrentInstance();

// console.log(localStorage.getItem("SESSDATA"));
// console.log(localStorage.getItem("bili_jct"));
// console.log(localStorage.getItem("DedeUserID"));

const SESSDATA = ref()
const bili_jct = ref()
const DedeUserID = ref()

function SetCookie(){
  SESSDATA.value=localStorage.getItem("SESSDATAs")
  bili_jct.value=localStorage.getItem("bili_jcts")
  DedeUserID.value=localStorage.getItem("DedeUserIDs")
  document.cookie =localStorage.getItem("SESSDATA")
  document.cookie =localStorage.getItem("bili_jct")
  document.cookie =localStorage.getItem("DedeUserID")
}


//路由
const Router = useRouter();
//模拟数据
const datas: any = ref([]);
//老的版本数据
const olddatas: any = ref([]);
//所有视频的video
const videoalldata = ref([]);
//存储用于所有的视频的个数
const videoallcount = ref(0);
//所有视频的上一个下一个
const videoallpage = ref(0);
//评论总数
const commentcount = ref(0);
//发表时间
const videolength = ref(0);
//播放量
const videoplay = ref(0);
//视频封面
const videopic = ref("");
//判断分页
const pageboolean = ref([]);
//当前页数
const currentPage = ref(1);
//当前总评论页数
const currentcountPage = ref(1);
//当前评论的位置
const commmentindex = ref(null);
//当前评论的rpid
const commentrpid = ref(0);
//详细评论
const DetailedComment: any = ref([]);
//滚动条ref
const Scrollref = ref(null);
//滚动条判断
const ScrollrefBoolean = ref(false);
//视频的oid
const videooid: any = ref("");
//视频评论的排序
const commentsort = ref(2);
//是否添加置顶的评论
const topcommment = ref(false);
//防止点击回复，点赞，时间时触发了滚动条
const clickScrollrefBoolean = ref(false);

//置顶的dom节点ref
const topref = ref(null);
//时间处理函数
function RenderTime(date) {
  var dateee = new Date(date * 1000).toJSON();
  return new Date(+new Date(dateee) + 8 * 3600 * 1000)
    .toISOString()
    .replace(/T/g, " ")
    .replace(/\.[\d]{3}Z/, "");
}
const mixinKeyEncTab = [
  46, 47, 18, 2, 53, 8, 23, 32, 15, 50, 10, 31, 58, 3, 45, 35, 27, 43, 5, 49,
  33, 9, 42, 19, 29, 28, 14, 39, 12, 38, 41, 13, 37, 48, 7, 16, 24, 55, 40, 61,
  26, 17, 0, 1, 60, 51, 30, 4, 22, 25, 54, 21, 56, 59, 6, 63, 57, 62, 11, 36,
  20, 34, 44, 52,
];

// 对 imgKey 和 subKey 进行字符顺序打乱编码
function getMixinKey(orig) {
  let temp = "";
  mixinKeyEncTab.forEach((n) => {
    temp += orig[n];
  });
  return temp.slice(0, 32);
}

// 为请求参数进行 wbi 签名
function encWbi(params, img_key, sub_key) {
  const mixin_key = getMixinKey(img_key + sub_key),
    curr_time = Math.round(Date.now() / 1000),
    chr_filter = /[!'\(\)*]/g;
  let query: any = [];
  params = Object.assign(params, { wts: curr_time }); // 添加 wts 字段
  // 按照 key 重排参数
  Object.keys(params)
    .sort()
    .forEach((key) => {
      query.push(
        encodeURIComponent(key) +
          "=" +
          // 过滤 value 中的 "!'()*" 字符
          encodeURIComponent(("" + params[key]).replace(chr_filter, ""))
      );
    });
  query = query.join("&");
  const wbi_sign = Md5.hashAsciiStr(query + mixin_key); // 计算 w_rid
  return query + "&w_rid=" + wbi_sign;
}

// 获取最新的 img_key 和 sub_key
async function getWbiKeys() {
  await axios.get("/sumber/x/web-interface/nav").then((resp) => {
    let img_url = resp.data.data.wbi_img.img_url;
    let sub_url = resp.data.data.wbi_img.sub_url;
    let img_key = img_url
      .substring(img_url.lastIndexOf("/") + 1, img_url.length)
      .split(".")[0];
    let sub_key = sub_url
      .substring(sub_url.lastIndexOf("/") + 1, sub_url.length)
      .split(".")[0];
    sessionStorage.setItem("img_key", img_key);
    sessionStorage.setItem("sub_key", sub_key);
    localStorage.setItem("img_key", img_key);
    localStorage.setItem("sub_key", sub_key);
  });
}
//更新wpi签名
async function UpdateWpi() {
  await getWbiKeys();
}

const videoarrbv = ref([])
//获取所有视频的BV号
async function GetUpAllVideoBv(data){
  //先清空,保持永远最新的是前50个视频
  videoarrbv.value=[]
  for(let i=0;i<data.length;i++){
    videoarrbv.value.push(data[i].bvid)
  }
}

//为所有的视频添加一件三连
async function SetVideoTriple(bvarr){
  for(let i =0 ;i<bvarr.length;i++){
    //暂时不用
    UserTriple(bvarr[i])
  }
}
//获取所有视频
async function GetUpAllVideo(sort, index, upmid) {
  UpdateWpi();
  const allvideoquery = encWbi(
    {
      mid: upmid,
      pn: 1,
      ps: 50,
    },
    sessionStorage.getItem("img_key"),
    sessionStorage.getItem("sub_key")
  );
  await axios
    .get("/sumber/x/space/wbi/arc/search?" + allvideoquery)
    .then((res) => { 
      //视频所有数据
      videoalldata.value = res.data.data.list.vlist;
      //传入数据获取所有的bv-50个
      GetUpAllVideoBv(videoalldata.value)
      //视频评论所有数据
      commentcount.value = res.data.data.list.vlist[index].comment;
      //视频长度
      videolength.value = res.data.data.list.vlist[index].length;
      //视频播放量
      videoplay.value = res.data.data.list.vlist[index].play;
      //视频封面
      videopic.value = res.data.data.list.vlist[index].pic;
      //视频oid
      videooid.value = res.data.data.list.vlist[index].aid;
      //按视频的点赞 时间 回复获取
      commentsort.value = sort;
      //获取视频评论
      GetAllComment(commentsort.value, videooid.value, 1);
    });
}
//获取视频和评论
async function GetVideoAndComment(page) {
  commentcount.value = videoalldata.value[page].comment;
  videolength.value = videoalldata.value[page].length;
  videoplay.value = videoalldata.value[page].play;
  videopic.value = videoalldata.value[page].pic;
  videooid.value = videoalldata.value[page].aid;
  datas.value = [];
  GetAllComment(commentsort.value, videooid.value, 1);
}


//获取单个视频详细信息
async function GetOnlyVideo(bvid) {
  // BV1zk4y1M7Eo
  await axios.get("/sumber/x/web-interface/view?bvid=" + bvid).then((res) => {
    //暂时不用
  });
}
//滚动条监听事件
function ScrollEvent(e) {
  //判断点击了按时间 回复 点赞获取
  if (clickScrollrefBoolean.value) {
    clickScrollrefBoolean.value = false;
    return;
  }
  //滚动条获取评论
  if (
    Scrollref.value.wrap$.offsetHeight + e.scrollTop >=
    Scrollref.value.wrap$.scrollHeight
  ) {
    if (ScrollrefBoolean.value) {
      ElMessage({
        showClose: true,
        message: "已经没有评论啦!!!!",
        type: "success",
      });
      return;
    } else {
      //分页+1
      currentcountPage.value += 1;
      GetAllComment(commentsort.value, videooid.value, currentcountPage.value);
    }
  }
}
//获取评论,按最热，时间，回复来
async function GetSortAllComment(sort, page) {
  //清空评论数据
  datas.value = [];
  //按时间 回复 点赞获取
  clickScrollrefBoolean.value = true;
  //获取数据
  GetAllComment(sort, videooid.value, 1);
}
//获取所有评论
async function GetAllComment(sort, oid, page) {
  let url =
    "/sumber/x/v2/reply?type=1&ps=20&sort=" + sort + "&oid=" + oid + "&pn=" + page;
  //await axios.get("/sumber/x/v2/reply?type=1&ps=15&oid=742469384&pn="+page+"&sort=2").then((res) => {
  //await axios.get("/sumber/x/v2/reply?type=1&ps=20&oid=" + oid + "&pn=" + page).then((res) => {
  await axios.get(url).then((res) => {
    if (res.data.data.replies === null) {
      ScrollrefBoolean.value = true;
      ElMessage({
        showClose: true,
        message: "已经没有评论啦!!!!",
        type: "success",
      });
      return;
    }
    //添加置顶的评论
    if (topcommment.value === false && res.data.data.upper.top) {
      datas.value.push(res.data.data.upper.top);
      topcommment.value = true;
    }
    commentcount.value = res.data.data.page.count;
    for (let i = 0; i < res.data.data.replies.length; i++) {
      datas.value.push(res.data.data.replies[i]);
    }
    //判断上一个显示全部评论将其隐藏
    if (commmentindex.value != null) {
      pageboolean.value[commmentindex.value] = false;
      datas.value[commmentindex.value].replies =
        olddatas.value[commmentindex.value].replies;
    }
    //深度克隆将数据发生改变
    olddatas.value = JSON.parse(JSON.stringify(datas.value));
  });
}
//获取指定评论区所有评论
async function GetDetailedComment(page, index, rpid) {
  //判断上一个显示全部评论将其隐藏
  if (commmentindex.value != null) {
    pageboolean.value[commmentindex.value] = false;
    datas.value[commmentindex.value].replies =
      olddatas.value[commmentindex.value].replies;
  }
  //设定当前分页
  currentPage.value = page;
  //设定当前评论的位置
  commmentindex.value = index;
  //设定当前评论的rpid
  commentrpid.value = rpid;
  //查询当前的评论
  GetPageDetailedComment(
    currentPage.value,
    commmentindex.value,
    commentrpid.value
  );
  //显示分页
  pageboolean.value[index] = true;
}
//获取指定评论区分页的评论
async function GetPageDetailedComment(page, index, rpid) {
  await axios
    .get(
      "/sumber/x/v2/reply/reply?type=1&oid=" +
        videooid.value +
        "&ps=15&pn=" +
        page +
        "&root=" +
        rpid
    )
    .then((res) => {
      datas.value[index].replies = res.data.data.replies;
    });
}
//分页
async function PageCurrentChange(number) {
  currentPage.value = number;
  GetPageDetailedComment(
    currentPage.value,
    commmentindex.value,
    commentrpid.value
  );
}
//上一页
function GetPageTop() {
  datas.value = [];
  videoallpage.value -= 1;
  if (videoallpage.value < 0) {
    videoallpage.value = 0;
    ElMessage({
      showClose: true,
      message: "已经没有上一个视频啦!!!!",
      type: "success",
    });
    return;
  }
  GetVideoAndComment(videoallpage.value);
}
//下一个视频
async function GetPageNext() {
  datas.value = [];
  videoallpage.value += 1;
  if (videoallpage.value >= videoalldata.value.length) {
    videoallpage.value = videoalldata.value.length - 1;
    ElMessage({
      showClose: true,
      message: "已经没有下一个视频啦!!!!",
      type: "success",
    });
    return;
  }
  GetVideoAndComment(videoallpage.value);
}

//更换Up主
function GetChangeUP() {
  GetUpAllVideo(0,0,"1254444178");
}

//获取自己的用户信息
async function GetUserInformation(){
  await axios.get("/sumber/nav?SESSDATA="+SESSDATA.value).then((res) => {
    console.log(res);
  });
}

//查询投币信息
async function UserCoinImformation() {
  await axios.get("sumber/x/member/web/coin/log").then((res) => {
    console.log(res);
  });
}

//投币频
//cookie的bili_jct就是csrf
async function UserCoin(bv,coin) {
  let datas = {
    bvid: bv,
    multiply: coin,
    csrf:bili_jct.value,
  };
  axios({
    method: "POST",
    url: "sumber/x/web-interface/coin/add",
    headers: {
      "content-type": "application/x-www-form-urlencoded; charset=UTF-8",
    },
    data: proxy.$qs.stringify(datas), //params会添加到url的请求字符串中，用于get请求。而data是添加到请求体（body）中的， 用于post请求。
    })
    .then((res) => {
      console.info(res);
    })
    .catch((error) => {
      console.info(error);
    });
}
//判断视频是否投币
async function UserOrCoin(bv){
  await axios.get("sumber/x/web-interface/archive/coins?bvid="+bv).then((res) => {
    console.log(res);
    if(res.data.data.multiply===0){
      console.log("没有投币");
    }else if(res.data.data.multiply===1){
      console.log("投币1枚");
    }else if(res.data.data.multiply===2){
      console.log("投币2枚");
  }});
}

//视频点赞
async function UserLike(bv){
  await axios.post("sumber/x/web-interface/archive/like","bvid="+bv+"&like=1&csrf="+bili_jct.value,{
    headers: {
      "content-type": "application/x-www-form-urlencoded; charset=UTF-8",
    },
  }).then((res) => {
    console.log(res);
  });
}
//判断视频是否普点赞
async function UserOrLike(bv){
  await axios.get("sumber/x/web-interface/archive/has/like?bvid="+bv).then((res) => {
    console.log(res);
    if(res.data.data===1){
      console.log("已经点赞");
    }else{
      console.log("没有点赞");
    }
  });
}
//一键三连
async function UserTriple(bv){
  await axios.post("sumber/x/web-interface/archive/like/triple","bvid="+bv+"&like=1&csrf="+bili_jct.value,{
    headers: {
      "content-type": "application/x-www-form-urlencoded; charset=UTF-8",
    },
  }).then((res) => {
    if(res.data.data.like===true){
      console.log("已经一键三连了");
    }
  });
}


//
const doclist = ref([])
async function GetLikeDraw(){
  // 'https://sumber.bilibili.com/x/dynamic/feed/draw/doc_list?uid=492659913&page_num=0&page_size=30&biz=all'
  await axios.get('sumber/x/dynamic/feed/draw/doc_list?uid=492659913&page_num=0&page_size=30&biz=all').then((res)=>{
    //console.log(res);
  })
  
}

onMounted(async () => {
  GetUpAllVideo(0,0,"492659913")
  //GetUpAllVideo(0,0,"242144977")
  // setTimeout(() => {
  //   GetUpAllVideo(0,0,"242144977")
  // }, 100);
  //GetUpAllVideo(0,0,"242144977");
  //GetOnlyVideo()
  //GetAllComment(2,"37754047",1)
  //GetDetailedComment()
  //console.log(Math.floor(247/50));
  //GetChangeUP()
  //GetUserInformation()
  //GetPassportKey()
  //GetCaptchaCode()
  SetCookie()
  //GetLikeDraw()
  //GetUserInformation()
  //GetLikeDraw()
  //UserTriple('BV1Ft4y1A7rY')
  //UserCoin();
  //UserOrCoin('BV1yP411e7Du')
  //UserLike('BV1yP411e7Du')
  //UserOrLike('BV1yP411e7Du')
  
  //
});
</script>

<template>
  <div class="panel">
    <!-- 遮罩 -->
    <!-- <div class="mask" v-if="maskboolean" ></div>
    <div class="model-panel" v-if="maskboolean">
      <div class="model" ></div>
    </div> -->
    <div class="panel-left">
      <div class="card">
        <div class="box">
          <div class="imgBx">
            <!-- <img
              src="https://img1.baidu.com/it/u=413643897,2296924942&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500"
            /> -->
            <img :src="videopic" />
          </div>
        </div>
        <div class="box">
          <div class="content">
            <h2>夏医生的日常<br /><span>Doctor-Summer</span></h2>
            <ul>
              <li>
                播放量<span>{{ videoplay }}</span>
              </li>
              <li>
                时长<span>{{ videolength }}</span>
              </li>
              <li>
                评论数<span>{{ commentcount }}</span>
              </li>
            </ul>
            <button @click="">播放</button>
          </div>
        </div>
        <div class="circle">
          <div class="imgBx">
            <img :src="videopic" />
          </div>
        </div>
      </div>
      <div class="card-btn">
        <div class="btn">
          <button @click="GetPageTop" v-onceClick>上一页</button>
          <button v-onceClick @click="GetChangeUP">更换UP主</button>
          <button @click="GetPageNext" v-onceClick>下一页</button>
          <button @click="SetVideoTriple(videoarrbv)" v-onceClick>批量三连</button>
        </div>
      </div>
    </div>
    <div class="panel-rigth">
      <div class="comment-btn">
        <div class="btn">
          <button v-onceClick @click="GetSortAllComment(1, 1)">点赞</button>
          <button v-onceClick @click="GetSortAllComment(0, 1)">按时间</button>
          <button v-onceClick @click="GetSortAllComment(2, 1)">按回复</button>
        </div>
      </div>
      <div class="comment">
        <el-scrollbar
          style="height: 99%"
          wrap-style="overflow-x:hidden;"
          @scroll="ScrollEvent($event)"
          ref="Scrollref"
        >
          <div class="reply-item" v-for="(item, index) in datas" :key="index">
            <div class="detailed-content">
              <div class="root-reply-avatar">
                <div class="avatar">
                  <div class="bili-avatar">
                    <img :src="item.member.avatar" />
                  </div>
                </div>
              </div>
              <div class="content-warp">
                <!-- <div class="reply-decorate"></div> -->
                <div class="user-info">
                  <div class="user-name">
                    {{ item.member.uname }}
                  </div>
                </div>
                <div class="root-reply" ref="topref">
                  <!-- <img class="emoji-small" src="//i0.hdslb.com/bfs/emote/bf7e00ecab02171f8461ee8cf439c73db9797748.png@48w_48h.webp" alt="[脱单doge]"> -->
                  <span class="root-reply-span"
                    ><span class="root-reply-span-conment">{{
                      item.content.message
                    }}</span></span
                  >
                  <div class="reply-info">
                    <span class="reply-time">{{ RenderTime(item.ctime) }}</span>
                    <span class="reply-like">
                      <i class="reply-like-icon">
                        <svg
                          t="1636093575017"
                          class="icon"
                          viewBox="0 0 1024 1024"
                          version="1.1"
                          xmlns="http://www.w3.org/2000/svg"
                          p-id="3323"
                          width="200"
                          height="200"
                        >
                          <path
                            d="M594.176 151.168a34.048 34.048 0 0 0-29.184 10.816c-11.264 13.184-15.872 24.064-21.504 40.064l-1.92 5.632c-5.632 16.128-12.8 36.864-27.648 63.232-25.408 44.928-50.304 74.432-86.208 97.024-23.04 14.528-43.648 26.368-65.024 32.576v419.648a4569.408 4569.408 0 0 0 339.072-4.672c38.72-2.048 72-21.12 88.96-52.032 21.504-39.36 47.168-95.744 63.552-163.008a782.72 782.72 0 0 0 22.528-163.008c0.448-16.832-13.44-32.256-35.328-32.256h-197.312a32 32 0 0 1-28.608-46.336l0.192-0.32 0.64-1.344 2.56-5.504c2.112-4.8 5.12-11.776 8.32-20.16 6.592-17.088 13.568-39.04 16.768-60.416 4.992-33.344 3.776-60.16-9.344-84.992-14.08-26.688-30.016-33.728-40.512-34.944zM691.84 341.12h149.568c52.736 0 100.864 40.192 99.328 98.048a845.888 845.888 0 0 1-24.32 176.384 742.336 742.336 0 0 1-69.632 178.56c-29.184 53.44-84.48 82.304-141.76 85.248-55.68 2.88-138.304 5.952-235.712 5.952-96 0-183.552-3.008-244.672-5.76-66.432-3.136-123.392-51.392-131.008-119.872a1380.672 1380.672 0 0 1-0.768-296.704c7.68-72.768 70.4-121.792 140.032-121.792h97.728c13.76 0 28.16-5.504 62.976-27.456 24.064-15.104 42.432-35.2 64.512-74.24 11.904-21.184 17.408-36.928 22.912-52.8l2.048-5.888c6.656-18.88 14.4-38.4 33.28-60.416a97.984 97.984 0 0 1 85.12-32.768c35.264 4.096 67.776 26.88 89.792 68.608 22.208 42.176 21.888 84.864 16 124.352a342.464 342.464 0 0 1-15.424 60.544z m-393.216 477.248V405.184H232.96c-40.448 0-72.448 27.712-76.352 64.512a1318.912 1318.912 0 0 0 0.64 282.88c3.904 34.752 32.96 61.248 70.4 62.976 20.8 0.96 44.8 1.92 71.04 2.816z"
                            p-id="3324"
                            fill="#9499a0"
                          ></path>
                        </svg> </i
                      ><span class="likecount">{{ item.like }}</span>
                    </span>
                    <span class="reply-dislike">
                      <i class="reply-dislike-icon">
                        <svg
                          t="1636093677814"
                          class="icon"
                          viewBox="0 0 1024 1024"
                          version="1.1"
                          xmlns="http://www.w3.org/2000/svg"
                          p-id="3933"
                          width="200"
                          height="200"
                        >
                          <path
                            d="M594.112 872.768a34.048 34.048 0 0 1-29.12-10.816c-11.264-13.248-15.872-24.064-21.504-40.064l-1.92-5.632c-5.632-16.128-12.8-36.864-27.712-63.232-25.344-44.928-50.24-74.432-86.144-97.024-23.104-14.528-43.648-26.432-65.024-32.64V203.84a4570.24 4570.24 0 0 1 339.072 4.672c38.656 2.048 72 21.12 88.896 52.032 21.504 39.36 47.232 95.744 63.552 163.008 16.448 67.52 21.568 123.776 22.592 163.008 0.448 16.832-13.44 32.256-35.392 32.256h-197.248a32 32 0 0 0-28.608 46.336l0.128 0.32 0.64 1.28 2.56 5.568c2.176 4.8 5.12 11.776 8.384 20.16 6.528 17.088 13.568 39.04 16.768 60.416 4.928 33.344 3.712 60.16-9.344 84.992-14.08 26.688-30.016 33.728-40.576 34.944z m97.728-190.016h149.568c52.8 0 100.864-40.128 99.392-97.92a846.336 846.336 0 0 0-24.32-176.448 742.016 742.016 0 0 0-69.632-178.56c-29.248-53.44-84.48-82.304-141.824-85.248-55.68-2.88-138.24-5.952-235.712-5.952-96 0-183.488 3.008-244.672 5.76-66.368 3.136-123.328 51.392-130.944 119.872a1380.608 1380.608 0 0 0-0.768 296.704c7.68 72.768 70.4 121.792 140.032 121.792h97.728c13.76 0 28.16 5.504 62.976 27.392 24.064 15.168 42.432 35.264 64.448 74.368 11.968 21.12 17.472 36.864 22.976 52.736l2.048 5.888c6.656 18.88 14.336 38.4 33.216 60.416 19.456 22.72 51.456 36.736 85.184 32.768 35.2-4.096 67.776-26.88 89.792-68.672 22.208-42.112 21.888-84.8 16-124.288a343.04 343.04 0 0 0-15.488-60.608zM298.688 205.568v413.184H232.96c-40.512 0-72.448-27.712-76.352-64.512a1318.912 1318.912 0 0 1 0.64-282.88c3.904-34.816 32.896-61.248 70.4-62.976 20.8-0.96 44.736-1.92 71.04-2.816z"
                            p-id="3934"
                            fill="#9499a0"
                          ></path>
                        </svg> </i
                      ><span class="likecount"></span>
                    </span>
                  </div>
                </div>
              </div>
            </div>
            <div class="sub-reply-container">
              <div
                class="sub-reply-list"
                v-for="(items, indexs) in datas[index].replies == null
                  ? 0
                  : datas[index].replies"
                :key="indexs"
              >
                <div class="sub-reply-item">
                  <div class="sub-user-info">
                    <div class="sub-reply-avatar">
                      <div class="avatar">
                        <div class="bili-avatar">
                          <img
                            class="bili-avatar-img"
                            :src="items.member.avatar"
                          />
                        </div>
                      </div>
                    </div>
                    <div class="sub-user-name">
                      {{ items.member.uname }}
                    </div>
                  </div>
                  <span class="sub-reply-content">
                    <span class="reply-content">{{
                      items.content.message
                    }}</span>
                  </span>
                  <div class="sub-reply-info">
                    <span class="sub-reply-time">{{
                      RenderTime(items.ctime)
                    }}</span>
                    <span class="sub-reply-like">
                      <i class="sub-like-icon">
                        <svg
                          t="1636093575017"
                          class="icon"
                          viewBox="0 0 1024 1024"
                          version="1.1"
                          xmlns="http://www.w3.org/2000/svg"
                          p-id="3323"
                          width="200"
                          height="200"
                        >
                          <path
                            d="M594.176 151.168a34.048 34.048 0 0 0-29.184 10.816c-11.264 13.184-15.872 24.064-21.504 40.064l-1.92 5.632c-5.632 16.128-12.8 36.864-27.648 63.232-25.408 44.928-50.304 74.432-86.208 97.024-23.04 14.528-43.648 26.368-65.024 32.576v419.648a4569.408 4569.408 0 0 0 339.072-4.672c38.72-2.048 72-21.12 88.96-52.032 21.504-39.36 47.168-95.744 63.552-163.008a782.72 782.72 0 0 0 22.528-163.008c0.448-16.832-13.44-32.256-35.328-32.256h-197.312a32 32 0 0 1-28.608-46.336l0.192-0.32 0.64-1.344 2.56-5.504c2.112-4.8 5.12-11.776 8.32-20.16 6.592-17.088 13.568-39.04 16.768-60.416 4.992-33.344 3.776-60.16-9.344-84.992-14.08-26.688-30.016-33.728-40.512-34.944zM691.84 341.12h149.568c52.736 0 100.864 40.192 99.328 98.048a845.888 845.888 0 0 1-24.32 176.384 742.336 742.336 0 0 1-69.632 178.56c-29.184 53.44-84.48 82.304-141.76 85.248-55.68 2.88-138.304 5.952-235.712 5.952-96 0-183.552-3.008-244.672-5.76-66.432-3.136-123.392-51.392-131.008-119.872a1380.672 1380.672 0 0 1-0.768-296.704c7.68-72.768 70.4-121.792 140.032-121.792h97.728c13.76 0 28.16-5.504 62.976-27.456 24.064-15.104 42.432-35.2 64.512-74.24 11.904-21.184 17.408-36.928 22.912-52.8l2.048-5.888c6.656-18.88 14.4-38.4 33.28-60.416a97.984 97.984 0 0 1 85.12-32.768c35.264 4.096 67.776 26.88 89.792 68.608 22.208 42.176 21.888 84.864 16 124.352a342.464 342.464 0 0 1-15.424 60.544z m-393.216 477.248V405.184H232.96c-40.448 0-72.448 27.712-76.352 64.512a1318.912 1318.912 0 0 0 0.64 282.88c3.904 34.752 32.96 61.248 70.4 62.976 20.8 0.96 44.8 1.92 71.04 2.816z"
                            p-id="3324"
                            fill="#9499a0"
                          ></path>
                        </svg>
                      </i>
                      <span>{{ items.like == 0 ? "" : items.like }}</span>
                    </span>
                    <span class="sub-reply-dislike">
                      <i class="sub-dislike-icon">
                        <svg
                          t="1636093677814"
                          class="icon"
                          viewBox="0 0 1024 1024"
                          version="1.1"
                          xmlns="http://www.w3.org/2000/svg"
                          p-id="3933"
                          width="200"
                          height="200"
                        >
                          <path
                            d="M594.112 872.768a34.048 34.048 0 0 1-29.12-10.816c-11.264-13.248-15.872-24.064-21.504-40.064l-1.92-5.632c-5.632-16.128-12.8-36.864-27.712-63.232-25.344-44.928-50.24-74.432-86.144-97.024-23.104-14.528-43.648-26.432-65.024-32.64V203.84a4570.24 4570.24 0 0 1 339.072 4.672c38.656 2.048 72 21.12 88.896 52.032 21.504 39.36 47.232 95.744 63.552 163.008 16.448 67.52 21.568 123.776 22.592 163.008 0.448 16.832-13.44 32.256-35.392 32.256h-197.248a32 32 0 0 0-28.608 46.336l0.128 0.32 0.64 1.28 2.56 5.568c2.176 4.8 5.12 11.776 8.384 20.16 6.528 17.088 13.568 39.04 16.768 60.416 4.928 33.344 3.712 60.16-9.344 84.992-14.08 26.688-30.016 33.728-40.576 34.944z m97.728-190.016h149.568c52.8 0 100.864-40.128 99.392-97.92a846.336 846.336 0 0 0-24.32-176.448 742.016 742.016 0 0 0-69.632-178.56c-29.248-53.44-84.48-82.304-141.824-85.248-55.68-2.88-138.24-5.952-235.712-5.952-96 0-183.488 3.008-244.672 5.76-66.368 3.136-123.328 51.392-130.944 119.872a1380.608 1380.608 0 0 0-0.768 296.704c7.68 72.768 70.4 121.792 140.032 121.792h97.728c13.76 0 28.16 5.504 62.976 27.392 24.064 15.168 42.432 35.264 64.448 74.368 11.968 21.12 17.472 36.864 22.976 52.736l2.048 5.888c6.656 18.88 14.336 38.4 33.216 60.416 19.456 22.72 51.456 36.736 85.184 32.768 35.2-4.096 67.776-26.88 89.792-68.672 22.208-42.112 21.888-84.8 16-124.288a343.04 343.04 0 0 0-15.488-60.608zM298.688 205.568v413.184H232.96c-40.512 0-72.448-27.712-76.352-64.512a1318.912 1318.912 0 0 1 0.64-282.88c3.904-34.816 32.896-61.248 70.4-62.976 20.8-0.96 44.736-1.92 71.04-2.816z"
                            p-id="3934"
                            fill="#9499a0"
                          ></path>
                        </svg>
                      </i>
                      <span>{{ items.dislike == 0 ? "" : items.dislike }}</span>
                    </span>
                  </div>
                </div>
              </div>
              <div class="view-more" v-show="item.rcount > 3">
                <!-- v-show="item.rcount > 0 ? 1 : display " -->
                <el-pagination
                  small
                  layout="total,prev, pager, next,"
                  :total="item.rcount"
                  :current-page="currentPage"
                  @current-change="PageCurrentChange"
                  v-show="pageboolean[index] == true"
                />
                <div
                  class="view-more-default"
                  v-show="pageboolean[index] != true"
                >
                  <span>共{{ item.rcount }}条回复,</span>
                  <span
                    class="view-more-btn"
                    @click="GetDetailedComment(1, index, datas[index].rpid)"
                    >显示全部</span
                  >
                </div>
              </div>
            </div>
            <div class="bottom-line"></div>
          </div>
        </el-scrollbar>
      </div>
    </div>
  </div>
















</template>

<style>
.mask {
  position: fixed;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  opacity: 0.5;
  overflow: auto;
  z-index: 99;
}
.model-panel {
  position: absolute;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-around;
  z-index: 100;
}
.model {
  width: 500px;
  height: 300px;
  background-color: #fff;
  cursor: pointer;
}
.el-scrollbar {
  width: 100%;
}
.el-scrollbar .el-scrollbar__view {
  display: flex;
  flex-wrap: wrap;
}
.maskpanel {
  width: 100%;
  height: 100%;
}
.panel {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
}

.panel-rigth,
.panel-left {
  width: 50%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.panel-left,
.panel-rigth {
  flex-direction: column;
}

.card {
  position: relative;
  width: 320px;
  height: 430px;
  /* background: #083d41; */
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.card-btn {
  width: 100%;
  height: 10%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.card-btn .btn {
  width: 95%;
  height: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  /* background: rgba(255, 255, 255, 0.2); */
}

.card-btn .btn button {
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

.card-btn .btn button:hover {
  letter-spacing: 0.5rem;
  background: #ff3d7f;
}

.card .box {
  position: relative;
  width: 110%;
  height: 200px;
  background: #f00;
  border-radius: 15px;
}

.card .box:nth-child(1)::before {
  content: "";
  position: absolute;
  top: 106px;
  left: -1px;
  width: 20px;
  height: 20px;
  background: transparent;
  z-index: 10;
  border-bottom-left-radius: 20px;
  box-shadow: -6px 6px #083d41;
}

.card .box:nth-child(1)::after {
  content: "";
  position: absolute;
  bottom: -1px;
  left: 105px;
  width: 20px;
  height: 20px;
  background: transparent;
  z-index: 10;
  border-bottom-left-radius: 20px;
  box-shadow: -6px 6px #083d41;
}

.card .box:nth-child(2) {
  background: #fff;
  height: 220px;
  width: 100%;
}

.card .box:nth-child(2)::after {
  content: "";
  position: absolute;
  top: -1px;
  left: 109px;
  width: 20px;
  height: 20px;
  background: transparent;
  z-index: 10;
  border-top-left-radius: 20px;
  box-shadow: -6px -6px #083d41;
}

.card .box:nth-child(2)::before {
  content: "";
  position: absolute;
  bottom: 106px;
  left: -1px;
  width: 20px;
  height: 20px;
  background: transparent;
  z-index: 10;
  border-top-left-radius: 20px;
  box-shadow: -6px -6px #083d41;
}

.card .circle {
  position: absolute;
  top: 50%;
  left: -70px;
  transform: translateY(-50%);
  width: 180px;
  height: 180px;
  background: #0f0;
  border-radius: 50%;
  border: 10px solid #083d41;
}

.card .circle .imgBx,
.card .box .imgBx {
  position: absolute;
  inset: 0;
  overflow: hidden;
  border-radius: 50%;
}

.card .box .imgBx {
  border-radius: 15px;
}
.card .circle .imgBx img,
.card .box .imgBx img {
  position: absolute;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.card .box .content {
  position: absolute;
  inset: 0;
  display: flex;
  align-items: center;
  padding: 30px 10px 20px;
  flex-direction: column;
  gap: 20px;
}

.card .box .content h2 {
  width: 100%;
  padding-left: 120px;
  text-transform: uppercase;
  font-size: 1.15rem;
  letter-spacing: 0.1rem;
  font-weight: 600;
  line-height: 1.1rem;
  color: #333;
}

.card .box .content h2 span {
  font-size: 0.75rem;
  font-weight: 400;
  letter-spacing: 0.05rem;
  color: #e91e64;
  text-transform: initial;
}

.card .box .content ul {
  position: relative;
  top: 15px;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  width: 100%;
  padding: 0 10px;
  justify-content: space-evenly;
}

.card .box .content ul li {
  list-style: none;
  display: flex;
  flex-direction: column;
  text-align: center;
  padding: 0 10px;
  font-size: 0.85rem;
  font-weight: 500;
  color: #999;
}

.card .box .content ul li:not(:last-child) {
  border-right: 1px solid #ccc;
}

.card .box .content ul li span {
  margin-top: 8px;
  font-size: 1.65rem;
  color: #333;
}

.card .box .content button {
  position: relative;
  top: 45px;
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
  box-shadow: 0 0 0 10px #fff;
  transition: 0.5s;
}

.card .box .content button:hover {
  letter-spacing: 0.5rem;
  background: #ff3d7f;
}

/* .p-img img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
} */

.panel-rigth .comment {
  width: 80%;
  height: 78%;
  background: #fff;
  border-radius: 10px;
  position: relative;
  margin-bottom: 60px;
}

/* .comment::after{
  content: "最新评论";
  position: absolute;
  top: -30px;
  left: -30px;
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background: #03a9f4;;
  z-index: 10;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  font-size: 13px;
  font-weight: 400;
}

.comment::before{
  content: "最热评论";
  position: absolute;
  top: -30px;
  right: -30px;
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background: #03a9f4;;
  z-index: 10;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  font-size: 13px;
  font-weight: 400;
} */

.comment .reply-item {
  width: 96%;
}
/* .comment-btn{
  width: 80%;
  height: 10%;
  background: red;
} */
.detailed-content {
  width: 98%;
  /* background: #03a9f4; */
  margin-top: 5px;
  margin-left: 5px;
  border-radius: 5px;
  padding: 16px 0 10px 80px;
  position: relative;
}
.detailed-content .root-reply-avatar {
  display: flex;
  justify-content: center;
  position: absolute;
  left: 0;
  width: 80px;
  cursor: pointer;
}
.detailed-content .root-reply-avatar .avatar {
  font-family: PingFang SC, HarmonyOS_Regular, Helvetica Neue, Microsoft YaHei,
    sans-serif;
  font-weight: 400;
  box-sizing: border-box;
  -webkit-font-smoothing: antialiased;
}

.detailed-content .root-reply-avatar .avatar .bili-avatar {
  width: 48px;
  height: 48px;
}
.detailed-content .root-reply-avatar .avatar .bili-avatar img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  object-fit: cover;
}
.content-warp {
  position: relative;
}
.user-name {
  font-family: PingFang SC, HarmonyOS_Medium, Helvetica Neue, Microsoft YaHei,
    sans-serif;
  font-weight: 500;
  margin-right: 5px;
  color: #fb7299;
  /* cursor: pointer; */
}
.root-reply-span {
  position: relative;
  padding: 2px 0;
  font-size: 16px;
  line-height: 26px;
}
.root-reply-span-conment {
  font-family: PingFang SC, HarmonyOS_Regular, Helvetica Neue, Microsoft YaHei,
    sans-serif;
  font-weight: 400;
  box-sizing: border-box;
  -webkit-font-smoothing: antialiased;
  color: #18191c;
  overflow: hidden;
  word-wrap: break-word;
  word-break: break-word;
  white-space: pre-wrap;
  line-height: 24px;
  vertical-align: baseline;
}
.root-reply-span-conment img {
  width: 22px;
  height: 22px;
}
.emoji-small {
  vertical-align: text-bottom;
}
.reply-info {
  display: flex;
  align-items: center;
  position: relative;
  margin-top: 2px;
  font-size: 13px;
  color: #9499a0;
}
.reply-time {
  margin-right: 20px;
}
.reply-like {
  display: flex;
  align-items: center;
  margin-right: 19px;
}
.reply-dislike {
  display: flex;
  align-items: center;
  margin-right: 19px;
}
.reply-dislike-icon,
.reply-like-icon {
  width: 16px;
  height: 16px;
}

.reply-dislike-icon svg,
.reply-like-icon svg {
  width: 100%;
  height: 100%;
}
.likecount {
  margin-left: 4px;
}

.sub-reply-container {
  padding-left: calc(80px - 8px);
}
.sub-reply-item {
  position: relative;
  padding: 8px 0 8px 42px;
  border-radius: 4px;
  font-size: 16px;
  line-height: 26px;
}
.sub-user-info {
  display: inline-flex;
  align-items: center;
  margin-right: 9px;
  line-height: 24px;
  vertical-align: baseline;
  white-space: nowrap;
}
.sub-reply-avatar {
  position: absolute;
  left: 8px;
}
.sub-reply-avatar .bili-avatar {
  width: 30px;
  height: 30px;
  display: block;
  position: relative;
  -webkit-background-size: cover;
  background-size: cover;
  border-radius: 50%;
  margin: 0;
  padding: 0;
}
.sub-reply-avatar .bili-avatar .bili-avatar-img {
  border: none;
  display: block;
  position: absolute;
  width: 100%;
  height: 100%;
  -o-object-fit: cover;
  object-fit: cover;
  image-rendering: -webkit-optimize-contrast;
}
.sub-user-name {
  font-size: 14px;
  line-height: 26px;
  font-weight: 500;
  margin-right: 5px;
  color: #fb7299;
}
.sub-reply-content .reply-content {
  color: #18191c;
  overflow: hidden;
  word-wrap: break-word;
  word-break: break-word;
  white-space: pre-wrap;
  line-height: 24px;
  vertical-align: baseline;
}
.sub-reply-info {
  display: flex;
  align-items: center;
  position: relative;
  margin-top: 2px;
  font-size: 13px;
  color: #9499a0;
}
.sub-reply-time {
  margin-right: 20px;
}
.sub-reply-like,
.sub-reply-dislike {
  display: flex;
  align-items: center;
  margin-right: 19px;
}
.sub-reply-like .sub-like-icon,
.sub-reply-dislike .sub-dislike-icon {
  width: 16px;
  height: 16px;
  margin-right: 5px;
  color: #9499a0;
  display: inline-flex;
  justify-content: center;
  align-items: center;
}
.sub-like-icon svg {
  width: 100%;
  height: 100%;
}
.view-more {
  padding-left: 8px;
  font-size: 13px;
  color: #9499a0;
}
.bottom-line {
  margin-left: 80px;
  border-bottom: 1px solid #e3e5e7;
  margin-top: 14px;
}
.view-more-btn {
  cursor: pointer;
}
.view-more-btn:hover {
  color: #438edb;
}

.comment-btn {
  width: 100%;
  height: 10%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.comment-btn .btn {
  width: 80%;
  height: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  /* background: rgba(255, 255, 255, 0.2); */
}

.comment-btn .btn button {
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

.comment-btn .btn button:hover {
  letter-spacing: 0.5rem;
  background: #ff3d7f;
}
</style>
