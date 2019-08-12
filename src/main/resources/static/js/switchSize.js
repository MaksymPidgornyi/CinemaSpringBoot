$('#sizeSelector').change(function () {

    if (window.location.href.includes("?size")) {
        console.log("one");
        window.location.href = window.location.href.replace("\?*", "") + '?size=' + $(this).val();
    }
    else if(window.location.href.includes("?*")){
        console.log("two");
        window.location.href = window.location.href.replace("\&.*", "") + '&size=' + $(this).val();
    }
    else {
        console.log("three");
        window.location.href = window.location.href + '?size=' + $(this).val();
    }

});
