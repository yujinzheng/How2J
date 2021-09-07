<template>
  <div class="top-content">
    <img id="tmallLogo" src="src/assets/tmallbuy.png">
    <a class="navbar-brand" href="#nowhere">天猫后台</a>
    <a class="navbar-brand" href="admin_category_list">分类管理</a>
    <a class="navbar-brand" href="admin_user_list">用户管理</a>
    <a class="navbar-brand" href="admin_order_list">订单管理</a>
  </div>
</template>

<script>
export default {
  name: "AdminNavTop"
}
</script>

<style scoped>

.top-content {
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
}

.navbar-brand {
  float: left;
  height: 20px;
  padding: 15px;
  font-size: 18px;
  line-height: 20px;
  color: #9d9d9d;
  text-decoration: none;
}

.navbar-brand:hover {
  color: white;
}
</style>