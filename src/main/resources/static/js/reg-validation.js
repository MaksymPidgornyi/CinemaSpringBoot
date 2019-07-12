function validate(form) {
    var elements = form.elements;
    console.log(elements);

    if(elements.password-field.value != elements.confirm-field.value){
        showError(elements.password, "Passwords are different")
        Console.log("Beep-Bop");
    }
}