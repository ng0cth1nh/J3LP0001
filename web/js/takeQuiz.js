
const questions = document.querySelectorAll(".ques");
const checkTimeForm = document.getElementById("checkTimeForm");
const timeInput = document.getElementById("timeInput");
const result = document.getElementById("result");
const timeDisplay = document.getElementById("time");
const nextBtn = document.getElementById("nextBtn");

let index = 1;
//set default index is 1
let rs = [];



function startTimer(duration, display) {
    let timer = duration;
    let minutes, seconds;
    
    setInterval(function () {
        
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;

        if (--timer < 0) {
            display.textContent = "00:00";
            addResult();
            submitResult();
            return;
        }
    }, 1000);
}

window.onload = function () {

    questions[0].style.display = "block";

    timeDisplay.textContent = (questions.length >= 10)
            ? questions.length + ":00"
            : "0" + questions.length + ":00";

    let duration = (60 * questions.length) - 1;
    
    startTimer(duration, timeDisplay);
};

function addResult() {
    let checkedValue = null;
    let inputElements = document.getElementsByClassName('op');
    for (let i = 0; inputElements[i]; ++i) {
        if (inputElements[i].checked) {
            checkedValue = inputElements[i].value;
            rs.push(checkedValue);
        }
    }
}

function submitResult() {
    let tempRs = "";
    for (let i = 0; i < rs.length; i++) {
        tempRs += rs[i] + " ";
    }
    result.value = tempRs;
    
    checkTimeForm.submit();
}



nextBtn.onclick = function () {
    if (index === questions.length) {
        addResult();
        submitResult();
    } else {
        if (index === questions.length - 1) {
            nextBtn.textContent = "Finish";
        }
        questions[index - 1].style.display = "none";
        questions[index].style.display = "block";
        ++index;
    }
}


