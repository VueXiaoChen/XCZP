<script setup lang="ts">
import { ref,onMounted } from 'vue'
import Router from "../router";
//接收父组件的参数
interface Props {
  fatherMessage?: string,
}
//参数实例化
defineProps<Props>()

// 定义所要传给父组件的值
const value = ref<String>("我是子组件传给父组件的值")
 
// 使用defineEmits注册一个自定义事件
const emit = defineEmits(["getValue"])
 
// 点击事件触发emit，去调用我们注册的自定义事件getValue,并传递value参数至父组件
const transValue = () => {
  emit("getValue", value.value)
}



// 暴露给父组件的值
const toFatherValue = ref<string>("我是要暴露给父组件的值")
 
// 暴露给父组件的方法
const toFatherMethod = () => {
  console.log("我是要暴露给父组件的方法")
}
// 暴露方法和属性给父组件
defineExpose({toFatherMethod, toFatherValue})

onMounted(() => {
  
});
</script>

<template>
  <div class="di">{{fatherMessage}}</div>
  <button @click="transValue" style="margin: 5px">传值给父组件</button>
</template>

<style scoped>

</style>
