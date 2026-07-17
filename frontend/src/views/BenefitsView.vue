<template>
  <div class="benefits-page">

    <div class="page-header">
      <div>
        <h1>Beneficios</h1>
        <p>Gestiona los beneficios ofrecidos a los empleados.</p>
      </div>

      <button
        class="new-btn"
        @click="openNewModal"
      >
        Nuevo Beneficio
      </button>
    </div>

    <BenefitStats
  :totalBenefits="benefits.length"
  :totalEmployees="124"
  :totalCost="totalCost"
/>

    <div class="content">

      <BenefitList
        :benefits="benefits"
        @select="selectedBenefit = $event"
        @edit="handleEditFromCard"
        @delete="deleteBenefit"
      />

      <BenefitDetail
        :benefit="selectedBenefit"
        @edit="editBenefit"
        @delete="deleteBenefit"
      />

    </div>

    <BenefitFormModal
      :show="showModal"
      :benefit="benefitToEdit"
      @close="closeModal"
      @save="saveBenefit"
    />

  </div>
</template>

<script setup lang="ts">

import BenefitList from "../components/benefits/BenefitList.vue";
import BenefitDetail from "../components/benefits/BenefitDetail.vue";
import BenefitStats from "../components/benefits/BenefitStats.vue";
import { onMounted, ref, computed } from "vue";
import BenefitFormModal from "../components/benefits/BenefitFormModal.vue";
import {
  getBenefits,
  createBenefit,
  updateBenefit,
  deleteBenefit as deleteBenefitService,
} from "../services/benefitService";

import type { Benefit } from "../types/benefit";

const benefits = ref<Benefit[]>([]);
const selectedBenefit = ref<Benefit | null>(null);
const showModal = ref(false);
const benefitToEdit = ref<Benefit | null>(null);

  const totalCost = computed(() =>
  benefits.value.reduce(
    (total, benefit) => total + Number(benefit.value),
    0
  )
);

const loadBenefits = async () => {
  try {
    const data = await getBenefits();

    console.log("Beneficios:", data);

    benefits.value = data;

    if (data.length > 0) {
      selectedBenefit.value = data[0];
    }
  } catch (error) {
    console.error(error);
  }
};

onMounted(loadBenefits);

const openNewModal = () => {
  benefitToEdit.value = null;
  showModal.value = true;
};

const closeModal = () => {
  showModal.value = false;
  benefitToEdit.value = null;
};

const saveBenefit = async (benefit: {
  name: string;
  category: string;
  description: string;
  value: number;
  mandatory: boolean;
}) => {
  try {
    if (benefitToEdit.value) {
      await updateBenefit(benefitToEdit.value.id, benefit);
    } else {
      await createBenefit(benefit);
    }

    closeModal();

    await loadBenefits();
  } catch (error) {
    console.error(error);

    alert(
      benefitToEdit.value
        ? "No se pudo actualizar el beneficio."
        : "No se pudo crear el beneficio."
    );
  }
};

const editBenefit = (id: string) => {
  const found = benefits.value.find(benefit => benefit.id === id);

  if (!found) {
    console.error("Beneficio no encontrado:", id);
    return;
  }

  benefitToEdit.value = found;
  showModal.value = true;
};

// BenefitCard/BenefitList emiten el objeto Benefit completo,
// aquí lo normalizamos para reusar editBenefit(id: string)
const handleEditFromCard = (benefit: Benefit) => {
  editBenefit(benefit.id);
};

const deleteBenefit = async (id: string) => {
  try {
    await deleteBenefitService(id);

    benefits.value = benefits.value.filter(
      benefit => benefit.id !== id
    );

    if (selectedBenefit.value?.id === id) {
      selectedBenefit.value =
        benefits.value.length > 0
          ? benefits.value[0]
          : null;
    }
  } catch (error) {
    console.error(error);
  }
};
</script>

<style scoped>
.benefits-page{
  display:flex;
  flex-direction:column;
  gap:30px;
}

.page-header{
  display:flex;
  justify-content:space-between;
  align-items:center;
}

.page-header h1{
  margin:0;
  font-size:34px;
}

.page-header p{
  margin-top:8px;
  color:#6B7280;
}

.new-btn{
  background:#4F46E5;
  color:white;
  border:none;
  padding:12px 20px;
  border-radius:12px;
  cursor:pointer;
  font-weight:600;
}

.content{
  display:grid;
  grid-template-columns:2fr 1fr;
  gap:24px;
  align-items:start;
}

@media(max-width:1100px){

.content{
  grid-template-columns:1fr;
}

}
</style>