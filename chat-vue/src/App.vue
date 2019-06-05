<template>
  <div id="app">
    <router-view v-wechat-title='$route.meta.title'></router-view>
  </div>
</template>
<script>
  export default {
    name: 'app',
    data(){
      return{

      }
    }
  }
</script>

<style>
  html,body,#app{
    height: 100%;
    width: 100%;
    overflow: hidden;
  }
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
  ul{
    list-style: none;
  }
  /* 滚动条样式 */
  ::-webkit-scrollbar{width:4px;border-radius: 2px;}
  ::-webkit-scrollbar-thumb{
    -webkit-box-shadow: inset 0 0 4px rgba(0,0,0,.3);
    border-radius: 2px;
  }
  /* 	IE滚动条颜色 */
  html {scrollbar-face-color:#dadcde;}
  ::-webkit-input-placeholder {color: #a4b2bc; }
  :-moz-placeholder { color: #a4b2bc;}
  ::-moz-placeholder {color: #a4b2bc;}
  :-ms-input-placeholder {color: #a4b2bc; }
</style>
