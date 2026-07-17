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
import { ref, onMounted } from "vue";
import { getEmployees } from "../../services/employeeService";
import EmployeeLocationMap from "../map/EmployeeLocationMap.vue";

import {
  LMap,
  LTileLayer,
  LMarker,
  LPopup,
} from "@vue-leaflet/vue-leaflet";

import L from "leaflet";

import "leaflet/dist/leaflet.css";

import markerIcon2x from "leaflet/dist/images/marker-icon-2x.png";
import markerIcon from "leaflet/dist/images/marker-icon.png";
import markerShadow from "leaflet/dist/images/marker-shadow.png";

const zoom = ref(6);

const center = ref<[number, number]>([
  4.570868,
  -74.297333,
]);

const employees = ref<any[]>([]);

onMounted(async () => {
  employees.value = await getEmployees();
});

delete (L.Icon.Default.prototype as any)._getIconUrl;

L.Icon.Default.mergeOptions({
  iconRetinaUrl: markerIcon2x,
  iconUrl: markerIcon,
  shadowUrl: markerShadow,
});
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