$(document).ready(function() {

    function confirmDialog(quantity) {
        var dialog = $(`<div id="dialog-confirm" class="ui-dialog-content ui-widget-content" title="Detalle de compra" style="width: auto; min-height: 13px; max-height: none; height: auto;">
                                        <div id="dialog-confirm" >
                                        <p>Producto: ${product.name}</p>
                                        <p>Cantidad: ${quantity}</p>
                                        <p>Monto total: $${product.price * quantity}</p>
                                    </div>`);
        var def = $.Deferred();
        $(dialog).dialog({
            resizable: false,
            height: "auto",
            width: 400,
            modal: true,
            buttons: {
                "Comprar": function () {
                    def.resolve();
                    $(this).dialog("close");
                },
                "No, gracias": function () {
                    def.reject();
                    $(this).dialog("close");
                }
            }
        });
        return def.promise();
    }

    $('#buy').click(function(){
        var quantity = Number.parseInt($("input[name='quantity']").val());
        confirmDialog(quantity).done(function() {
            var postObject = {
                productId:product.id,
                quantity:quantity
            };
            purchase(postObject);
        }).fail(function() {
        });
    });

    function purchase(postObject){
        $.ajax({
            type: "POST",
            dataType: 'text',
            contentType: 'application/json',
            url: `/purchase`,
            data: JSON.stringify(postObject),
            success:function(data){
                if(data == "true"){
                    console.log('exito');
                    Toastify({
                        text: 'Tu compra fue hecha exitosamente',
                        duration: 5000,
                        close: true,
                        backgroundColor: 'green'
                    }).showToast();
                }
                else{
                    Toastify({
                        text: 'No hay suficiente stock del producto solicitado',
                        duration: 5000,
                        close: true,
                        backgroundColor: 'red'
                    }).showToast();
                }
            },
            error:function(error){
                console.log('error');
                Toastify({
                    text: 'Hubo un error, no se pudo realizar la compra',
                    duration: 5000,
                    close: true,
                    backgroundColor: 'red'
                }).showToast();
            },
            complete:function(){
                $('#quantityInput').val(undefined);
            }
        });
    }
});