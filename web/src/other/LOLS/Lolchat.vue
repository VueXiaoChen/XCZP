<script setup lang="ts">
import { ref, onMounted,watch,reactive } from "vue";

import axios from 'axios'



const find = ref('')
const currentPage = ref(1)
const pageSize = ref(10)

const commentpanel = ref([
  {
    name:"对局日期",
    data:"2",
  },
  {
    name:"对局类型",
    data:"2",
  },
  {
    name:"开始时间",
    data:"2",
  },
  {
    name:"人头对比",
    data:"2/5/5",
  },
  {
    name:"数据显示",
    data:"输出伤害",
  },
  {
    name:"双方比分",
    data:"2",
  },
  {
    name:"对局时长",
    data:"2",
  },
  {
    name:"经济对比",
    data:"2",
  },
])
function handleSizeChange(){
  
}
function handleCurrentChange(){
  
}

onMounted(() => {

});
</script>

<template>
  <div class="lolchatpanel">
    <div class="lol-Header">
      <div class="lol-Header-left">
        <el-input v-model="find" placeholder="请输入召唤师的昵称"></el-input>
        <el-button type="primary">查询</el-button>
      </div>
      <div class="lol-Header-rigth">
        <div class="demo-pagination-block">
          <el-pagination
            v-model:current-page="currentPage"
            v-model:page-size="pageSize"
            background 
            layout="prev, pager, next"
            :total="500"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            />
        </div>
      </div>
    </div>
    <div class="lol-bottom">
      <div class="lol-aside">
        <div class="lol-aside-hero" v-for="(item,index) in 10" :key="index">
          <div class="lol-aside-hero-image">
            <img src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg">
          </div>
          <div class="lol-aside-hero-data">
            <div class="lol-aside-hero-data-comment">
              <span class="record">12-12-13</span><span class="time">9-1</span>
            </div>
            <div class="lol-aside-hero-data-rank">
              <span>极地大乱斗</span>
            </div>
          </div>
          
        </div>
      </div>
      <div class="lol-main">
        <div class="spance"></div>
        <div class="lol-main-data">
          <div class="lol-main-data-conmment">
            <div class="lol-main-data-conmment-panel" v-for="(item,index) in commentpanel" :key="index">
              <div class="panel-text">
                <span>{{item.name}}</span>
              </div>
              <div class="panel-data">
                <span>{{item.data}}</span>
              </div>
            </div>
          </div>
          <div class="lol-main-data-information">
            <div class="panel-information" v-for="(item,index) in 10" :key="index">
              <div class="information-img">
                <img src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg">
              </div>
              <div class="information-name">
                <span>我是傻逼</span>
              </div>
              <div class="information-img" v-for="(item,index) in 6" :key="index">
                <img src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg">
              </div>
              <div class="information-kill">
                <span>KDA:2/6/7</span>
              </div>
              <div class="information-money">
                <span>金钱:13K</span>
              </div>
              <div class="information-herophysicsdamage">
                <span>物理伤害:24K</span>
              </div>
              <div class="information-herowithstanddamage">
                <span>承受伤害:24K</span>
              </div>
              
            </div>
          </div>
        </div>
      </div>
    </div>
    
  </div>
</template>

<style scoped>
.lolchatpanel{
  width: 70%;
  height: 90%;
  border: 1px solid #959595;
}
.lol-Header{
  width: 100%;
  height: 10%;
  display: flex;
  align-items: center;
  justify-content: center;
  /* background: rgb(10, 10, 9); */
}
.lol-Header-left,.lol-Header-rigth{
  width: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}
/* .lol-Header-rigth
{
  width: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
} */
.lol-Header .el-input{
  width: 60%;
}
.lol-Header .el-button{
  margin-left: 15px;
}
.lol-bottom{
  width: 100%;
  height: 90%;
  display: flex;
  flex-direction: row;
}
.lol-aside{
  width: 15%;
  height: 100%;
}
.lol-aside-hero{
  width: 100%;
  height: 10%;
  border-bottom: 1px solid #959595;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
}
.lol-aside-hero:last-child{
  border:none;
}
.lol-aside-hero-image{
  width: 60px;
  height: 60px;
}
.lol-aside-hero-image img{
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.lol-aside-hero-data{
  margin-left: 5px;
  width: 120px;
  height: 60px;
  display: flex;
  flex-direction: column;
}
.lol-aside-hero-data-comment,.lol-aside-hero-data-rank{
  width: 100%;
  height: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 14px;
}
.lol-aside-hero-data-comment{
  
}
.lol-aside-hero-data-rank{
 
}


.record{
  width: 60%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #AFE1AF; 
  /* color: #008000; */
  color: red;
}
.time{
  width: 40%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #00FF7F;
  
}
.lol-main{
  width: 85%;
  height: 100%;
  display: flex;
  flex-direction: row;
}
.lol-main .spance{
  width: 10%;
  height: 100%;
}
.lol-main-data{
  width: 90%;
  height: 100%;
  display: flex;
  flex-direction: column;
}
.lol-main-data-conmment{
  width: 100%;
  height: 10%;
  display: flex;
  flex-direction: row;
}
.lol-main-data-information{
  width: 100%;
  height: 90%;
  display: flex;
  flex-direction: column;
}
.panel-information{
  width: 100%;
  height: 20%;
  /* background: #AFE1AF; */
  border-bottom: 1px solid #959595;
  flex-direction: row;
  display: flex;
  align-items: center;
}
.panel-information:last-child{
  border:none;
}
.panel-information:first-child{
  border-top: 1px solid #959595;
}

.information-img{
  width: 60px;
  height: 60px;
  padding: 3px;
  
}
.information-img img{
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.information-name{
  width: 15%;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.information-kill,.information-money,.information-herophysicsdamage,.information-herowithstanddamage{
  width: 100px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.information-herowithstanddamage{
  margin-left: 15px;
}












.lol-main-data-conmment-panel{
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
.lol-main-data-conmment-panel>div{
  width: 100%;
  height: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.panel-text{
  color: #959595;
}

</style>
