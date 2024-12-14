import { type Ref, ref, watch } from "vue"
import { defineStore } from "pinia"
import store from "@/store"
interface ListItem {
  avatar?: string
  title: string
  datetime?: string
  description?: string
  status?: "" | "success" | "info" | "warning" | "danger"
  extra?: string
}

export const NotifyStore = defineStore("Notifys", () => {
  let notifyData:[]= ref([
    {
      avatar: "https://gw.alipayobjects.com/zos/rmsportal/OKJXDXrmkNshAMvwtvhu.png",
      title: "V3 Admin Vite 上线啦",
      datetime: "半年前",
      description:
        "一个免费开源的中后台管理系统基础解决方案，基于 Vue3、TypeScript、Element Plus、Pinia 和 Vite 等主流技术"
    },
    {
      avatar: "https://gw.alipayobjects.com/zos/rmsportal/OKJXDXrmkNshAMvwtvhu.png",
      title: "V3 Admin Vite 上线啦",
      datetime: "半年前",
      description:
        "一个免费开源的中后台管理系统基础解决方案，基于 Vue3、TypeScript、Element Plus、Pinia 和 Vite 等主流技术"
    },
  ]
  )
  let messageData:[]= ref([
    {
      avatar: "https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png",
      title: "来自楚门的世界",
      description: "如果再也不能见到你，祝你早安、午安和晚安",
      datetime: "1998-06-05"
    },
    {
      avatar: "https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png",
      title: "345435354",
      description: "如果再也不能见到你，祝你早安、午安和晚安",
      datetime: "1998-06-05"
    },
  ]
  )
  let todoData:[]= ref([
    {
      title: "任务名称",
      description: "这家伙很懒，什么都没留下",
      extra: "未开始",
      status: "info"
    },
  ]
  )
  return {notifyData,messageData,todoData}
})
/** 在 setup 外使用 */
export function useUserStoreHook() {
  return useUserStore(store)
}
