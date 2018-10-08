$(document).ready(function() {
   //Products - list
    function confirmDialog() {
            var dialog = $(`<div id="dialog-confirm" class="ui-dialog-content ui-widget-content" title="Confirmacion" style="width: auto; min-height: 13px; max-height: none; height: auto;">
                                        <div id="dialog-confirm" >
                                        <p>Seguro que queres borrar este producto?</p>
                                    </div>`);
        var def = $.Deferred();
        $(dialog).dialog({
            resizable: false,
            height: "auto",
            width: 400,
            modal: true,
            buttons: {
                "Si": function () {
                    def.resolve();
                    $(this).dialog("close");
                },
                "No": function () {
                    def.reject();
                    $(this).dialog("close");
                }
            }
        });
        return def.promise();
    }

    $.each(products,function(index,value){
        $('#delete-' + value.id).click(function(){
            confirmDialog().done(function() {
                $.ajax({
                    type: "DELETE",
                    contentType: 'application/json',
                    dataType: 'text',
                    url: `/product/${value.id}`,
                    data: value.id,
                    success:function(){
                        $("#product-" + value.id).remove();
                    },
                    error:function(error){
                        console.log(error);
                    }
                });
            }).fail(function() {
            });
        });
    });

    var urlParams = new URLSearchParams(window.location.search);
    if(urlParams.has('msg')){
        var msg;
        switch (urlParams.get('msg')){
            case 'productUpdateSuccess':
                msg = {
                    sucess:true,
                    text: 'El producuo se modifico exitosamente'
                };

                break;
            case 'productCreateSuccess':
                msg = {
                    sucess: true,
                    text: 'El producuo se creo exitosamente'
                }
                break;
            case 'productSaveError':
                msg = {
                    sucess: false,
                    text: 'Hubo un error, no se pudo guardar el producto'
                }
                break;
        }
        var myToast = Toastify({
            text: msg.text,
            duration: 5000,
            close: true,
            backgroundColor: msg.sucess ? 'green' : 'red'
        }).showToast();
    }

});