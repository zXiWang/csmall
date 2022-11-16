<template>
  <div style="width: 600px;margin: 150px auto;background: white;padding-top: 30px;text-align: center;">
    <h1>管理员登录</h1>
    <el-form style="padding: 30px 50px;margin: 0 auto;" :model="ruleForm" :rules="rules" ref="ruleForm"
             label-width="100px" class="demo-ruleForm">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="ruleForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="ruleForm.password"></el-input>
      </el-form-item>
      <label style="padding-bottom:20px;display: inline-block ">
        <input type="checkbox" v-model="ruleForm.rem">记住用户名和密码<br>
      </label>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      ruleForm: {
        username: '',
        password: '',
        rem: false,
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 4, max: 15, message: '长度在 4 到 15 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 4, max: 15, message: '长度在 4 到 15 个字符', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let formData = this.qs.stringify(this.ruleForm);
          let url = 'http://localhost:8081/admin/login';
          this.axios.post(url, formData).then((response) => {
            let responseBody = response.data;
            console.log(responseBody);
            switch (responseBody.state) {
              case 1:
                this.$message.error("用户名或密码不能为空!");
                break;
              case 2:
                this.$message.error("用户名不存在!");
                break;
              case 3:
                this.$message.error("用户名或密码错误!");
                break;
              case 200:
                this.$message.success("登录成功!");
                console.log("JWT=" + responseBody);
                localStorage.setItem("jwt", responseBody.data);
                const username = this.ruleForm.username;
                const password = this.ruleForm.password;
                if (this.ruleForm.rem) {
                  this.$cookie.set('username', username);
                  this.$cookie.set('password', password);
                }
                setTimeout(function () {
                  this.$router.push('/sys-admin/index');
                }, 1000)

                break;
            }
          })

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  created: function () {
    this.ruleForm.username = this.$cookie.get('username');
    this.ruleForm.password = this.$cookie.get('password')
  }
}
</script>

<style>
body {
  background: #568a97;
}

.el-form-item {
  margin-bottom: 22px;
  margin-right: 60px;
}

</style>