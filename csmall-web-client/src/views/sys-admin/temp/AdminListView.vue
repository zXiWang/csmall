<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/sys-admin' }">
        <i class="el-icon-s-promotion"></i> 后台管理
      </el-breadcrumb-item>
      <el-breadcrumb-item>管理员列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-divider></el-divider>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="ID" align="center" width="80"></el-table-column>
      <el-table-column prop="username" label="用户名" header-align="center" width="180"></el-table-column>
      <el-table-column prop="nickname" label="昵称" align="center" width="100"></el-table-column>
      <el-table-column prop="phone" label="手机号码" align="center" width="110"></el-table-column>
      <el-table-column prop="email" label="电子邮箱" header-align="center" width="180"></el-table-column>
      <el-table-column prop="description" label="简介" header-align="center"></el-table-column>
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
          <el-button @click="handleDetails(scope.row)" type="primary" icon="el-icon-view" size="mini"
                     circle></el-button>
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

    }
  },
  methods: {
    changeEnable(admin) {
      let enable = ["disable", "enable"];
      let url = "http://localhost:8081/admin/" + admin.id + "/" + enable[admin.enable];
      console.log(url);
      // let params = new URLSearchParams();
      // params.append("id",admin.id);
      // params.append("enable",admin.enable);
      this.axios.create({
        'headers': {'Authorization': localJwt}
      }).post(url).then(response => {
        let responseBody = response.data;
        if (responseBody.state == 200) {
          this.$message.success("修改成功!");$
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
    handleDetails(admin) {
      let url = "http://localhost:8081/admin/details?id=" + admin.id;
      this.axios
          .create({headers: {'Authorization': localJwt}})
          .get(url).then(response => {
        let responseBody = response.data;
        console.log(responseBody);
        alert(responseBody.data);
      })
    },
    handleEdit(admin) {
      alert(admin.name);
    },
    handleDelete(admin) {
      let url = "http://localhost:8081/admin/" + admin.id + "/delete";
      this.axios
          .create({'headers': {'Authorization': localJwt}})
          .post(url).then(response => {
        let responseBody = response.data;
        if (responseBody.state == 200) {
          this.$message.success("删除成功!");
        } else {
          this.$message.error(responseBody.message);
        }
        this.loadAlbumList();
      })
    },
    openDeleteConfirm(admin) {
      let title = "提示";
      let message = "此操作将永久删除" + admin.username + "管理员,是否继续?";
      this.$confirm(message, title, {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleDelete(admin);
      }).catch(() => {
      });
    },
    loadAlbumList() {
      let url = "http://localhost:8081/admin/list";
      console.log("url:" + url);
      this.axios
          .create({'headers': {'Authorization': localJwt}})
          .get(url).then(response => {
        let responseBody = response.data;
        console.log(responseBody);
        console.log(localStorage.getItem("jwt"));
        this.tableData = responseBody.data;
      })
    }
  },
  mounted() {
    this.loadAlbumList();

  },
  created() {
    // localJwt = ;
  }
}
</script>
