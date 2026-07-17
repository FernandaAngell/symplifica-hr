import axios from "axios";

const API = "http://localhost:8080/api/employee-benefits";

export interface EmployeeBenefit {
  id: string;
  benefitId: string;
  benefitName: string;
  description: string;
  category: string;
  amount: number;
}

export async function getEmployeeBenefits(employeeId: string) {
  const response = await axios.get<EmployeeBenefit[]>(
    `${API}/${employeeId}`
  );

  return response.data;
}

export async function assignBenefit(data: {
  employeeId: string;
  benefitId: string;
  amount: number;
}) {
  const response = await axios.post(API, data);

  return response.data;
}