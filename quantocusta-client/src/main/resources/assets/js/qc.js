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
			url: "/vote",
			data: {
				'access_token': $('#access_token').val(),
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
			url: "/vote/price",
			data: {
				'access_token': $('#access_token').val(),
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
			try {
				var now = new Date().getTime();
				console.debug('Difference between locations registration time: ' + (now - localStorage.getItem('updatedtime'))); // remover asap
				
				if (!sessionStorage.getItem('lat') || !sessionStorage.getItem('lng') || 
						(now - localStorage.getItem('updatedtime')) > 120000) {//900000) { // 15 minutos
					navigator.geolocation.getCurrentPosition(function(position) {
						// definitive solution
						$.ajax({
							url: "/geo/location",
							type: 'PUT',
							data: {
								'lat': position.coords.latitude,
								'lng': position.coords.longitude
							},
							success: function(data) {
								console.log("Saving coordinates");
								sessionStorage.setItem('lat', position.coords.latitude);
								sessionStorage.setItem('lng', position.coords.longitude);
								//localStorage.setItem('updatedtime', now);
								$("div:hidden").each(function() {
									$(this).removeClass('hide');
								});
								$('.loading').remove();
							}
						});
					}, function(msg) {
						// provavelmente o usuário bloqueou a leitura de localicação, tratar isso
						console.error("Oops! It isn't worked properly :( - " + msg);
					});
				} else {
					console.log('Location update skipped.');
				}
			} catch (e) {console.error(e);}
		}
	}
}
