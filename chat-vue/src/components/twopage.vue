<template>
  <div class="listOuter">
    <div class="operateBtn">
      <el-button type="primary" icon="el-icon-plus" v-on:click="addBracelet()" class="addNewUser">新增</el-button>
    </div>
    <div id="datagrid">
      <!--<el-table :data="dataInfo.records" stripe border v-loading="loading" :fit="true">-->
      <el-table :data="tableData3" border style="width: 100%">
        <!--<el-table-column type="index" :index="indexStart" label="序号"></el-table-column>-->
        <el-table-column prop="number" label="门禁卡号"></el-table-column>
        <!--<el-table-column label="状态">-->
          <!--<template slot-scope="scope">-->
            <!--<div v-if="scope.row.status == 1">-->
              <!--{{statusFMT(scope.row.status)}}-->
            <!--</div>-->
            <!--<div v-else>-->
              <!--{{useStatusFMT(scope.row.useStatus)}}-->
            <!--</div>-->
          <!--</template>-->
        <!--</el-table-column>-->
        <el-table-column prop="createTime" label="入区登记时间"></el-table-column>
        <el-table-column prop="userName" label="使用民警"></el-table-column>
        <el-table-column prop="userNo" label="警号"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
            <el-button type="text" size="small">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!--<el-pagination prev-text="上一页" next-text="下一页"  background :page-size="dataInfo.size"-->
                     <!--layout="total, sizes, prev, pager, next, jumper" :total="dataInfo.total"-->
                     <!--:current-page="dataInfo.current"-->
                     <!--@size-change="sizeChange" @current-change="currentChange"-->
      <!--&gt;</el-pagination>-->
      <!--测试-->
      <el-pagination prev-text="上一页" next-text="下一页"  background :page-size="5"
                     layout="total, sizes, prev, pager, next, jumper" :total="100"
                     :current-page="1"
      ></el-pagination>
      <!--<el-dialog title="新增" :visible.sync="newEntranceNumVisiable" :close-on-click-modal="false"  :lock-scroll="true">-->
        <!--<newEntranceNum ref="newEntranceNum"></newEntranceNum>-->
        <!--<div slot="footer" class="dialog-footer">-->
          <!--<el-button v-on:click="cancelAdd">取 消</el-button>-->
          <!--<el-button type="primary" v-on:click="saveEntranceNum">保存</el-button>-->
        <!--</div>-->
      <!--</el-dialog>-->
    </div>
  </div>
</template>

