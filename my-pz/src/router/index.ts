import {createRouter,createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            name: "loading",
            //component: () => import("../views/websocket/websocket.vue")
            //component: () => import("../layout/index.vue")
            component: () => import("../views/HelloWorld.vue")
            
        },
        {
            path: "/homes",
            name: "Home",
            //component: () => import("../views/websocket/websocket.vue")
            //component: () => import("../layout/index.vue")
            component: () => import("../components/Home.vue")
            
        },
    ]
})

export default router
