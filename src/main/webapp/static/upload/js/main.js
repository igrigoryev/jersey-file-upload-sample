$(function () {
    'use strict';
    $('#fileupload').fileupload({
        //xhrFields: {withCredentials: true},
        url: '../../rest/files/upload'
    });

    $('#fileupload').addClass('fileupload-processing');
    $.ajax({
        //xhrFields: {withCredentials: true},
        url: $('#fileupload').fileupload('option', 'url'),
        dataType: 'json',
        context: $('#fileupload')[0]
    }).always(function () {
        $(this).removeClass('fileupload-processing');
    }).done(function (result) {
        $(this).fileupload('option', 'done')
            .call(this, $.Event('done'), {result: result});
    });
});
