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
		
		<style type="text/css">
			#map {height: 200px; margin: -10px; z-index: 0;}
		</style>
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
							<a href="#menu" class="pull-left btn btn-link" data-toggle="offcanvas"><i class="icon-bar"></i></a>
							<form class="form-search pull-left" action="/buscar">
								<div class="input-group">
									<input type="text" name="q" placeholder="Restaurantes, bares, cafés..." required class="form-control">
									<div class="input-group-btn">
										<button type="submit" class="btn btn-default"><i class="icon icon-search"></i></button>
									</div>
								</div>
							</form>
						</div>
					</nav>
					
					<div class="breathe"></div>
		
					<div class="row">
						<div class="col-xs-12">
						
							<div id="map"></div>
						
							<div class="list-group">
								<#if venues??>
									<#list venues as venue>
										<a href="/local/thrd/${venue.idFoursquare}" class="list-group-item">
											<span class="pull-left">${venue.name?html}</span><br>
											<small class="pull-left"><#if venue.category??>${venue.category.name!""}</#if></small>
											<!-- <span class="price pull-right"><small>R$</small> 58</span> -->
											<div class="clearfix"></div>
										</a>
									</#list>
								</#if>
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
			});
		</script>
	</body>
</html>
