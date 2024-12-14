import { type RouteRecordRaw, createRouter, createWebHashHistory, createWebHistory } from "vue-router"

const Layouts = () => import("@/layouts/index.vue")

/** 常驻路由 */
export const constantRoutes: RouteRecordRaw[] = [
  {
    path: "/redirect",
    component: Layouts,
    meta: {
      hidden: true
    },
    children: [
      {
        path: "/redirect/:path(.*)",
        component: () => import("@/views/redirect/index.vue")
      }
    ]
  },
  {
    path: "/403",
    component: () => import("@/views/error-page/403.vue"),
    meta: {
      hidden: true
    }
  },
  {
    path: "/404",
    component: () => import("@/views/error-page/404.vue"),
    meta: {
      hidden: true
    },
    alias: "/:pathMatch(.*)*"
  },
  {
    path: "/login",
    component: () => import("@/views/login/index.vue"),
    meta: {
      hidden: true
    }
  },
  {
    path: "/",
    component: Layouts,
    redirect: "/dashboard",
    children: [
      {
        path: "dashboard",
        component: () => import("@/views/dashboard/index.vue"),
        name: "Dashboard",
        meta: {
          title: "首页",
          svgIcon: "dashboard",
          affix: true
        }
      }
    ]
  },
  {
    path: "/blog",
    component: Layouts,
    name: "Blog",
    meta: {
      title: "博客管理",
      elIcon: "Grid"
    },
    children: [
      {
        path: "blog/index",
        component: () => import("@/views/Blog/index.vue"),
        name: "bloglist",
        meta: {
          title: "博客列表",
          svgIcon: "link"
        }
      },
      {
        path: "blogUnaudited/index",
        component: () => import("@/views/Blog/Unaudited.vue"),
        name: "blogUnaudited",
        meta: {
          title: "博客审核",
          svgIcon: "link"
        }
      },
      {
        path: "blogupdata/index",
        component: () => import("@/views/Blog/updateindex.vue"),
        name: "blogupdata",
        meta: {
          title: "博客修改",
          svgIcon: "link"
        }
      },
      {
        path: "blogadd/index",
        component: () => import("@/views/Blog/addindex.vue"),
        name: "blogadd",
        meta: {
          title: "博客增加",
          svgIcon: "link"
        }
      },
    ]
  },
  {
    path: "/videoaddress",
    component: Layouts,
    name: "videoaddress",
    meta: {
      title: "视频管理",
      elIcon: "Grid"
    },
    children: [
      {
        path: "videoaddresslist/index",
        component: () => import("@/views/videoaddress/index.vue"),
        name: "videoaddresslist",
        meta: {
          title: "视频地址列表",
          svgIcon: "link"
        }
      },
      {
        path: "videoaddressadd/index",
        component: () => import("@/views/videoaddress/addindex.vue"),
        name: "videoaddressadd",
        meta: {
          title: "视频地址增加",
          svgIcon: "link"
        }
      },
    ]
  },

  {
    path: "/doctorsumber",
    component: Layouts,
    name: "doctorsumber",
    meta: {
      title: "夏医生",
      elIcon: "Grid"
    },
    children: [
      {
        path: "sumberloading/index",
        component: () => import("@/views/dotorsumber/Sessdata.vue"),
        name: "sumberloading",
        meta: {
          title: "登录",
          svgIcon: "link"
        }
      },
      {
        path: "sumber/index",
        component: () => import("@/views/dotorsumber/DoctorSumber.vue"),
        name: "sumber",
        meta: {
          title: "夏医生Vlog",
          svgIcon: "link"
        }
      },
    ]
  },
  {
    path: "/websockets",
    component: Layouts,
    name: "websockets",
    meta: {
      title: "websocket测试",
      elIcon: "Grid"
    },
    //component: () => import("@/views/websocket/Websocket.vue"),
    children: [
      {
        path: "websockets/index",
        component: () => import("@/views/websocket/Websocket.vue"),
        name: "websockettest",
        meta: {
          title: "测试",
          svgIcon: "link"
        }
      },
    ]
  },
  {
    path: "/table",
    component: Layouts,
    redirect: "/table/element-plus",
    name: "Table",
    meta: {
      title: "表格",
      elIcon: "Grid"
    },
    children: [
      {
        path: "element-plus",
        component: () => import("@/views/table/element-plus/index.vue"),
        name: "ElementPlus",
        meta: {
          title: "Element Plus",
          keepAlive: true
        }
      },
      {
        path: "vxe-table",
        component: () => import("@/views/table/vxe-table/index.vue"),
        name: "VxeTable",
        meta: {
          title: "Vxe Table",
          keepAlive: true
        }
      }
    ]
  },
  {
    path: "/menu",
    component: Layouts,
    redirect: "/menu/menu1",
    name: "Menu",
    meta: {
      title: "多级菜单",
      svgIcon: "menu"
    },
    children: [
      {
        path: "menu1",
        component: () => import("@/views/menu/menu1/index.vue"),
        redirect: "/menu/menu1/menu1-1",
        name: "Menu1",
        meta: {
          title: "menu1"
        },
        children: [
          {
            path: "menu1-1",
            component: () => import("@/views/menu/menu1/menu1-1/index.vue"),
            name: "Menu1-1",
            meta: {
              title: "menu1-1"
            }
          },
          {
            path: "menu1-2",
            component: () => import("@/views/menu/menu1/menu1-2/index.vue"),
            redirect: "/menu/menu1/menu1-2/menu1-2-1",
            name: "Menu1-2",
            meta: {
              title: "menu1-2"
            },
            children: [
              {
                path: "menu1-2-1",
                component: () => import("@/views/menu/menu1/menu1-2/menu1-2-1/index.vue"),
                name: "Menu1-2-1",
                meta: {
                  title: "menu1-2-1"
                }
              },
              {
                path: "menu1-2-2",
                component: () => import("@/views/menu/menu1/menu1-2/menu1-2-2/index.vue"),
                name: "Menu1-2-2",
                meta: {
                  title: "menu1-2-2"
                }
              }
            ]
          },
          {
            path: "menu1-3",
            component: () => import("@/views/menu/menu1/menu1-3/index.vue"),
            name: "Menu1-3",
            meta: {
              title: "menu1-3"
            }
          }
        ]
      },
      {
        path: "menu2",
        component: () => import("@/views/menu/menu2/index.vue"),
        name: "Menu2",
        meta: {
          title: "menu2"
        }
      }
    ]
  },
]

