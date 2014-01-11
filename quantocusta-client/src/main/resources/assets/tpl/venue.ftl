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
							<a href="/me" data-transition="slide" data-rel="back" class="pull-left btn btn-link"><i class="icon icon-chevron-left-o"></i></a>
							<div class="page-title">
								${venue.name}
							</div>
							<!-- 
							<form class="form-search pull-left hide" action="/buscar" style="display: block !important;">
								<input type="text" name="q" placeholder="Restaurantes, bares, caf&eacute;s..." required>
								<button type="submit"><i class="icon icon-search"></i></button>
								<div class="clearfix"></div>
							</form>
							-->
							<a href="/buscar" id="btn-search" class="btn btn-link pull-right"><i class="icon icon-search"></i></a>
						</div>
					</nav>
					
					<div class="breathe"></div>
					
					<div class="section">
						<img class="img" src="/assets/images/no-image.gif" width="330" >
					</div>
					
					<div class="row">
						<div class="col-xs-12">
							<div class="panel panel-default">
								<div class="panel-body">
									<h1>${venue.name}</h1>
									<strong><#if venue.category??>${venue.category.name!""}</#if></strong>
									<address>
										<#if venue.address??><i class="icon-pin-marker"></i> ${venue.address!""}<br></#if>
										<#if venue.site??><i class="icon-world"></i> ${venue.site!""}<br></#if>
										<#if venue.phone??><i class="icon-phone"></i> ${venue.phone!""}</#if>
									</address>
									
									<div class="section-price">
										<div class="text-center">
											<span class="sign">R$</span>
											<span class="price"><#if venue.reviews.averagePrice &gt; 0>${venue.reviews.averagePrice?string("0")},00<#else>--</#if></span><br>
											<small class="info">por pessoa</small>
										</div>
										<br>
										<div class="row">
											<div class="summary col-xs-4 text-center">
												<span class="markup markup-green"></span>
												Comida<br>
												<i class="icon-soup fa-lg"></i> <span class="val food">${venue.valuation.food.average?string("")}%</span>
											</div>
											<div class="summary col-xs-4 text-center">
												<span class="markup markup-blue"></span>
												Atendimento<br>
												<i class="icon-foodtray fa-lg"></i> <span class="val treatment">${venue.valuation.treatment.average?string("")}%</span>
											</div>
											<div class="summary col-xs-4 text-center">
												<span class="markup markup-yellow"></span>
												Ambiente<br>
												<i class="icon-store fa-lg"></i> <span class="val environment">${venue.valuation.environment.average?string("")}%</span> 
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-xs-12">
							<div class="panel panel-default">
								<div class="panel-heading">Quanto custa?</div>
								<div class="panel-body">
									<form id="submitPrice" class="form-horizontal" role="form" action="/api/vote/price" method="post">
										<input type="hidden" name="where" value="${venue.id}">
										<div class="form-group">
											<div class="col-xs-8 col-xs-offset-2">
												<div class="input-group">
													<span class="input-group-addon">R$</span>
													<input type="text" name="price" class="form-control input-lg">
												</div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="col-xs-8 col-xs-offset-2 text-right">
												<small>por pessoa</small>
											</div>
										</div>
										
										<hr>
										
										<p>
											Já foi? Quanto pagou?
										</p>
										<p>
											Se não lembrar o valor exato, informe um valor aproximado. 
										</p>
									</form>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-xs-12">
							<div class="panel panel-default">
								<div class="panel-heading">Do que mais gostou?</div>
								<div class="panel-body">
									<div class="list-group">
										<div class="list-group-item">
											<i class="icon-soup fa-2x"></i> <span>Comida</span>
											<div class="btn-group pull-right">
												<button type="button" class="btn btn-default btn-food-s" onclick="qc.vote(this, '${venue.id}', 'food', 1);"><i class="icon icon-heart fa-lg"></i></button>
											</div>
											<div class="clearfix"></div>
										</div>
										<div class="list-group-item">
											<i class="icon-foodtray fa-2x"></i> <span>Atendimento</span>
											<div class="btn-group pull-right">
												<button type="button" class="btn btn-default btn-treatment-s" onclick="qc.vote(this, '${venue.id}', 'treatment', 1);"><i class="icon icon-heart fa-lg"></i></button>
											</div>
											<div class="clearfix"></div>
										</div>
										<div class="list-group-item">
											<i class="icon-store fa-2x"></i> <span>Ambiente</span>
											<div class="btn-group pull-right">
												<button type="button" class="btn btn-default btn-environment-s" onclick="qc.vote(this, '${venue.id}', 'environment', 1);"><i class="icon icon-heart fa-lg"></i></button>
											</div>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					
					</div>
					<!-- 
					<div class="row">
						<div class="col-xs-12">
							<div class="panel panel-default">
								<div class="panel-heading">Comentários <span class="badge">0</span></div>
								<div class="panel-body">
									
								</div>
							</div>
						</div>
					
					</div>
					-->
					
					<div class="breathe breathe-big"></div>
					
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
