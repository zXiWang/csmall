<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/sys-admin' }">
        <i class="el-icon-s-promotion"></i> 后台管理
      </el-breadcrumb-item>
      <el-breadcrumb-item>添加管理员</el-breadcrumb-item>
    </el-breadcrumb>

    <el-divider></el-divider>

    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="130px" class="demo-ruleForm">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="ruleForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password"></el-input>
      </el-form-item>
      <el-form-item label="昵称" prop="nickname">
        <el-input v-model="ruleForm.nickname"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="ruleForm.phone"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="ruleForm.email"></el-input>
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input v-model="ruleForm.description"></el-input>
      </el-form-item>
      <el-form-item label="头像" prop="avatar">
        <el-input v-model="ruleForm.avatar"></el-input>
      </el-form-item>
      <el-form-item label="角色" prop="roleIds">
        <el-select
            v-model="ruleForm.roleIds"
            multiple
            collapse-tags
            style="margin-left: 20px;"
            placeholder="请选择">
          <el-option
              v-for="item in roleList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      roleList: [],
      ruleForm: {
        username: '',
        description: '',
        enable: 1,
        keywords: '',
        nickname: '',
        avatar: '',
        phone: '',
        email: '',
        roleIds: [],
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 25, message: '长度在 6 到 25 个字符', trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '请输入号码', trigger: 'blur'},
          {min: 6, max: 25, message: '长度在 6 到 25 个字符', trigger: 'blur'}
        ],
        email: [
          {required: true, message: '请输入邮箱', trigger: 'blur'},
          {min: 6, max: 25, message: '长度在 6 到 25 个字符', trigger: 'blur'}
        ],
        description: [
          {required: true, message: '请输入用户描述', trigger: 'blur'},
          {min: 2, max: 30, message: '长度在 2 到 30 个字符', trigger: 'blur'}
        ],
        roleIds:[
          {required: true, message: '请选择角色', trigger: 'blur'},
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let formData = this.qs.stringify(this.ruleForm);
          let url = 'http://localhost:8081/admin/addNew';
          this.axios.post(url, formData).then(response => {
            let responseBody = response.data;
            if (responseBody.state == 200) {
              // console.log('添加管理员成功！');
              this.$message({
                message: '添加管理员成功！',
                type: 'success'
              });
              this.resetForm(formName);
            } else {
              this.$message.error(responseBody.message);
            }
          });
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    loadRoleList() {
      let url = "http://localhost:8081/role/list";
      this.axios.get(url).then(response => {
        let responseBody = response.data;
        if (responseBody.state == 200) {
          this.roleList = responseBody.data;
          console.log(responseBody.data);
          console.log(this.ruleForm.roleIds);
        }
      })
    },

  },
  mounted() {
    this.loadRoleList();
  }
}
</script>

<style scoped>

</style>
<style>
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

.el-icon-arrow-down {
  font-size: 12px;

}
</style>