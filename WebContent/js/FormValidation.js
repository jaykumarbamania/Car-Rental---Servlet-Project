let fnameNode = document.getElementById("fname")
let fnameLabel = document.getElementById("fnamelabel")
let fnameError = document.getElementById("fnameError")

function fnameVal(){
    fnameError.innerHTML="";
    fnameLabel.className="";
    let fname = fnameNode.value;
    if(fname == ''){
        fnameError.innerHTML="<br>This field is required";
        fnameNode.style.border="none";
        fnameNode.style.borderBottom = "2px solid red";
        // fnameNode.className="error"
        fnameError.className= fnameLabel.className="errorTxt";
        return false;
    }else if(fname.includes(' ')){
        fnameError.innerHTML="<br>Spaces not allowed";
        // fnameError.style.color = "red";
        fnameNode.style.border="none";
        fnameNode.style.borderBottom = "2px solid red";
        // fnameNode.className="error"
        fnameError.className= fnameLabel.className="errorTxt";
        return false;
    } 
    else{
        fnameNode.style.border = "1px solid #6B3783";
        return true;
    } 
   
}

let lnameNode = document.getElementById("lname")
let lnameLabel = document.getElementById("lnamelabel")
let lnameError = document.getElementById("lnameError")

let lnameVal = ()=>{
    lnameError.innerHTML=""
    lnameLabel.className=""
    let lname = lnameNode.value
    if(lname == ''){
        lnameError.innerHTML="<br>This field is required"
        lnameNode.style.border="none"
        lnameNode.style.borderBottom = "2px solid red"
        lnameError.className=lnameLabel.className="errorTxt"
        return false
    }else if(lname.includes(' ')){
        lnameError.innerHTML="<br>Spaces not allowed"
        lnameNode.style.border="none"
        lnameNode.style.borderBottom = "2px solid red"
        lnameError.className=lnameLabel.className="errorTxt"
        return false
    } 
    else{
        lnameNode.style.border = "1px solid #6B3783"
        return true
    } 
}

let contactnameNode = document.getElementById("contactname")
let contactnameLabel = document.getElementById("contactnamelabel")
let contactnameError = document.getElementById("contactnameError")

let contactnameVal = ()=>{
    contactnameError.innerHTML=""
    contactnameLabel.className=""
    let contactname = contactnameNode.value
    if(contactname == ''){
        contactnameError.innerHTML="<br>Please Enter your name"
        contactnameNode.style.border="none"
        contactnameNode.style.borderBottom = "2px solid red"
        contactnameError.className=contactnameLabel.className="errorTxt"
        return false
    }
    else{
        contactnameNode.style.border = "1px solid #6B3783"
        return true
    } 
}

let phoneNode = document.getElementById("phoneno")
let phoneLabel = document.getElementById("phonelabel")
let phoneError = document.getElementById("phoneError")

let phoneVal = ()=>{
    phoneError.innerHTML=""
    phoneLabel.className=""
    let phone = phoneNode.value
    let mobExp = new RegExp("[0-9]{10}");
    if(phone == ''){
        phoneError.innerHTML="<br>Phone No is required"
        phoneNode.style.border="none"
        phoneNode.style.borderBottom = "2px solid red"
        phoneError.className=phoneLabel.className="errorTxt"
        return false
    } else if(mobExp.test(phone)==false){
        phoneError.innerHTML="<br>Phone number must be with only 10 digits"
        phoneNode.style.border="none"
        phoneNode.style.borderBottom = "2px solid red"
        phoneError.className=phoneLabel.className="errorTxt"
        return false
    }
    else{
        phoneNode.style.border = "1px solid #6B3783"
        return true
    } 
}

let emailNode = document.getElementById("email")
let emailLabel = document.getElementById("emaillabel")
let emailError = document.getElementById("emailError")

let emailVal = ()=>{
    emailError.innerHTML=""
    emailLabel.className=""
    let email = emailNode.value
    let substring = email.substring(email.indexOf('@')+1)
    if(email == ''){
        emailError.innerHTML="<br>Please Enter your email"
        emailNode.style.border="none"
        emailNode.style.borderBottom = "2px solid red"
        emailError.className=emailLabel.className="errorTxt"
        return false
    }else if(!email.includes('@') || substring==''){
        emailError.innerHTML="Invalid Email"
        emailNode.style.border="none"
        emailNode.style.borderBottom = "2px solid red"
        emailError.className=emailLabel.className="errorTxt"
        return false
    }
    else{
        emailNode.style.border = "1px solid #6B3783"
        return true
    } 
}

let ageNode = document.getElementById("age")
let ageLabel = document.getElementById("agelabel")
let ageError = document.getElementById("ageError")

let ageVal = () => {
    ageError.innerHTML=""
    ageLabel.className=""
     let x = age.value;
     if(x == ''){
        ageError.innerHTML="<br>please Enter your age"
        ageNode.style.border="none"
        ageNode.style.borderBottom = "2px solid red"
        ageError.className=ageLabel.className="errorTxt"
        return false
    }else if (x < 1 || x > 100){  
        ageError.innerHTML="<br>enter age between 1 to 100"
        ageNode.style.border="none"
        ageNode.style.borderBottom = "2px solid red"
        ageError.className=ageLabel.className="errorTxt"
        return false
     }else{
        ageNode.style.border = "1px solid #6B3783"
        return true
    } 
}

let passNode = document.getElementById("password")
let passLabel = document.getElementById("passlabel")
let passError = document.getElementById("passError")

