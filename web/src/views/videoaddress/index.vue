<script lang="ts" setup>
import { ref, watch,reactive,onMounted  } from "vue"
import {useRoute, useRouter } from "vue-router"
import { type FormInstance, type FormRules, ElMessage, ElMessageBox } from "element-plus"
import { Search, Refresh, CirclePlus, Delete, Download, RefreshRight } from "@element-plus/icons-vue"
import { usePagination } from "@/hooks/usePagination"
import { GetvideoaddressApi,GetvideoaddressUpdateOrAddApi,DeletevideoaddressApi,GetAllvideoaddressApi } from "@/api/videoaddress/index"
import { useUserStore } from "@/store/modules/user"
import fs from 'file-saver'
import * as XLSX from 'xlsx'
const route = useRoute()
const router = useRouter()

/** 调用user Pian */
const user = useUserStore()
const loading = ref<boolean>(false)
const { paginationData, handleCurrentChange, handleSizeChange } = usePagination()
//修改对话框
const dialogFormVisible = ref(false)
//删除对话框
const centerDialogVisible = ref(false)
//lable的值
const formLabelWidth = ref(120)
//通用index的位置 用于删除和修改
const olditemindex:any = ref()
//储存总数据的条数
const pagetotal =  ref(0)
//使用递归的方式实现数组、对象的深拷贝
function deepClone (obj) {
    let objClone = Array.isArray(obj) ? [] : {};
    if (obj && typeof obj === "object") {
        for (var key in obj) {
            if (obj.hasOwnProperty(key)) {
                //判断ojb子元素是否为对象，如果是，递归复制
                if (obj[key] && typeof obj[key] === "object") {
                    objClone[key] = deepClone(obj[key]);
                } else {
                    //如果不是，简单复制
                    objClone[key] = obj[key];
                }
            }
        }
    }
    return objClone;
};   

const resetSearch = () => {
  searchData.videotag=''
  searchData.videosource=''
  searchData.videoaddress=''
  searchData.videotype=''
  paginationData.currentPage=1
  let numbertotal = (paginationData.currentPage-1)*paginationData.pageSize + paginationData.pageSize
  for(let i=(paginationData.currentPage-1)*paginationData.pageSize;i<numbertotal;i++){
    tableData.value.push(oldtableData.value[i])
  } 
  paginationData.total=oldtableData.value.length
  ElMessage({
    message: '重置成功',
    type: 'success',
  })
}
const oldtableData = ref([])
const tableData= ref([
  {
    videotag: "1",
    videosource: "2",
    videoaddress:"3",
    videotype:'11',
    videostate:"未存盘",
    currencyone:"",
    currencytwo:'',
    currencythree:"",
  },
  {
    videotag: "4",
    videosource: "5",
    videoaddress:"6",
    videotype:'11',
    videostate:"未存盘",
    currencyone:"",
    currencytwo:'',
    currencythree:"",
  },
])
//查询参数
const searchData = reactive({
  videotag: "",
  videosource: "",
  videoaddress:"",
  videotype:'',
  videostate:"无条件",
  currencyone:"",
  currencythree:"",
})
//查询功能
const SearchAll = () => {
  if(searchData.videotag == "" && searchData.videosource == "" && searchData.videoaddress == "" && searchData.videotype == "" && searchData.videostate == "无条件"){
    ElMessage.error('请输入查询的数据')
    return
  }
  let newtabledata = []
  if(searchData.videotag!= null && searchData.videotag != ""){
    for(let i=0;i<tableData.value.length;i++){
      if(tableData.value[i].videotag.indexOf(searchData.videotag)!=-1){
        newtabledata.push(tableData.value[i])
      }
    }
    tableData.value= []
    tableData.value = newtabledata
    paginationData.total = tableData.value.length
    newtabledata= []
  }
  if(searchData.videosource!= null && searchData.videosource != ""){
    for(let i=0;i<tableData.value.length;i++){
    if(tableData.value[i].videosource.indexOf(searchData.videosource)!=-1){
      newtabledata.push(tableData.value[i])
    }
  }
  tableData.value= []
    tableData.value = newtabledata
    paginationData.total = tableData.value.length
    newtabledata= []
  }
  if(searchData.videoaddress!= null && searchData.videoaddress != ""){
    for(let i=0;i<tableData.value.length;i++){
    if(tableData.value[i].videoaddress.indexOf(searchData.videoaddress)!=-1){
      newtabledata.push(tableData.value[i])
    }
  }
    tableData.value= []
    tableData.value = newtabledata
    paginationData.total = tableData.value.length
    newtabledata= []
  }
  if(searchData.videotype!= null && searchData.videotype != ""){
    for(let i=0;i<tableData.value.length;i++){
    if(tableData.value[i].videotype.indexOf(searchData.videotype)!=-1){
      newtabledata.push(tableData.value[i])
    }
  }
    tableData.value= []
    tableData.value = newtabledata
    paginationData.total = tableData.value.length
    newtabledata= []
  }
  
  if(searchData.videostate!= "无条件"){
    for(let i=0;i<tableData.value.length;i++){
    if(tableData.value[i].videostate.indexOf(searchData.videostate)!=-1){
      newtabledata.push(tableData.value[i])
    }
  }
    tableData.value= []
    tableData.value = newtabledata
    paginationData.total = tableData.value.length
    newtabledata= []
  }
  ElMessage({
    message: '查询成功',
    type: 'success',
  })
}


