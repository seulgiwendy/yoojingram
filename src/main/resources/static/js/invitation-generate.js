/**
 * 
 */
function getInvitation(){
    var targetDiv = $('#generated_uri');

$.get('/api/invitation', function (data) {
    $('#invite-panel').show();

    if(targetDiv.text().length > 0){
        targetDiv.empty();
    }

    targetDiv.append(window.location.host + '/invitation/' + data.path);
});


}