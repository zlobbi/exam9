'use strict'
window.addEventListener('load', function () {
    const loginForm = document.getElementById('login-form');
    loginForm.addEventListener('submit', onLoginHandler);

    async function onLoginHandler(e) {
        const form = e.target;
        const userFormData = new FormData(form);
        const user = Object.fromEntries(userFormData);
        console.log(user)
        saveUser(user);
    }

    function saveUser(user) {
        const userAsJSON = JSON.stringify(user)
        localStorage.setItem('user', userAsJSON);
    }
    function restoreUser() {
        const userAsJSON = localStorage.getItem('user');
        return JSON.parse(userAsJSON);
    }
})