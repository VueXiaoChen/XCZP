
<template>
  <div class="div">1</div>
</template>


<script setup lang="ts">
import { forEach } from 'lodash';


let Numbers :number[] = [1,2,3,4,5,6,7,8]
let Strings :string[] = ['1','2']
let Anys :any[] = ['1','2',1,2,3,4,5,6,7,8,]

function Sum(x:number,y:number):number{
  return x+y;
}
function push(array:any[],...items:any[]){
  items.forEach(function(item){
    array.push(item)
  })
}

let a = []
push(a,1,2,3,4,5,'1')


//类型断言
//两者相互断言 必须要拥有相同的属性(name)
interface Person{
  name:string;
  age:number;
}
interface Person1{
  name:string;
  age?:number;
  run():void;
}

function A(a:Person){
  return (a as Person1)
}

function B(b:Person1){
  return (b as Person)
}
</script>


<style scoped>
</style>