/** 视频地址获取 */
const Getvideoaddress = (currentPage,pagesize) => {
  return new Promise((resolve,reject)=>{
    GetvideoaddressApi(currentPage,pagesize).then((res:any)=>{
      if(res){   
        tableData.value = res.data.list 
        tableData.value=tableData.value.reverse()  
        oldtableData.value = res.data.list 
        oldtableData.value=oldtableData.value.reverse()    
        paginationData.total = res.data.total
        pagetotal.value = res.data.total
        ElMessage({
          message: '获取成功',
          type: 'success',
        })
      }
    }).catch((error)=>{
      reject(error)
      console.log(error);
    })
  })
}


//用于更新的数据
const updatedata:any = ref(
  {
    videotag: '',
    videosource: '',
    videoaddress: '',
    videotype:'',
    videostate:''
  }
)
const oldupdatedata:any = ref({})
//获取修改的内容
function GetUpdatadata(index,item){
  olditemindex.value = index
  oldupdatedata.value  = deepClone(item)
  dialogFormVisible.value =true
}

/** 视频地址修改 */
const updatevideoaddress = () => {
  return new Promise((resolve,reject)=>{ 
    GetvideoaddressUpdateOrAddApi(oldupdatedata.value).then((res:any)=>{
      if(res){   
        tableData.value[olditemindex.value] = oldupdatedata.value
        ElMessage({
          message: res.message,
          type: 'success',
        })
        dialogFormVisible.value =false
      }
    }).catch((error)=>{
      reject(error)
      console.log(error);
    })
  })
}

