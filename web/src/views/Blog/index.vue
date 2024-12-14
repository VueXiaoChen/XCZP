<script lang="ts" setup>
import { ref, watch,reactive,onMounted  } from "vue"
import {useRoute, useRouter } from "vue-router"
import { type FormInstance, type FormRules, ElMessage, ElMessageBox } from "element-plus"
import { Search, Refresh, CirclePlus, Delete, Download, RefreshRight } from "@element-plus/icons-vue"
import { usePagination } from "@/hooks/usePagination"
import { GetBlogApi } from "@/api/blog/index"
import { useUserStore } from "@/store/modules/user"
const route = useRoute()
const router = useRouter()
/** 调用user Pian */
//let user = useUserStore()
const user = reactive(JSON.parse(sessionStorage.getItem("user")))
console.log(user);

const loading = ref<boolean>(false)
const { paginationData, handleCurrentChange, handleSizeChange } = usePagination()


const handleSearch = () => {
  
}
const resetSearch = () => {
  
}
const searchData = reactive({
  blogTitle: "",
  typeId: "",
  blogContent:"",
})

const tableData = ref([
  {
  blogId:"1",
  blogTitle:"331231231232131231231321312231231231233",
  userid:"2",
  typeId:"3",
  blogStatus:false,
  createTime:"2022-09-05 12:22:22",
  updateTime:"2022-09-05 12:22:22",
  coverImage:"www.baidu.com",
  blogContent:"我是一直小青蛙我是一直小青蛙我是一直小青蛙我是一直小青蛙我是一直小青蛙我是一直小青蛙我是一直小青蛙我是一直小青蛙我是一直小青蛙",
  like:"1",
  collect:'1',
  subscribe:'1',
  comment:'1'
},
  {
  blogId:"1",
  blogTitle:"333",
  userid:"2",
  typeId:"3",
  blogStatus:false,
  createTime:"2022-09-05 12:22:22",
  updateTime:"2022-09-05 12:22:22",
  coverImage:"www.baidu.com",
  blogContent:"我是一直小青蛙1111111111111111111111111111111111111111111111111111111111111",
  like:"1",
  collect:'1',
  subscribe:'1',
  comment:'1'
},
])
/** 博客获取 */
const GetBlog = (userid,currentPage,pagesize) => {
  return new Promise((resolve,reject)=>{
    GetBlogApi(userid,currentPage,pagesize).then((res:any)=>{
      if(res){   
        tableData.value = res.data.list        
        paginationData.total = res.data.total
      }
    }).catch((error)=>{
      reject(error)
      console.log(error);
    })
  })
}


//获取修改的内容
function GetUpdataComment(item){
  user.blogform = item
  router.push({name:"blogupdata"})
}
//监听分页
watch(()=>[paginationData.currentPage,paginationData.pageSize],(newValue, oldValue) => {
  GetBlog(user.userid,paginationData.currentPage,paginationData.pageSize)
});



onMounted(() => {
  GetBlog(user.userid,paginationData.currentPage,paginationData.pageSize)
});
</script>

<template>
  <div class="app-container">
    <el-card v-loading="loading" shadow="never" class="search-wrapper">
      <el-form ref="searchFormRef" :inline="true" :model="searchData">
        <el-form-item prop="blogTitle" label="博客标题">
          <el-input v-model="searchData.username" placeholder="请输入" />
        </el-form-item>
        <el-form-item prop="typeId" label="博客类型">
          <el-input v-model="searchData.phone" placeholder="请输入" />
        </el-form-item>
        <el-form-item prop="blogContent" label="博客内容">
          <el-input v-model="searchData.phone" placeholder="请输入" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="handleSearch">查询</el-button>
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
            <el-button type="primary" :icon="Download" circle />
          </el-tooltip>
          <el-tooltip content="刷新当前页">
            <el-button type="primary" :icon="RefreshRight" circle @click="" />
          </el-tooltip>
        </div>
      </div>
      <div class="table-wrapper">
        <el-table :data="tableData">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column type="index" width="50" />
          <el-table-column prop="blogTitle" label="博客标题" align="center" :show-overflow-tooltip="true"/>
          <el-table-column prop="typeId" label="博客类型" width="100" align="center" />
          <el-table-column prop="blogStatus" label="状态" width="100" align="center">
            <template #default="scope">
              <el-tag v-if="scope.row.status" type="success" effect="plain">通过</el-tag>
              <el-tag v-else type="danger" effect="plain">未审核</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间"   width="160" align="center" />
          <el-table-column prop="updateTime" label="更新时间"   width="160" align="center" />
          <el-table-column prop="coverImage" label="封面图片"  align="center" />
          <el-table-column prop="blogContent" label="博客内容"  align="center" :show-overflow-tooltip="true"/>
          <el-table-column prop="like" label="点赞"  align="center"   width="60" :show-overflow-tooltip="true"/>
          <el-table-column prop="collect" label="收藏"  align="center"  width="60" :show-overflow-tooltip="true"/>
          <el-table-column prop="subscribe" label="订阅"  align="center"  width="60" :show-overflow-tooltip="true"/>
          <el-table-column prop="comment" label="评论"  align="center" width="60" :show-overflow-tooltip="true"/>
          <el-table-column fixed="right" label="操作" width="250" align="center">
            <template #default="scope">
              <el-button type="primary"  @click="GetUpdataComment(scope.row)">修改</el-button>
              <el-button type="danger"  @click="">删除</el-button>
              
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
  </div>
</template>

<style lang="scss" scoped>
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

</style>
