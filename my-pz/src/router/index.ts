import {createRouter,createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            name: "home",
            component: () => import("../views/websocket/websocket.vue")
        }
    ]
})

export default router
