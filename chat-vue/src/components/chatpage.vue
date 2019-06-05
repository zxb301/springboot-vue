<template>
  <div class="mainContent">
    <!--导航栏-->
    <div class="navbar"></div>
    <div class="bottomContent">
      <!--左侧通讯录和聊天窗口-->
      <div class="leftContent">
        <div class="addressList">
          <ul class="contactslist">
            <li v-for="(member, index) in members" :key="member" @click="showCurrentChat(index)">{{member}}</li>
          </ul>
        </div>
        <div class="chatwindowBox">
          <div class="chatwindow">
            <!--一对一对话------A对象-->
            <div class="mess-containersend">
              <div class="mess-sendlogo">弗兰克</div>
              <div class="sendBox">
                <div class="mess-sendcontent left">
                  本周六格致花艺沙龙课精品花礼盒 主题 “繁盛的末春”
                  特级花艺师为你讲解每一朵花的故事。教授你花艺独特的处理技巧,格致花艺会员单价198元，非会员单价238元
                  名额限制：12 人 日期：2019年4月20日 时间：17:00-18:00 作品可带走 本周五12点截止报名
                </div>
              </div>
            </div>
            <!--B对象-->
            <div class="mess-containeraccept">
              <div class="acceptBox">
                <div class="mess-acceptcontent right">
                  收到
                </div>
              </div>
              <div class="mess-acceptlogo">玛利亚</div>
            </div>
          </div>
          <!--编辑信息框-->
          <div class="mess-edit">
            <quill-editor
              v-model="content"
              ref="myQuillEditor"
              :options="editorOption"
              @blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
              @change="onEditorChange($event)">
            </quill-editor>
            <el-button type="primary" class="sendBtn">发送</el-button>
            <el-button type="primary" class="uploadFile">上传文件</el-button>
          </div>
        </div>
      </div>
      <!--右侧历史记录-->
      <div class="rightContent">
        <div class="rightHeader">
          <el-input v-model="searchVal" placeholder="请输入内容"></el-input>&nbsp;搜索
          <span @click="selectChathistory" class="chatHistory">查看聊天记录</span>
        </div>
        <div class="historyinfo">
          <ul class="messageList">
            <li>你好，我是弗兰克</li>
            <li>你好</li>
            <li>你吃饭了吗</li>
            <li>还没有</li>
            <li>那咱们晚上约个饭？</li>
            <li>可以啊</li>
            <li>你离哪块比较近</li>
            <li>人民南路</li>
            <li>那咱们就约在海底捞</li>
          </ul>
        </div>
        <div class="rightBottom">
          <!--<el-pagination prev-text="上一页" next-text="下一页"  background :page-size="dataInfo.size"-->
                         <!--layout="total, sizes, prev, pager, next, jumper" :total="dataInfo.total"-->
                         <!--:current-page="dataInfo.current"-->
                         <!--@size-change="sizeChange" @current-change="currentChange"-->
          <!--&gt;</el-pagination>-->
          <el-pagination prev-text="上一页" next-text="下一页"  background :page-size="5"
                         layout="total, sizes, prev, pager, next, jumper" :total="10"
                         :current-page="1"
                         @size-change="sizeChange" @current-change="currentChange"
          ></el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import {quillEditor} from 'vue-quill-editor'
  export default {
    data() {
      return {
        members:["李易峰","张彬彬","张艺兴","易阳千玺","朱亚文","鹿晗","刘德华","周润发"],
        content:null,
        searchVal:"",
        editorOption:{
          placeholder:"请输入文本......",
          modules:{
            toolbar:[
              ['bold', 'italic', 'underline', 'strike','image','clean'], // toggled buttons
              [{ 'align': [] }],
              [{ 'color': [] }, { 'background': [] }],
              [{ 'script': 'sub'}, { 'script': 'super' }],
              [{ 'size': ['small', false, 'large', 'huge'] }],
              [{ 'font': [] }]
            ]
          }
        }
      }
    },
    methods:{
      showCurrentChat (index) {
        alert(index)
      },
      onEditorBlur(){//失去焦点事件

      },
      onEditorFocus(){//获得焦点事件

      },
      onEditorChange(){//内容改变事件

      },
      selectChathistory(){

      }
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
    }
  }
