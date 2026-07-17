<template>
  <div class="card">
    <div class="card-header">
      <div>
        <h3>Beneficios por categoría</h3>
        <p>Distribución de beneficios registrados</p>
      </div>
    </div>

    <apexchart
      v-if="series.length"
      type="donut"
      height="320"
      :options="chartOptions"
      :series="series"
    />

    <div v-else class="empty">
      No hay beneficios registrados.
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import VueApexCharts from "vue3-apexcharts";
import { getBenefits } from "../../services/benefitService";

defineOptions({
  components: {
    apexchart: VueApexCharts,
  },
});

const series = ref<number[]>([]);

const chartOptions = ref({
  chart: {
    toolbar: {
      show: false,
    },
  },
  labels: [] as string[],
  legend: {
    position: "bottom",
    fontSize: "14px",
  },
  dataLabels: {
    enabled: true,
  },
  stroke: {
    width: 0,
  },
  plotOptions: {
    pie: {
      donut: {
        size: "65%",
      },
    },
  },
  colors: [
    "#3B82F6",
    "#10B981",
    "#F59E0B",
    "#EF4444",
    "#8B5CF6",
    "#06B6D4",
    "#F97316",
  ],
});

onMounted(async () => {
  try {
    const benefits = await getBenefits();

    const categories: Record<string, number> = {};

    benefits.forEach((benefit: any) => {
      categories[benefit.category] =
        (categories[benefit.category] || 0) + 1;
    });

    chartOptions.value = {
      ...chartOptions.value,
      labels: Object.keys(categories),
    };

    series.value = Object.values(categories);
  } catch (error) {
    console.error(error);
  }
});
</script>

<style scoped>
.card {
  background: white;
  border-radius: 18px;
  padding: 24px;
  box-shadow: 0 8px 24px rgba(15, 23, 42, 0.08);
  height: 100%;
}

.card-header {
  margin-bottom: 20px;
}

.card-header h3 {
  margin: 0;
  font-size: 20px;
  color: #111827;
}

.card-header p {
  margin-top: 6px;
  color: #6b7280;
  font-size: 14px;
}

.empty {
  height: 300px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #9ca3af;
}
</style>