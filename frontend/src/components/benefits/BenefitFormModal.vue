<template>
  <div v-if="show" class="overlay">
    <div class="modal">
      <div class="header">
        <h2>
          {{ benefit ? "Editar Beneficio" : "Nuevo Beneficio" }}
        </h2>

        <button class="close" @click="$emit('close')">
          ✕
        </button>
      </div>

      <form @submit.prevent="saveBenefit">

        <div class="field">
          <label>Nombre</label>
          <input
            v-model="form.name"
            type="text"
            required
          />
        </div>

        <div class="field">
          <label>Categoría</label>
          <input
            v-model="form.category"
            type="text"
            required
          />
        </div>

        <div class="field">
          <label>Descripción</label>
          <textarea
            v-model="form.description"
            rows="3"
            required
          />
        </div>

        <div class="field">
          <label>Valor</label>
          <input
            v-model.number="form.value"
            type="number"
            min="0"
            required
          />
        </div>

        <div class="checkbox">
          <input
            id="mandatory"
            v-model="form.mandatory"
            type="checkbox"
          />

          <label for="mandatory">
            Beneficio obligatorio
          </label>
        </div>

        <div class="actions">
          <button
            type="button"
            class="cancel"
            @click="$emit('close')"
          >
            Cancelar
          </button>

          <button
            type="submit"
            class="save"
          >
            {{ benefit ? "Actualizar" : "Guardar" }}
          </button>
        </div>

      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, watch } from "vue";
import type { Benefit } from "../../types/benefit";

const props = defineProps<{
  show: boolean;
  benefit?: Benefit | null;
}>();

const emit = defineEmits<{
  (e: "close"): void;
  (
    e: "save",
    benefit: {
      name: string;
      category: string;
      description: string;
      value: number;
      mandatory: boolean;
    }
  ): void;
}>();

const form = reactive({
  name: "",
  category: "",
  description: "",
  value: 0,
  mandatory: false,
});

watch(
  () => props.benefit,
  (benefit) => {
    if (benefit) {
      form.name = benefit.name;
      form.category = benefit.category;
      form.description = benefit.description;
      form.value = benefit.value;
      form.mandatory = benefit.mandatory;
    } else {
      form.name = "";
      form.category = "";
      form.description = "";
      form.value = 0;
      form.mandatory = false;
    }
  },
  { immediate: true }
);

const saveBenefit = () => {
  emit("save", { ...form });

  form.name = "";
  form.category = "";
  form.description = "";
  form.value = 0;
  form.mandatory = false;
};
</script>

<style scoped>
.overlay{
  position:fixed;
  inset:0;
  background:rgba(0,0,0,.4);
  display:flex;
  justify-content:center;
  align-items:center;
}

.modal{
  width:500px;
  background:white;
  border-radius:18px;
  padding:24px;
}

.header{
  display:flex;
  justify-content:space-between;
  align-items:center;
  margin-bottom:20px;
}

.close{
  border:none;
  background:none;
  cursor:pointer;
  font-size:20px;
}

.field{
  display:flex;
  flex-direction:column;
  gap:6px;
  margin-bottom:18px;
}

.field input,
.field textarea{
  border:1px solid #d1d5db;
  border-radius:10px;
  padding:10px;
}

.checkbox{
  display:flex;
  gap:10px;
  margin-bottom:20px;
}

.actions{
  display:flex;
  justify-content:flex-end;
  gap:12px;
}

.cancel{
  background:#e5e7eb;
}

.save{
  background:#4F46E5;
  color:white;
}

.actions button{
  border:none;
  padding:10px 18px;
  border-radius:10px;
  cursor:pointer;
}
</style>