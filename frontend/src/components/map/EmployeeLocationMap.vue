<template>
  <LMap
    ref="map"
    v-model:zoom="zoom"
    :center="center"
    style="height: 400px; width: 100%; border-radius: 14px;"
  >
    <LTileLayer
      url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      attribution="&copy; OpenStreetMap contributors"
    />

    <LMarker
      v-for="employee in employeesWithLocation"
      :key="employee.id"
      :lat-lng="[employee.latitude, employee.longitude]"
    >
      <LPopup>
  <div class="popup">

    <h4>
      {{ employee.firstName }} {{ employee.lastName }}
    </h4>

    <div class="info">
      <strong>Cargo</strong>
      <span>{{ employee.position }}</span>
    </div>

    <div class="info">
      <strong>Ciudad</strong>
      <span>{{ employee.city }}</span>
    </div>

  </div>
</LPopup>
    </LMarker>
  </LMap>
</template>
<script setup lang="ts">
import { ref, computed, onMounted, nextTick } from "vue";
import { getEmployees } from "../../services/employeeService";

import {
  LMap,
  LTileLayer,
  LMarker,
  LPopup,
} from "@vue-leaflet/vue-leaflet";



import markerIcon2x from "leaflet/dist/images/marker-icon-2x.png";
import markerIcon from "leaflet/dist/images/marker-icon.png";
import markerShadow from "leaflet/dist/images/marker-shadow.png";
import L from "leaflet";
import type { LatLngBoundsLiteral } from "leaflet";

delete (L.Icon.Default.prototype as any)._getIconUrl;


L.Icon.Default.mergeOptions({
  iconRetinaUrl: markerIcon2x,
  iconUrl: markerIcon,
  shadowUrl: markerShadow,
});

const map = ref<any>(null);

const zoom = ref(6);

const center = ref<[number, number]>([
  4.570868,
  -74.297333,
]);

const employees = ref<any[]>([]);

const employeesWithLocation = computed(() =>
  employees.value.filter(
    employee =>
      employee.latitude !== null &&
      employee.longitude !== null
  )
);

onMounted(async () => {
  employees.value = await getEmployees();

  await nextTick();

  if (employeesWithLocation.value.length === 0) return;

  const bounds: LatLngBoundsLiteral =
  employeesWithLocation.value.map(employee => [
    employee.latitude!,
    employee.longitude!,
  ]);

  if (map.value?.leafletObject) {
  map.value.leafletObject.fitBounds(bounds, {
    padding: [40, 40],
  });
}
});
</script>
<style scoped>

.popup{

  min-width:180px;

}

.popup strong{

  display:block;

  margin-bottom:6px;

}

.popup p{

  margin:0;

}

.popup small{

  color:#6B7280;

}

.popup h4{
    margin:0 0 12px;
    color:#111827;
}

.info{
    display:flex;
    flex-direction:column;
    margin-bottom:10px;
}

.info strong{
    font-size:12px;
    color:#6B7280;
}

.info span{
    font-size:14px;
    color:#111827;
}
</style>