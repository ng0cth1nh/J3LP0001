/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


const ops = document.querySelectorAll(".option-answer");
const makeForm = document.getElementById("make-form");
const question = document.getElementById("question");
const mess = document.getElementById("mess");

function check(ops) {
    for (let i = 0; i < ops.length; i++) {
        //loop each option input to check at least 1 option has checked
        if (ops[i].checked === true)
            //1 option has checked return true
            return true;
    }
    return false;
}

makeForm.addEventListener("submit", (e) => {
    if (!check(ops)) {
        //in all option is not check at least 1 then prevent to submit form
        e.preventDefault();
        mess.innerHTML = "You have to check at least one option";
    }
})
