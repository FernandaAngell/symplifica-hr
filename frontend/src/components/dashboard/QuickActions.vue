<template>
  <div class="quick-actions">

    <div class="header">
      <h3>Accesos rápidos</h3>
      <p>Acciones frecuentes</p>
    </div>

    <div class="grid">

      <button class="action-card" @click="goToNewEmployee">
        <UserPlus :size="30" />
        <h4>Nuevo empleado</h4>
        <span>Registrar colaborador</span>
      </button>

      <button class="action-card" @click="goToBenefits">
        <Gift :size="30" />
        <h4>Beneficios</h4>
        <span>Gestionar beneficios</span>
      </button>

      <button class="action-card" @click="goToEmployees">
        <Users :size="30" />
        <h4>Empleados</h4>
        <span>Ver listado</span>
      </button>

      <button class="action-card" @click="goToDashboard">
        <LayoutDashboard :size="30" />
        <h4>Dashboard</h4>
        <span>Ver resumen</span>
      </button>

    </div>

  </div>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";
import {
  UserPlus,
  Gift,
  Users,
  LayoutDashboard,
} from "lucide-vue-next";

const router = useRouter();

const goToEmployees = () => {
  router.push("/employees");
};

const goToNewEmployee = () => {
  router.push({ name: "create-employee" });
};

const goToBenefits = () => {
  router.push("/benefits");
};

const goToDashboard = () => {
  if (router.currentRoute.value.path === "/dashboard") {
    window.location.reload();
  } else {
    router.push("/dashboard");
  }
};
</script>

<style scoped>

.quick-actions{
  background:white;
  border-radius:18px;
  padding:24px;
  border:1px solid #E5E7EB;
  box-shadow:0 4px 12px rgba(0,0,0,.04);
}

.header{
  margin-bottom:20px;
}

.header h3{
  margin:0;
  font-size:20px;
  font-weight:600;
  color:#111827;
}

.header p{
  margin-top:4px;
  color:#6B7280;
  font-size:14px;
}

.grid{
  display:grid;
  grid-template-columns:repeat(2,1fr);
  gap:16px;
}

.action-card{

  background:#fff;

  border:1px solid #E5E7EB;
  border-radius:14px;

  padding:20px;

  display:flex;
  flex-direction:column;
  align-items:center;
  justify-content:center;

  transition:.25s;

  cursor:pointer;
}

.action-card:hover{

  transform:translateY(-4px);

  border-color:#6366F1;

  box-shadow:0 10px 22px rgba(99,102,241,.15);

}

.action-card svg{

  color:#6366F1;

  margin-bottom:14px;

}

.action-card h4{

  margin:0;

  font-size:15px;

  font-weight:600;

  color:#111827;

}

.action-card span{

  margin-top:6px;

  font-size:13px;

  color:#6B7280;

  text-align:center;

}

</style>