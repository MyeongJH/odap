$('.form').find('input, textarea').on('keyup blur focus', function (e) {
  
  var $this = $(this),
      label = $this.prev('label');

	  if (e.type === 'keyup') {
			if ($this.val() === '') {
          label.removeClass('active highlight');
        } else {
          label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
    	if( $this.val() === '' ) {
    		label.removeClass('active highlight'); 
			} else {
		    label.removeClass('highlight');   
			}   
    } else if (e.type === 'focus') {
      
      if( $this.val() === '' ) {
    		label.removeClass('highlight'); 
			} 
      else if( $this.val() !== '' ) {
		    label.addClass('highlight');
			}
    }

});

$('.tab a').on('click', function (e) {
  
  e.preventDefault();
  
  $(this).parent().addClass('active');
  $(this).parent().siblings().removeClass('active');
  
  target = $(this).attr('href');

  $('.tab-content > div').not(target).hide();
  
  $(target).fadeIn(600);
  
});

$(document).ready(function(){
	 $("#id").keyup(function(){
	 
	  $.post("../auth/overlap.do", {		  
	   id : $("#id").val()
	  }, function(result) {
		  console.log($("#id").val())
		  if($("#id").val() == "") {
			  $("#idlabel").html("ID")
			  $("#idlabel").css("color","#a0b3b0");
			  $("#signUpBtn").css("display","");
		  } else{
		  if(!result){
			  $("#signUpBtn").css("display","none");
			  $(".highlight").html("<p id='tof'>사용 불가</p>");
			  $(".highlight").css("color","red");
		  } else {
			  $("#signUpBtn").css("display","");
			  $("#tof").remove();
			  $(".highlight").html("<p id='tof'>사용 가능</p>");
			  $(".highlight").css("color","#ffffff");
		  }	      	      
     }
	  }, "json"
	    );
	});
});


$("#signUpBtn").click(function(event) {
	 $.post("../ajax/member/add.do", {		      
		      id: $("#id").val(), 
		      pw: $("#password").val(),
		      name: $("#name").val(),
		      tel: $("#tel").val(),
		      mem: $("#email").val()
		    }, function(result) {
		      if (result.status == "success") {
		        location.href = "mypage_han.html";
		        } else {
		        window.alert("등록 실패입니다!");
		      }
		    }, "json"
		    );
		  });



$("#loginBtn").click(function(event) {
	$.post("../auth/exist.do", {		
		id: $("#id2").val(), 
	    password: $("#password2").val()
	    }, function(result) {
	      if(result.exist) {
	    	  console.log(result.mno);
	    	  sessionStorage.setItem("mno",result.mno);
	    	  console.log("로그인 성공!");
	    	  location.href = "mypage_han.html";
	      } else {
	    	  window.alert("없는 아이디!")
	      }
	      }, "json"
	    );
	  });

