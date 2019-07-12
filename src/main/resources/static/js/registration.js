$(function(){
    $.ajaxSetup({
        cache: false
    });


    $("#regButton").click( function(){
        sendUserToServer();
    } );



});

function RegistrationData(login, email, firstName, lastName, password){
    this.login = login;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
}

function readUserDataFromUI(){
    var reg = new RegistrationData( $('#login-field').val(),
                                    $('#email-field').val(),
                                    $('#firstName-field').val(),
                                    $('#lastName-field').val(),
                                    $('#password-field').val()
                                                                );
    return reg;
}

function sendUserToServer(){

    var reg = readUserDataFromUI();

    $.ajax({
        type: "POST",
        url: "/register",
        contentType: 'application/json',
        data: JSON.stringify(reg),
        success: function(data){
            console.log(reg)
        },
        error:function(error){
            console.log(error.responseText);
        }
    });
}

