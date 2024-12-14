<script setup lang="ts">
import { ref, onMounted } from "vue";
import Router from "../router";

import son from "../commment/Son.vue";
//接收子组件传过来的值
const sonMessage = ref<string>("");
const fatherMessage = ref<string>("我是父组件传过来的值");
//子组件的方法赋值
const getSonValue = (value: string) => {
  sonMessage.value = value;
};


//用ref来获取子组件的方法和值
const sonMethodRef = ref()
//获取子组件的传过来的值和方法
const getSonMethod = () => {
  sonMethodRef.value.toFatherMethod()
  console.log(sonMethodRef.value.toFatherValue)
}

onMounted(() => {
  getSonMethod()
});
</script>

<template>
  <div class="div">{{sonMessage}}</div>
  <son :fatherMessage="fatherMessage" @getValue="getSonValue" ref="sonMethodRef"></son>
</template>

<style scoped>
</style>
