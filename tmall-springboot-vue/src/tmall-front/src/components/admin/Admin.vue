<template>
  <div class="page_content">
    <div class="admin nav_tabs">
      <img id="tmallLogo" src="/src/assets/tmallbuy.png">
      <el-menu default-active="1" mode="horizontal" background-color="#222222" text-color="#9d9d9d" active-text-color="#ffd04b">
        <el-menu-item class="navbar-brand" index="1">天猫后台</el-menu-item>
        <el-menu-item class="navbar-brand"  index="2">分类管理</el-menu-item>
        <el-menu-item class="navbar-brand"  index="3">用户管理</el-menu-item>
        <el-menu-item class="navbar-brand"  index="4">订单管理</el-menu-item>
      </el-menu>
    </div>
    <CategoryManagement/>
  </div>
</template>

<script>
import CategoryManagement from "./CategoryManagement.vue";

export default {
  name: "admin",
  components: {
    CategoryManagement: CategoryManagement
  }
}
</script>

<style>
.nav_tabs {
  background-color: #222222;
  border-color: #080808;
  overflow: hidden;
  min-height: 50px;
  margin-bottom: 20px;
  border-style: solid;
  border-width: 1px;
}

#tmallLogo {
  width: 45px;
  float: left;
  margin-left: 10px;
  margin-top: 7px;
}

.nav_tabs .el-menu.el-menu--horizontal {
  border-bottom-width: 0;
}


</style>