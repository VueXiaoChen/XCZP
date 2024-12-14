
<template>
  <div class="div">11111111111111111</div>
</template>


<script setup lang="ts">
import { inspect } from 'util';
interface Animat{
  alert():void;
}
//接口继承接口
interface Bnimat extends Animat{
  GetName():void;
  GetAge():void;
}
class Door{

}
//类继承类与接口
class OneDoor extends Door implements Animat{
  alert(){
    console.log("你好");
  }
}
//类继承接口
class Car implements Animat{
  alert(){
    console.log("我不好");
  }
}

class Cars implements Bnimat{
  alert(){
    console.log("我不好");
  }
  GetName(){
    console.log("我不好1");
  };
  GetAge(){
    console.log("我不好2");
  };
}

let a = new OneDoor()
let b = new Car()
let c = new Cars()
a.alert()
b.alert()
c.GetAge()
c.GetName()
c.alert()

</script>


<style scoped>

</style>