let passVal = ()=>{
    passError.innerHTML=""
    passLabel.className=""
    let password = passNode.value
    let regExp = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])");

    if(password == ''){
        passError.innerHTML="<br>Password is required"
        passNode.style.border="none"
        passNode.style.borderBottom = "2px solid red"
        passError.className=passLabel.className="errorTxt"
        return false
    }else if(regExp.test(password) == false){
        passError.innerHTML="Password should be alphanumeric with special symbol"
        passNode.style.border="none"
        passNode.style.borderBottom = "2px solid red"
        passError.className=passLabel.className="errorTxt"
        return false
    }else if(password.length <6 || password.length>12){
        passError.innerHTML="<br>Password should be 6 to 12 characters long"
        passNode.style.border="none"
        passNode.style.borderBottom = "2px solid red"
        passError.className=passLabel.className="errorTxt"
        return true
    } 
    else{
        passNode.style.border = "1px solid #6B3783"
        return true
    } 
}

let cpassNode = document.getElementById("cpassword")
let cpassLabel = document.getElementById("cpasslabel")
let cpassError = document.getElementById("cpassError")

let cpassVal = ()=>{
    cpassError.innerHTML=""
    cpassLabel.className=""
    let password = passNode.value
    let c_password = cpassNode.value
    if(c_password == ''){
        cpassError.innerHTML="<br>Please confirm your password"
        cpassNode.style.border="none"
        cpassNode.style.borderBottom = "2px solid red"
        cpassError.className=cpassLabel.className="errorTxt"
        return false
    }else if(c_password != password){
        cpassError.innerHTML="Password should match"
        cpassError.className=cpassLabel.className="errorTxt"
        cpassNode.style.border="none"
        cpassNode.style.borderBottom = "2px solid red"
        return false
    }
    else{
        cpassNode.style.border = "1px solid #6B3783"
        return true
    } 
}

let messageNode = document.getElementById("message")
let messageLabel = document.getElementById("messagelabel")
let messageError = document.getElementById("messageError")

let messageVal = ()=>{
    messageError.innerHTML=""
    messageLabel.className=""
    let message = messageNode.value
    if(message == ''){
        messageError.innerHTML="<br>Please Enter your query"
        messageNode.style.border="none"
        messageNode.style.borderBottom = "2px solid red"
        messageError.className=messageLabel.className="errorTxt"
        return false
    }
    else{
        messageNode.style.border = "1px solid #6B3783"
        return true
    } 
}

let loginemailNode = document.getElementById("loginemail")
let loginemailLabel = document.getElementById("loginemaillabel")
let loginemailError = document.getElementById("loginemailError")

let loginemailVal = ()=>{
    loginemailError.innerHTML=""
    loginemailLabel.className=""
    let loginemail = loginemailNode.value
    if(loginemail == ''){
        loginemailError.innerHTML="<br>Please Enter your username"
        loginemailNode.style.border="none"
        loginemailNode.style.borderBottom = "2px solid red"
        loginemailError.className=loginemailLabel.className="errorTxt"
        return false
    }
    else{
        loginemailNode.style.border = "1px solid #6B3783"
        return true
    } 
}

let lpassNode = document.getElementById("lpassword")
let lpassLabel = document.getElementById("lpasslabel")
let lpassError = document.getElementById("lpassError")

let lpassVal = ()=>{
    lpassError.innerHTML=""
    lpassLabel.className=""
    let lpassword = lpassNode.value
    if(lpassword == ''){
        lpassError.innerHTML="<br>Password is required"
        lpassNode.style.border="none"
        lpassNode.style.borderBottom = "2px solid red"
        lpassError.className=lpassLabel.className="errorTxt"
        return false;
    }
    else{
        lpassNode.style.border = "1px solid #6B3783"
        return true;
    } 
}

//Onsubmit form
// let form = document.getElementById('signupForm');
// let formError = document.getElementById("formError")
// let submitForm = (event) => {
//     console.log("Submitting...")
//     formError.innerHTML=""
//     if(!fnameNode.value || !lnameNode.value){
//         formError.innerHTML="Please fill the form without any Errors"
//         formError.className="errorTxt"
//     }
//     else {
//         event.preventDefault();
//         formError.innerHTML="Everything is fine"
//         formError.className="errorTxt"
//     }
// }

let formError = document.getElementById("formError")

let registerForm = () => {
    formError.innerHTML=""
    let varnameVal= fnameVal();
    let varusernameVal = lnameVal();
    let varphoneVal = phoneVal();
    let varemailVal = emailVal();
    let varpassVal = passVal();
    let varcpassVal = cpassVal();

    if(!varnameVal && !varusernameVal && !varphoneVal && !varcpassVal && !varemailVal && !varpassVal){
        formError.innerHTML="Please fullfill coditions of required fields";
        formError.className="errorTxt";
        return false;
    }else{
        return ( varnameVal && varusernameVal && varphoneVal && varemailVal && varpassVal && varcpassVal);
    }

    
}


let loginForm = () => {
    formError.innerHTML=""
    let varemailVal = loginemailVal();
    let varlpassVal = lpassVal();

    if( !varemailVal && !varlpassVal){
        formError.innerHTML="Must Enter Email and Password"
        formError.className="errorTxt";
        return false;
    }else{
        return ( varemailVal &&  varlpassVal);
    }

    
}

let contactForm = () => {

    let varcontactnameVal= contactnameVal();
    let varphoneVal = phoneVal();
    let varemailVal = emailVal();
    let varmessageVal = messageVal();

    return ( varcontactnameVal && varphoneVal && varemailVal &&  varmessageVal);
}