import { request } from "@/utils/service"
import type * as Blog from "./types/index"
import { log } from "console"

/** 获取审核的博客 */
export function GetBlogApi(userid,currentPage,pagesize) {
  return request<any>({
    url: "api/blog/list?blogStatus=1&userid=" + userid+"&page="+currentPage+"&size="+pagesize,
    method: "get"
  })
}
/** 获取未审核的博客 */
export function GetUnauditedBlogApi(userid,currentPage,pagesize) {
  return request<any>({
    url: "api/blog/list?blogStatus=0&userid=" + userid+"&page="+currentPage+"&size="+pagesize,
    method: "get"
  })
}
/** 获取博客的类型 */
export function GetBlogTypeApi() {
  return request<any>({
    url: "api/blogtype/list",
    method: "get"
  })
}
/** 获取博客的所有标签 */
export function GetBlogTagApi() {
  return request<any>({
    url: "api/tag/list",
    method: "get"
  })
}

/** 博客修改或增加 */
export function GetBlogUpdateOrAddApi(data) {
  return request<any>({
    url: "api/blog/save",
    method: "post",
    data
  })
}

