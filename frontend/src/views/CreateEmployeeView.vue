<template>
  <div class="container">
    <div class="card">
      <h1>{{ isEditing ? "Editar Empleado" : "Nuevo Empleado" }}</h1>

      <form @submit.prevent="save">

        <div class="grid">
          <input v-model="employee.firstName" placeholder="Nombre" required />
          <input v-model="employee.lastName" placeholder="Apellido" required />

          <input v-model="employee.email" type="email" placeholder="Correo" required />
          <input v-model="employee.phone" placeholder="Teléfono" required />

          <input v-model="employee.city" placeholder="Ciudad" required />
          <input v-model="employee.position" placeholder="Cargo" required />
        </div>

        <div class="buttons">
          <button type="button" class="secondary" @click="router.push('/employees')">
            Cancelar
          </button>

          <button type="submit" class="primary">
            {{ isEditing ? "Actualizar empleado" : "Guardar empleado" }}
          </button>
        </div>

      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, onMounted, computed } from "vue";
import { useRouter, useRoute } from "vue-router";
import {
  createEmployee,
  getEmployeeById,
  updateEmployee,
} from "../services/employeeService";

const router = useRouter();
const route = useRoute();

const employeeId = route.params.id as string | undefined;

const isEditing = computed(() => !!employeeId);

const employee = reactive({
  firstName: "",
  lastName: "",
  email: "",
  phone: "",
  city: "",
  position: "",
});

onMounted(async () => {
  if (!employeeId) return;

  try {
    const data = await getEmployeeById(employeeId);

    employee.firstName = data.firstName;
    employee.lastName = data.lastName;
    employee.email = data.email;
    employee.phone = data.phone;
    employee.city = data.city;
    employee.position = data.position;
  } catch (error) {
    console.error(error);
    alert("No se pudo cargar el empleado.");
    router.push("/employees");
  }
});

const save = async () => {
  try {
    if (isEditing.value && employeeId) {
      await updateEmployee(employeeId, employee);
    } else {
      await createEmployee(employee);
    }

    router.push("/employees");
  } catch (error) {
    console.error(error);

    alert(
      isEditing.value
        ? "No se pudo actualizar el empleado."
        : "No se pudo crear el empleado."
    );
  }
};
</script>

<style scoped>
.container{
  display:flex;
  justify-content:center;
  padding:40px;
}

.card{
  width:100%;
  max-width:700px;
  background:#fff;
  border-radius:20px;
  padding:32px;
  box-shadow:0 10px 30px rgba(0,0,0,.08);
}

h1{
  margin-bottom:24px;
}

.grid{
  display:grid;
  grid-template-columns:1fr 1fr;
  gap:18px;
}

input{
  padding:14px;
  border:1px solid #ddd;
  border-radius:12px;
  font-size:15px;
}

input:focus{
  outline:none;
  border-color:#4F46E5;
}

.buttons{
  display:flex;
  justify-content:flex-end;
  gap:12px;
  margin-top:28px;
}

button{
  padding:12px 20px;
  border:none;
  border-radius:12px;
  cursor:pointer;
  font-weight:600;
}

.primary{
  background:#4F46E5;
  color:white;
}

.primary:hover{
  background:#4338CA;
}

.secondary{
  background:#E5E7EB;
}
</style>