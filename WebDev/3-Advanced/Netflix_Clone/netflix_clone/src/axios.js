import axios from "axios";

const response = axios.create({
  baseURL: "https://api.themoviedb.org/3",
});

export default response;
