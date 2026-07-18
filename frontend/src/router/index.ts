import { createRouter, createWebHistory } from "vue-router";

import MainLayout from "../layouts/MainLayout.vue";

import LoginView from "../views/LoginView.vue";
import DashboardView from "../views/DashboardView.vue";
import EmployeesView from "../views/EmployeesView.vue";
import EmployeeDetailView from "../views/EmployeeDetailView.vue";
import BenefitsView from "../views/BenefitsView.vue";
import CreateEmployeeView from "../views/CreateEmployeeView.vue";

import { isAuthenticated } from "../services/authService";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/login",
      name: "login",
      component: LoginView,
    },
    {
      path: "/",
      component: MainLayout,
      children: [
        {
          path: "",
          redirect: "/dashboard",
        },
        {
          path: "employees",
          component: EmployeesView,
        },
        // IMPORTANTE: esta ruta debe ir antes de employees/:id
        {
          path: "employees/new",
          name: "create-employee",
          component: CreateEmployeeView,
        },
        {
          path: "employees/:id/edit",
          name: "edit-employee",
          component: CreateEmployeeView,
          props: true,
        },
        {
          path: "employees/:id",
          component: EmployeeDetailView,
          props: true,
        },
        {
          path: "benefits",
          component: BenefitsView,
        },
        {
          path: "dashboard",
          component: DashboardView,
        },
      ],
    },
  ],
});

router.beforeEach((to, _from, next) => {
  if (to.path !== "/login" && !isAuthenticated()) {
    next("/login");
  } else if (to.path === "/login" && isAuthenticated()) {
    next("/dashboard");
  } else {
    next();
  }
});

export default router;