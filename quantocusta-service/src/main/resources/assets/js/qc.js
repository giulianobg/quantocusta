var qc = {
	vote: function(where, kind, v) {
		var params = new Object();
		params.id = where;
		params.kind = kind;
		params.v = v;
		
		$.ajax({
			url: "/api/vote",
			type: "POST",
			contentType: "application/json; charset=utf-8",
			cache: false,
			data: JSON.stringify(params),
			success: function(data) {
				alert(data);
			}
		});
	}
};
/*
function think(where, thinks) {
	//return false;
	$.ajax({
		url: "/api/vote",
		type: "POST",
		cache: false,
		data: {
			"id": where,
			"vote": thinks
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
*/

