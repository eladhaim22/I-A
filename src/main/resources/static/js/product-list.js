$(document).ready(function() {
   //Products - list
    function confirmDialog() {
        var dialog = $(`
                <div id="dialog-confirm" title="Confirmacion">
                    <p>
                        <span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>
                        Seguro que queres borrar este producto?
                        </p>
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
                    dataType: 'json',
                    contentType: 'application/json',
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
});