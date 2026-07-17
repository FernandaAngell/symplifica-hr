<template>
  <aside class="detail-card" v-if="benefit">
    <div class="header">
      <div class="icon">
        <HeartPulse :size="24" />
      </div>

      <div>
  <h2>{{ benefit.name }}</h2>
  <p>{{ benefit.mandatory ? "Beneficio obligatorio" : "Beneficio opcional" }}</p>
</div>
    </div>

    <div class="section">
      <span>Categoría</span>
      <strong>{{ benefit.category }}</strong>
    </div>

    <div class="section">
  <span>Valor</span>
  <strong>${{ benefit.value.toLocaleString() }}</strong>
</div>

    <div class="section">
      <span>Descripción</span>
      <p>{{ benefit.description }}</p>
    </div>

    <div class="actions">
      <button
        class="edit"
        @click.stop="editBenefit(benefit.id)"
      >
        <Pencil :size="16" />
        Editar
      </button>

      <button
        class="delete"
        @click.stop="deleteBenefit(benefit.id)"
      >
        <Trash2 :size="16" />
        Eliminar
      </button>
    </div>
  </aside>

  <aside v-else class="empty">
    <HeartPulse :size="40" />
    <h3>Selecciona un beneficio</h3>
    <p>Haz clic en una tarjeta para ver su información.</p>
  </aside>
</template>

<script setup lang="ts">
import type { Benefit } from "../../types/benefit";
import {
  HeartPulse,
  Pencil,
  Trash2,
} from "lucide-vue-next";

defineProps<{
  benefit: Benefit | null;
}>();

const emit = defineEmits<{
  (e: "edit", id: string): void;
  (e: "delete", id: string): void;
}>();

const editBenefit = (id: string) => {
  emit("edit", id);
};

const deleteBenefit = (id: string) => {
  emit("delete", id);
};
</script>

<style scoped>
.detail-card{
  background:white;
  border:1px solid #e5e7eb;
  border-radius:18px;
  padding:24px;
  display:flex;
  flex-direction:column;
  gap:24px;
}

.header{
  display:flex;
  align-items:center;
  gap:16px;
}

.icon{
  width:56px;
  height:56px;
  border-radius:50%;
  background:#EEF2FF;
  color:#4F46E5;
  display:flex;
  justify-content:center;
  align-items:center;
}

.header h2{
  margin:0;
}

.header p{
  margin-top:4px;
  color:#6B7280;
}

.section{
  display:flex;
  flex-direction:column;
  gap:6px;
}

.section span{
  color:#6B7280;
  font-size:14px;
}

.section strong{
  font-size:16px;
}

.section p{
  margin:0;
  color:#374151;
  line-height:1.6;
}

.actions{
  display:flex;
  gap:12px;
  margin-top:auto;
}

.actions button{
  flex:1;
  display:flex;
  justify-content:center;
  align-items:center;
  gap:8px;
  padding:12px;
  border:none;
  border-radius:12px;
  cursor:pointer;
  font-weight:600;
}

.edit{
  background:#4F46E5;
  color:white;
}

.delete{
  background:#EF4444;
  color:white;
}

.empty{
  border:2px dashed #d1d5db;
  border-radius:18px;
  min-height:420px;
  display:flex;
  flex-direction:column;
  justify-content:center;
  align-items:center;
  gap:12px;
  color:#6B7280;
}
</style>