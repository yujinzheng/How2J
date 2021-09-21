<template>
  <div class="admin-content">
    <el-tag size="small" effect="dark">分类管理</el-tag>
    <el-table :data="categoryData.categoryList" border style="width: 100%">
      <el-table-column label="分类ID" width="90" prop="id">
      </el-table-column>
      <el-table-column label="图片">
        <template #default="scope">
          <img :src="scope.row.imagePath">
        </template>
      </el-table-column>
      <el-table-column label="分类名称" width="180" prop="name">
      </el-table-column>
      <el-table-column label="属性管理" width="90">
        <el-button size="mini" icon="el-icon-s-operation" type="primary"
        ></el-button>
      </el-table-column>
      <el-table-column label="产品管理" width="90">
        <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)"
                   icon="el-icon-shopping-cart-2"
        ></el-button
        >
      </el-table-column>
      <el-table-column label="操作" width="180">
        <template #default="scope">
          <el-button size="mini" type="primary" @click="handleEdit(scope.row)"
                     icon="el-icon-edit"
          ></el-button
          >
          <el-button
              size="mini"
              type="danger"
              @click="deleteCategory(scope.row)"
              icon="el-icon-delete">
          </el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="pages">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageInfo.currentPage"
          :page-sizes="pageInfo.pageSizes"
          :page-size="pageInfo.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pageInfo.total"
      >
      </el-pagination>
    </div>

    <div class="addCategory">
      <div class="panel-heading">新增分类</div>
      <el-form label-position="right" label-width="100px" style="margin-right: 10px">
        <el-form-item label="分类名称：" border>
          <el-input v-model="categoryAddInfo.name"></el-input>
        </el-form-item>
        <el-form-item label="分类图片：">
          <el-upload
              ref="categoryUpload"
              action="tmall-vue/admin/category/add"
              list-type="text"
              style="margin-right: 100px"
              name="picture"
              :data="categoryAddInfo"
              :limit="1"
              :auto-upload="false"
              :on-success="uploadSuccess"
              accept=".jpg,.png"
          >
            <el-button size="small" type="primary">选取图片</el-button>
          </el-upload>
        </el-form-item>
        <el-button id="submit" type="success" @click="addCategory()">提交</el-button>
      </el-form>
    </div>
    <div class="update dialog">
      <el-dialog title="编辑分类" v-model="editFormVisible">
        <el-form label-position="right" label-width="100px" style="margin-right: 10px">
          <el-form-item label="分类名称：" border>
            <el-input v-model="categoryEditInfo.name"></el-input>
          </el-form-item>
          <el-form-item label="分类图片：">
            <el-upload
                ref="categoryUpdateUpload"
                action="tmall-vue/admin/category/update"
                list-type="text"
                style="margin-right: 100px"
                name="picture"
                :data="{'category': JSON.stringify(categoryEditInfo)}"
                :limit="1"
                :auto-upload="false"
                :on-success="updateUploadSuccess"
                accept=".jpg,.png"
            >
              <el-button size="small" type="primary">选取图片</el-button>
            </el-upload>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="editFormVisible = false">取 消</el-button>
            <el-button id="update" type="success" @click="updateCategory"
            >确 定</el-button
            >
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import {reactive, toRaw, toRefs} from '@vue/reactivity';
import {categoryListGet, categoryDelete, categoryAdd, categoryUpdate} from "../../api/admin";
import {ref} from 'vue'
import {onMounted} from '@vue/runtime-core';
import {ElMessage, ElMessageBox} from 'element-plus'
import {Edit} from '@element-plus/icons'

