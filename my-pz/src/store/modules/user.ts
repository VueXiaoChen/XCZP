
import { ref } from "vue"
import { defineStore } from "pinia"
export const useUserStore = defineStore("user", () => {
  
    const token = ref("")
    
    const username = ref("")
    const userid = ref('')
    
    /** 登出 */
    const logout = () => {
      
    }
    /** 重置 Token */
    const resetToken = () => {
      
    }
    return { token, username,userid,logout, resetToken,}
  })