
<template>
  <div class="div">22222222222</div>
</template>


<script setup lang="ts">

function CreateDate<T>(length:number,value:T):Array<T>{
  let result:T[] = []
  for(let i=0;i<length;i++){
    result[i]=value
  }
  return result
}
console.log(CreateDate<string>(4,"244"));
//泛型约束
interface Animo{
  length:number;
}
function CreateDates<T extends Animo>(arg:T):T{
  console.log(arg.length);
  return arg
}
console.log(CreateDates("244"));

</script>


<style scoped>

</style>
