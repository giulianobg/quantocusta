<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Quanto Custa?</title>
		
		<!-- Bootstrap core and jQuery Mobile CSS -->
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
				</div>
				
				<div class="col-xs-12">
				
					<nav class="navbar navbar-inverse navbar-fixed-top st-nav" role="navigation">
						<div class="navbar-header">
							<a href="#voltar" data-transition="slide" data-rel="back" class="pull-left btn btn-link"><i class="icon-arrow-circle-left"></i></a>
							<form class="form-search pull-left" action="/buscar">
								<input type="text" name="q" placeholder="Restaurantes, bares, caf&eacute;s..." required>
								<button type="submit"><i class="icon-search"></i></button>
								<div class="clearfix"></div>
							</form>
						</div>
					</nav>
					
					<div class="breathe"></div>
					
					<div class="panel panel-default">
						<div class="panel-body">
							<img class="img img-responsive" src="/assets/images/no-image.png">
							<h1>${venue.name}</h1>
							<strong><#if venue.category??>${venue.category.name!""}</#if></strong>
							<address>
								<#if venue.address??><i class="icon-pin-marker"></i> ${venue.address!""}<br></#if>
								<#if venue.site??><i class="icon-world"></i> ${venue.site!""}<br></#if>
								<#if venue.phone??><i class="icon-phone"></i> ${venue.phone!""}</#if>
							</address>
						</div>
					</div>
					
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="section-price">
								<span class="sign">R$</span>
								<div class="text-center"><span class="price"><#if venue.reviews.averagePrice &gt; 0>${venue.reviews.averagePrice?string("0")},00<#else>--</#if></span></div>
								<small class="info pull-right">por pessoa</small>
							</div>
							
							<div class="row">
								<div class="summary col-xs-4 text-center">
									<span class="markup markup-green"></span>
									Comida<br>
									<i class="icon-soup fa-lg"></i> <span class="val food">${venue.valuation.food.smileAverage?string("0")}%</span>
								</div>
								<div class="summary col-xs-4 text-center">
									<span class="markup markup-blue"></span>
									Atendimento<br>
									<i class="icon-foodtray fa-lg"></i> <span class="val treatment">${venue.valuation.treatment.smileAverage?string("0")}%</span>
								</div>
								<div class="summary col-xs-4 text-center">
									<span class="markup markup-yellow"></span>
									Ambiente<br>
									<i class="icon-store fa-lg"></i> <span class="val environment">${venue.valuation.environment.smileAverage?string("0")}%</span> 
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="panel panel-info">
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-12">
									<form id="submitPrice" class="form-horizontal" role="form" action="/api/vote/price" method="post">
										<input type="hidden" name="where" value="${venue.id}">
										
										<div class="form-group col-xs-12 text-center">
											<span>J&aacute; fui e paguei</span>
										</div>
										
										<div class="form-group">
											<label class="col-xs-3 text-right" style="font-size: 32px;">R$</label>
											
											<div class="col-xs-8 text-right">
												<div class="input-group input-group-lg">
													<input type="text" name="price" class="form-control">
													<span class="input-group-btn">
														<button class="btn btn-default" type="submit"><i class="icon-thumbs-up"></i></button>
													</span>
												</div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-xs-12 text-right">
												<small>por pessoa</small>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				
					<div class="row">
						<div class="col-xs-12">
							<h4>Avalie</h4>
							<div class="list-group">
								<div class="list-group-item">
									<i class="icon-soup fa-2x"></i> <span>Comida</span>
									<div class="btn-group pull-right">
										<button type="button" class="btn btn-inverse btn-food-s" onclick="qc.vote('${venue.id}', 'food', 1);"><i class="icon-smile fa-2x"></i></button>
										<button type="button" class="btn btn-inverse btn-food-p" onclick="qc.vote('${venue.id}', 'food', -1);"><i class="icon-frown fa-2x"></i></button>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="list-group-item">
									<i class="icon-foodtray fa-2x"></i> <span>Atendimento</span>
									<div class="btn-group pull-right">
										<button type="button" class="btn btn-inverse btn-treatment-s" onclick="qc.vote('${venue.id}', 'treatment', 1);"><i class="icon-smile fa-2x"></i></button>
										<button type="button" class="btn btn-inverse btn-treatment-p" onclick="qc.vote('${venue.id}', 'treatment', -1);"><i class="icon-frown fa-2x"></i></button>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="list-group-item">
									<i class="icon-store fa-2x"></i> <span>Ambiente</span>
									<div class="btn-group pull-right">
										<button type="button" class="btn btn-inverse btn-environment-s" onclick="qc.vote('${venue.id}', 'environment', 1);"><i class="icon-smile fa-2x"></i></button>
										<button type="button" class="btn btn-inverse btn-environment-p" onclick="qc.vote('${venue.id}', 'environment', -1);"><i class="icon-frown fa-2x"></i></button>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>		
					
					</div>
					
					<#include "/assets/tpl/components/modal.ftl">
			
				</div>
				
			</div>
		
		</div>

		<#include "/assets/tpl/components/scripts.ftl">
		<script type="text/javascript">
			$(document).ready(function() {
				qc.load($("#submitPrice").find("input[name='where']").val());
			});
			
			$("#submitPrice").submit(function(event) {
				// Stop form from submitting normally
				event.preventDefault();
				
				// Get some values from elements on the page:
				var $form = $(this),
					where = $form.find("input[name='where']").val(),
					price = $form.find("input[name='price']").val(),
					url = $form.attr("action");
			 
				// Send the data using post
				qc.submitPrice(where, price);
			 
				// Put the results in a div
				//posting.done(function( data ) {
				//	var content = $( data ).find( "#content" );
				//	$( "#result" ).empty().append( content );
				//});
			});
		</script>
	</body>
</html>