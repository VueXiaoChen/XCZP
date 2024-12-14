<script lang="ts" setup>
import { ref, watch, reactive,onMounted,shallowRef,onBeforeUnmount } from "vue"
import {type FormInstance,FormRules,ElMessage } from 'element-plus'
import { GetBlogTypeApi,GetBlogTagApi,GetBlogUpdateOrAddApi } from "@/api/blog/index"
import { useUserStore } from "@/store/modules/user"
import '@wangeditor/editor/dist/css/style.css'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

const loading = ref<boolean>(false)
const user = useUserStore()
const formSize = ref('default')
const ruleFormRef = ref<FormInstance>()
let ruleForm = reactive<any>({
  blogTitle: '',
  coverImage: '',
  typeId: '',
  blogContent: '',
  tagBlog: [],
  userid:user.userid
})
//博客类型数组
const bolgtype = ref()
//博客标签数组
const bolgtag = ref([])
const rules = reactive<FormRules>({
  
})
//typeId
const typeName = ref()
const tag = ref()


const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}

const options = Array.from({ length: 10000 }).map((_, idx) => ({
  value: `${idx + 1}`,
  label: `${idx + 1}`,
}))


// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef()
// 内容 HTML
const valueHtml = ref('<p>请输入内容.3333333333..</p>')
const toolbarConfig = {}
const editorConfig = { placeholder: '请输入内容...' }
// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
    const editor = editorRef.value
    if (editor == null) return
    editor.destroy()
})
const handleCreated = (editor: any) => {
  editorRef.value = editor // 记录 editor 实例，重要！
}
const mode = ref('default')

//修改富文本的值
function UpdateRichText(){
  ruleForm = user.blogform
  console.log(ruleForm);
  setTimeout(() => {
    editorRef.value.setHtml(ruleForm.blogContent)
  }, 100)
}

onMounted(() => {
  
  
  
  UpdateRichText()
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
        <el-form-item label="文章标题：" prop="blogTitle" >
          <el-input v-model="ruleForm.blogTitle" />
        </el-form-item>
        <el-form-item label="文章图片：" prop="coverImage" >
          <el-input v-model="ruleForm.coverImage" />
        </el-form-item>
        <el-form-item label="创建时间：" >
          <el-col :span="3">
            <el-form-item prop="createTime">
              <el-date-picker
                v-model="ruleForm.createTime"
                type="date"
                label="Pick a date"
                placeholder="Pick a date"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
          <el-col class="text-center" :span="1">
            <span class="text-gray-500">-</span>
          </el-col>
          <el-col :span="3">
            <el-form-item prop="createTime">
              <el-time-picker
                v-model="ruleForm.createTime"
                label="Pick a time"
                placeholder="Pick a time"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
        </el-form-item>
        <el-form-item label="更新时间：" >
          <el-col :span="3">
            <el-form-item prop="updateTime">
              <el-date-picker
                v-model="ruleForm.updateTime"
                type="date"
                label="Pick a date"
                placeholder="Pick a date"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
          <el-col class="text-center" :span="1">
            <span class="text-gray-500">-</span>
          </el-col>
          <el-col :span="3">
            <el-form-item prop="updateTime">
              <el-time-picker
                v-model="ruleForm.updateTime"
                label="Pick a time"
                placeholder="Pick a time"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
        </el-form-item>
        <!-- <el-form-item label="文章图片：" prop="coverImage">
          <template #default="scope">
              <el-form-item label="文章图片：" prop="coverImage">
                <el-input v-model="ruleForm.coverImage" />
              </el-form-item>
              <el-form-item label="文章图片：" prop="coverImage">
                <el-input v-model="ruleForm.coverImage" />
              </el-form-item>
              <el-form-item label="文章图片：" prop="coverImage">
                <el-input v-model="ruleForm.coverImage" />
              </el-form-item>
              <el-form-item label="文章图片：" prop="coverImage">
                <el-input v-model="ruleForm.coverImage" />
              </el-form-item>
          </template>
        </el-form-item> -->
        <el-form-item label="文章类型：" prop="typeName" required>
          <el-radio-group v-model="typeName">
            <el-radio :label=item.typeName v-for="(item,index) in bolgtype" :key="index" />
          </el-radio-group>
        </el-form-item>
        <el-form-item label="文章标签：" prop="tag" required>
          <el-checkbox-group v-model="tag">
            <el-checkbox :label="item.tagName" :name="item.tagName" v-for="(item,index) in bolgtag" :key="index"/>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="文章内容：" prop="blogContent" required>
          <div style="border: 1px solid #ccc">
            <Toolbar
              style="border-bottom: 1px solid #ccc"
              :editor="editorRef"
              :defaultConfig="toolbarConfig"
              :mode="mode"
            />
            <Editor
              style="height: 500px; overflow-y: hidden;"
              v-model="valueHtml"
              :defaultConfig="editorConfig"
              :mode="mode"
              @onCreated="handleCreated"
            />
          </div>
        </el-form-item> 
        <el-form-item>
          <el-button type="primary" @click="">修改</el-button>
          <el-button @click="resetForm(ruleFormRef)">重置</el-button>
        </el-form-item>
      </el-form>
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
    width: 30%;
  }
}

</style>
