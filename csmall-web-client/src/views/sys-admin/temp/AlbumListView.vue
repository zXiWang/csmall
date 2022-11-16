<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/sys-admin' }">
        <i class="el-icon-s-promotion"></i> 后台管理
      </el-breadcrumb-item>
      <el-breadcrumb-item>相册列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-divider></el-divider>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="序号" align="center" width="180">
      </el-table-column>
      <el-table-column prop="name" label="相册名称" align="center" width="180">
      </el-table-column>
      <el-table-column prop="description" header-align="center" label="相册简介">
      </el-table-column>
      <el-table-column prop="sort" align="center" label="排序序号" width="100">
      </el-table-column>
      <el-table-column align="center" label="操作" width="150">
        <template slot-scope="scope">
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
    handleEdit(album) {
      alert(album.name);
    },
    handleDelete(album) {
      let url = "http://localhost:8080/album/" + album.id + "/delete";
      this.axios
          .create({headers: {'Authorization': localJwt}})
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
    openDeleteConfirm(album){
      let title = "提示";
      let message = "此操作将永久删除"+album.name+"相册,是否继续?";
      this.$confirm(message, title, {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleDelete(album);
      }).catch(() => {
      });
    },
    loadAlbumList() {
      let url = "http://localhost:8080/album/list";
      console.log("url:" + url);
      this.axios
          .create({headers: {'Authorization': localJwt}})
          .get(url).then(response => {
        let responseBody = response.data;
        console.log(responseBody);
        this.tableData = responseBody.data;
      })
    }
  },
  mounted() {
    this.loadAlbumList();

  },
  created() {
  }
}
</script>
