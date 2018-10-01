$(document).ready(function() {
    // Users
    $('#buy').click(function(){
        var quantity = Number.parseInt($("input[name='quantity']").val());
        purchase(productId,quantity);
    });

    function purchase(productId,quantity){
        var postObject = {
            productId:productId,
            quantity:quantity
        };

        $.ajax({
            type: "POST",
            dataType: 'json',
            contentType: 'application/json',
            url: `/purchase`,
            data: JSON.stringify(postObject),
            sucess:function(data){
                console.log(data);
            },
            error:function(error){
                console.log(error);
            }
        });
    }
});