//删除用的videoid
const itemvideoid = ref()
const Deletedata =(index,item)=>{
  olditemindex.value = index
  centerDialogVisible.value=true
  itemvideoid.value = item.videoid
}
/** 视频地址删除 */
const deletevideoaddress = () => {
  return new Promise((resolve,reject)=>{ 
    DeletevideoaddressApi(itemvideoid.value).then((res:any)=>{
      if(res){   
        //oldtableData.value.splice((paginationData.currentPage-1)*paginationData.pageSize + olditemindex.value,1)
        tableData.value.splice(olditemindex.value,1)
        pagetotal.value-=1
        paginationData.total = pagetotal.value
        ElMessage({
          message: res.message,
          type: 'success',
        })
        centerDialogVisible.value =false
      }
    }).catch((error)=>{
      reject(error)
      console.log(error);
    })
  })
}
//刷新当前页
function shuaxin(){
  //router.push({name:router.currentRoute.value.name})
  Getvideoaddress(paginationData.currentPage,paginationData.pageSize)
}
//设置execl的表头
const headerexecl = ref({
  videoid:"id",
  videotag: "视频标签",
  videosource: "视频来源",
  videoaddress:"视频地址",
  videotype:"视频类型",
  videostate:"视频存盘",
  currencyone:"文件位置",
  currencytwo:'文件名',
  currencythree:"文件大小",
  
})
//导出Execl文件函数
function xlsx(json, fields, filename = '.xlsx') {//导出xlsx
  json.forEach(item => {
    for (let i in item) {
      if (fields.hasOwnProperty(i)) {
        item[fields[i]] = item[i];
      }
      delete item[i]; //删除原先的对象属性
    }
  })
  let sheetName = filename //excel的文件名称
  let wb = XLSX.utils.book_new()  //工作簿对象包含一SheetNames数组，以及一个表对象映射表名称到表对象。XLSX.utils.book_new实用函数创建一个新的工作簿对象。
  let ws = XLSX.utils.json_to_sheet(json, { header: Object.values(fields) }) //将JS对象数组转换为工作表。
  wb.SheetNames.push(sheetName)
  wb.Sheets[sheetName] = ws
  const defaultCellStyle = { font: { name: "Verdana", sz: 13, color: "FF00FF88" }, fill: { fgColor: { rgb: "FFFFAA00" } } };//设置表格的样式
  let wopts:any = { bookType: 'xlsx', bookSST: false, type: 'binary', cellStyles: true, defaultCellStyle: defaultCellStyle, showGridLines: false }  //写入的样式
  let wbout = XLSX.write(wb, wopts)
  let blob = new Blob([s2ab(wbout)], { type: 'application/octet-stream' })
  fs.saveAs(blob, filename + '.xlsx')
}
const s2ab = s => {
  var buf;
  if (typeof ArrayBuffer !== 'undefined') {
    buf = new ArrayBuffer(s.length)
    var view = new Uint8Array(buf)
    for (var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xff
    return buf
  } else {
    buf = new Array(s.length);
    for (var i = 0; i != s.length; ++i) buf[i] = s.charCodeAt(i) & 0xFF;
    return buf;
  }
}

//获取所有的视频地址不分页的
async function GetAllVideoAdder(){
  await GetAllvideoaddressApi().then((res:any)=>{
    oldtableData.value = res.data.list
    oldtableData.value=oldtableData.value.reverse()
    paginationData.total = res.data.total
    tableData.value=[]
    //防止第一页数据不够每页的最低数据进行处理
    if(paginationData.pageSize>=paginationData.total){
      for(let i=0;i<paginationData.total;i++){
        tableData.value.push(oldtableData.value[i])
      }
      tableData.value=tableData.value.reverse()
    }else{
      for(let i=0;i<paginationData.pageSize;i++){
        tableData.value.push(oldtableData.value[i])
      }
      tableData.value=tableData.value.reverse()
    }
  })
}
//监听分页
watch(()=>[paginationData.currentPage],(newValue, oldValue) => {
  //Getvideoaddress(paginationData.currentPage,paginationData.pageSize)
  tableData.value=[]
  let numbertotal = 0
  if(paginationData.currentPage*paginationData.pageSize>paginationData.total){
    let number = paginationData.pageSize-(paginationData.currentPage*paginationData.pageSize-paginationData.total)
    numbertotal= (paginationData.currentPage-1)*paginationData.pageSize + number
    for(let i=(paginationData.currentPage-1)*paginationData.pageSize;i<numbertotal;i++){
      tableData.value.push(oldtableData.value[i])
    }
  }else{
    numbertotal = (paginationData.currentPage-1)*paginationData.pageSize + paginationData.pageSize
    for(let i=(paginationData.currentPage-1)*paginationData.pageSize;i<numbertotal;i++){
      tableData.value.push(oldtableData.value[i])
    } 
  }
});
//导出execl
const OutExecl = () => {
  // return new Promise((resolve,reject)=>{
  //   GetAllvideoaddressApi().then((res:any)=>{
  //     if(res){   
  //       xlsx(res.data.list,headerexecl.value,"数据")
  //     }
  //   }).catch((error)=>{
  //     reject(error)
  //     console.log(error);
  //   })
  // })
  xlsx(oldtableData.value,headerexecl.value,"数据")
}
onMounted(() => {
  //Getvideoaddress(paginationData.currentPage,paginationData.pageSize)
  GetAllVideoAdder()
});
</script>

<template>
  
  <div class="app-container">
    <el-card v-loading="loading" shadow="never" class="search-wrapper">
      <el-form ref="searchFormRef" :inline="true" :model="searchData">
        <el-form-item prop="videotag" label="视频标签">
          <el-input v-model="searchData.videotag" placeholder="请输入" />
        </el-form-item>
        <el-form-item prop="videosource" label="视频来源">
          <el-input v-model="searchData.videosource" placeholder="请输入" />
        </el-form-item>
        <el-form-item prop="videoaddress" label="视频地址">
          <el-input v-model="searchData.videoaddress" placeholder="请输入" />
        </el-form-item>
        <el-form-item prop="videotype" label="视频类型">
          <el-input v-model="searchData.videotype" placeholder="请输入" />
        </el-form-item>
        <el-form-item prop="videotype" label="文件位置">
          <el-input v-model="searchData.currencyone" placeholder="请输入" />
        </el-form-item>
        <!-- <el-form-item prop="videostate" label="视频存盘">
          <el-input v-model="searchData.videostate" placeholder="请输入" />
        </el-form-item> -->
        <el-form-item label="视频存盘：" prop="videostate">
        <el-select v-model="searchData.videostate" placeholder="">
          <el-option label="无条件" value="无条件" />
          <el-option label="未存盘" value="未存盘" />
          <el-option label="已存盘" value="已存盘" />
        </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="SearchAll()">查询</el-button>
          <el-button :icon="Refresh" @click="resetSearch">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card v-loading="loading" shadow="never">
      <div class="toolbar-wrapper">
        <div>
          <el-button type="danger" :icon="Delete">批量删除</el-button>
        </div>
        <div>
          <el-tooltip content="下载">
            <el-button type="primary" :icon="Download" circle @click="OutExecl"/>
          </el-tooltip>
          <el-tooltip content="刷新当前页">
            <el-button type="primary" :icon="RefreshRight" circle @click="shuaxin" />
          </el-tooltip>
        </div>
      </div>
      <div class="table-wrapper">
        <el-table :data="tableData">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column type="index" width="50" />
          <el-table-column prop="videotag" label="视频标签" width="160" align="center" :show-overflow-tooltip="true"/>
          <el-table-column prop="videosource" label="视频来源"  align="center"  :show-overflow-tooltip="true"/>
          <el-table-column prop="videoaddress" label="视频地址"  align="center"  :show-overflow-tooltip="true"/>
          <el-table-column prop="videotype" label="视频类型"  align="center" />
          <el-table-column prop="currencyone" label="文件位置"  align="center" />
          <el-table-column prop="currencytwo" label="文件名"  width="160" align="center" />
          <el-table-column prop="currencythree" label="文件大小"  align="center" />
          <el-table-column prop="videostate" label="视频存盘" width="100" align="center">
            <template #default="scope:any">
              <el-tag v-if="scope.row.videostate==='已存盘'" type="success" effect="plain" width="50">已存盘</el-tag>
              <el-tag v-else type="danger" effect="plain">未存盘</el-tag>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="200" align="center">
            <template #default="scope">
              <el-button type="primary"  @click="GetUpdatadata(scope.$index,scope.row)">修改</el-button>
              <el-button type="danger"  @click="Deletedata(scope.$index,scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="pager-wrapper">
        <el-pagination
          background
          :layout="paginationData.layout"
          :page-sizes="paginationData.pageSizes"
          :total="paginationData.total"
          :page-size="paginationData.pageSize"
          :currentPage="paginationData.currentPage"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </el-card>
    <el-dialog v-model="dialogFormVisible" title="修改" center>
    <el-form :model="oldupdatedata">
      <el-form-item label="视频标签：" :label-width="formLabelWidth">
        <el-input v-model="oldupdatedata.videotag" autocomplete="off" />
      </el-form-item>
      <el-form-item label="视频来源：" :label-width="formLabelWidth">
        <el-input v-model="oldupdatedata.videosource" autocomplete="off" />
      </el-form-item>
      <el-form-item label="视频地址：" :label-width="formLabelWidth">
        <el-input v-model="oldupdatedata.videoaddress" autocomplete="off" />
      </el-form-item>
      <el-form-item label="视频类别：" prop="videotype" :label-width="formLabelWidth">
        <el-select v-model="oldupdatedata.videotype" placeholder="其他">
          <el-option label="其他" value="其他" />
          <el-option label="口交" value="口交" />
        </el-select>
        </el-form-item>
        <el-form-item label="视频存盘：" prop="videostate" :label-width="formLabelWidth">
          <el-radio-group v-model="oldupdatedata.videostate">
            <el-radio label="未存盘" />
            <el-radio label="已存盘" />
          </el-radio-group>
        </el-form-item>
        <el-form-item label="视频大小：" :label-width="formLabelWidth">
        <el-input v-model="oldupdatedata.currencythree" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false" round>取消</el-button>
        <el-button type="primary" round @click="updatevideoaddress">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="centerDialogVisible" title="提示" width="30%"  top=30vh center>
    <span>确定删除该条数据?</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="deletevideoaddress" >
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>
  </div>
  
</template>

<style lang="scss">
.search-wrapper {
  margin-bottom: 20px;
  :deep(.el-card__body) {
    padding-bottom: 2px;
  }
}

.toolbar-wrapper {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.table-wrapper {
  margin-bottom: 20px;
}

.pager-wrapper {
  display: flex;
  justify-content: flex-end;
}
// .el-overlay-dialog{
//   :deep(.el-dialog) {
//     border-radius: 20px; 
//   }
// }
.app-container .el-dialog--center{
  border-radius: 20px !important;
}
.app-container .el-dialog__body{
  text-align: center;
  font-size: 1.3rem;
}
</style>
