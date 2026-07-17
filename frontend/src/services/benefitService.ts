import api from "./api";

export const getBenefits = async () => {
  const response = await api.get("/benefits");
  return response.data;
};

export const createBenefit = async (benefit: {
  name: string;
  description: string;
  category: string;
  value: number;
  mandatory: boolean;
}) => {
  const response = await api.post("/benefits", benefit);
  return response.data;
};

export const updateBenefit = async (
  id: string,
  benefit: {
    name: string;
    description: string;
    category: string;
    value: number;
    mandatory: boolean;
  }
) => {
  const response = await api.put(`/benefits/${id}`, benefit);
  return response.data;
};

export const deleteBenefit = async (id: string) => {
  await api.delete(`/benefits/${id}`);
};