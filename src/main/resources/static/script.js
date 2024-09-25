let update = document.getElementById("passwordModal")
let create = document.getElementById("createModal")
let deleted = document.getElementById("deleteModal")
let edits = document.getElementsByClassName("editButton")
let deletes = document.getElementsByClassName("deleteButton")
let copUsername = document.getElementsByClassName("copyUsername")
let copPassword = document.getElementsByClassName("copyPassword")
let createClose = document.getElementById("create-close")
let updateClose = document.getElementById("update-close")
let deleteClose = document.getElementById("delete-close")
let showPasswordCheckbox = document.getElementById("show-password")
const URL = "http://107.120.121.97:8081/accounts"

showPasswordCheckbox.addEventListener('change', function () {
    let passwordField = document.getElementById("modal-password");
    if (this.checked) {
        passwordField.type = "text";
    } else {
        passwordField.type = "password";
    }
});

function createClick() {
    create.style.display = "block";
}

updateClose.onclick = function () {
    update.style.display = "none";
}
createClose.onclick = function () {
    create.style.display = "none";
}
deleteClose.onclick = function () {
    deleted.style.display = "none";
}
window.onclick = function (event) {
    if (event.target === update) {
        update.style.display = "none";
    }
    if (event.target === create) {
        create.style.display = "none";
    }
    if (event.target === deleted) {
        deleted.style.display = "none";
    }
}
Array.prototype.forEach.call(copUsername, function (copy) {
    copy.onclick = async function () {
        let row = copy.parentNode.parentNode;
        let password = row.cells[1].innerText
        try {
            await copyToClipboard(password)
            alert('Username copied to clipboard!')
        } catch (error) {
            console.log(error)
        }
    }
});
Array.prototype.forEach.call(copPassword, function (copy) {
    copy.onclick = async function () {
        let row = copy.parentNode.parentNode;
        let password = row.cells[3].innerText
        try {
            await copyToClipboard(password)
            alert('Password copied to clipboard!')
        } catch (error) {
            console.log(error)
        }
    }
});

async function copyToClipboard(text) {
    if (navigator.clipboard && window.isSecureContext) {
        await navigator.clipboard.writeText(text)
    } else {
        let textArea = document.createElement("textarea")
        textArea.value = text
        textArea.style.position = "absolute"
        textArea.style.left = "-999999px"
        document.body.prepend(textArea)
        textArea.select()
        try {
            document.execCommand('copy')
        } catch (error) {
            console.error(error)
        } finally {
            textArea.remove();
        }
    }
}

Array.prototype.forEach.call(edits, function (btn) {
    btn.onclick = function () {
        let row = btn.parentNode.parentNode;
        let type = row.cells[0].innerText;
        let username = row.cells[1].innerText;
        let password = row.cells[3].innerText;
        document.getElementById("modal-type").value = type;
        document.getElementById("modal-username").value = username;
        document.getElementById("modal-password").value = password;
        update.style.display = "block";
    }
});
Array.prototype.forEach.call(deletes, function (del) {
    del.onclick = function () {
        let row = del.parentNode.parentNode;
        let type = row.cells[0].innerText;
        let username = row.cells[1].innerText;
        document.getElementById("modal-delete-type").value = type;
        document.getElementById("modal-delete-username").value = username;
        deleted.style.display = "block";
    }
});

function submitPasswordUpdate() {
    let type = document.getElementById("modal-type").value;
    let username = document.getElementById("modal-username").value;
    let newPassword = document.getElementById("modal-password").value;
    let req = new XMLHttpRequest();
    req.open("POST", URL + "/update", false);
    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            switch (req.status) {
                case 200: {
                    update.style.display = "none";
                    alert("Update password successfully!");
                    location.reload()
                    break
                }
                case 400: {
                    alert(JSON.parse(req.responseText).message)
                    break
                }
                default: {
                    alert("Account updated fail!");
                    break
                }
            }
        }
    };
    req.send(JSON.stringify({
        type: type.replace(/\s/g, ""),
        username: username.replace(/\s/g, ""),
        password: newPassword.replace(/\s/g, "")
    }));
}

function submitPasswordCreate() {
    const environments = document.getElementsByName('environment');
    let selectedValue;
    for (const environment of environments) {
        if (environment.checked) {
            selectedValue = environment.value;
            break;
        }
    }
    let type = document.getElementById("modal-create-type").value;
    let username = document.getElementById("modal-create-username").value;
    let password = document.getElementById("modal-create-password").value;
    let req = new XMLHttpRequest();
    req.open("POST", URL + "/create", false);
    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            switch (req.status) {
                case 200: {
                    create.style.display = "none";
                    alert("Create account successfully!");
                    location.reload()
                    break
                }
                case 400: {
                    alert(JSON.parse(req.responseText).message)
                    break
                }
                default: {
                    alert("Account updated fail!");
                    break
                }
            }
        }
    };
    req.send(
        JSON.stringify({
            environment: selectedValue,
            type: type.replace(/\s/g, ""),
            username: username.replace(/\s/g, ""),
            password: password.replace(/\s/g, "")
        })
    );
}

function submitPasswordDelete() {
    let type = document.getElementById("modal-delete-type").value;
    let username = document.getElementById("modal-delete-username").value;
    let req = new XMLHttpRequest();
    req.open("POST", URL + "/delete", false);
    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            switch (req.status) {
                case 200: {
                    deleted.style.display = "none";
                    alert("Delete account successfully!");
                    location.reload()
                    break
                }
                case 400: {
                    alert(JSON.parse(req.responseText).message)
                    break
                }
                default: {
                    alert("Account delete fail!");
                    break
                }
            }
        }
    };
    req.send(
        JSON.stringify({
            type: type,
            username: username,
        })
    );
}
