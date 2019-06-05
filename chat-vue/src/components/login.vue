<template>
  <div class="fullPage">
    <div class="loginBox">
        <el-form :model="formName" :rules="rules" ref="formName" class="accountLogin">
          <el-form-item class="loginFormItem" prop="code">
            <el-input placeholder="请输入账号" maxlength="16" type = "text" prefix-icon="el-icon-third-xingmingyonghumingnicheng" id="code" v-model="formName.code"></el-input>
          </el-form-item>
          <el-form-item class="loginFormItem" prop="password">
            <el-input placeholder="请输入密码" maxlength="16" type = "password" prefix-icon="el-icon-third-mima" id="password" v-model="formName.password"></el-input>
          </el-form-item>
          <el-form-item >
            <el-button class="loginButton" @click="submitForm('formName')">登录</el-button>
          </el-form-item>
        </el-form>
    </div>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        pic: '',
        formName: {//表单中的参数
          code: '',
          password: '',
        },
        rules: {
          code: [
            { required: true, message: '请输入账号', trigger: 'blur' },
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ]
        }
      }
    },
    created: function () {

    },
    methods: {


    }
  }
</script>
<style scoped>
  .fullPage{
    width: 100%;
    height: 100%;
    /*background: url("../../../assets/img/indexBg.png")center no-repeat;*/
    /*background-size: cover;*/
    background: lightblue;
    display: flex;
    justify-content: center;
    align-items:center;
  }
  .loginBox{
    width: 300px;
    height: 220px;
    background: #ffffff;
    border-radius: 10px;
  }
  .accountLogin{
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
  }
  .loginButton{
    width: 100%;
    background: #5879bb;
    color: #000000;
  }
  .loginButton:hover{
    background: #7994c9 !important;
  }
  >>> .el-form-item{
    width: 90%;
    margin-bottom: 0px !important;
  }
</style>
