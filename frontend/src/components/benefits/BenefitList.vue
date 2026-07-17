<template>
  <div class="benefit-list">
    <BenefitCard
      v-for="benefit in benefits"
      :key="benefit.id"
      :benefit="benefit"
      @select="selectBenefit"
    />
  </div>
</template>

<script setup lang="ts">
import BenefitCard from "./BenefitCard.vue";
import type { Benefit } from "../../types/benefit";

defineProps<{
  benefits: Benefit[];
}>();

const emit = defineEmits<{
  (e: "select", benefit: Benefit): void;
}>();

const selectBenefit = (benefit: Benefit) => {
  console.log("List", benefit);
  emit("select", benefit);
};
</script>

<style scoped>
.benefit-list{
  display:grid;
  grid-template-columns:repeat(auto-fill,minmax(320px,1fr));
  gap:24px;
}
</style>