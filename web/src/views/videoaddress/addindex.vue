<script lang="ts" setup>
import { ref, watch, reactive,onMounted,shallowRef,onBeforeUnmount } from "vue"
import {type FormInstance,FormRules,ElMessage } from 'element-plus'
import { GetvideoaddressApi,GetvideoaddressUpdateOrAddApi } from "@/api/videoaddress/index"
import { useUserStore } from "@/store/modules/user"
import { UploadFilled } from '@element-plus/icons-vue'

const loading = ref<boolean>(false)
const user = useUserStore()
const ruleFormRef = ref<FormInstance>()
const formSize = ref('default')
const uploadRef =ref(null)
let ruleForm = reactive<any>({
  videotag: '',
  videosource: '电报',
  videoaddress: '',
  videotype:'其他',
  videostate:"未存盘",
  videofile:'',
  currencyone:'',
  currencytwo:"",
  currencythree:"",
})
let oldruleForm=reactive<any>({
  videotag: '',
  videosource: '电报',
  videoaddress: '',
  videotype:'其他',
  videostate:"未存盘",
  videofile:'',
  currencyone:'',
  currencytwo:'磁力资源连接集合',
  currencythree:"",
})

const timestamp=ref([])

const ruleFormatag = ref([])
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
//new Date转化成时间格式
function dateToFormat(value) {
    const dateTme = new Date(value)
    const Y = dateTme.getFullYear()
    const M = dateTme.getMonth() + 1 < 10 ? "0" + (dateTme.getMonth() + 1) : dateTme.getMonth() + 1
    const D = dateTme.getDate() < 10 ? "0" + dateTme.getDate() : dateTme.getDate()
    const h = dateTme.getHours() < 10 ? "0" + dateTme.getHours() : dateTme.getHours()
    const m = dateTme.getMinutes() < 10 ? "0" + dateTme.getMinutes() : dateTme.getMinutes()
    const s = dateTme.getSeconds() < 10 ? "0" + dateTme.getSeconds() : dateTme.getSeconds()
    return Y + "-" + M + "-" + D + " " + h + ":" + m + ":" + s
}
function dateFormats(dataStr) {
    var dateee = new Date(dataStr).toJSON();
    return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
}
const rules = reactive<FormRules>({
  videotag: [
    { required: true, message: '视频标题不能为空', trigger: 'blur' },
  ],
  videosource: [
    { required: true, message: '视频来源不能为空', trigger: 'blur' },
  ],
  videoaddress: [
    { required: true, message: '视频地址不能为空', trigger: 'blur' },
  ],
})

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
/** 视频增加 */
const GetvideoaddressUpdateOrAdd = (ruleForm: any) => {
  return new Promise((resolve,reject)=>{
    GetvideoaddressUpdateOrAddApi(ruleForm).then((res:any)=>{
      if(res){
        ruleFormatag.value.push(deepClone(ruleForm))
        ruleFormatag.value.reverse()
        timestamp.value.push({
          time:dateToFormat(new Date())
        })
        timestamp.value.reverse()
        ruleForm.videotag= '',
        ruleForm.videosource= '电报',
        ruleForm.videoaddress= '',
        ruleForm.videotype= '其他',
        ruleForm.videostate= "未存盘",
        ruleForm.videofile= '',
        ruleForm.currencyone= '',
        ruleForm.currencytwo= "磁力资源连接集合",
        ruleForm.currencythree= ""
        ElMessage({
          message: res.message,
          type: 'success',
        })
      }
    }).catch((error)=>{
      reject(error)
      console.log(error);
    })
  })
}

const handleVideoSuccess = () => {
  return new Promise((resolve,reject)=>{

  })
}


function getData(n) {
  let now = new Date(n),
    y = now.getFullYear(),
    m = now.getMonth() + 1,
    d = now.getDate();
  return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
}

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      ruleForm.currencyone = getData(new Date()).substring(0,10)
      GetvideoaddressUpdateOrAdd(ruleForm)
      //uploadRef.value.submit()

    } else {
      console.log('error submit!', fields)
    }
  })
}
const count = ref(0)
const load = () => {
  count.value += 2
}

onMounted(() => {


});

</script>

<template>
  <div class="app-container">
    <el-card v-loading="loading" shadow="never" class="search-wrapper">
      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
        :size="formSize"
        status-icon

      >
        <el-form-item label="视频标签：" prop="videotag" required>
          <el-input v-model="ruleForm.videotag" />
        </el-form-item>
        <el-form-item label="视频大小：" prop="videosource" required>
          <el-input v-model="ruleForm.currencythree" />
        </el-form-item>
        <el-form-item label="视频地址：" prop="videoaddress" required>
          <el-input v-model="ruleForm.videoaddress" />
        </el-form-item>
        <el-form-item label="视频来源：" prop="videosource" required>
          <el-input v-model="ruleForm.videosource" />
        </el-form-item>
        <el-form-item label="地址文件：" prop="currencytwo">
          <el-input v-model="ruleForm.currencytwo" />
        </el-form-item>
        <el-form-item label="视频类别：" prop="videotype">
        <el-select v-model="ruleForm.videotype" placeholder="其他">
          <el-option label="其他" value="其他" />
          <el-option label="口交" value="口交" />
        </el-select>
        </el-form-item>
        <el-form-item label="视频存盘：" prop="videostate">
          <el-radio-group v-model="ruleForm.videostate">
            <el-radio label="未存盘" />
            <el-radio label="已存盘" />
          </el-radio-group>
        </el-form-item>
        <!-- <el-form-item label="文件上传：" prop="videofile">
          <el-upload
            ref="uploadRef"
            :auto-upload="false"
            class="upload-demo"
            :drag="true"
            action="api/videoAddress/upload"
            multiple
            :on-success="handleVideoSuccess"
            :data="{ruleForm:JSON.stringify(ruleForm)}"
          >
            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
            <div class="el-upload__text">
              将文件拖入此处 <em>或点击上传</em>
            </div>
          </el-upload>
        </el-form-item> -->
        <el-form-item>
          <el-button type="primary" @click="submitForm(ruleFormRef)">创建</el-button>
          <el-button @click="resetForm(ruleFormRef)">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card v-loading="loading" shadow="never" class="search-wrapper">
      <el-timeline>
        <el-timeline-item :timestamp="timestamp[index].time" placement="top" v-for="(item,index) in ruleFormatag" :key="index" type="primary">
          <el-card>
            <h3>{{ item.videotag }}</h3>
            <p>大小:{{item.currencythree}}</p>
            <p>下载地址:{{item.videoaddress}}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
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
.app-container{
  :deep(.el-input){
    width: 80%;
  }
}
</style>
