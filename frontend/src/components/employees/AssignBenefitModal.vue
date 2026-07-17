<template>
  <div class="overlay" @click.self="$emit('close')">
    <div class="modal">
      <h2>Asignar beneficio</h2>

      <div class="field">
        <label>Beneficio</label>

        <select v-model="benefitId">
          <option disabled value="">
            Selecciona un beneficio
          </option>

          <option
            v-for="benefit in benefits"
            :key="benefit.id"
            :value="benefit.id"
          >
            {{ benefit.name }}
          </option>
        </select>
        <p v-if="benefits.length === 0" class="empty">
        Este empleado ya tiene todos los beneficios disponibles.
</p>
      </div>

      <div class="field">
        <label>Monto</label>

        <input
          v-model.number="amount"
          type="number"
          placeholder="500000"
        />
      </div>

      <div class="buttons">
        <button
          class="cancel"
          @click="$emit('close')"
          :disabled="loading"
        >
          Cancelar
        </button>

        <button
          class="save"
          @click="save"
          :disabled="loading"
        >
          {{ loading ? "Guardando..." : "Guardar" }}
        </button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { getBenefits } from "../../services/benefitService";
import {
  assignBenefit,
  getEmployeeBenefits,
} from "../../services/employeeBenefitService";

const props = defineProps<{
  employeeId: string;
}>();

const emit = defineEmits(["close", "saved"]);

const benefits = ref<any[]>([]);
const benefitId = ref("");
const amount = ref<number | null>(null);
const loading = ref(false);

onMounted(async () => {
  try {
    const allBenefits = await getBenefits();
    const assignedBenefits = await getEmployeeBenefits(props.employeeId);

    benefits.value = allBenefits.filter(
      (benefit: any) =>
        !assignedBenefits.some(
          (assigned) => assigned.benefitId === benefit.id
        )
    );
  } catch (error) {
    console.error("Error cargando beneficios:", error);
    alert("No fue posible cargar los beneficios.");
  }
});

const save = async () => {
  if (!benefitId.value || amount.value == null) {
    alert("Completa todos los campos.");
    return;
  }

  try {
    loading.value = true;

    await assignBenefit({
      employeeId: props.employeeId,
      benefitId: benefitId.value,
      amount: amount.value,
    });

    emit("saved");
    emit("close");
  } catch (error) {
    console.error(error);
    alert("No fue posible asignar el beneficio.");
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
.overlay {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.45);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal {
  width: 420px;
  background: white;
  border-radius: 18px;
  padding: 28px;
}

h2 {
  margin-top: 0;
}

.field {
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-bottom: 18px;
}

select,
input {
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 10px;
  font-size: 14px;
}

.buttons {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
}

.cancel {
  background: #e5e7eb;
  border: none;
  padding: 10px 18px;
  border-radius: 10px;
  cursor: pointer;
}

.save {
  background: #4f46e5;
  color: white;
  border: none;
  padding: 10px 18px;
  border-radius: 10px;
  cursor: pointer;
}

button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.empty {
  margin-top: 10px;
  color: #6b7280;
  font-size: 14px;
}
</style>