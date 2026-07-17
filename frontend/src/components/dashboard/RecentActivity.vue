<template>
  <div class="card">
    <div class="header">
      <h3>Actividad reciente</h3>
    </div>

    <div v-if="activities.length">
      <div
        v-for="activity in activities"
        :key="activity.id"
        class="activity"
      >
        <div class="icon">
          ✓
        </div>

        <div class="content">
          <strong>{{ activity.title }}</strong>
          <span>{{ activity.subtitle }}</span>
        </div>

        <small>{{ activity.time }}</small>
      </div>
    </div>

    <p v-else class="empty">
      No hay actividad reciente.
    </p>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { getEmployees } from "../../services/employeeService";

const activities = ref<any[]>([]);

onMounted(async () => {
  const employees = await getEmployees();

  activities.value = employees.slice(0, 5).map((employee: any) => ({
    id: employee.id,
    title: `${employee.firstName} ${employee.lastName}`,
    subtitle: `Empleado registrado en ${employee.city}`,
    time: "Hace unos minutos",
  }));
});
</script>

<style scoped>
.card{
  background:white;
  border-radius:16px;
  padding:24px;
  box-shadow:0 4px 12px rgba(0,0,0,.05);
}

.header{
  margin-bottom:20px;
}

.activity{
  display:flex;
  align-items:center;
  gap:14px;
  padding:12px 0;
  border-bottom:1px solid #eee;
}

.activity:last-child{
  border-bottom:none;
}

.icon{
  width:36px;
  height:36px;
  border-radius:50%;
  background:#dbeafe;
  color:#2563eb;
  display:flex;
  justify-content:center;
  align-items:center;
  font-weight:bold;
}

.content{
  flex:1;
  display:flex;
  flex-direction:column;
}

.content strong{
  font-size:14px;
}

.content span{
  font-size:13px;
  color:#6b7280;
}

small{
  color:#9ca3af;
}

.empty{
  color:#9ca3af;
}
</style>