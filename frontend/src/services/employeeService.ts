import api from "./api";
import type { Employee } from "../types/employee";

export const getEmployees = async (): Promise<Employee[]> => {
  const { data } = await api.get<Employee[]>("/employees");
  return data;
};

export const getEmployeeById = async (id: string): Promise<Employee> => {
  const { data } = await api.get<Employee>(`/employees/${id}`);
  return data;
};

export const createEmployee = async (
  employee: Omit<Employee, "id" | "latitude" | "longitude" | "displayName">
): Promise<Employee> => {
  const { data } = await api.post<Employee>("/employees", employee);
  return data;
};

export const updateEmployee = async (
  id: string,
  employee: Partial<Employee>
): Promise<Employee> => {
  const { data } = await api.put<Employee>(`/employees/${id}`, employee);
  return data;
};

export const deleteEmployee = async (id: string): Promise<void> => {
  await api.delete(`/employees/${id}`);
};