<script>
  // import permissions from "../../base/permissionData"
  // import newEntranceNum from "../base/newEntranceNum"
  export default {
    // components: {
    //   newEntranceNum:newEntranceNum
    // },
    data: function () {
      return {
        name:"",
        // permissions:permissions,
        permissionList:"",
        editVisiable: false,
        newEntranceNumVisiable:false,
        currentUserId: -1,
        dataInfo: {
          records: [],
          total: 0,
          current: 1,
          size: 10
        },
        indexStart: 0,
        loading: true,
        // 测试
        tableData3: [{
          // index: '1',
          number: '01',
          createTime: '2016-05-02',
          userName:"罗飞",
          userNo:"001"
        }, {
          // index: '1',
          number: '01',
          createTime: '2016-05-02',
          userName:"罗飞",
          userNo:"001"
        }, {
          // index: '1',
          number: '01',
          createTime: '2016-05-02',
          userName:"罗飞",
          userNo:"001"
        }, {
          // index: '1',
          number: '01',
          createTime: '2016-05-02',
          userName:"罗飞",
          userNo:"001"
        }, {
          // index: '1',
          number: '01',
          createTime: '2016-05-02',
          userName:"罗飞",
          userNo:"001"
        }]
      }
    },
    // created: function () {
    //   let sessionId = window.sessionStorage.getItem('token')
    //   if(!sessionId) {
    //     this.$router.replace('login')
    //   } else {
    //     this.sessionId = sessionId
    //   }
    //   this.permissionList=window.sessionStorage.getItem('permissionList')
    //   this.getUser()
    // },
    methods: {
      handleClick(row) {
        console.log(row);
      },
      // permission:function(str){
      //   if(this.permissionList.toString().indexOf(str)!=-1){
      //     return true
      //   }else {
      //     return false
      //   }
      // },
      // useStatusFMT: function (useStatus) {
      //   switch (parseInt(useStatus)) {
      //     case 0:
      //       return "使用中"
      //     case 1:
      //       return "未使用"
      //   }
      // },
      // statusFMT: function (status) {
      //   switch (parseInt(status)) {
      //     case 0:
      //       return "启用"
      //     case 1:
      //       return "禁用"
      //   }
      // },
      addBracelet:function(){
        // this.newEntranceNumVisiable = true
      },
      // cancelAdd:function(){
      //   this.newEntranceNumVisiable = false
      //   this.getUser()
      // },
      // saveEntranceNum:function(){
      //   let newEntranceNum = this.$refs.newEntranceNum
      //   newEntranceNum.$refs.form.validate(valid => {
      //     if (valid) {
      //       this.$ajax({
      //         method: 'post',
      //         url: this.$basePath + "/platform/cardInfo/insertCard",
      //         data: {
      //           number: newEntranceNum.form.number
      //         },
      //         headers: {
      //           token: this.sessionId
      //         }
      //       }).then(res => {
      //         console.log(res)
      //         if(res.data.success) {
      //           this.newEntranceNumVisiable = false
      //           this.getUser()
      //           newEntranceNum.$refs.form.resetFields();
      //         }
      //       }).catch(res => {
      //         console.log(res)
      //       })
      //     } else {
      //       console.log("no submit")
      //     }
      //   })
      // },
      // sizeChange: function (num) {
      //   let sessionId = window.sessionStorage.getItem('token')
      //   if (!sessionId) {
      //     this.$router.replace('login')
      //   }
      //   this.dataInfo.size = num
      //   this.getUser()
      // },
      // currentChange: function (page) {
      //   let sessionId = window.sessionStorage.getItem('token')
      //   if (!sessionId) {
      //     this.$router.replace('login')
      //   }
      //   this.dataInfo.current = page
      //   this.getUser()
      // },
      // cancelEdit: function () {
      //   this.editVisiable = false
      // },
      // getUser: function () {
      //   this.loading = true
      //   this.$ajax({
      //     method: 'get',
      //     url: this.$basePath + "/platform/cardInfo/findAllCards?rows="+ this.dataInfo.size + "&page=" + this.dataInfo.current,
      //     headers: {
      //       'token': this.sessionId
      //     }
      //   }).then(res => {
      //     console.log(res)
      //     this.dataInfo = res.data.info
      //     this.indexStart = (this.dataInfo.current - 1 ) * this.dataInfo.size + 1
      //     this.loading = false
      //   }).catch(function (res) {
      //     this.loading = false
      //     console.log(res)
      //   })
      // }
    }
  }
</script>

<style scoped>
  .listOuter{
    background: #ffffff;
    width: 100%;
    height: calc(100vh - 60px);
    padding-top: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
    border: 1px solid #dee1e7;
  }
  .operateBtn{
    width: 100%;
    height: 30px;
    display: flex;
    justify-content: flex-end;
    align-items: center;
  }
  .addNewUser{
    height: 30px;
    line-height: 30px;
    padding: 0px 20px;
    margin-right: 1%;
  }
  .el-pagination {
    display: flex;
    justify-content: flex-end;
    margin-top: 8px;
    height: 24px;
  }
  #datagrid {
    width: 98%;
    margin-top: 10px;
  }
  #datagrid > .el-table {
    height: calc(100vh - 170px);
    overflow: auto;
    font-size: 15px;
  }
  >>> .el-table td, .el-table tr{
    padding: 5px 0px;
    font-weight: normal;
  }
  >>> .el-button--mini, .el-button--small{
    font-size: 15px;
  }
</style>
