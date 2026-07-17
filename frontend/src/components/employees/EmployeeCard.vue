<template>
  <div class="card">
    <div class="top">
      <div class="avatar">
        {{ initials }}
      </div>

      <div>
        <h2>{{ employee.firstName }} {{ employee.lastName }}</h2>
        <p class="position">{{ employee.position }}</p>
      </div>
    </div>

    <div class="info">

      <div class="item">
        <Mail :size="18" class="icon" />
        <div>
          <span>Email</span>
          <strong>{{ employee.email }}</strong>
        </div>
      </div>

      <div class="item">
        <Phone :size="18" class="icon" />
        <div>
          <span>Teléfono</span>
          <strong>{{ employee.phone }}</strong>
        </div>
      </div>

      <div class="item">
        <Building2 :size="18" class="icon" />
        <div>
          <span>Ciudad</span>
          <strong>{{ employee.city }}</strong>
        </div>
      </div>

      <div class="item">
        <MapPin :size="18" class="icon" />
        <div>
          <span>Dirección</span>
          <strong>
            {{ employee.displayName || "Dirección no disponible" }}
          </strong>
        </div>
      </div>

    </div>
    <div class="actions">
  <button
  class="edit-btn"
  @click="editEmployee"
>
  Editar
</button>

  <button
  class="delete-btn"
  @click="removeEmployee"
>
  Eliminar
</button>
</div>
  </div>
</template>

<script setup lang="ts">
import { computed } from "vue";
import {
  Mail,
  Phone,
  Building2,
  MapPin,
} from "lucide-vue-next";

import type { Employee } from "../../types/employee";
import { useRouter } from "vue-router";
import { deleteEmployee } from "../../services/employeeService";


const removeEmployee = async () => {
  const confirmed = confirm(
    `¿Eliminar a ${props.employee.firstName} ${props.employee.lastName}?`
  );

  if (!confirmed) return;

  try {
    await deleteEmployee(props.employee.id);

    alert("Empleado eliminado correctamente.");

    window.location.href = "/employees";
  } catch (error) {
    console.error(error);
    alert("No se pudo eliminar el empleado.");
  }
};

const router = useRouter();

const editEmployee = () => {
  router.push(`/employees/${props.employee.id}/edit`);
};

const props = defineProps<{
  employee: Employee;
}>();

const initials = computed(() =>
  `${props.employee.firstName.charAt(0)}${props.employee.lastName.charAt(0)}`.toUpperCase()
);
</script>

<style scoped>
.card{
  background:white;
  border-radius:24px;
  padding:24px;
  box-shadow:0 10px 30px rgba(0,0,0,.06);
}

.top{
  display:flex;
  align-items:center;
  gap:20px;
  margin-bottom:32px;
}

.avatar{
  width:72px;
  height:72px;
  border-radius:50%;
  background:linear-gradient(135deg,#6366F1,#4338CA);
  color:white;
  display:flex;
  justify-content:center;
  align-items:center;
  font-size:34px;
  font-weight:700;
}

h2{
  margin:0;
  font-size:30px;
  color:#111827;
}

.position{
  margin-top:6px;
  color:#6B7280;
}

.info{
  display:flex;
  flex-direction:column;
  gap:24px;
}

.item{
  display:flex;
  gap:14px;
  align-items:flex-start;
}

.icon{
  color:#4F46E5;
  margin-top:2px;
}

.item span{
  display:block;
  color:#9CA3AF;
  font-size:14px;
  margin-bottom:4px;
}

.item strong{
  color:#111827;
  font-size:16px;
  font-weight:600;
}

.actions{
  display:flex;
  gap:12px;
  margin-top:32px;
}

.edit-btn,
.delete-btn{
  flex:1;
  padding:12px;
  border:none;
  border-radius:12px;
  cursor:pointer;
  font-size:14px;
  font-weight:600;
  transition:.2s;
}

.edit-btn{
  background:#EEF2FF;
  color:#4F46E5;
}

.edit-btn:hover{
  background:#4F46E5;
  color:white;
}

.delete-btn{
  background:#FEE2E2;
  color:#DC2626;
}

.delete-btn:hover{
  background:#DC2626;
  color:white;
}
</style>