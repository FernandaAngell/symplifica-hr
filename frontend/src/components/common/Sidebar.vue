<template>
  <aside class="sidebar">

    <div class="logo">
      <div class="icon">S</div>

      <div>
        <h2>Symplifica</h2>
        <span>HR Dashboard</span>
      </div>
    </div>

    <nav>

      <RouterLink to="/dashboard">
        Dashboard
      </RouterLink>

      <RouterLink to="/employees">
        Empleados
      </RouterLink>

      <RouterLink to="/benefits">
        Beneficios
      </RouterLink>

    </nav>

  </aside>
</template>

<style scoped>

.sidebar{
    width:260px;
    background:#111827;
    color:white;
    display:flex;
    flex-direction:column;
    padding:25px;
}

.logo{
    display:flex;
    align-items:center;
    gap:15px;
    margin-bottom:40px;
}

.icon{
    width:45px;
    height:45px;
    border-radius:12px;
    background:#4f46e5;
    display:flex;
    align-items:center;
    justify-content:center;
    font-weight:bold;
}

.logo h2{
    margin:0;
}

.logo span{
    font-size:13px;
    color:#cbd5e1;
}

nav{
    display:flex;
    flex-direction:column;
    gap:10px;
}

nav a{

    color:#cbd5e1;

    text-decoration:none;

    padding:14px;

    border-radius:12px;

    transition:.25s;

}

nav a:hover{

    background:#4f46e5;

    color:white;

}

.router-link-active{

    background:#4f46e5;

    color:white;

}

</style>