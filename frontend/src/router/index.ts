import { createRouter, createWebHistory } from "vue-router";

import MainLayout from "../layouts/MainLayout.vue";

import DashboardView from "../views/DashboardView.vue";
import EmployeesView from "../views/EmployeesView.vue";
import EmployeeDetailView from "../views/EmployeeDetailView.vue";
import BenefitsView from "../views/BenefitsView.vue";
import CreateEmployeeView from "../views/CreateEmployeeView.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
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

export default router;