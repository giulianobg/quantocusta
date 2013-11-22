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
	},
	loadCoordinates: function() {
		if (navigator && navigator.geolocation) {
			//try {
			//if (!localStorage.getItem('lat') || !localStorage.getItem('lng')) {
			navigator.geolocation.getCurrentPosition(function(position) {
				// temporary solution
				$("input[name='lat']").val(position.coords.latitude);
				$("input[name='lng']").val(position.coords.longitude);
				
				// definitive solution
				$.ajax({
					url: "/geo/location",
					type: 'PUT',
					data: {
						'lat': position.coords.latitude,
						'lng': position.coords.longitude
					}
				});
			}, function() {
			});
			//}
			//} catch (e) {
			//}
		}
	}
}
