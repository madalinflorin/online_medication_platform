import axios from "axios";

class AuthService {
  login(username, password) {
    return axios
      .post("/api/auth/signin", {
        username,
        password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("patient", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("patient");
  }

  register(username, email, password, name, birthDate,gender, address) {
    return axios.post("/api/auth/signup", {
      username,
      email,
      password,
      name,
      birthDate,
      gender,
      address
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('patient'));;
  }
}

export default new AuthService();
