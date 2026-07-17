import axios from "axios";

const api = axios.create({
  baseURL: "https://symplifica-hr.onrender.com/api/",
  headers: {
    "Content-Type": "application/json",
  },
});

export default api;