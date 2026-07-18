<template>
  <header class="topbar">
    <div></div>

    <div class="profile">
      <div class="avatar">
        MF
      </div>

      <div>
        <strong>Maria Fernanda</strong>
        <p>Software Developer</p>
      </div>

      <button class="logout-btn" @click="handleLogout" title="Cerrar sesión">
        <LogOut :size="18" />
      </button>
    </div>
  </header>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";
import { LogOut } from "lucide-vue-next";
import { logout } from "../../services/authService";

const router = useRouter();

const handleLogout = () => {
  logout();
  router.push("/login");
};
</script>

<style scoped>

.topbar {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding: 20px 30px;
  background: white;
  border-bottom: 1px solid #eee;
}

input{
  width:350px;
  padding:12px 16px;
  border-radius:12px;
  border:1px solid #ddd;
  outline:none;
}

.profile{

display:flex;

align-items:center;

gap:15px;

}

.avatar{

width:45px;

height:45px;

border-radius:50%;

background:#4f46e5;

display:flex;

align-items:center;

justify-content:center;

color:white;

font-weight:bold;

}

.profile p{

margin:0;

font-size:13px;

color:#888;

}

.logout-btn{
  width:40px;
  height:40px;
  border-radius:10px;
  border:1px solid #E5E7EB;
  background:#F9FAFB;
  color:#6B7280;
  display:flex;
  align-items:center;
  justify-content:center;
  cursor:pointer;
  transition:.2s;
  margin-left:6px;
}

.logout-btn:hover{
  background:#FEE2E2;
  border-color:#FCA5A5;
  color:#EF4444;
}

</style>