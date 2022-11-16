<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/sys-admin' }">
        <i class="el-icon-s-promotion"></i> 后台管理
      </el-breadcrumb-item>
      <el-breadcrumb-item>添加属性</el-breadcrumb-item>
    </el-breadcrumb>

    <el-divider></el-divider>

    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="130px" class="demo-ruleForm">
      <el-form-item label="名称" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="简介" prop="description">
        <el-input v-model="ruleForm.description"></el-input>
      </el-form-item>
      <el-form-item label="计量单位" prop="unit">
        <el-input v-model="ruleForm.unit"></el-input>
      </el-form-item>
      <el-form-item label="排序序号" prop="sort">
        <el-input v-model="ruleForm.sort"></el-input>
      </el-form-item>
      <el-form-item label="输入类型" prop="inputType">
        <el-select
            v-model="ruleForm.inputType"
            style="margin-left: 20px;"
            placeholder="请选择">
          <el-option
              v-for="item in inputTypes"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="属性类型" prop="type">
        <el-select
            v-model="ruleForm.type"
            style="margin-left: 20px;"
            placeholder="请选择">
          <el-option
              v-for="item in types"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所属属性模板" prop="templateId">
        <el-select
            v-model="ruleForm.templateId"
            style="margin-left: 20px;"
            clearable
            placeholder="请选择">
          <el-option
              v-for="item in attributeTemplateList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <!--      <el-form-item label="是否允许自定义" prop="enable">-->
      <!--        <el-switch-->
      <!--            v-model="ruleForm.isAllowCustomize"-->
      <!--            :active-value="1"-->
      <!--            :inactive-value="0"-->
      <!--            active-color="#13ce66"-->
      <!--            inactive-color="#999">-->
      <!--        </el-switch>-->
      <!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
let localJwt = localStorage.getItem("jwt");

export default {
  data() {
    return {
      attributeTemplateList: [],
      types: [{
        value: 0,
        label: '非销售属性'
      }, {
        value: 1,
        label: '销售属性'
      }],
      inputTypes: [
        {
          value: 0,
          label: '手动录入'
        },
        {
          value: 1,
          label: '单选'
        }, {
          value: 2,
          label: '多选'
        }, {
          value: 3,
          label: '单选(下拉菜单)'
        },
        {
          value: 4,
          label: '多选(下拉菜单)'
        },
      ],
      ruleForm: {
        templateId: '',
        name: '',
        description: '',
        type: '',
        inputType: 0,
        unit: '',
        sort: 99,
        isAllowCustomize: 1,
      },
      rules: {
        name: [
          {required: true, message: '请输入类别名称', trigger: 'blur'},
          {min: 1, max: 25, message: '长度在 1 到 25 个字符', trigger: 'blur'}
        ],
        templateId: [
          {required: true, message: '请输入所属属性模板id', trigger: 'blur'},
        ],
        description: [
          {required: true, message: '请输入属性简介', trigger: 'blur'},
          {min: 4, max: 30, message: '长度在 4 到 30 个字符', trigger: 'blur'}
        ],
        type: [
          {required: true, message: '请输入属性类型', trigger: 'blur'},
        ],
      }
    };
  },
  methods: {
    LoadAttributeTemplateList() {
      let url = "http://localhost:8080/attributeTemplate/list";
      this.axios.create({
        'headers': {'Authorization': localJwt}
      }).get(url).then(response => {
        let responseBody = response.data;
        console.log(responseBody);
        if (responseBody.state == 200) {
          this.attributeTemplateList = responseBody.data;
        }
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let formData = this.qs.stringify(this.ruleForm);
          let url = 'http://localhost:8080/attribute/addNew';
          this.axios.post(url, formData).then(response => {
            let responseBody = response.data;
            console.log(responseBody);
            if (responseBody.state == 200) {
              // console.log('添加管理员成功！');
              this.$message({
                message: '添加属性成功！',
                type: 'success'
              });
              this.resetForm(formName);
            } else {
              this.$message.error(responseBody.message);
            }
          });
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
  mounted() {
    this.LoadAttributeTemplateList();
  }
}
</script>

<style scoped>

</style>