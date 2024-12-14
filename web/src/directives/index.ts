import { type App } from "vue"
import { permission } from "./permission"

/** 挂载自定义指令 */
export function loadDirectives(app: App) {
  app.directive("permission", permission)
}

export function OnceClicks(app: App) {
  app.directive('onceClick',{
    mounted(el, binding, vnode) {
        el.addEventListener('click', () => {
            if (!el.disabled) {
                el.disabled = true;
                setTimeout(() => {
                    el.disabled = false;
                }, binding.value || 1000);
            }
        });
    }
  })
}
