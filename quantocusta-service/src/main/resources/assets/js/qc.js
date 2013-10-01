var url = document.URL;
var ctx = "/starving/";
if (url.indexOf(".cc") > -1 ) {
	ctx = "";
}
/*
(function( $ ) {
	 
	$.fn.rate = function() {
	// Open popup code
	};
	 
	$.fn.closePopup = function() {
	// Close popup code
	};
 
}( jQuery ));*/

$.fn.qc = function() {
	this.css( "color", "green" );
	return this;
};

var qc = {
	ithink: function(where, thinks) {
		$.ajax({
			url: ctx+"/api/ithink",
			type: "POST",
			cache: false,
			data: {
				"ap.venue.id": where,
				"ap.think": thinks
			},
			success: function(data) {
				alert(data);
			}
		});
	}
};

function think(where, thinks) {
	$.ajax({
		url: ctx+"/api/ithink",
		type: "POST",
		cache: false,
		data: {
			"ap.id.idVenue": where,
			"ap.think": thinks
		},
		success: function(data) {
			if (data.status == "ok") {
				var t = data.venue.social.amount_0 + data.venue.social.amount_1 + data.venue.social.amount_2;
				$(".bar-danger").css("width", data.venue.social.amount_0 / t * 100 + "%");
				$(".bar-warning").css("width", data.venue.social.amount_1 / t * 100 + "%");
				$(".bar-success").css("width", data.venue.social.amount_2 / t * 100 + "%");
				
				if (data.venue.me) {
					$("*[data-toggle='ap']").each(function(index) {
						if ($(this).attr("data-value") == data.venue.me.think) {
							$(this).addClass("active");
						} else {
							$(this).removeClass("active");
						}
					});
				}
			} else if (data.status == "error" && data.code == 403) {
				$("#modal-help").modal('show');
				$("*[data-toggle='ap']").each(function(index) {
					$(this).removeClass("active");
				});
			}
		}
	});
}

