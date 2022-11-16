<template>
  <div>
    <el-container>

      <el-header class="layout-header">管理系统
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>

      <el-container class="layout-body">
        <el-aside class="layout-aside">
          <el-menu :default-active="$router.currentRoute.path"
                   router
                   class="el-menu-vertical-demo"
                   background-color="#222c32"
                   text-color="#fff"
                   active-text-color="#ffd04b">

            <el-menu-item index="1">
              <i class="el-icon-s-home"></i>
              <span>首页</span>
            </el-menu-item>

            <!--临时页面-->
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-s-tools"></i>临时页面</template>
              <el-menu-item index="/sys-admin/temp/admin/list">
                <i class="el-icon-s-operation"></i>
                <span>管理员列表</span>
              </el-menu-item>
              <el-menu-item index="/sys-admin/temp/admin/add-new">
                <i class="el-icon-circle-plus-outline"></i>
                添加管理员
              </el-menu-item>
              <el-menu-item index="/sys-admin/temp/role/list">
                <i class="el-icon-s-operation"></i>
                <span>角色列表</span>
              </el-menu-item>
              <el-menu-item index="/sys-admin/temp/role/add-new">
                <i class="el-icon-circle-plus-outline"></i>
                添加角色
              </el-menu-item>
              <el-menu-item index="/sys-admin/temp/brand/list">
                <i class="el-icon-s-operation"></i>
                品牌列表
              </el-menu-item>
              <el-menu-item index="/sys-admin/temp/brand/add-new">
                <i class="el-icon-circle-plus-outline"></i>
                添加品牌
              </el-menu-item>
              <el-menu-item index="/sys-admin/temp/category/list">
                <i class="el-icon-s-operation"></i>
                类别列表
              </el-menu-item>
              <el-menu-item index="/sys-admin/temp/category/add-new">
                <i class="el-icon-circle-plus-outline"></i>
                添加类别
              </el-menu-item>
              <el-menu-item index="/sys-admin/temp/album/list">
                <i class="el-icon-s-operation"></i>
                相册列表
              </el-menu-item>
              <el-menu-item index="/sys-admin/temp/album/add-new">
                <i class="el-icon-circle-plus-outline"></i>
                添加相册
              </el-menu-item>
              <el-menu-item index="/sys-admin/temp/attributeTemplate/list">
                <i class="el-icon-s-operation"></i>
                属性模板列表
              </el-menu-item>
              <el-menu-item index="/sys-admin/temp/attributeTemplate/add-new">
                <i class="el-icon-circle-plus-outline"></i>
                添加属性模板
              </el-menu-item>
              <el-menu-item index="/sys-admin/temp/attribute/list">
                <i class="el-icon-s-operation"></i>
                属性列表
              </el-menu-item>
              <el-menu-item index="/sys-admin/temp/attribute/add-new">
                <i class="el-icon-circle-plus-outline"></i>
                添加属性
              </el-menu-item>
            </el-submenu>
            <!--商品管理-->
            <el-submenu index="3">
              <template slot="title"><i class="el-icon-goods"></i>商品管理</template>
              <el-menu-item index="3-1">
                <i class="el-icon-s-grid"></i>
                SPU台账
              </el-menu-item>
              <el-menu-item index="3-2">
                <i class="el-icon-circle-plus-outline"></i>
                新增SPU
              </el-menu-item>
              <el-menu-item index="3-3">
                <i class="el-icon-picture"></i>
                相册管理
              </el-menu-item>
              <el-menu-item index="3-4">
                <i class="el-icon-s-operation"></i>
                商品类别
              </el-menu-item>
              <el-menu-item index="3-5">
                <i class="el-icon-s-flag"></i>
                属性模板
              </el-menu-item>
              <el-menu-item index="3-6">
                <i class="el-icon-s-data"></i>
                品牌管理
              </el-menu-item>
            </el-submenu>

            <el-submenu index="4">
              <template slot="title"><i class="el-icon-s-order"></i>订单管理</template>
            </el-submenu>
            <el-submenu index="5">
              <template slot="title"><i class="el-icon-s-ticket"></i>营销管理</template>
            </el-submenu>
            <el-submenu index="6">
              <template slot="title"><i class="el-icon-s-shop"></i>商家管理</template>
            </el-submenu>
            <el-submenu index="7">
              <template slot="title"><i class="el-icon-s-check"></i>权限管理</template>
            </el-submenu>

          </el-menu>
        </el-aside>

        <el-main class="layout-main">
          <router-view></router-view>
        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  data() {
    return {}
  },
  methods: {}
};
</script>

<style>

.layout-header {
  background: cadetblue;
  line-height: 60px;
}

.layout-body {
  position: absolute;
  top: 60px;
  bottom: 0;
  left: 0;
  right: 0;
  border: 1px solid black;
}

.layout-aside {
  overflow-y: scroll;
  background-color: #222c32;

}

.layout-aside i {
  color: white !important;
}

.layout-main {
  border: 1px solid blue;
}

.el-menu-item.is-active {
  background: #2c3e50  !important;
}


</style>