/*
Password Confirmation
 */
$('#password, #passwordConf').on('keyup', function () {
    if ($('#password').val() == $('#passwordConf').val()) {
        $('#message').html('Matching').css('color', 'green');
        $('#submit').prop('disabled', false);
    } else {
        $('#message').html('Not Matching').css('color', 'red');
        $('#submit').prop('disabled', true);
    }
});
