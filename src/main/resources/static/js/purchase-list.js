$(document).ready(function() {
    function showClaim(purchase) {
        var dialog = $(`<div id="dialog-confirm" class="ui-dialog-content ui-widget-content" title="Reclamo compra #${purchase.id}" +  style="width: auto; min-height: 13px; max-height: none; height: auto;">
                                        <div id="dialog-confirm" >
                                         <p>${purchase.claims[0].description}</p>
                                        <p>Estado de reclamo:${purchase.claims[0].state}</p>
                                    </div>`);
        var def = $.Deferred();
        $(dialog).dialog({
            resizable: false,
            height: "auto",
            width: 400,
            modal: true,
            buttons: {
                "Cerrar": function () {
                    def.resolve();
                    $(this).dialog("close");
                }
            }
        });
        return def.promise();
    }

    $.each(purchases,function(index,value) {
        $('#purchase-' + value.id).click(function () {
            showClaim(value);
        });
    });

});