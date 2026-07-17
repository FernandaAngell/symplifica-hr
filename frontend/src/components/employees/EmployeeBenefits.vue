<template>
  <div class="card">
    
    <!-- Encabezado con icono Gift y contador -->
    <div class="header">
      <div class="title">
        <Gift :size="22" />
        <h2>Beneficios asignados</h2>
      </div>

      <span class="count">
        {{ benefits.length }}
      </span>
    </div>

    <div v-if="loading" class="state">
      Cargando beneficios...
    </div>

    <div v-else-if="benefits.length === 0" class="state">
      Este empleado aún no tiene beneficios asignados.
    </div>

    <div v-else class="benefits">
      <div
        v-for="benefit in benefits"
        :key="benefit.id"
        class="benefit"
      >
        <!-- Icono con componente dinámico y stroke-width configurado -->
        <div class="icon">
          <component
            :is="getBenefitIcon(benefit.category)"
            :size="26"
            :stroke-width="2"
          />
        </div>

        <div class="info">
          <h3>{{ benefit.benefitName }}</h3>

          <p>{{ benefit.description }}</p>

          <div class="footer">
            <span class="category">
              {{ benefit.category }}
            </span>

            <span class="amount">
              {{ formatCurrency(benefit.amount) }}
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import {
  HeartPulse,
  UtensilsCrossed,
  Bus,
  Laptop,
  GraduationCap,
  Gift,
} from "lucide-vue-next";

import {
  getEmployeeBenefits,
  type EmployeeBenefit,
} from "../../services/employeeBenefitService";

const props = defineProps<{
  employeeId: string;
}>();

const loading = ref(true);
const benefits = ref<EmployeeBenefit[]>([]);

onMounted(async () => {
  try {
    benefits.value = await getEmployeeBenefits(props.employeeId);
  } finally {
    loading.value = false;
  }
});

function formatCurrency(value: number) {
  return new Intl.NumberFormat("es-CO", {
    style: "currency",
    currency: "COP",
    maximumFractionDigits: 0,
  }).format(value);
}

function getBenefitIcon(category: string) {
  switch (category.toUpperCase()) {
    case "SALUD":
      return HeartPulse;

    case "ALIMENTACIÓN":
    case "ALIMENTACION":
      return UtensilsCrossed;

    case "TRANSPORTE":
      return Bus;

    case "TECNOLOGÍA":
    case "TECNOLOGIA":
      return Laptop;

    case "EDUCACIÓN":
    case "EDUCACION":
      return GraduationCap;

    default:
      return Gift;
  }
}
</script>

<style scoped>
.card{
  margin-top:24px;
  background:white;
  border-radius:24px;
  padding:24px;
  box-shadow:0 10px 30px rgba(0,0,0,.06);
}

.header{
  display:flex;
  justify-content:space-between;
  align-items:center;
  margin-bottom:24px;
}

.title {
  display: flex;
  align-items: center;
  gap: 10px;
  color: #111827;
}

.title h2 {
  margin: 0;
  font-size: 20px;
  font-weight: 600;
}

.count{
  background:#EEF2FF;
  color:#4F46E5;
  padding:6px 12px;
  border-radius:999px;
  font-weight:600;
}

.state{
  padding:40px;
  text-align:center;
  color:#6B7280;
}

.benefits{
  display:flex;
  flex-direction:column;
  gap:18px;
}

.benefit{
  display:flex;
  gap:18px;
  padding:18px;
  border:1px solid #E5E7EB;
  border-radius:16px;
  transition:.2s;
}

.benefit:hover{
  border-color:#6366F1;
  transform:translateY(-2px);
}

/* Estilo de la clase .icon actualizado */
.icon {
  width: 56px;
  height: 56px;
  border-radius: 16px;
  background: #eef2ff;
  color: #4f46e5;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.info{
  flex:1;
}

.info h3{
  margin:0;
  font-size:18px;
}

.info p{
  margin:8px 0 16px;
  color:#6B7280;
}

.footer{
  display:flex;
  justify-content:space-between;
  align-items:center;
}

.category{
  background:#F3F4F6;
  padding:6px 12px;
  border-radius:999px;
  font-size:13px;
}

.amount{
  color:#4F46E5;
  font-weight:700;
}
</style>