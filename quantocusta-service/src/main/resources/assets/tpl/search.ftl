<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Quanto Custa?</title>

		<!-- Bootstrap core CSS -->
		<link href="/assets/css/bootstrap.css" rel="stylesheet">

		<!-- Add custom CSS here -->
		<link href="/assets/css/starving-3.css" rel="stylesheet">
		<link href="/assets/css/font-awesome.min.css" rel="stylesheet">
		<link href="/assets/css/whhg.css" rel="stylesheet">
		<link href='http://fonts.googleapis.com/css?family=Raleway:400,200,300' rel='stylesheet' type='text/css'>
	</head>

	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top st-nav" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a href="javascript:window.history.back()" class="pull-left"><i class="fa fa-arrow-circle-o-left"></i></a>
					<form class="form-search pull-left" action="/buscar">
						<input type="text" name="q" placeholder="Restaurantes, bares, caf&eacute;s..." required>
						<button type="submit"><i class="icon-search"></i></button>
						<div class="clearfix"></div>
					</form>
				</div>
			</div>
		</nav>
		
		<div class="breathe"></div>
		
		<div class="section">
			<div class="container">
				<span>Termo de busca</span>
				<div class="row">
					<div class="col-xs-12">
						<div class="list-group">
							<#if venues??>
								<#list venues as venue>
									<a href="/thrd/${venue.idFoursquare}" class="list-group-item">
										<img class="img img-circle pull-left" src="http://placehold.it/40x40">
										<span class="pull-left">
											${venue.name?html}<br>
											<small><#if venue.category??>${venue.category.name!""}</#if></small>
										</span>
										<span class="pull-right"><i class="fa fa-chevron-right"></i></span>
										<div class="clearfix"></div>
									</a>
								</#list>
							</#if>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<#include "/assets/tpl/components/footer.ftl">

		<#include "/assets/tpl/components/scripts.ftl">
	</body>
</html>