function onDocumentReady(fn) {
    if (document.readyState === "complete" || document.readyState === "interactive") {
        setTimeout(fn, 1);
    } else {
        document.addEventListener("DOMContentLoaded", fn);
    }
}

const API_PATH = 'http://localhost:8080/prompt-tuner';

onDocumentReady(() => {

    const left = document.querySelector('#left > .messages');
    const right = document.querySelector('#right > .messages');

    let send = document.getElementById("send");
    send.onclick = function() {

        document.getElementById("loading").style.display = 'block';

        left.replaceChildren();
        right.replaceChildren();

        fetch(API_PATH, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                model: document.getElementById("model").value,
                systemPrompt: document.getElementById("system-prompt").value,
                prompt: document.getElementById("prompt").value
            })
        })
        .then(response => response.json())
        .then(data => {
            document.getElementById("loading").style.display = 'none';

            addChatEntry(left, "user", data["raw-prompt"]["user"]);
            addChatEntry2(left, "assistant", data["raw-prompt"]["assistant"]);
            addChatEntry(right, "user", data["tuned-prompt"]["user"]);
            addChatEntry2(right, "assistant", data["tuned-prompt"]["assistant"]);


        })
        .catch(error => {
            document.getElementById("loading").style.display = 'none';
            console.error('Error:', error);
        });

    };

    init();

});

function init() {






    fetch(API_PATH + '/models')
        .then(response => response.json())
        .then(data => {
            for (let bla of data) {
                let option = document.createElement("option");
                option.value = bla.key;
                option.text = bla.name;
                document.getElementById("model").appendChild(option);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });

    fetch(API_PATH + '/system-prompts')
        .then(response => response.json())
        .then(data => {
            for (let bla of data) {
                let option = document.createElement("option");
                option.value = bla;
                option.text = bla;
                document.getElementById("system-prompt").appendChild(option);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });



}

function addChatEntry(parent, role, text) {
    let li = document.createElement('li');
    let div = document.createElement('div');
    div.classList.add('chat-entry');
    div.classList.add(role);
    div.innerHTML = text.replace(/\n/g, '<br>');
    li.appendChild(div);
    let div2 = document.createElement('div');
    div2.innerHTML = '🧑';
    div2.classList.add('icon');
    li.appendChild(div2);
    parent.appendChild(li);
}

function addChatEntry2(parent, role, text) {
    let li = document.createElement('li');
    let div2 = document.createElement('div');
    div2.innerHTML = '🤖';
    div2.classList.add('icon2');
    li.appendChild(div2);
    let div = document.createElement('div');
    div.classList.add('chat-entry');
    div.classList.add(role);
    div.innerHTML = text.replace(/\n/g, '<br>');
    li.appendChild(div);
    parent.appendChild(li);
}
