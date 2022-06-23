<template>
  <nav>
    <!-- <router-link to="/">Home</router-link> |
    <router-link to="/about">Sign In</router-link> -->
  </nav>
  <router-view />
</template>

<style>
#app {
  font-family: "Libre Franklin", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

  color: #2c3e50;
  background-color: #f8f9fa;
}
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
