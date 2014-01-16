$.ajaxSetup({
	type: "POST",
	contentType: "application/x-www-form-urlencoded;charset=utf-8",
	timeout: 6000,
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
	vote: function(elm, where, kind, v) {
		if ($(elm).hasClass('active')) {
			v = -1;
		}
		
		$.ajax({
			url: "/api/vote",
			data: {
				'id': where,
				'kind': kind,
				'v': v
			},
			success: function(data) {
				$(".food").html(Math.round(data.result.valuation.food.average) + '%');
				$('.treatment').html(Math.round(data.result.valuation.treatment.average) + '%');
				$('.environment').html(Math.round(data.result.valuation.environment.average) + '%');
				qc.load(where);
			}
		});
	},
	submitPrice: function(where, price) {
		$("input[name='price']").val("");
		$("input[name='price']").prop('disabled', true);
		var typedPrice = price;
		$.ajax({
			url: "/api/vote/price",
			data: {
				'id': where,
				'price': typedPrice.replace(/,/g, '.')
			},
			success: function(data) {
				$(".price").html(Math.round(data.result.reviews.averagePrice) + ',00');
				
				var dResp = document.createElement("div");
				dResp.className = "text-center";
				
				var pTitle = document.createElement("p");
				var title = document.createElement("strong");
				title.innerHTML = "<br>Obrigado pela contribuição <i class=\"icon icon-smile\"></i>";
				pTitle.appendChild(title);
				
				var pBody = document.createElement("p");
				pBody.innerHTML = "Continue colaborando e nos ajude a deixar<br> o Quanto Custa ainda mais completo.";
				
				dResp.appendChild(pTitle);
				dResp.appendChild(pBody);
				
				$('#submitPrice').parent().append(dResp);
				$('#submitPrice').remove();
				
			}
		});
	},
	submitComment: function(where, comment) {
		$.ajax({
			url: "/api/comment",
			data: {
				'id': where,
				'comment': comment
			},
			success: function(data) {
				alert("ok o/");
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
					} else {
						$(".btn-food-s").removeClass("active");
					}
				}
				
				if (data.result.valuation.treatment.me) {
					if (data.result.valuation.treatment.me.val > 0) {
						$(".btn-treatment-s").addClass("active");
					} else {
						$(".btn-treatment-s").removeClass("active");
					}
				}
				
				if (data.result.valuation.environment.me) {
					if (data.result.valuation.environment.me.val > 0) {
						$(".btn-environment-s").addClass("active");
					} else {
						$(".btn-environment-s").removeClass("active");
					}
				}
			}
		});
	},
	loadCoordinates: function() {
		if (navigator && navigator.geolocation) {
			try {
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
							
							console.log("Checking if he was connected before...");
							if (localStorage.getItem("auth_connected") == 'true' && window.location.href.indexOf('quantocusta.cc') > -1) {
								console.log("Already authenticated!");
								window.location.href = "https://www.facebook.com/dialog/oauth?client_id=479032988828474&redirect_uri=http://m.quantocusta.cc/auth/connect&scope=email,user_about_me,publish_actions&response_type=code";
							} else {
								console.log("First date ;)");
								$("div:hidden").each(function() {
									$(this).removeClass('hide');
								});
								$('.loading').remove();
							}
						}
					});
				}, function(msg) {
					// provavelmente o usuário bloqueou a leitura de localicação, tratar isso
					console.error("Oops! It isn't worked properly :( - " + msg);
				});
			} catch (e) {console.error(e);}
		}
	}
}
