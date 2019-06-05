<template>
  <div class="leftMenu">
    <el-menu default-active="1" class="el-menu-vertical-demo">
      <ul class="ulist">
        <li><router-link  to="../childpage/onepage" >页面1</router-link></li>
        <li><router-link  to="../childpage/twopage" >页面2</router-link></li>
        <li><router-link  to="../childpage/threepage" >页面3</router-link></li>
      </ul>
    </el-menu>
  </div>
</template>
<script>
  export default {
    data(){
      return{

      }

    },
    methods:{


    }
  }
</script>
<style scoped>
  .leftMenu{
    height: 99.5%;
    display: flex;
    justify-content: space-between;
    border: 1px solid red;
  }
  >>> .el-menu-item{
    padding-left: 8px !important;
  }
  ul li{
    text-align: left;
    line-height: 30px;
    cursor: pointer;
  }
  ul li a{
    text-decoration: none;
    color: #000000;
  }
  >>> .el-menu{
    border: 0px;
  }
</style>
