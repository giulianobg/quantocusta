$.ajaxSetup({
	type: "POST",
	contentType: "application/x-www-form-urlencoded;charset=utf-8",
	timeout: 2000,
	error: function(xhr, textStatus, err) {
		var data = JSON.parse(xhr.responseText);
		$("#error-modal .modal-title").html("Ooops!");
		if (data.code == 403) {
			$("#error-modal .modal-body").html("<p>Voc&ecirc; precisa estar loggado para executar essa a&ccedil;&atilde;o :(</p>")
		} else {
			$("#error-modal .modal-body").html("<p>Algum erro ocorreu:</p>" + "<p>" + data.message + "</p>" + "<p>:/</p>");
		}
		$("#error-modal").modal('show');		
	}
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
				$(".food").html(Math.round(data.result.valuation.food.smileAverage) + '%');
				$('.treatment').html(Math.round(data.result.valuation.treatment.smileAverage) + '%');
				$('.environment').html(Math.round(data.result.valuation.environment.smileAverage) + '%');
				qc.load(where);
			}
		});
	},
	submitPrice: function(where, price) {
		$.ajax({
			url: "/api/vote/price",
			data: {
				'id': where,
				'price': price.replace(/,/g, '.')
			},
			success: function(data) {
				$(".price").html(Math.round(data.result.reviews.averagePrice) + ',00');
			}
		});
	},
	load: function(where) {
		$.ajax({
			url: "/api/venue/" + where,
			type: "GET",
			success: function(data) {
				if (data.result.valuation.food.me) {
					if (data.result.valuation.food.me.val > 0) {
						$(".btn-food-s").addClass("active");
						$(".btn-food-p").removeClass("active");
					} else {
						$(".btn-food-p").addClass("active");
						$(".btn-food-s").removeClass("active");
					}
				}
				
				if (data.result.valuation.treatment.me) {
					if (data.result.valuation.treatment.me.val > 0) {
						$(".btn-treatment-s").addClass("active");
						$(".btn-treatment-p").removeClass("active");
					} else {
						$(".btn-treatment-p").addClass("active");
						$(".btn-treatment-s").removeClass("active");
					}
				}
				
				if (data.result.valuation.environment.me) {
					if (data.result.valuation.environment.me.val > 0) {
						$(".btn-environment-s").addClass("active");
						$(".btn-environment-p").removeClass("active");
					} else {
						$(".btn-environment-p").addClass("active");
						$(".btn-environment-s").removeClass("active");
					}
				}
			}
		});
	}
}

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

