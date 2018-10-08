$(document).ready(function() {
    // Users
    $(".selectpicker").selectpicker();
    $('#passwordchange').change(function() {
        if(this.checked) {
            $('#passwordContainer input').prop('disabled', false);
            $('#passwordContainer').removeClass("d-none");
        }
        else {
            $('#passwordContainer').addClass("d-none");
            $('#passwordContainer input').prop('disabled', true);
        }
    });
});