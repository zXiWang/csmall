<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/sys-brand' }">
        <i class="el-icon-s-promotion"></i> 后台管理
      </el-breadcrumb-item>
      <el-breadcrumb-item>品牌列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-divider></el-divider>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="序号" align="center" width="100">
      </el-table-column>
      <el-table-column prop="name" label="品牌名称" align="center" width="120">
      </el-table-column>
      <el-table-column prop="pinyin" label="品牌名称的拼音" align="center" width="180">
      </el-table-column>
      <el-table-column prop="logo" label="品牌logo" align="center" width="180">
      </el-table-column>
      <el-table-column prop="description" label="品牌简介" align="center" >
      </el-table-column>
      <el-table-column prop="keywords" label="品牌关键词" align="center" width="150">
      </el-table-column>
      <el-table-column prop="sort" label="排序序号" align="center" width="80">
      </el-table-column>
<!--      <el-table-column prop="sales" label="销量" align="center" width="180">-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="productCount" label="商品种类数量" align="center" width="180">-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="commentCount" label="买家评论数量" align="center" width="180">-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="positiveCommentCount" label="买家好评数量" align="center" width="180">-->
<!--      </el-table-column>-->
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
    changeEnable(brand) {
      let enable =["disable", "enable"];
      let url = "http://localhost:8080/brand/" + brand.id +"/"+ enable[brand.enable];
      console.log(url);
      // let params = new URLSearchParams();
      // params.append("id",brand.id);
      // params.append("enable",brand.enable);
      this.axios
          .create({'headers': {'Authorization': localJwt}})
          .post(url).then(response => {
        let responseBody = response.data;
        if (responseBody.state == 200) {
          this.$message.success("修改成功!");
        } else {
          this.$message.error(responseBody.message);
        }
      })
    },
    handleEdit(brand) {
      alert(brand.name);
    },
    handleDelete(brand) {
      let url = "http://localhost:8080/brand/" + brand.id + "/delete";
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
    openDeleteConfirm(brand) {
      let title = "提示";
      let message = "此操作将永久删除" + brand.name + "品牌,是否继续?";
      this.$confirm(message, title, {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleDelete(brand);
      }).catch(() => {
      });
    },
    loadAlbumList() {
      let url = "http://localhost:8080/brand/list";
      console.log("url:" + url);
      this.axios
          .create({'headers': {'Authorization': localJwt}})
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
