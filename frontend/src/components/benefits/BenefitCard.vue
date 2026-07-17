<template>
  <div
    class="benefit-card"
    @click="selectBenefit"
  >
    <div class="header">
      <div class="icon">
        <HeartPulse :size="24" />
      </div>

      <div class="title">
        <h3>{{ benefit.name }}</h3>
        <p>{{ benefit.mandatory ? "Obligatorio" : "Opcional" }}</p>
      </div>
    </div>

    <div class="body">
      <span class="badge">
        {{ benefit.category }}
      </span>

      <p class="description">
        {{ benefit.description }}
      </p>

      <div class="cost">
        <span>Valor</span>
        <strong>${{ benefit.value.toLocaleString() }}</strong>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { Benefit } from "../../types/benefit";
import { HeartPulse } from "lucide-vue-next";

const props = defineProps<{
  benefit: Benefit;
}>();

const emit = defineEmits<{
  (e: "select", benefit: Benefit): void;
}>();

const selectBenefit = () => {
  console.log("Card", props.benefit);
  emit("select", props.benefit);
};
</script>

<style scoped>
.benefit-card {
  background: #fff;
  border: 1px solid #e5e7eb;
  border-radius: 18px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 18px;
  transition: all .2s ease;
}

.benefit-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 10px 24px rgba(0, 0, 0, .08);
}

.header {
  display: flex;
  align-items: center;
  gap: 16px;
}

.icon {
  width: 52px;
  height: 52px;
  border-radius: 50%;
  background: #EEF2FF;
  color: #4F46E5;
  display: flex;
  align-items: center;
  justify-content: center;
}

.title h3 {
  margin: 0;
  font-size: 18px;
  font-weight: 600;
}

.title p {
  margin-top: 4px;
  color: #6b7280;
  font-size: 14px;
}

.body {
  display: flex;
  flex-direction: column;
  gap: 14px;
}

.badge {
  width: fit-content;
  padding: 5px 12px;
  border-radius: 999px;
  background: #EEF2FF;
  color: #4F46E5;
  font-size: 12px;
  font-weight: 600;
}

.description {
  color: #6b7280;
  font-size: 14px;
  line-height: 1.5;
}

.cost {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.cost span {
  color: #6b7280;
}

.cost strong {
  font-size: 16px;
}
</style>