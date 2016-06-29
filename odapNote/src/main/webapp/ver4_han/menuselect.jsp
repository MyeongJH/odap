<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
  <c:when test="${param.menu == 1}"> 
    <div class="profile-header">PROFILE</div>
  <form id="frm" name="frm" method="POST">
  <div class="preview img-wrapper"></div>
  <div class="file-upload-wrapper">
      <input type="file" id="uploadFile" name="uploadFile" class="file-upload-native" accept="image/*"/>
      <input type="text" disabled placeholder="upload image" class="file-upload-text" />
  </div>
  <!-- ---------------------------------------------------------------- -->
  <div class="container edit-profile-page">
    <div class="row">
      <div class="col-sm-12 profile-panel">    

        <div class="edit-container"><button id="upload" name='upload' type='submit' class='btn-edit'>SAVE</button></div>
        <!------------------------------------------------------------------------------------------------------------>        
        <div class="profile-label MID-label">ID</div>
        <div class="profile MID"></div>
        <div class="profile-label MPW-label">PASSWORD</div>
        <div class="profile MPW"></div>
        <div class="profile-label MNM-label">NAME</div>
        <div class="profile MNM"></div>
        <div class="profile-label MTEL-label">TEL</div>
        <div class="profile MTEL"></div>
        <div class="profile-label label MEM-label">EMAIL</div>
        <div class="profile MEM"></div>
        <div class="label MJOB-label">JOB</div>
        <div class="profile MJOB"></div>
        <div class="label MADR-label">LOCATION</div>
        <div class="profile MADR"></div>
        <div class="label MPG-label">HOMEPAGE</div>
        <div class="profile MPG"></div>
      </div>
    </div>
  </div>
  </form>
  <script>
  loadMyInfo();
  
  function loadMyInfo() {
       $.getJSON("../ajax/member/detail.do", function(result) {
          /* var img = new Image();
          img.src=result.mpic; 
           img.onerror=function(){
            $("#frm").css("opacity","0.5");
            $('#loading').append("<div id='loadingImg'><img src='img/reload.gif'/></div>").show();
            setTimeout(function() {
            location.href="profile.html";
            }, 3000);
          } */
          
          $(".MID").append("<text id='MID' name='mid'>"+result.mid+"</text><span class='focus-border'></span>");
          $(".MPW").append("<div class='col-3'><input id='MPW' name='mpw' class='effect-1' type='password' value='"+result.mpw+"'><span class='focus-border'></span></div>");
          $(".MNM").append("<div class='col-3'><input id='MNM' name='mnm' class='effect-1' type='text' value='"+result.mnm+"'><span class='focus-border'></span></div>");
          $(".MTEL").append("<div class='col-3'><input id='MTEL' name='mtel' class='effect-1' type='text' value='"+result.mtel+"'><span class='focus-border'></span></div>");
          if (!result.mem)
            result.mem="";
          $(".MEM").append("<div class='col-3'><input id='MEM' name='mem' class='effect-1' type='text' value='"+result.mem+"'><span class='focus-border'></span></div>");
          if (!result.mjob)
                result.mjob="";
          $(".MJOB").append("<div class='col-3'><input id='MJOB' name='mjob' class='effect-1' type='text' value='"+result.mjob+"'><span class='focus-border'></span></div>");
          if (!result.madr)
                result.madr="";
          $(".MADR").append("<div class='col-3'><input id='MADR' name='madr' class='effect-1' type='text' value='"+result.madr+"'><span class='focus-border'></span></div>");
          if (!result.mpg)
                result.mpg="";
          $(".MPG").append("<div class='col-3'><input id='MPG' name='mpg' class='effect-1' type='text' value='"+result.mpg+"'><span class='focus-border'></span></div>");
          if (!result.mpic)
                result.mpic="img/noimage.jpg";
          $(".img-wrapper").css('background', 'url(../ver1_m/' + result.mpic + ') no-repeat center center').css('background-size', 'cover');     
        
    });
  }    

  $("form#frm").submit(function(event){    
      //disable the default form submission
      event.preventDefault();

      var fd = new FormData($(this)[0]);  
      
      $.ajax({
        url: "../ajax/member/upload.do",
        type: "POST",
        data: fd, 
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success:  function(data){
          alert("저장되었습니다.");
        }
      });   
      return false;   
  });

  $(function() {
    function maskImgs() {
      //$('.img-wrapper img').imagesLoaded({}, function() {
      $.each($('.img-wrapper img'), function(index, img) {
        var src = $(img).attr('src');
        var parent = $(img).parent();
        parent
          .css('background', 'url(' + src + ') no-repeat center center')
          .css('background-size', 'cover');
        $(img).remove();
      });
      //});
    }

    var preview = {
      init: function() {
        preview.setPreviewImg();
        preview.listenInput();
      },
      setPreviewImg: function(fileInput) {
        var path = $(fileInput).val();
        var uploadText = $(fileInput).siblings('.file-upload-text');

        if (!path) {
          $(uploadText).val('');
        } else {
          path = path.replace(/^C:\\fakepath\\/, "");
          $(uploadText).val(path);

          preview.showPreview(fileInput, path, uploadText);
        }
      },
      showPreview: function(fileInput, path, uploadText) {
        var file = $(fileInput)[0].files;

        if (file && file[0]) {
          var reader = new FileReader();

          reader.onload = function(e) {
            var previewImg = $(fileInput).parents('.file-upload-wrapper').siblings('.preview');
            var img = $(previewImg).find('img');

            if (img.length == 0) {
              $(previewImg).html('<img src="' + e.target.result + '" alt=""/>');
            } else {
              img.attr('src', e.target.result);
            }

            uploadText.val(path);
            maskImgs();
          }

          reader.onloadstart = function() {
            $(uploadText).val('uploading..');
          }

          reader.readAsDataURL(file[0]);
        }
      },
      listenInput: function() {
        $('.file-upload-native').on('change', function() {
          preview.setPreviewImg(this);
        });
      }
    };
    preview.init();
  });
  
  $("#m1").attr('class','menu selectmenu');
  $("#m2").removeClass('selectmenu');
  $("#m3").removeClass('selectmenu');
  $("#m4").removeClass('selectmenu');
  </script>
  
  </c:when>
  <c:when test="${param.menu == 2}"> 
     <div class="profile-header">CLASS</div>
              <table id="classlist" cellpadding="10px">
              <tr>
              <th>강사</th><th>과목</th><th>클래스명</th><th>회원수</th><th>질문수</th><th>입장</th>
              </tr>   
              </table>
              
  <script>

  loadClassList();
  

  function loadClassList() {   
      $.getJSON("../ajax/class/list.do", function(result) {  
        $.each(result, function(i,d){                
              $("#classlist").append("<tr><td>"+d.mno+"</td><td>"+d.csub+"</td><td>"+d.cnm+"</td><td>180</td><td>41564</td><td><a href='#'>☎</a></td></tr>"); 
        });
    })
  }
  $("#m1").removeClass('selectmenu');
  $("#m2").attr('class','menu selectmenu');
  $("#m3").removeClass('selectmenu');
  $("#m4").removeClass('selectmenu');

  </script>
  </c:when>
  <c:when test="${param.menu == 3}"> 
    <div class="profile-header">MY QUESTION</div>
              <article id="wrapper">
              <nav id="columns"></nav>
              </article>
              
  <script>
