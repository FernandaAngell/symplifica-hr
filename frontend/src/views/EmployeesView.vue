<template>
  <div class="employees-page">
    <EmployeeList />
  </div>
</template>

<script setup lang="ts">
import EmployeeList from "../components/employees/EmployeeList.vue";
</script>

<style scoped>
.employees-page {
  padding: 24px;
}
</style>