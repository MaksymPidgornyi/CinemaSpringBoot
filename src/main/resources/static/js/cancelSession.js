$("button.cancelBtn").click(function() {

    console.log(this.id);

    $.ajax({
        url: "/afisha/delete/" + this.id,
        method: "DELETE"
    })
});