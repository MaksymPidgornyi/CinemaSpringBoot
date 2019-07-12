<#import 'parts/common.ftl' as c>

<@c.page "Register">
    <div id="container">
        <div id="form-container">
            <form id="registrationForm">
                <label for="login-field">Login</label><input type="text" name="login-field" id="login-field"/><br/>
                <label for="email-field">Email</label><input type="email" name="email-field" id="email-field"><br/>
                <label for="firstName-field">First Name</label><input type="text" name="firstName-field" id="firstName-field"/><br/>
                <label for="lastName-field">Last Name</label><input type="text" name="lastName-field" id="lastName-field"><br/>
                <label for="password-field">Password</label><input type="password" name="password-field" id="password-field"/><br/>
                <label for="confirm-field">Confirm password</label><input type="password" name="confirm-field" id="confirm-field"/><br/>
                <input type="submit" id="regButton" value="Register"/>
            </form>
        </div>
    </div>

    <script>
        $(function(){
            $.ajaxSetup({
                cache: false
            });

            $("#registrationForm").submit( function(event){
                event.preventDefault();
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
                        console.log(reg);
                        document.getElementById("registrationForm").reset();
                        alert('Succesfully registrated');
                        window.location.href = "/login";
                    },
                    error:function(error){
                        console.log(error.responseText);
                        alert('Something went wrong')
                    }
                });
        }

    </script>

</@c.page>