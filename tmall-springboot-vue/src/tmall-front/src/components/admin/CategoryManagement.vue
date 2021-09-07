<template>
  <div class="admin-content">
    <el-tag size="small" effect="dark">分类管理</el-tag>
    <el-table :data="categoryList" border style="width: 100%" header-cell-style="">
      <el-table-column label="分类ID" width="90" prop="categoryId">
      </el-table-column>
      <el-table-column label="图片">

      </el-table-column>
      <el-table-column label="分类名称" width="180" prop="name">
      </el-table-column>
      <el-table-column label="属性管理" width="90">
        <span>测试8</span>
      </el-table-column>
      <el-table-column label="产品管理" width="90">
        <span>测试8</span>
      </el-table-column>
      <el-table-column label="操作" width="180">
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
          >编辑</el-button
          >
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
          >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="pages">
      <el-pagination background layout="prev, pager, next" :total="200">
      </el-pagination>
    </div>

    <div class="addCategory">
      <div class="panel-heading">新增分类</div>
      <el-form label-position="right" label-width="100px" style="margin-right: 10px">
        <el-form-item label="分类名称：" border>
          <el-input></el-input>
        </el-form-item>
        <el-form-item label="分类图片：">
          <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              list-type="picture"
              style="margin-right: 100px"
          >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
          <el-button id="submit" type="success">提交</el-button>
      </el-form>
    </div>
  </div>
</template>

<script>
import {getCategoryList} from "../../api/admin";

export default {
  name: "CategoryManagement",
  mounted() {
    this.getCategoryList();
  },
  data() {
    return {
      categoryList: []
    }
  },
  methods: {
    async getCategoryList() {
      let res = await getCategoryList();
      if (res !== null) {
        this.categoryList = res;
      }
    },
    handleEdit(index, row) {
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
    },
  },
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