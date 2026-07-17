<template>
  <div class="layout">
    <Sidebar />

    <div class="content">
      <Topbar />

      <main class="page">
        <RouterView />
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import Sidebar from "../components/common/Sidebar.vue";
import Topbar from "../components/common/Topbar.vue";
</script>

<style scoped>
.layout {
  display: flex;
  min-height: 100vh;
  background:#F3F4F6;;
}

.content {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.page{
padding:35px;
overflow:auto;
}
</style>