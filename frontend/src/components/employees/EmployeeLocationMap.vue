<template>
  <div class="card">
    <h2>Ubicación</h2>

    <div v-if="!latitude || !longitude" class="empty">
      Este empleado no tiene ubicación registrada.
    </div>

    <div
      v-else
      ref="mapContainer"
      class="map"
    ></div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, nextTick } from "vue";
import L from "leaflet";
import "leaflet/dist/leaflet.css";

const props = defineProps<{
  latitude: number | null;
  longitude: number | null;
}>();

const mapContainer = ref<HTMLDivElement | null>(null);

onMounted(async () => {
  if (!props.latitude || !props.longitude) return;

  await nextTick();

  if (!mapContainer.value) return;

  const map = L.map(mapContainer.value).setView(
    [props.latitude, props.longitude],
    13
  );

  L.tileLayer(
    "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
    {
      attribution: "&copy; OpenStreetMap contributors",
    }
  ).addTo(map);

  L.marker([props.latitude, props.longitude]).addTo(map);

  setTimeout(() => {
    map.invalidateSize();
  }, 100);
});
</script>

<style scoped>
.card {
  background: white;
  border-radius: 24px;
  padding: 24px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.06);
}

.empty {
  height: 420px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #6b7280;
}

.map {
  width: 100%;
  height: 420px;
  margin-top: 20px;
  border-radius: 16px;
}
</style>