loadQuestions();
  
  function loadQuestions() {
    $.getJSON("../ajax/question/list.do",function(data) {
       $.each(data, function(index,entry) {
          $("<div class='pin'>").html("<img src='" + entry.qpic +"'/>" + "<p>" 
                + entry.qtit + "</p><br>" + "<p>" + entry.qcnt + "</p>").appendTo($("#columns"));
       });
  })
  }
  $("#m1").removeClass('selectmenu');
  $("#m2").removeClass('selectmenu');
  $("#m3").attr('class','menu selectmenu');
  $("#m4").removeClass('selectmenu');
  </script>
  </c:when>
  <c:when test="${param.menu == 4}"> 
  <div class="profile-header">BOOKMARK</div>
              <div class="ia-container"></div>
  <script>
loadQuestions();
  function loadQuestions() {
    $.getJSON("../ajax/bookmark/list.do",function(data) {
       $.each(data, function(i,d) {      
         if(i == 0) {
               $(".ia-container").append("<figure id='fic-" + i + "'><img class='bimg' src='"+d.qpic+"' alt='seocips'/>" +
                   "<input type='radio' name='radio-set' checked='checked'/>" +
                   "<figcaption><span>"+d.qtit+"<br>"+d.qcnt+"</span></figcaption></figure>")
                                       
           }  else {
             $("#fic-"+(i-1)).append("<figure id='fic-" + i + "'><img class='bimg' src='"+d.qpic+"' alt='seocips'/>" +
                       "<input type='radio' name='radio-set' checked='checked'/>" +
                       "<figcaption><span>"+d.qtit+"<br>"+d.qcnt+"</span></figcaption></figure>")         
           } 
       });
  }) 
  }
  $("#m1").removeClass('selectmenu');
  $("#m2").removeClass('selectmenu');
  $("#m3").removeClass('selectmenu');
  $("#m4").attr('class','menu selectmenu');
  </script>
  </c:when>
  
  <c:otherwise> 
    <p>메뉴를 선택해 주세요.</p>
  </c:otherwise>
</c:choose>