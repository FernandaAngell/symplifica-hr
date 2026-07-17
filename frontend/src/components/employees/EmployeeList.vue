<template>
  <div class="employee-table-container">
    
    <!-- Encabezado con buscador y botón de nuevo empleado (con redirección al formulario) -->
    <div class="header">
      <div>
        <h2>Empleados</h2>
        <p>Administra los empleados registrados</p>
      </div>

      <div class="actions">
        <div class="search-box">
          <Search :size="18" />
          <input
            v-model="search"
            type="text"
            placeholder="Buscar empleado..."
          />
        </div>

        <!-- Botón "Nuevo" actualizado con la acción del router -->
        <button class="add-btn" @click="router.push('/employees/new')">
          <Plus :size="18" />
          Nuevo
        </button>
      </div>
    </div>

    <!-- Tabla de Empleados -->
    <div class="content">

  <div class="table-wrapper">

    <table class="employee-table">

      <thead>
        <tr>
          <th>Empleado</th>
          <th>Cargo</th>
          <th>Ciudad</th>
          <th>Email</th>
          <th>Estado</th>
        </tr>
      </thead>

      <tbody>

        <tr
  v-for="employee in filteredEmployees"
  :key="employee.id"
  class="employee-row"
  :class="{ selected: selectedEmployee?.id === employee.id }"
  @click="selectedEmployee = employee"
>
          <td>
            <div class="employee">
              <div class="avatar">
                {{ getInitials(employee.firstName, employee.lastName) }}
              </div>

              <div>
                <strong>{{ employee.firstName }} {{ employee.lastName }}</strong>

                <small>
                  {{ employee.displayName || "Dirección no disponible" }}
                </small>
              </div>
            </div>
          </td>

          <td>{{ employee.position }}</td>
          <td>{{ employee.city }}</td>
          <td>{{ employee.email }}</td>

          <td>
            <span class="status active">
              Activo
            </span>
          </td>


        </tr>

      </tbody>

    </table>

  </div>

  <div class="detail-panel">

    <EmployeeCard
      v-if="selectedEmployee"
      :employee="selectedEmployee"
    />

  </div>

</div>

  </div>
</template>

<script setup lang="ts">
import EmployeeCard from "../employees/EmployeeCard.vue";
import { ref, onMounted, computed } from "vue";
import { getEmployees } from "../../services/employeeService"; // Ajusta la ruta a tu estructura
import {
  Eye,
  Search,
  Plus,
} from "lucide-vue-next";
import { useRouter } from "vue-router";

// Definición de interfaz para Tipado de TypeScript
interface Employee {
  id: string;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  city: string;
  position: string;
  latitude: number | null;
  longitude: number | null;
  displayName: string | null;
}

const employees = ref<Employee[]>([]);
const search = ref("");
const selectedEmployee = ref<Employee | null>(null);

// Inicialización del router
const router = useRouter();

// Helper para obtener las iniciales del nombre
const getInitials = (firstName: string, lastName: string) => {
  return `${firstName.charAt(0)}${lastName.charAt(0)}`.toUpperCase();
};

// Carga los empleados al montar el componente
onMounted(async () => {
  try {
    employees.value = await getEmployees();

    if (employees.value.length > 0) {
      selectedEmployee.value = employees.value[0];
    }
  } catch (error) {
    console.error("Error al obtener empleados:", error);
  }
});

// Propiedad computada que realiza el filtro en tiempo real
const filteredEmployees = computed(() => {
  if (!search.value) return employees.value;

  return employees.value.filter((employee) => {
    const fullName =
      `${employee.firstName} ${employee.lastName}`.toLowerCase();

    return (
      fullName.includes(search.value.toLowerCase()) ||
      employee.city.toLowerCase().includes(search.value.toLowerCase()) ||
      employee.position.toLowerCase().includes(search.value.toLowerCase())
    );
  });
});

// Función para ver el detalle del empleado
function viewEmployee(id: string) {
  const employee = employees.value.find(e => e.id === id);

  if (employee) {
    selectedEmployee.value = employee;
  }
}
</script>

<style scoped>
.employee-table-container {
  background: #ffffff;
  border-radius: 16px;
  padding: 24px;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.header h2 {
  font-size: 20px;
  font-weight: 600;
  color: #111827;
  margin: 0;
}

.header p {
  font-size: 14px;
  color: #6b7280;
  margin: 4px 0 0 0;
}

/* Estilos de las acciones y caja de búsqueda */
.actions{
  display:flex;
  align-items:center;
  gap:16px;
}

.search-box{
  display:flex;
  align-items:center;
  gap:10px;
  width:220px;
  padding:10px 14px;
  border:1px solid #E5E7EB;
  border-radius:12px;
  transition:.2s;
}

.search-box:focus-within{
  border-color:#4F46E5;
  box-shadow:0 0 0 3px rgba(79,70,229,.15);
}

.search-box input{
  border:none;
  outline:none;
  width:100%;
  font-size:14px;
  background:transparent;
}

.search-box svg{
  color:#6B7280;
}

.add-btn{
  display:flex;
  align-items:center;
  gap:8px;
  border:none;
  background:#4F46E5;
  color:white;
  padding:10px 16px;
  border-radius:12px;
  cursor:pointer;
  font-weight:600;
  transition:.2s;
}

.add-btn:hover{
  background:#4338CA;
}

/* Estilos de la tabla */
.table-wrapper {
  overflow-x: auto;
}

.employee-table {
  width: 100%;
  border-collapse: collapse;
  text-align: left;
}

.employee-table th {
  padding: 12px 16px;
  border-bottom: 1px solid #e5e7eb;
  color: #374151;
  font-weight: 600;
}

.employee-table td {
  padding: 16px;
  border-bottom: 1px solid #f3f4f6;
  color: #4b5563;
}

/* Estilos de la celda del empleado y avatar */
.employee {
  display: flex;
  align-items: center;
  gap: 12px;
}

.employee div {
  display: flex;
  flex-direction: column;
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background: #F3F4F6;
  color: #374151;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 600;
  font-size: 14px;
  border: 1px solid #E5E7EB;
}

.status {
  display: inline-flex;
  align-items: center;
  padding: 6px 12px;
  border-radius: 999px;
  background: #DCFCE7;
  color: #15803D;
  font-size: 13px;
  font-weight: 600;
}

/* Estilos del botón de acción */
.action-btn {
  width: 38px;
  height: 38px;
  border: none;
  border-radius: 10px;
  background: #EEF2FF;
  color: #4F46E5;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: .2s;
}

.action-btn:hover {
  background: #4F46E5;
  color: white;
  transform: scale(1.05);
}

.content{
  display:grid;
  grid-template-columns:minmax(0, 1fr) 340px;
  gap:24px;
  align-items:start;
}

.detail-panel{
  position:sticky;
  top:24px;
  width:340px;
}

.employee-row{
  cursor:pointer;
  transition:.2s;
}

.employee-row:hover{
  background:#F8FAFC;
}
</style>