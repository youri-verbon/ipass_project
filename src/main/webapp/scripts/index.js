async function fetchTasks() {
    await fetch('http://localhost:8080/restservices/tasks', {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(async response => await response.json())
        .then(data => {
            return data.forEach((name) => {
                const taskDashboard = document.querySelector('#selectTask');
                const task = document.createElement('option');
                task.setAttribute('value', name.name);
                task.textContent = name.name;
                taskDashboard.appendChild(task);
            });
        });
}

async function fetchUsers() {
    await fetch('http://localhost:8080/restservices/users', {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(async response => await response.json())
        .then(data => {
            return data.forEach((name) => {
                const selectUser = document.querySelector('#selectUser');
                const user = document.createElement('option');
                user.setAttribute('value', name.userId);
                user.textContent = name.name;
                selectUser.appendChild(user);
            });
        });
}

function fetchAssignTask(){
    // const userId = document.querySelector("#selectUser").value;
    const userId = document.getElementById("selectUser");
    const valueUser = userId.options[userId.selectedIndex].value;
    const task = document.getElementById("selectTask");
    const valueTask = task.options[task.selectedIndex].value;
    console.log(valueUser);
    console.log(valueTask);
    // const task = document.querySelector("#selectTask").value;

    fetch("http://localhost:8080/restservices/users/"+valueUser, {
        method: "PATCH",
        body: valueTask,
        headers: {'Content-Type': 'application/json'}
    })
        .then(response => response.json())
        .then(function (myJson) {
            console.log(myJson)
        })
}
// document.querySelector("#submitNewTask").addEventListener("click", fetchAssignTask());
