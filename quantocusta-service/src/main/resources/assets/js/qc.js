$.ajaxSetup({
	type: "POST",
	contentType: "application/x-www-form-urlencoded;charset=utf-8",
	timeout: 800
});

var qc = {
	vote: function(where, kind, v) {
		$.ajax({
			url: "/api/vote",
			data: {
				'id': where,
				'kind': kind,
				'v': v
			},
			success: function(data) {
				$('.ctn-food-pout').html(data.result.valuation.food.poutCount);
				$('.ctn-food-smile').html(data.result.valuation.food.smileCount);
				$('.ctn-treatment-pout').html(data.result.valuation.treatment.poutCount);
				$('.ctn-treatment-smile').html(data.result.valuation.treatment.smileCount);
				$('.ctn-environment-pout').html(data.result.valuation.environment.poutCount);
				$('.ctn-environment-smile').html(data.result.valuation.environment.smileCount);
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

