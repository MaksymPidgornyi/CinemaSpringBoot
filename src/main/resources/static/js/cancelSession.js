$("button.cancelBtn").click(function() {
    $.ajax({
        url: "/afisha/session/" + this.id,
        type: "DELETE",
        headers: {"X-CSRF-TOKEN": $("meta[name='_csrf']").attr("content")},
        success: function () {
            console.log("deleted");
        }
    })
});