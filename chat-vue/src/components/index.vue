<template>
  <div class="container">
    <div class="headerNav">
      <h2 class="platformName">合成作战协作平台</h2>
      <div class="personInfo">
        <el-dropdown trigger="click">
          <span class="el-dropdown-link">欢迎您，张三<i class="el-icon-arrow-down el-icon--right"></i></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="userinfo">个人信息</el-dropdown-item>
            <el-dropdown-item @click.native="editpwd">修改密码</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span @click.native="logout" class="quitLogin">退出登录</span>
      </div>
    </div>
    <div class="containerBottom" style="border: 1px solid green">
      <el-aside class="el-aside">
        <div class="leftMenu">
          <el-menu default-active="1" class="ulist"  @select="handleSelect">
            <el-menu-item index="1"><i class="el-icon-message"></i>页面1</el-menu-item>
            <el-menu-item index="2"><i class="el-icon-menu"></i>页面2</el-menu-item>
            <el-menu-item index="3"><i class="el-icon-setting"></i>页面3</el-menu-item>
          </el-menu>
        </div>
      </el-aside>
      <el-main style="overflow: hidden;padding: 0px 20px 0px 10px;">
        <router-view></router-view>
      </el-main>
    </div>
  </div>
</template>
<script>
  export default {
    data(){
      return{
        // 假设这是后台传来的数据来源
        data: [],
        // 所有页面的数据
        totalPage: [],
        // 每页显示数量
        pageSize: 5,
        // 共几页
        pageNum: 1,
        // 当前显示的数据
        dataShow: "",
        // 默认当前显示第一页
        currentPage: 0
      }
    },
    methods:{
      //退出
      logout() {

      },
      //个人信息
      userinfo() {

      },
      //修改密码
      editpwd() {

      },
      handleSelect(key, keyPath){
        switch(key){
          case '1':
            this.$router.push('/chatpage');
            break;
          case '2':
            this.$router.push('/twopage')
            break;
          case '3':
            this.$router.push('/threepage')
            break;
        }
      }
    }
  }
</script>

<style scoped>
  /*aside样式*/
  .leftMenu{
    height: 99.5%;
    display: flex;
    justify-content: space-between;
    border: 1px solid red;
  }
  >>> .el-menu{
    border: 0px;
    width: 100%;
  }
  .container{
    height: 100%;
    width: 100%;
    overflow: hidden;
  }
  .headerNav{
    width: 100%;
    height: 50px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    border: 1px solid red;
  }
  .containerBottom{
    width: 100%;
    height: calc(100% - 60px);
    display: flex;
    justify-content: center;
    border: 1px solid red;
  }
  .el-aside{
    width: 180px !important;
    height: 100%;
  }
  .platformName{
    margin-left: 20px;
  }
  .personInfo{
    margin-right: 20px;
    min-width: 185px;
    display: flex;
    justify-content: space-between;
  }
  .quitLogin, >>> .el-dropdown{
    font-size: 15px;
    cursor: pointer;
  }
</style>
