$(function(){
	Stuart.init();
	Stuart.search();

	$("#dialog").dialog({
      autoOpen: false,
      height: 300,
      width: 480,
      modal: true,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "blind",
        duration: 1000
      }
    });

	$(".rec a.name").each(function() {
		$(this).click(function() {

	      $("#dialog").dialog("open");
	    });
	});

});

