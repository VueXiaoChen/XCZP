<script setup lang="ts">

</script>

<template>
  <router-view></router-view>
</template>

<style>
html {
  height: 100%;
}

body {
  height: 100%;
  background-color: var(--v3-body-bg-color);
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial,
    sans-serif;
  @include scrollbar();
}

#app {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

*,
*:before,
*:after {
  box-sizing: border-box;
}

a,
a:focus,
a:hover {
  color: inherit;
  outline: none;
  text-decoration: none;
}

div:focus {
  outline: none;
}

</style>
