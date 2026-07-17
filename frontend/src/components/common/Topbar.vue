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
    </div>
  </header>
</template>

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

</style>