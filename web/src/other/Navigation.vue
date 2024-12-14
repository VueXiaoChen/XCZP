<script setup lang="ts">
import { ref, watch } from "vue";
import axios from "axios";
import { onMounted } from "vue";
import { useRouter } from "vue-router";
import pinias from "../components/Pinias.vue";
import router from "../router";
import { routeStore } from "../type/routeStore";
import { storeToRefs } from "pinia";
const viteComponent = import.meta.glob("../views/**/*.vue");
//定义pinia的组件
const routeStores = routeStore()
//结构SaveRouter的数值
const {SaveRouter} = storeToRefs(routeStore())
//获取路由
const Router = useRouter();
//定义有子路由的值
const NavigationRouter = ref([]);
const NavigationeChindren = ref([])
const Navigatione = ref([])
function fnAddDynamicMenuRoutes(menuList: any = [], routes: any = []) {
  // 用于保存普通路由数据
  let temp: any[] = [];
  // 用于保存存在子路由的路由数据
  let route: any = {};
  // 遍历数据
  for (let i = 0; i < menuList.length; i++) {
    // 存在子路由，则递归遍历，并返回数据作为 children 保存
    if (menuList[i].children && menuList[i].children.length > 0) {
      // 获取路由的基本格式
      route = getRoute(menuList[i]);
      // 递归处理子路由数据，并返回，将其作为路由的 children 保存
      route.children = fnAddDynamicMenuRoutes(menuList[i].children);
      // 保存存在子路由的路由
      routes.push(route);
    } else {
      // 保存普通路由
      temp.push(getRoute(menuList[i]));
    }
  }
  // 返回路由结果
  return routes.concat(temp);
}

// 返回路由的基本格式
function getRoute(item: any) {
  // 路由基本格式
  let route = {
    // 路由的路径
    path: item.path,
    // 路由名
    name: item.name,
    // 路由所在组件
    // component: (resolve) => require([`@/layout/Index`], resolve),
    component: viteComponent[`../views/${item.name}.vue`],
    meta: item.meta,
    // 路由的子路由
    children: [],
  };
  // 返回 route
  return route;
}
onMounted(() => {
  axios.post("/api/navigation").then((res) => {
    NavigationRouter.value = fnAddDynamicMenuRoutes(res.data.data);
    for (let i = 0; i < NavigationRouter.value.length; i++) {
      //添加父路由的子路由
      router.addRoute('Home',NavigationRouter.value[i]);
    }
    NavigationeChindren.value = NavigationRouter.value.filter((ment:any)=>{return ment.children.length!=0})
    Navigatione.value = NavigationRouter.value.filter((ment:any)=>{return ment.children.length===0})
    //路由完成后跳转到首页
    // Router.push({ path:"/Manager"});
    Router.push({ path:"/Student"});
  });
});

//动态路由
// NavigationRouter.value = Router.getRoutes();
const handleOpen = (key: string, keyPath: string[]) => {
  // console.log(key);
  // console.log(keyPath);
};
const handleClose = (key: string, keyPath: string[]) => {
  // console.log(key, keyPath);
};

//tab的处理
const editableTabsValue = ref("/");
const editableTabs = ref([]);
//添加tab
const addTab = (targetName: string, path: string) => {
  if (typeof targetName === "undefined") {
    return;
  }
  const tabs:any = editableTabs.value;
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
    tabs.forEach((tab:any, index:number) => {
      if (tab.name === targetName) {
        const nextTab:any = tabs[index + 1] || tabs[index - 1];
        if (nextTab) {
          activeName = nextTab.name;
          Router.push({ path: activeName });
        }
      }
    });
  }
  editableTabsValue.value = activeName;
  //过滤
  editableTabs.value = tabs.filter((tab:any) => tab.name !== targetName);
};

//点击table跳转
const clickTab = (tabPath) => {
  Router.push({ path: tabPath.props.name });
};

//路由监听
watch(
  () => Router.currentRoute.value,
  (val, oldVal) => {
    if (Router.currentRoute.value.path === "/Home") {
      //设定导航跳转到首页
      Router.currentRoute.value.path = "/Manager";
    }
    const title: string = val.meta.title as string;
    const path: string = val.path as string;
    addTab(title, path);
  },
  {
    immediate: true,
  }
);
</script>

<template>
  <el-container>
    <el-container>
      <el-aside width="200px">
        <el-row class="Navigation">
          <el-col :span="24">
            <el-menu
              active-text-color="#ffd04b"
              background-color="#545c64"
              class="el-menu-vertical-demo"
              :default-active="Router.currentRoute.value.path"
              text-color="#fff"
              @open="handleOpen"
              @close="handleClose"
              router
            >
              <el-menu-item  v-for="item in Navigatione" :index="item.path">
                <span>{{item.meta.title}}</span>
              </el-menu-item>
              <el-sub-menu
                :index="item.name"
                v-for="(item, indexs) in NavigationeChindren"
              >
                <template #title>
                  <span>{{ item.meta.title }}</span>
                </template>
                <el-menu-item-group >
                   <el-menu-item  v-for="menuItem in item.children" :index="menuItem.path">{{
                    menuItem.meta.title
                  }}</el-menu-item>
                </el-menu-item-group>
              </el-sub-menu>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
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
          >
          </el-tab-pane>
        </el-tabs>
        <!-- <pinias></pinias> -->
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>
<style scoped>
.el-header {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 24px;
  --el-header-height: 40px;
}
.tags {
  --el-main-padding: 5px;
}
/* jj */
/* .el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  height: 100%;
} */
/* .el-header {
  height: 80px;
} */
/* .Navigation .el-menu {
  height: 300px;
} */
/* .el-menu{
  height: calc(100vh );
} */
</style>

