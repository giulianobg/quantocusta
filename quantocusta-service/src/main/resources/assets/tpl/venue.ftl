<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Quanto Custa?</title>
		
		<#ionclude

		<!-- Bootstrap core CSS -->
		<link href="/assets/css/bootstrap.css" rel="stylesheet">

		<!-- Add custom CSS here -->
		<link href="/assets/css/starving-3.css" rel="stylesheet">
		<link href="/assets/css/font-awesome.min.css" rel="stylesheet">
		<link href="/assets/css/whhg.css" rel="stylesheet">
		<link href='http://fonts.googleapis.com/css?family=Raleway:400,200,300' rel='stylesheet' type='text/css'>
	</head>

	<body>
		<#include "/assets/tpl/components/header.ftl">
		
		<div class="section">
			<div class="container">
				<h1>${venue.name}</h1>
				<address>
					<i class="fa fa-map-marker"></i> ${venue.address!""}<br>
					${venue.city.name!""} ${venue.city.state!""}
				</address>
			
				<!-- img class="img img-responsive" src="http://placehold.it/480x240/ccdd55" -->

				
				<div class="row">
					<#if venue.averagePrice??>
					<div class="col-xs-12">
						<div class="panel panel-default">
							<div class="panel-body text-center">
								<span class="fa-3x"><i class="icon-salealt"></i>
								${venue.averagePrice}</span><br>
								<small>reais por pessoa</small>
							</div>
						</div>
					</div>
					</#if>
					
					<div class="col-xs-12">
						<h3>Avalie esse local</h3>
						<div class="list-group">
							<div class="list-group-item">
								<strong><i class="icon-soup fa-lg"></i> Comida</strong>
								
								<span class="pull-right">
									<button type="button" class="btn btn-default" onclick="qc.vote('${venue.id}', 'food', 1);">
										<i class="fa fa-smile-o"></i> <span class="text-success">0%</span>
									</button> 
									<button type="button" class="btn btn-default" onclick="qc.vote('${venue.id}', 'food', -1);">
										<i class="fa fa-frown-o"></i> <span class="text-danger">0%</span>
									</button>
									
								</span>
								<div class="clearfix"></div>
							</div>
							<div class="list-group-item">
								<strong><i class="icon-foodtray fa-lg"></i> Atendimento</strong>
								
								<span class="pull-right">
									<button type="button" class="btn btn-default" onclick="qc.vote('${venue.id}', 'treatment', 1);">
										<i class="fa fa-smile-o"></i> <span class="text-success">0%</span>
									</button> 
									<button type="button" class="btn btn-default" onclick="qc.vote('${venue.id}', 'treatment', -1);">
										<i class="fa fa-frown-o"></i> <span class="text-danger">0%</span>
									</button>
								</span>
								<div class="clearfix"></div>
							</div>
							<div class="list-group-item">
								<strong><i class="icon-store fa-lg"></i> Ambiente</strong>

								<span class="pull-right">
									<button type="button" class="btn btn-default" onclick="qc.vote('${venue.id}', 'environment', 1);">
										<i class="fa fa-smile-o"></i> <span class="text-success">0%</span>
									</button> 
									
									<button type="button" class="btn btn-default" onclick="qc.vote('${venue.id}', 'environment', -1);">
										<i class="fa fa-frown-o"></i> <span class="text-danger">0%</span>
									</button>
								</span>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					
					
					
					
					<!--  
					<div class="col-xs-12">
						<h3>Andam falando...</h3>
						
						<div class="panel panel-default">
							<strong>Fulano de tal</strong>
							<p>COmentárioas oaks da sd asd asd asd ads</p>
						</div>
					
						<div class="panel panel-default">
							<strong>Fulano de tal</strong>
							<p>COmentárioas oaks da sd asd asd asd ads</p>
						</div>
					
						<div class="panel panel-default">
							<strong>Fulano de tal</strong>
							<p>COmentárioas oaks da sd asd asd asd ads</p>
						</div>
					</div>
					-->
				</div>
				
			</div>
		</div>

		<#include "/assets/tpl/components/footer.ftl">

		<#include "/assets/tpl/components/scripts.ftl">
	</body>
</html>