function addActor(id) {
    var input = document.getElementById("actors");
    var btn = document.getElementById("btn" + id);
    var dbtn = document.getElementById("dbtn" + id);

    if (input.value === "") {
        input.value = id;
    } else {
        input.value += ", " + id;
    }

    btn.style.display = 'none';
    dbtn.style.display = 'block';
}

function deleteActor(id) {
    var input = document.getElementById("actors");
    var btn = document.getElementById("btn" + id);
    var dbtn = document.getElementById("dbtn" + id);

    var reg = new RegExp("^[\\w]{1}$");

    if (input.value.indexOf(id) !== -1 && input.value.indexOf(id) !== 0) {
        input.value = input.value.replace(", " + id, "");
    } else if (input.value.indexOf(id) === 0) {
        if (reg.test(input.value)) {
            console.log("single character");
            input.value = "";
        }
        else {
            input.value = input.value.replace(id + ", ", "");
        }
    }

    btn.style.display = 'block';
    dbtn.style.display = 'none';
}

function showFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

function filterFunction() {
    var input, filter, p;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    div = document.getElementById("myDropdown");
    option = div.getElementsByClassName("movieOption");
    for (i = 0; i < p.length; i++) {
        if (option[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
            option[i].style.display = "";
        } else {
            option[i].style.display = "none";
        }
    }
}

function selectMovie(id){
    var dbtn = document.getElementById(id + "dbtn");
    var btn = document.getElementById(id + "btn");
    var input = document.getElementById("film");
    var dbtns = document.getElementsByClassName("dmbtn");

/*    for(var i = 0; i < dbtns.length(); i++){
        dbtns[i].style.display = 'none';
    }*/

    input.value = id;

    btn.style.display = 'none';
    dbtn.style.display = 'block';
}

function deselectMovie(id){
    var dbtn = document.getElementById(id + "dbtn");
    var btn = document.getElementById(id + "btn");
    var input = document.getElementById("film");

    input.value = "";

    dbtn.style.display = 'none';
    btn.style.display = 'block';

}