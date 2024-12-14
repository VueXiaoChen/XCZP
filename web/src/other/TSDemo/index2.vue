
<template>
  <div class="div">1</div>
</template>


<script setup lang="ts">
//接口
interface Person{
  name:string;
  age:number;
}
//age为可有可无属性
interface Person1{
  name:string;
  age?:number;
}
//propName为任意属性
interface Person2{
  name:string;
  age?:number;
  [propName:string]:any;
}
//id为只读属性,不可以更改值
interface Person3{
  readonly id:number;
  name:string;
  age?:number;
  [propName:string]:any;
}
let tom : Person = {
  name:'xiaochen',
  age:23,
}
let tom1 : Person1 = {
  name:'xiaochen1',
}
let tom2 : Person2 = {
  name:'xiaochen2',
  emall:333333,
}

console.log(tom);
console.log(tom.name);
console.log(tom.age);

console.log(tom1);
console.log(tom1.name);

console.log(tom2.name);
console.log(tom2.emall);


</script>


<style scoped>
</style>
