<template>
  <div class="login-page">

    <div class="brand-panel">
      <div class="brand-icon">S</div>
      <h1>Symplifica</h1>
      <p>HR</p>

      <div class="brand-footer">
        <p>Gestiona empleados, beneficios y ubicaciones desde un solo lugar.</p>
        <p>Credenciales de prueba</p>
        <p>Usuario: admin</p>
        <p>Contraseña: admin123</p>
      </div>
    </div>

    <div class="form-panel">
      <div class="form-card">
        <h2>Bienvenido de nuevo</h2>
        <p class="subtitle">Ingresa tus credenciales para acceder al panel</p>

        <form @submit.prevent="handleLogin">

          <div class="field">
            <label>Usuario</label>
            <input
              v-model="username"
              type="text"
              placeholder="admin"
              required
              autofocus
            />
          </div>

          <div class="field">
            <label>Contraseña</label>
            <input
              v-model="password"
              type="password"
              placeholder="••••••••"
              required
            />
          </div>

          <p v-if="errorMessage" class="error">
            {{ errorMessage }}
          </p>

          <button type="submit" class="submit-btn" :disabled="loading">
            {{ loading ? "Ingresando..." : "Ingresar" }}
          </button>

        </form>
      </div>
    </div>

  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { useRouter } from "vue-router";
import { login } from "../services/authService";

const router = useRouter();

const username = ref("");
const password = ref("");
const loading = ref(false);
const errorMessage = ref("");

const handleLogin = async () => {
  errorMessage.value = "";
  loading.value = true;

  try {
    const { token } = await login({
      username: username.value,
      password: password.value,
    });

    localStorage.setItem("token", token);
    router.push("/dashboard");
  } catch (error) {
    console.error(error);
    errorMessage.value = "Usuario o contraseña incorrectos.";
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>

.login-page{
  display:flex;
  min-height:100vh;
}

.brand-panel{
  flex:1;
  background:#0F0B2E;
  color:white;
  display:flex;
  flex-direction:column;
  align-items:center;
  justify-content:center;
  padding:40px;
  text-align:center;
}

.brand-icon{
  width:64px;
  height:64px;
  border-radius:16px;
  background:#4F46E5;
  display:flex;
  align-items:center;
  justify-content:center;
  font-size:28px;
  font-weight:700;
  margin-bottom:24px;
}

.brand-panel h1{
  margin:0;
  font-size:32px;
  font-weight:700;
}

.brand-panel > p{
  margin-top:4px;
  color:#A5A6C9;
  font-size:16px;
}

.brand-footer{
  margin-top:48px;
  max-width:320px;
}

.brand-footer p{
  color:#A5A6C9;
  font-size:14px;
  line-height:1.6;
}

.form-panel{
  flex:1;
  display:flex;
  align-items:center;
  justify-content:center;
  background:#F9FAFB;
  padding:40px;
}

.form-card{
  width:100%;
  max-width:400px;
}

.form-card h2{
  margin:0;
  font-size:28px;
  font-weight:700;
  color:#111827;
}

.subtitle{
  margin-top:8px;
  margin-bottom:32px;
  color:#6B7280;
  font-size:15px;
}

.field{
  display:flex;
  flex-direction:column;
  gap:6px;
  margin-bottom:18px;
}

.field label{
  font-size:14px;
  font-weight:600;
  color:#374151;
}

.field input{
  padding:14px;
  border:1px solid #ddd;
  border-radius:12px;
  font-size:15px;
}

.field input:focus{
  outline:none;
  border-color:#4F46E5;
}

.error{
  color:#EF4444;
  font-size:14px;
  margin-bottom:16px;
}

.submit-btn{
  width:100%;
  padding:14px;
  border:none;
  border-radius:12px;
  background:#4F46E5;
  color:white;
  font-weight:600;
  font-size:15px;
  cursor:pointer;
  transition:.2s;
}

.submit-btn:hover:not(:disabled){
  background:#4338CA;
}

.submit-btn:disabled{
  opacity:.7;
  cursor:not-allowed;
}

@media(max-width:900px){
  .login-page{
    flex-direction:column;
  }

  .brand-panel{
    padding:32px;
  }

  .brand-footer{
    display:none;
  }
}

</style>