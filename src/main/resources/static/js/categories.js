/**
 * 
 */
function checkCategoryDuplicated(){

    var input = $('#category').val();
    var url = 'http://localhost:8080/api/categories/check';

    if (input.length < 1){
        $('#category-warning').hide();
    }
    else {
        $.ajax({
            type: 'POST',
            url : url,
            data : input,
            error : function () {
                console.log('something unpleasant has happened.');
            },

            success : function(data){
                console.log(data);

                if (data.unique){
                    $('#category-warning').hide();
                    $('#join-submit').attr('disabled', false);

                }

                else{
                    $('#category-warning').show();
                }



            }

        })

    }
}