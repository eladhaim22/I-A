$(document).ready(function() {
    // Users
    $.each(users,function(index,value){
        $('#select-' + value.id).selectpicker();

        $('#select-' + value.id).on('changed.bs.select', function (e, clickedIndex, newValue, oldValue) {
            saveRoles(value.id,$(e.currentTarget).val());
        });

        $('#checkbox-' + value.id).click(function() {
            toggleActive(value.id,this.checked);
        });

        function toggleActive(userId,active){
            $.ajax({
                type: "POST",
                dataType: 'json',
                contentType: 'application/json',
                url: `/admin/user/${userId}/active`,
                data: active.toString(),
                sucess:function(data){
                    console.log(data);
                },
                error:function(error){
                    console.log(error);
                }
            });
        }

        function saveRoles(userId,rolesId){
            $.ajax({
                type: "POST",
                dataType: 'json',
                contentType: 'application/json',
                url: `/admin/user/${userId}/role`,
                data: JSON.stringify(rolesId ? rolesId : []),
                sucess:function(data){
                    console.log(data);
                },
                error:function(error){
                    console.log(error);
                }
            });
        }
    });
});