</script>
<style scoped>
  .mainContent{
    width: 100%;
    height:100%;
    border:1px solid red;
  }
  .navbar{
    width: 100%;
    height: 50px;
    border: 1px solid red;
  }
  .bottomContent{
    width: 100%;
    margin-top: 10px;
    height: calc(100% - 65px);
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    border: 1px solid blue;
  }
  .leftContent{
    width: 75%;
    height: 100%;
    border: 1px solid green;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .addressList{
    width: 24%;
    height: 100%;
    border: 1px solid pink;
  }
  .contactslist{
    width: 100%;
    height: 100%;
    padding-left: 0px;
    margin-top: 0px;
  }
  .contactslist li{
    height: 30px;
    line-height: 30px;
    cursor: pointer;
    border-bottom: 1px solid #e8e8e8;
  }
  .chatwindowBox{
    width: 75%;
    height: 100%;
  }
  .chatwindow{
    width: 100%;
    height: 68%;
    overflow: hidden;
    overflow-y: auto;
  }
  .mess-edit{
    width: 100%;
    height: 30%;
    border: 1px solid green;
  }
  >>> .ql-editor{
    border: 1px solid #e8e8e8;
    height: 100%;
    overflow-y: auto;
  }
  >>> .quill-editor, >>> .ql-container.ql-snow{
    height: 75%;
  }
  >>> .ql-toolbar{
    height: 25%;
    text-align: left;
  }
  .sendBtn, .uploadFile{
    padding: 8px 18px;
    font-size: 14px;
    margin-top: 5px;
    float: right;
    margin-right: 5px;
  }
  .mess-containersend, .mess-containeraccept{
    width: 97%;
    height: auto;
    border: 1px solid green;
    margin-top: 20px;
    margin-left: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
  }
  .mess-containeraccept{
    justify-content: flex-end;
  }
  .mess-sendlogo, .mess-acceptlogo{
    width: 50px;
    height: 50px;
    border-radius: 50px;
    background: pink;
    text-align: center;
    line-height: 50px;
    font-size: 13px;
  }
  .mess-acceptlogo{
    color: #ffffff;
    background: rebeccapurple;
  }
  .sendBox{
    width: 50%;
    margin-left: 15px;
    display: flex;
    align-items: center;
  }
  .acceptBox{
    width: 50%;
    margin-right: 15px;
    display: flex;
    align-items: center;
    justify-content: flex-end;
  }
  .mess-sendcontent, .mess-acceptcontent{
    max-width: 100%;
    height: auto;
    padding: 8px 10px;
    border-radius: 4px;
    position: relative;
    background: deepskyblue;
    text-align: left;
    font-size: 15px;
    letter-spacing: 0.1rem;
  }
  .mess-acceptcontent{
    background: green;
    color: #ffffff;
  }
  .mess-sendcontent:after, .mess-acceptcontent:after{
    content: '';
    position: absolute;
    width: 0;
    height: 0;
    border: 6px solid;
  }
  .left:after {
    border-right-color: deepskyblue;
    border-left-color: transparent;
    border-top-color: transparent;
    border-bottom-color: transparent;
    border-bottom-right-radius: 4px;
    border-top-right-radius: 4px;
    top: 20px;
    right: 99.5%;
  }
  .right:after {
    border-left-color: green;
    border-right-color: transparent;
    border-top-color: transparent;
    border-bottom-color: transparent;
    border-bottom-left-radius: 4px;
    border-top-left-radius: 4px;
    left: 97%;
    top: 12px;
  }
  .rightContent{
    width: 24%;
    height: 100%;
    border: 1px solid pink;
  }
  .rightHeader{
    width: 100%;
    height: 5%;
    border: 1px solid green;
    font-size: 14px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    cursor: pointer;
  }
  .historyinfo{
    width: 100%;
    height: 87%;
    border: 1px solid green;
  }
  .rightBottom {
    height: 6%;
    width: 100%;
    border: 1px solid black;
  }
  >>> .el-input{
    width: 40%;
  }
  >>> .el-input__inner{
    width: 100%;
    height: 30px;
    line-height: 30px;
  }
  .chatHistory{
    margin-left: 10px;
  }
  .messageList{
    width: 90%;
    height: 100%;
    font-size: 15px;
    border: 1px solid green;
    margin: 0px;
  }
  .messageList li{
    width: 100%;
    text-align: left;
    padding: 5px 0px;
  }
  >>> .ql-editor img{
    width: 100px;
    height: 100px;
  }
  >>> .ql-toolbar.ql-snow .ql-formats {
    margin-right: 5px;
  }
</style>
