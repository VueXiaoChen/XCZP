
<template>
  <div class="div">1</div>
</template>


<script setup lang="ts">
import {ref} from 'vue'
//联合类型
let number :string | number;
number = '1231'
function GetType(number:string | number ):string{
  return number.toString()
}
// console.log(GetType(number));



</script>


<style scoped>
</style>
