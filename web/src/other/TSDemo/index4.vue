
<template>
  <div class="div">1</div>
</template>


<script setup lang="ts">
class Animal{
  constructor(name){
    this.name=name;
  }
  get name(){
    return '13313'
  }
  set name(value){
    console.log('setter:'+ value);
    
  }
}

let a = new Animal("xiaochen")
console.log(a.name);
a.name = "dachen"
console.log(a.name);




</script>


<style scoped>
</style>
