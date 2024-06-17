const email = document.getElementById("email");
const password = document.getElementById("password");
const repeatedPassword = document.getElementById("repeatedPassword");
const repeatedEmail = document.getElementById("repeatedEmail");
const form = document.getElementById("form");
form.addEventListener("submit", (event) => {
    alert("Working!!")
    let isEmailValid = emailValidator();
    let isPasswordValid = passwordValidator();
    let isRepeatedPasswordValid = repeatPasswordCheck();
    let isRepeatedEmailValid = repeatEmailCheck();
    if(!isEmailValid || !isPasswordValid || !isRepeatedPasswordValid || !isRepeatedEmailValid) {
        event.preventDefault();
    }
});

function passwordValidator() {
    const validator = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/g;
    if(!validator.test(password.value)) {
        document.getElementById("password").style.borderColor = "red";
        let error = document.getElementById("password-error");
        error.style.color = "red";
        error.innerText = "Password must contain at least 8 characters, one letter, one number and one special character(@$!%*#?&)";
        return false;
    }
    return true;
}

function emailValidator() {
    const validator = /^\w((\.)?\w+)*@\w+\.[a-z]+(\.\w+)*$/i;
    if(!validator.test(email.value)) {
        document.getElementById("email").style.borderColor = "red";
        let error = document.getElementById("emailError");
        error.style.color = "red";
        error.innerText = "Email is not valid";
        return false;
    }
    return true;
}

function repeatPasswordCheck(){
    if(repeatedPassword.value !== password.value){
        document.getElementById("repeatedPassword").style.borderColor = "red";
        let error = document.getElementById("repeatedPasswordError");
        error.style.color = "red";
        error.innerText = "Passwords do not match";
        return false;
    }
    return true;
}

function repeatEmailCheck(){
    if(repeatedEmail.value !== email.value){
        document.getElementById("repeatedEmail").style.borderColor = "red";
        let error = document.getElementById("repeatedEmailError");
        error.style.color = "red";
        error.innerText = "Emails do not match";
        return false;
    }
    return true;
}