<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib tagdir="/WEB-INF/tags" prefix="wjaa" %>
<html>
<wjaa:header/>
<body>

<div class="container">
   	 <wjaa:top/>
	 <ol class="breadcrumb">
		<li><a href="/upload"><span class="label label-success">1° Escolha o Porta retrato</span> </a></li>
		<li><span class="label label-success">2° Envie Sua foto</span></li>
		<li><span class="label label-default">3° Veja como ficou</span></li>
  	 </ol>
  	 
      <!-- Jumbotron -->
      <div class="jumbotron">
        <h1>Envie sua foto!</h1>
        <p class="lead">Sua foto serÃ¡ tratada da melhor forma para ser impressa</p>
       </div>
		    <!-- The fileinput-button span is used to style the file input field as button -->
		    <span class="btn btn-success fileinput-button">
		        <i class="glyphicon glyphicon-plus"></i>
		        <span>Adicionar Foto...</span>
		        <!-- The file input field used as target for the file upload widget -->
		        <input id="fileupload" type="file" name="files[]" multiple>
		    </span>
		    <br>
		    <br>
		    <!-- The global progress bar -->
		    <div id="progress" class="progress">
		        <div class="progress-bar progress-bar-success"></div>
		    </div>
		    <!-- The container for the uploaded files -->
		    <div id="files" class="files"></div>
	
</div>
<script src="assets/js/jquery-1.10.2.min.js"></script>
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="assets/js/vendor/jquery.ui.widget.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="assets/js/load-image.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="assets/js/canvas-to-blob.min.js"></script>
<!-- Bootstrap JS is not required, but included for the responsive demo navigation -->
<script src="assets/js/bootstrap.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="assets/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="assets/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="assets/js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="assets/js/jquery.fileupload-image.js"></script>
<!-- The File Upload audio preview plugin -->
<script src="assets/js/jquery.fileupload-audio.js"></script>
<!-- The File Upload video preview plugin -->
<script src="assets/js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script src="assets/js/jquery.fileupload-validate.js"></script>
<script>
/*jslint unparam: true, regexp: true */
/*global window, $ */
function startPage(){
    'use strict';
    // Change this to the location of your server-side upload handler:
    var url = 'upload',
        uploadButton = $('<button/>')
            .addClass('btn btn-primary')
            .prop('disabled', true)
            .text('Processing...')
            .on('click', function () {
                var $this = $(this),
                    data = $this.data();
                $this
                    .off('click')
                    .text('Abort')
                    .on('click', function () {
                        $this.remove();
                        data.abort();
                    });
                data.submit().always(function () {
                    $this.remove();
                });
            });
    $('#fileupload').fileupload({
        url: url,
        dataType: 'json',
        autoUpload: false,
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        maxFileSize: 5000000, // 5 MB
        // Enable image resizing, except for Android and Opera,
        // which actually support image resizing, but fail to
        // send Blob objects via XHR requests:
        disableImageResize: /Android(?!.*Chrome)|Opera/
            .test(window.navigator.userAgent),
        previewMaxWidth: 100,
        previewMaxHeight: 100,
        previewCrop: true
    }).on('fileuploadadd', function (e, data) {
        data.context = $('<div/>').appendTo('#files');
        $.each(data.files, function (index, file) {
            var node = $('<p/>')
                    .append($('<span/>').text(file.name));
            if (!index) {
                node
                    .append('<br>')
                    .append(uploadButton.clone(true).data(data));
            }
            node.appendTo(data.context);
        });
    }).on('fileuploadprocessalways', function (e, data) {
        var index = data.index,
            file = data.files[index],
            node = $(data.context.children()[index]);
        if (file.preview) {
            node
                .prepend('<br>')
                .prepend(file.preview);
        }
        if (file.error) {
            node
                .append('<br>')
                .append($('<span class="text-danger"/>').text(file.error));
        }
        if (index + 1 === data.files.length) {
            data.context.find('button')
                .text('Enviar Foto')
                .prop('disabled', !!data.files.error);
        }
    }).on('fileuploadprogressall', function (e, data) {
        var progress = parseInt(data.loaded / data.total * 100, 10);
        $('#progress .progress-bar').css(
            'width',
            progress + '%'
        );
    }).on('fileuploaddone', function (e, data) {
        $.each(data.result, function (index, file) {
            if (file.url) {
                var link = $('<a>')
                    .prop('href', file.url);
                $("#files span").text("Veja como ficou sua foto.");
                $(data.context.children()[index])
                    .wrap(link);
            } else if (file.error) {
                var error = $('<span class="text-danger"/>').text(file.error);
                $(data.context.children()[index])
                    .append('<br>')
                    .append(error);
            }
        });
    }).on('fileuploadfail', function (e, data) {
        $.each(data.files, function (index, file) {
            var error = $('<span class="text-danger"/>').text('File upload failed.');
            $(data.context.children()[index])
                .append('<br>')
                .append(error);
        });
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
}
startPage();
</script>
</body> 
</html>
