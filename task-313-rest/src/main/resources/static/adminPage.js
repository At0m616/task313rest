// const userList = document.getElementById('main-tab')
const URL = 'http://localhost:8080/api/users'

fetch(URL)
    .then(res=>console.log(res))