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
							<a href="/me" data-transition="slide" data-rel="back" class="pull-left btn btn-link"><i class="icon icon-chevron-left-o"></i></a>
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
							<div class="list-group">
								<#if venues??>
									<#list venues as venue>
										<a href="/local/thrd/${venue.idFoursquare}" class="list-group-item">
											<!-- <img class="img img-circle pull-left" src="http://placehold.it/40x40"> -->
											<span class="pull-left">
												${venue.name?html}<br>
												<small><#if venue.category??>${venue.category.name!""}</#if></small>
											</span>
											<span class="pull-right"><i class="icon-chevron-right"></i></span>
											<div class="clearfix"></div>
										</a>
									</#list>
								</#if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="breathe breathe-big"></div>

		<#include "/assets/tpl/components/scripts.ftl">
	</body>
</html>