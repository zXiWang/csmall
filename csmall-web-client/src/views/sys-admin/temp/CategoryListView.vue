<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/sys-category' }">
        <i class="el-icon-s-promotion"></i> 后台管理
      </el-breadcrumb-item>
      <el-breadcrumb-item>类别列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-divider></el-divider>
    <el-button v-show="i!=0" @click="back" type="primary" icon="el-icon-arrow-left">返回
    </el-button>
    <el-button @click="loadCategoryList" type="primary">全部</el-button>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="ID" align="center" width="80"></el-table-column>
      <el-table-column prop="name" label="用户名" header-align="center" width="180"></el-table-column>
      <el-table-column prop="parentId" label="父类id" align="center" width="100"></el-table-column>
      <el-table-column prop="keywords" label="关键词列表" align="center"></el-table-column>
      <el-table-column prop="icon" label="图标图片url" header-align="center" width="180"></el-table-column>
      <el-table-column label="是否启用" align="center" width="80">
        <template slot-scope="scope">
          <el-switch
              v-model="scope.row.enable"
              :active-value="1"
              :inactive-value="0"
              active-color="#13ce66"
              inactive-color="#ccc"
              @change="changeEnable(scope.row)"
          >
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="handleShowAttributeList(scope.row)" type="primary" icon="el-icon-view" size="mini"
                     circle></el-button>
          <!--          <el-button @click="handleDetails(scope.row)" type="primary" icon="el-icon-view" size="mini"-->
          <!--                     circle></el-button>-->
          <el-button @click="handleEdit(scope.row)" type="primary" icon="el-icon-edit" size="mini" circle></el-button>
          <el-button @click="openDeleteConfirm(scope.row)" type="danger" icon="el-icon-delete" size="mini"
                     circle></el-button>
        </template>
      </el-table-column>

    </el-table>
  </div>
</template>

<script>
let localJwt = localStorage.getItem("jwt");
export default {

  data() {
    return {
      tableData: [],
      hierarchyId: [0],
      i: 1,
    }
  },
  methods: {
    changeEnable(category) {
      let enable = ["disable", "enable"];
      let url = "http://localhost:8080/category/" + category.id + "/" + enable[category.enable];
      console.log(url);
      // let params = new URLSearchParams();
      // params.append("id",category.id);
      // params.append("enable",category.enable);
      this.axios.create({
        'headers': {'Authorization': localJwt}
      }).post(url).then(response => {
        let responseBody = response.data;
        if (responseBody.state == 200) {
          this.$message.success("修改成功!");
        } else {
          // this.$message.error(responseBody.message);
          let title = '操作失败';
          this.$alert(responseBody.message, title, {
            confirmButtonText: '确定',
            callback: action => {
              this.loadAdminList();
            }
          });
        }
      })
    },
    handleDetails(category) {
      let url = "http://localhost:8080/category/details?id=" + category.id;
      this.axios.create({
        'headers': {'Authorization': localJwt}
      }).get(url).then(response => {
        let responseBody = response.data;
        console.log(responseBody);
        alert(responseBody.data);
      })
    },
    handleEdit(category) {
      alert(category.name);
    },
    handleDelete(category) {
      let url = "http://localhost:8080/category/" + category.id + "/delete";
      this.axios.create({
        'headers': {'Authorization': localJwt}
      }).post(url).then(response => {
        let responseBody = response.data;
        if (responseBody.state == 200) {
          this.$message.success("删除成功!");
        } else {
          this.$message.error(responseBody.message);
        }
        this.loadCategoryList();
      })
    },
    openDeleteConfirm(category) {
      let title = "提示";
      let message = "此操作将永久删除" + category.name + "类别,是否继续?";
      this.$confirm(message, title, {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleDelete(category);
      }).catch(() => {
      });
    },
    loadCategoryList() {
      let url = "http://localhost:8080/category/list";
      console.log("url:" + url);
      this.axios
          .create({'headers': {'Authorization': localJwt}}).get(url).then(response => {
        let responseBody = response.data;
        console.log(responseBody);
        // console.log(localStorage.getItem("jwt"));
        this.tableData = responseBody.data;
      })
    },
    handleShowAttributeList(category) {
      this.i = this.i + 1;
      this.hierarchyId.push(category.parentId);
      console.log(this.hierarchyId);
      this.switchShow = 0;
      let url = "http://localhost:8080/category/" + category.id + "/list";
      console.log("url:" + url);
      this.axios
          .create({'headers': {'Authorization': localJwt}}).get(url).then(response => {
        let responseBody = response.data;
        console.log(responseBody);
        this.tableData = responseBody.data;
      })
    },
    back() {
      this.i=this.i-1;
      let id = this.hierarchyId[this.hierarchyId.length - 1];
      if (this.hierarchyId.length > 1) {
        this.hierarchyId.splice(this.hierarchyId.length - 1);
      }
      let url = "http://localhost:8080/category/" + id + "/list";
      console.log("url:" + url);
      this.axios
          .create({'headers': {'Authorization': localJwt}}).get(url).then(response => {
        let responseBody = response.data;
        console.log(responseBody);
        this.tableData = responseBody.data;

      })

    },
  },
  mounted() {
    this.back();

  },
  created() {
    // localJwt = ;
  }
}
</script>
