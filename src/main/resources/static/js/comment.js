'use strict'
document.getElementById('com-but').addEventListener('click', function () {
    const com = document.getElementById('comment-form');
    com.hidden = false;
    com.addEventListener('submit', async function (e) {
        e.preventDefault();
        const data = new FormData(com);
        const options = {
            method: 'POST',
            body: data
        }
        await fetch("http://localhost:8080/api/comment", options);
        com.hidden = true;
        location.reload();
    });
});