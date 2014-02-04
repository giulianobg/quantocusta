<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Quanto Custa?</title>

		<!-- Bootstrap core CSS -->
		<link href="/assets/css/bootstrap.min.css" rel="stylesheet">

		<!-- Add custom CSS here -->
		<link href="/assets/css/starving-3.css" rel="stylesheet">
		<link href='http://fonts.googleapis.com/css?family=Raleway:400,200,300' rel='stylesheet' type='text/css'>
		
		<link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.1/leaflet.css">
	</head>

	<body>
		
		<div class="container">
	
			<div class="row row-offcanvas row-offcanvas-left">
			
				<div class="col-xs-9 sidebar-offcanvas" id="sidebar" role="navigation">
					<#include "/assets/tpl/components/nav.ftl">
				</div><!--/span-->
				
				<div class="col-xs-12">
				
					<nav class="navbar navbar-inverse navbar-fixed-top st-nav" role="navigation">
						<div class="navbar-header">
							<div class="navbar-text"><a href="#menu" data-toggle="offcanvas" class="btn btn-link navbar-link"><i class="icon icon-bar"></i></a></div>
							<div class="navbar-center">
								<form class="form-search" action="/buscar">
									<div class="input-group">
										<input type="text" name="q" placeholder="Restaurantes, bares, cafés..." required class="form-control">
										<div class="input-group-btn">
											<button type="submit" class="btn btn-default"><i class="icon icon-search"></i></button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</nav>
					
					<div class="breathe"></div>
		
					<div class="row">
						<div class="col-xs-12">
						
							<div id="map"></div>
						
							<div class="panel panel-info">
								<div class="panel-heading">Locais próximos</div>
								<div class="panel-body">
									<div id="data-result" class="list-group">
										<#include "/assets/tpl/components/loading.ftl">
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</div>
				
			</div>
			
			<div class="breathe breathe-big"></div>
			
		</div>

		<#include "/assets/tpl/components/scripts.ftl">
		<script>
			$(document).ready(function() {
				var map = L.map('map').setView([${request.session.getAttribute('lat')}, ${request.session.getAttribute('lng')}], 13);
				
				L.tileLayer('http://{s}.tile.cloudmade.com/5e92db154a5541809618b12d82e2edbb/997/256/{z}/{x}/{y}.png?token=?token=d3c898f7ef6f494b913353df4fef7cee', {
					maxZoom: 18,
					minZoom: 10,
					attribution: ''
				}).addTo(map);
				
				localStorage.setItem('auth_connected', 'true');
				
				$.ajax({
					url: "/api/near",
					type: "GET",
					timeout: 15000,
					success: function(data) {
						var list = $("#data-result");

						for (var i in data.result) {
							var venue = data.result[i];

							var item = document.createElement("a");
							item.href = "/local/" + (venue._id ? venue._id : "thrd/" + venue.id_foursquare);
							item.className = "list-group-item";
							
							var title = document.createElement("span");
							title.className = "pull-left";
							
							var category = document.createElement("span");
							category.innerHTML = venue.category ? venue.category.name : "";
							
							title.innerHTML = venue.name + "<br>";
							title.appendChild(category);
							
							var price = document.createElement("span");
							price.className = "pull-right price";
							if (venue.reviews.averagePrice > 0) {
								price.innerHTML = "<small>R$</small> " + Math.round(venue.reviews.averagePrice) + ",00";
							}
							
							item.appendChild(title);
							//item.appendChild(document.createElement("br"));
							//item.appendChild(category);
							item.appendChild(price);
							
							var clearfix = document.createElement("div");
							clearfix.className = "clearfix";
							item.appendChild(clearfix);
							
							// map marker
							if (venue.lat && venue.lng) {
								L.marker([venue.lat, venue.lng]).addTo(map).bindPopup("<b>" + venue.name + "</b>");
							}
							
							$(".loading").remove();
							$(list).append(item);
						}
					}
				});
			});
		</script>
	</body>
</html>
