document.write("<script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js'></script>");  
/*----------------------------------- class list move ---------------------------------------------*/
loadQuestionList();
loadClassList();
loadMyInfo();

function loadQuestionList() {
	var state;
	$.getJSON("../ajax/question/list.do", function(result) { 		
		$.each(result, function(i,d){		
			if(d.qst){
				state = "active"				
			} else {
				state = "danger"
			}
			$("tbody").append("<tr class="+state+"><td>"+d.qno+"</td><td>"+d.qtit+"</td><td>"+d.qcd+"</td></tr>");
	        });
	  });
}


function loadClassList() {	 
	  $.getJSON("../ajax/class/list.do?", function(result) {  
		  $.each(result, function(i,d){   
		        $("#classbtns").append("<a href='java.html'><button class='button button--winona button--border-thin button--round-s' data-text='enter'><span>"+d.cnm+"</span></button></a>");
		        $("#sortable").append("<li class='ui-state-default'><span class='ui-icon ui-icon-arrowthick-2-n-s'></span>"+d.cnm+"</li>");
		        
		  });
	})
}

function loadMyInfo() {
	
	 $.getJSON("../ajax/member/detail.do", function(result) { 		 
		 if(!result.mpic) {
			 result.mpic = "./img/noimage.jpg" ;
		 }
		  $("#mypic").append("<br><img src="+result.mpic+" class='img-circle'><br><br>");
      $("#myinfo").append("Name : " + result.mnm + "<br>E-mail : " + result.mem + "<br>Tel : " + result.mtel + "<br>Job : " + result.mjob + "<br>office : " + result.madr);
   });
}

	  $(function(){
		  $.getJSON("mypage.json",function(result){   
		      $.each(result.bookmark,function(i,d){		       
		        if(i == 0) {
		            $(".ia-container").append("<figure id='fic-" + i + "'><img class='bimg' src='"+d["img"]+"' alt='seocips'/>" +
		                "<input type='radio' name='radio-set' checked='checked'/>" +
		                "<figcaption><span>"+d["cl"]+"<br>"+d["p"]+"</span></figcaption></figure>")
		                                    
		        }  else {
		          $("#fic-"+(i-1)).append("<figure id='fic-" + i + "'><img class='bimg' src='"+d["img"]+"' alt='seocips'/>" +
		                    "<input type='radio' name='radio-set' checked='checked'/>" +
		                    "<figcaption><span>"+d["cl"]+"<br>"+d["p"]+"</span></figcaption></figure>")         
		        } 
		      });
		    
		    })
		  $( "#sortable" ).sortable();
		  $( "#sortable" ).disableSelection();
		  });

/*--------------------------------- class list move end -------------------------------------------*/

/* ------------------------------------- tab ---------------------------------------------*/
$(document).ready(function() { 

    (function ($) { 
      $('.tab ul.tabs').addClass('active').find('> li:eq(0)').addClass('current');
      
      $('.tab ul.tabs li a').click(function (g) { 
        var tab = $(this).closest('.tab'), 
          index = $(this).closest('li').index();
        
        tab.find('ul.tabs > li').removeClass('current');
        $(this).closest('li').addClass('current');
        
        tab.find('.tab_content').find('div.tabs_item').not('div.tabs_item:eq(' + index + ')').slideUp();
        tab.find('.tab_content').find('div.tabs_item:eq(' + index + ')').slideDown();
        
        g.preventDefault();
      } );
    })(jQuery);

  });
/* ------------------------------------- tab end ----------------------------------------*/




/*--------------------------------------- modal ------------------------------------------*/ 
$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').focus()
})
/*--------------------------------------- modal end ------------------------------------------*/


/*----------------------------------class add ---------------------------------------------------*/
$("#newClassSave").click(function(event) {
	 $.post("../ajax/class/add.do", { 
		      cnm: $("#Cname").val(), 
		      csub: $("#Csub").val(),
		      cdes: $("#Cdes").val()
		    }, function(result) {
		      if (result.status == "success") {
		        location.href = "mypage_han.html";
		        } else {
		        window.alert("등록 실패입니다!");
		      }
		    }, "json"
		    );
		  });

/*----------------------------------class add end---------------------------------------------------*/
/*-------------------------------------log out ---------------------------------------------------*/
