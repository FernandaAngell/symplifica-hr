<template>
  <div class="map-card">

    <div class="card-header">
      <div>
        <h3>Ubicación de empleados</h3>
        <p>Distribución geográfica</p>
      </div>
    </div>

    <EmployeeLocationMap />

  </div>
</template>

<script setup lang="ts">
import EmployeeLocationMap from "../map/EmployeeLocationMap.vue";
</script>

<style scoped>

.map-card{
  background:#fff;
  border:1px solid #E5E7EB;
  border-radius:18px;
  padding:24px;
  box-shadow:0 4px 12px rgba(0,0,0,.04);

  display:flex;
  flex-direction:column;
}

.card-header{
  margin-bottom:20px;
}

.card-header h3{
  margin:0;
  font-size:20px;
  font-weight:600;
  color:#111827;
}

.card-header p{
  margin-top:4px;
  color:#6B7280;
  font-size:14px;
}

.map-placeholder{

  flex:1;

  min-height:320px;

  border:2px dashed #D1D5DB;
  border-radius:14px;

  display:flex;
  flex-direction:column;
  align-items:center;
  justify-content:center;

  color:#6B7280;
}

.map-placeholder svg{
  color:#6366F1;
  margin-bottom:16px;
}

.map-placeholder h4{
  margin:0;
  font-size:18px;
  color:#111827;
}

.map-placeholder span{
  margin-top:8px;
  max-width:240px;
  text-align:center;
  font-size:14px;
}

</style>