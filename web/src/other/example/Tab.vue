<script setup lang="ts">
import { ref, onMounted, nextTick,watch } from "vue";
import Router from "../router";
import sonOne from "../commment/sonOne.vue";
import sonTwo from "../commment/sonTwo.vue";
import sonThree from "../commment/sonThree.vue";


//tab的处理
const editableTabsValue = ref("/");
const editableTabs = ref([{name:"SonOne",title:"我不知道我是谁123"},{name:"SonTwo",title:"我不知道我是谁456"},{name:"SonThree",title:"我不知道我是谁789"}]);
//添加tab
const addTab = (targetName: string, path: string) => {
  if (typeof targetName === "undefined") {
    return;
  }
  const tabs = editableTabs.value;
  //判断相等
  for (let i = 0; i < tabs.length; i++) {
    if (tabs[i].title === targetName) {
      editableTabsValue.value = path;
      return;
    }
  }
  //增加tab的名称
  editableTabs.value.push({
    title: targetName,
    name: path,
  });
  //tab的model的值改为新的tab名称
  editableTabsValue.value = path;
};
//移除tab
const removeTab = (targetName: string) => {
  //获取tab的名称
  const tabs = editableTabs.value;
  //获取tab的model的值
  let activeName = editableTabsValue.value;
  //判断相等
  if (activeName === targetName) {
    tabs.forEach((tab, index) => {
      if (tab.name === targetName) {
        const nextTab = tabs[index + 1] || tabs[index - 1];
        if (nextTab) {
          activeName = nextTab.name;
          Router.push({ path: activeName });
        }
      }
    });
  }
  editableTabsValue.value = activeName;
  //过滤
  editableTabs.value = tabs.filter((tab) => tab.name !== targetName);
};

//点击table跳转
const clickTab = (tabPath) => {
  Router.push({ path: tabPath.props.name });
};


//路由监听
watch(
  () => Router.currentRoute.value,
  (val, oldVal) => {
    if (Router.currentRoute.value.path === "/") {
      //设定导航跳转到首页
      Router.push({ path: "/SonOne" });
    }
    const title: string = val.meta.title as string;
    const path: string = val.path as string;
    addTab(title, path);
  },
  {
    immediate: true,
  }
);

const tabnumber = ref(0)

onMounted(() => {});
</script>

<template>
  <el-main class="tags">
    <el-header>Header</el-header>
    <el-tabs
      v-model="editableTabsValue"
      type="card"
      closable
      class="demo-tabs"
      @tab-remove="removeTab"
      @tab-click="clickTab"
    >
      <el-tab-pane
        v-for="item in editableTabs"
        :key="item.name"
        :label="item.title"
        :name="item.name"
      ></el-tab-pane>
    </el-tabs>
    <router-view />
  </el-main>
</template>

<style scoped>
.tags {
  --el-main-padding: 5px;
}
</style>