export default {
  name: "CategoryManagement",
  components: {
    Edit
  },
  setup() {
    let categoryData = reactive({
      'categoryList': []
    });
    let pageInfo = reactive({
      'total': 0,
      'currentPage': 1,
      'pageSize': 10,
      'pageSizes': [10, 50, 100]
    });
    const categoryUpload = ref(null);
    const categoryUpdateUpload = ref(null);
    const categoryAddInfo = reactive({
      name: ''
    })
    const state = reactive({
      editFormVisible: false
    })
    const categoryEditInfo = reactive({
      id: '',
      name: '',
      imagePath: ''
    })
    const getCategories = () => {
      categoryListGet(pageInfo.currentPage, pageInfo.pageSize).then((response) => {
        if (response.code !== "0000000") {
          ElMessage.error(response.desc)
          return;
        }
        const data = JSON.parse(response.data);
        pageInfo.total = data.total;
        pageInfo.currentPage = data.currentPage;
        categoryData.categoryList = data.categoryList
      })
    };
    const addCategory = () => {
      if (toRaw(categoryUpload.value.uploadFiles).length === 0) {
        const data = {
          'name': categoryAddInfo.name
        }
        categoryAdd(data);
        categoryAddInfo.name = '';
      } else {
        categoryUpload.value.submit();
      }
      setTimeout(function () {
        getCategories(pageInfo.currentPage, pageInfo.pageSize)
      }, 50)
    };
    const uploadSuccess = () => {
      categoryUpload.value.clearFiles();
      categoryAddInfo.name = '';
    };
    const updateUploadSuccess = () => {
      categoryUpdateUpload.value.clearFiles();
      categoryEditInfo.name = '';
    };
    const updateCategory = () => {
      if (toRaw(categoryUpdateUpload.value.uploadFiles).length === 0) {
        const data = {
          'name': categoryEditInfo.name,
          'id': categoryEditInfo.id,
          'imagePath': categoryEditInfo.imagePath
        }
        categoryUpdate(data);
        categoryEditInfo.name = '';
      } else {
        categoryUpdateUpload.value.submit();
      }
      state.editFormVisible = false;
      setTimeout(function () {
        getCategories(pageInfo.currentPage, pageInfo.pageSize)
      }, 150)
    };
    const handleSizeChange = (val) => {
      pageInfo.pageSize = val;
      getCategories(pageInfo.currentPage, pageInfo.pageSize);
    };
    const handleCurrentChange = (val) => {
      pageInfo.currentPage = val;
      getCategories(pageInfo.currentPage, pageInfo.pageSize);
    };
    onMounted(() => {
      getCategories(pageInfo.currentPage, pageInfo.pageSize);
    });
    const handleEdit = (row) => {
      state.editFormVisible = true;
      categoryEditInfo.name = row.name;
      categoryEditInfo.id = row.id;
      categoryEditInfo.imagePath = row.imagePath;
    };
    const deleteCategory = (row) => {
      ElMessageBox.confirm('此操作将删除该分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      })
          .then(() => {
            const data = {
              id: row.id,
              imagePath: row.imagePath
            }
            categoryDelete(data).then((response) => {
              if (response.code !== "0000000") {
                ElMessage.error(response.desc)
              } else {
                ElMessage({
                  type: 'success',
                  message: '删除成功!',
                });
              }
            });
            setTimeout(function () {
              getCategories(pageInfo.currentPage, pageInfo.pageSize)
            }, 50)
          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '已取消删除',
            });
          });

    };
    return {
      categoryData,
      getCategories,
      handleEdit,
      deleteCategory,
      pageInfo,
      handleSizeChange,
      handleCurrentChange,
      addCategory,
      categoryUpload,
      categoryUpdateUpload,
      categoryAddInfo,
      uploadSuccess,
      updateUploadSuccess,
      ...toRefs(state),
      categoryEditInfo,
      updateCategory
    }
  }
}
</script>

<style scoped>
.admin-content {
  margin: 0 40px;
  overflow: hidden;
}

.admin-content .el-tag--small {
  float: left;
  margin: 0 0 10px 0;
}

.admin-content .el-table {
  --el-table-header-background-color: #dff0d8;
  --el-table-border-color: #dddddd;
}

div.pages {
  margin-top: 20px;
}

div.addCategory {
  width: 400px;
  margin: 20px auto 40px auto;
  border: #dddddd solid 1px;
}

div.addCategory .panel-heading {
  background-color: #fcf8e3;
  text-align: left;
  color: #8a6d3b;
  border-color: #faebcc;
  border-bottom: 1px solid transparent;
  border-top-left-radius: 3px;
  border-top-right-radius: 3px;
  size: 14px;
  padding: 10px 15px;
  margin-bottom: 10px;
}

button#submit {
  margin-bottom: 10px;
}

</style>