<template>
  <div class="dashboard">

    <div class="header">
      <div>
        <h1>Bienvenido</h1>
        <p>Visualiza empleados, beneficios y ubicaciones desde un solo lugar.</p>
      </div>
    </div>

    <!-- Tarjetas -->
    <div class="stats">

      <StatCard
        title="Empleados"
        :value="totalEmployees"
        subtitle="Registrados"
      >
        <template #icon>
          <Users :size="28" />
        </template>
      </StatCard>

      <StatCard
        title="Beneficios"
        :value="totalBenefits"
        subtitle="Activos"
      >
        <template #icon>
          <Gift :size="28" />
        </template>
      </StatCard>

      <StatCard
        title="Con ubicación"
        :value="employeesWithLocation"
        subtitle="Geocodificados"
      >
        <template #icon>
          <MapPinned :size="28" />
        </template>
      </StatCard>

      <StatCard
        title="Ciudades"
        :value="totalCities"
        subtitle="Registradas"
      >
        <template #icon>
          <Building2 :size="28" />
        </template>
      </StatCard>

    </div>

    <!-- Grid -->
    <div class="main-grid">
      <EmployeeTable />

      <div class="right-column">
        <QuickActions />
        <EmployeeMap />
      </div>
    </div>

    <div class="bottom-grid">
      <RecentActivity />
      <BenefitsChart />
    </div>

  </div>
</template>

<script setup lang="ts">
import StatCard from "../components/common/StatCard.vue";
import QuickActions from "../components/dashboard/QuickActions.vue";

import { ref, onMounted } from "vue";
import { getEmployees } from "../services/employeeService";
import { getBenefits } from "../services/benefitService";
import EmployeeTable from "../components/dashboard/EmployeeTable.vue";
import EmployeeMap from "../components/dashboard/EmployeeMap.vue";
import RecentActivity from "../components/dashboard/RecentActivity.vue";
import BenefitsChart from "../components/dashboard/BenefitsChart.vue";


import {
  Users,
  Gift,
  MapPinned,
  Building2,
} from "lucide-vue-next";

const totalEmployees = ref(0);
const totalBenefits = ref(0);
const employeesWithLocation = ref(0);
const totalCities = ref(0);

onMounted(async () => {
  try {
    const employees = await getEmployees();
    const benefits = await getBenefits();

    totalEmployees.value = employees.length;
    totalBenefits.value = benefits.length;

    // Empleados con ubicación
    employeesWithLocation.value = employees.filter(
      (employee) =>
        employee.latitude !== null &&
        employee.longitude !== null
    ).length;

    // Ciudades únicas
    totalCities.value = new Set(
      employees.map((employee) => employee.city)
    ).size;

  } catch (error) {
    console.error(error);
  }
});
</script>

<style scoped>

.dashboard{
  display:flex;
  flex-direction:column;
  gap:32px;
  zoom: 86%;
}

.header{
  display:flex;
  justify-content:space-between;
  align-items:center;
}

.header h1{
  font-size:34px;
  font-weight:700;
  margin:0;
  color:#111827;
}

.header p{
  color:#6B7280;
  margin-top:6px;
}

.stats{
  display:grid;
  grid-template-columns:repeat(4,1fr);
  gap:22px;
}

.main-grid {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 24px;
  align-items: start;
}

.right-column {
  display: grid;
  grid-template-rows: auto 1fr;
  gap: 24px;
}

.bottom-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 24px;
}

@media(max-width:1200px){

  .stats{
    grid-template-columns:repeat(2,1fr);
  }

  .main-grid,
  .bottom-grid {
    grid-template-columns: 1fr;
  }

}

@media(max-width:700px){

  .stats{
    grid-template-columns:1fr;
  }

}

</style>