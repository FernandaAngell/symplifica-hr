<template>
  <div class="stats-grid">
    <div class="stat-card">
      <div class="icon purple">
        <Gift :size="24" />
      </div>

      <div>
        <p class="label">Beneficios</p>
        <h2>{{ totalBenefits }}</h2>
      </div>
    </div>

    <div class="stat-card">
      <div class="icon green">
        <Users :size="24" />
      </div>

      <div>
        <p class="label">Empleados cubiertos</p>
        <h2>{{ totalEmployees }}</h2>
      </div>
    </div>

    <div class="stat-card">
      <div class="icon blue">
        <DollarSign :size="24" />
      </div>

      <div>
        <p class="label">Costo mensual</p>
        <h2>${{ totalCost.toLocaleString() }}</h2>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {
  Gift,
  Users,
  DollarSign,
} from "lucide-vue-next";

defineProps<{
  totalBenefits: number;
  totalEmployees: number;
  totalCost: number;
}>();
</script>

<style scoped>
.stats-grid{
  display:grid;
  grid-template-columns:repeat(3,1fr);
  gap:20px;
  margin-bottom:30px;
}

.stat-card{
  background:white;
  border:1px solid #e5e7eb;
  border-radius:18px;
  padding:22px;
  display:flex;
  align-items:center;
  gap:18px;
}

.icon{
  width:54px;
  height:54px;
  border-radius:50%;
  display:flex;
  justify-content:center;
  align-items:center;
}

.purple{
  background:#EEF2FF;
  color:#4F46E5;
}

.green{
  background:#ECFDF5;
  color:#16A34A;
}

.blue{
  background:#EFF6FF;
  color:#2563EB;
}

.label{
  margin:0;
  color:#6B7280;
  font-size:14px;
}

h2{
  margin-top:6px;
  font-size:28px;
}

@media (max-width:900px){

.stats-grid{
  grid-template-columns:1fr;
}

}
</style>