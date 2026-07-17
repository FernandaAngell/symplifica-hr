<template>
  <div class="card">

    <div class="top">

      <div class="icon">
        <slot name="icon" />
      </div>

      <span class="badge">
        +12%
      </span>

    </div>

    <div class="content">

      <p>{{ title }}</p>

      <h2>{{ value }}</h2>

      <small>{{ subtitle }}</small>

    </div>

  </div>
</template>

<script setup lang="ts">
defineProps<{
  title: string
  value: string | number
  subtitle: string
}>()
</script>

<style scoped>

.card{

    background:white;

    border-radius:22px;

    padding:24px;

    box-shadow:0 10px 30px rgba(0,0,0,.06);

    transition:.25s;

    cursor:pointer;

}

.card:hover{

    transform:translateY(-6px);

    box-shadow:0 18px 40px rgba(79,70,229,.15);

}

.top{

display:flex;

justify-content:space-between;

align-items:center;

margin-bottom:30px;

}

.icon{

width:58px;

height:58px;

background:#EEF2FF;

border-radius:18px;

display:flex;

align-items:center;

justify-content:center;

color:#4F46E5;

}

.badge{

background:#DCFCE7;

color:#15803D;

padding:6px 12px;

border-radius:30px;

font-size:12px;

font-weight:600;

}

.content p{

margin:0;

color:#6B7280;

font-size:14px;

}

.content h2{

margin:8px 0;

font-size:34px;

font-weight:700;

color:#111827;

}

.content small{

color:#9CA3AF;

}

</style>