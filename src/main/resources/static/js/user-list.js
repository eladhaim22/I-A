$(document).ready(function() {
    // Users
    $.each(users,function(index,value){
        $('#role-select-' + value.id).multiselect({
            onChange: function(element, checked) {
                var userId = element[0].id.split('-')[0];
                var roleId = element[0].id.split('-')[1];
                $.ajax({
                    type: "POST",
                    dataType: 'json',
                    contentType: 'application/json',
                    url: `/admin/user/${userId}/role`,
                    data: roleId,
                    error:function(error){
                        console.log(error);
                    }
                });
            }
        });
    });
});