/**
 * 动态路由
 * 用来放置有权限 (Roles 属性) 的路由
 * 必须带有 Name 属性
 */
export const asyncRoutes: RouteRecordRaw[] = [
  {
    path: "/permission",
    component: Layouts,
    redirect: "/permission/page",
    name: "Permission",
    meta: {
      title: "权限管理",
      svgIcon: "lock",
      roles: ["admin", "editor"], // 可以在根路由中设置角色
      alwaysShow: true // 将始终显示根菜单
    },
    children: [
      {
        path: "page",
        component: () => import("@/views/permission/page.vue"),
        name: "PagePermission",
        meta: {
          title: "页面权限",
          roles: ["admin"] // 或者在子导航中设置角色
        }
      },
      {
        path: "directive",
        component: () => import("@/views/permission/directive.vue"),
        name: "DirectivePermission",
        meta: {
          title: "指令权限" // 如果未设置角色，则表示：该页面不需要权限，但会继承根路由的角色
        }
      }
    ]
  },
  {
    path: "/:pathMatch(.*)*", // Must put the 'ErrorPage' route at the end, 必须将 'ErrorPage' 路由放在最后
    redirect: "/404",
    name: "ErrorPage",
    meta: {
      hidden: true
    }
  }
]

const router = createRouter({
  history:
    import.meta.env.VITE_ROUTER_HISTORY === "hash"
      ? createWebHashHistory(import.meta.env.VITE_PUBLIC_PATH)
      : createWebHistory(import.meta.env.VITE_PUBLIC_PATH),
  routes: constantRoutes
})

/** 重置路由 */
export function resetRouter() {
  // 注意：所有动态路由路由必须带有 Name 属性，否则可能会不能完全重置干净
  try {
    router.getRoutes().forEach((route) => {
      const { name, meta } = route
      if (name && meta.roles?.length) {
        router.hasRoute(name) && router.removeRoute(name)
      }
    })
  } catch {
    // 强制刷新浏览器也行，只是交互体验不是很好
    window.location.reload()
  }
}

export default router
