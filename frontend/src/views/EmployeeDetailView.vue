<template>
  <div class="detail">

    <button class="back" @click="router.back()">
      ← Volver
    </button>

    <div class="content" v-if="employee">

      <EmployeeCard
        :employee="employee"
      />

      <EmployeeLocationMap
        :latitude="employee.latitude"
        :longitude="employee.longitude"
      />

    </div>

    <!-- Sección de beneficios corregida -->
    <div v-if="employee" class="benefits-section">

      <div class="benefits-header">
        <h2>Beneficios</h2>

        <button
          class="assign-btn"
          @click="showModal = true"
        >
          + Asignar beneficio
        </button>
      </div>

      <EmployeeBenefits
        :key="benefitsKey"
        :employee-id="employee.id"
      />  

    </div>

    <AssignBenefitModal
      v-if="showModal && employee"
      :employee-id="employee.id"
      @close="showModal = false"
      @saved="reloadBenefits"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { getEmployeeById } from "../services/employeeService";
import EmployeeCard from "../components/employees/EmployeeCard.vue";
import EmployeeLocationMap from "../components/employees/EmployeeLocationMap.vue";
import EmployeeBenefits from "../components/employees/EmployeeBenefits.vue";
import AssignBenefitModal from "../components/employees/AssignBenefitModal.vue";

const showModal = ref(false);
const benefitsKey = ref(0);

function reloadBenefits() {
  benefitsKey.value++;
}
const route = useRoute();
const router = useRouter();

const employee = ref<any>(null);

onMounted(async () => {
  employee.value = await getEmployeeById(route.params.id as string);
});
</script>

<style scoped>

.detail{
  display:flex;
  flex-direction:column;
  gap:24px;
}

.back{
  width:max-content;
  border:none;
  background:#EEF2FF;
  color:#4F46E5;
  padding:10px 18px;
  border-radius:10px;
  cursor:pointer;
  font-weight:600;
}

.content{
  display:grid;
  grid-template-columns:380px 1fr;
  gap:24px;
}

.benefits-section{
  display:flex;
  flex-direction:column;
  gap:16px;
}

.benefits-header{
  display:flex;
  justify-content:space-between;
  align-items:center;
}

.assign-btn{
  border:none;
  background:#4F46E5;
  color:white;
  padding:10px 18px;
  border-radius:10px;
  cursor:pointer;
  font-weight:600;
  transition:.2s;
}

.assign-btn:hover{
  background:#4338CA;
}

</style>