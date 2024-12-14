import { request } from "@/utils/service"
import type * as videoaddress from "./types/index"
import { log } from "console"

/** 获取视频的所有地址带分页 */
export function GetvideoaddressApi(currentPage,pagesize) {
  return request<any>({
    url: "api/videoAddress/list?page="+currentPage+"&size="+pagesize,
    method: "get"
  })
}
/** 获取视频的所有地址 */
export function GetAllvideoaddressApi() {
  return request<any>({
    url: "api/videoAddress/list",
    method: "get"
  })
}
  
  /** 博客修改或增加 */
  export function GetvideoaddressUpdateOrAddApi(data) {
    return request<any>({
      url: "api/videoAddress/save",
      method: "post",
      data,
    })
  }

  /** 删除视频的所有地址 */
export function DeletevideoaddressApi(videoid) {
  return request<any>({
    url: "api/videoAddress/delete/"+videoid,
    method: "get"
  })
}