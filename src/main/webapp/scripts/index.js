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
    let taskJson = `{"name":"${valueTask}"}`
    // const task = document.querySelector("#selectTask").value;

    fetch("http://localhost:8080/restservices/users/"+valueUser, {
        method: "PATCH",
        body: taskJson,
        headers: {'Content-Type': 'application/json'}
    })
        .then(response => response.json())
        .then(function (myJson) {
            console.log(myJson)
        })
}

async function fetchGetAllAssignedTasks(){
    await fetch('http://localhost:8080/restservices/users/allUserTasks', {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(async response => await response.json())
        .then(function (myJson){
            for(const [key, value] of Object.entries(myJson)){
                const dashboard = document.getElementById("task-dashboard");
                if(value.length > 0){
                    console.log(key, value);
                    const user = document.createElement('h1');
                    user.textContent = key;
                    dashboard.appendChild(user);
                    value.forEach(element =>{
                            const val = document.createElement('h2');
                            val.textContent = element.name;
                            dashboard.appendChild(val)
                        }
                    );
                }
            }
            });
}
