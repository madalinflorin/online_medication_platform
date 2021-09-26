export default function authHeader() {
  const user = JSON.parse(localStorage.getItem('patient'));

  if (user && user.accessToken) {
    return { Authorization: 'Bearer ' + user.accessToken,
                         'Accept': 'application/json',
                         'Content-Type': 'application/json'
                     }; // for Spring Boot back-end
   // return { 'x-access-token': user.accessToken };       // for Node.js Express back-end
  } else {
    return {};
  }
}
