<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'App',
}
</script>

<style>
  * {
    font-family: Arial, Helvetica, sans-serif;
  }
  .red-border {
    border: 1px solid red;
  }
  select {
    font-size: 16px;
    padding: 2px 5px;
  }
  input, label {
    font-size: 18px;
  }
</style>
