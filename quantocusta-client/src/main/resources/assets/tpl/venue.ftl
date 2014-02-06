<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, target-densityDpi=device-dpi" />
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
							<div class="navbar-text"><a href="/me" class="btn btn-link navbar-link"><i class="icon icon-chevron-left-o"></i></a></div>
							<div class="navbar-center">
								<div class="page-title">${venue.name}</div>
							</div>
							<div class="navbar-text navbar-right"><a href="/buscar" id="btn-search" class="btn btn-link navbar-link"><i class="icon icon-search"></i></a></div>
						</div>
					</nav>
					
					<div class="breathe"></div>
					
					<div class="section">
						<img class="img img-venue" src="/assets/images/no-image.gif" width="320" >
					</div>
					
					<div class="row">
						<div class="col-xs-12">
							<div class="panel panel-primary">
								<div class="panel-body">
									<h1>${venue.name}</h1>
									<strong><#if venue.category??>${venue.category.name!""}</#if></strong>
									<address>
										<#if venue.address??><i class="icon icon-pin-marker"></i> ${venue.address!""}<br></#if>
										<#if venue.site??><i class="icon icon-world"></i> ${venue.site!""}<br></#if>
										<#if venue.phone??><i class="icon icon-phone"></i> ${venue.phone!""}</#if>
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
												<i class="icon icon-soup fa-lg"></i> <span class="val food">${venue.valuation.food.average?string("")}%</span>
											</div>
											<div class="summary col-xs-4 text-center">
												<span class="markup markup-blue"></span>
												Atendimento<br>
												<i class="icon icon-foodtray fa-lg"></i> <span class="val treatment">${venue.valuation.treatment.average?string("")}%</span>
											</div>
											<div class="summary col-xs-4 text-center">
												<span class="markup markup-yellow"></span>
												Ambiente<br>
												<i class="icon icon-store fa-lg"></i> <span class="val environment">${venue.valuation.environment.average?string("")}%</span> 
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-xs-12">
							<div class="panel panel-primary">
								<div class="panel-heading">Quanto custa?</div>
								<div class="panel-body">
									<br>
									<form id="submitPrice" class="form-horizontal" role="form" action="/api/vote/price" method="post">
										<input type="hidden" name="where" value="${venue.id}">
										<br>
										<div class="form-group">
											<div class="col-xs-8 col-xs-offset-2">
												<div class="input-group input-group-lg">
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
										
										<p>Já foi? Quanto pagou?</p>
										<p>Se não lembrar o valor exato, informe um valor aproximado.</p>
									</form>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-xs-12">
							<div class="panel panel-primary">
								<div class="panel-heading">Do que mais gostou?</div>
								<div class="panel-body">
									<div class="list-group">
										<div class="list-group-item">
											<i class="icon icon-soup fa-2x"></i> <span>Comida</span>
											<div class="btn-group pull-right">
												<button type="button" class="btn btn-default btn-food-s" onclick="qc.vote(this, '${venue.id}', 'food', 1);"><i class="icon icon-heart fa-lg"></i></button>
											</div>
											<div class="clearfix"></div>
										</div>
										<div class="list-group-item">
											<i class="icon icon-foodtray fa-2x"></i> <span>Atendimento</span>
											<div class="btn-group pull-right">
												<button type="button" class="btn btn-default btn-treatment-s" onclick="qc.vote(this, '${venue.id}', 'treatment', 1);"><i class="icon icon-heart fa-lg"></i></button>
											</div>
											<div class="clearfix"></div>
										</div>
										<div class="list-group-item">
											<i class="icon icon-store fa-2x"></i> <span>Ambiente</span>
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
							<div class="panel panel-primary">
								<div class="panel-heading">Comentários <span class="badge">0</span></div>
								<div class="panel-body">
									<ul id="comments">
									<#if venue.comments??>
									<#list venue.comments as comment>
										<li>
											<img src="http://graph.facebook.com/${comment.userInstance.thirdyId}/picture"> ${comment.userInstance.name}<br>
											<p>${comment.comment}</p>	
										</li>
									</#list>
									</#if>
									</ul>
									
									<hr>
									
									<form id="submitComment" class="form-horizontal" role="form" action="/api/comment" method="post">
										<input type="hidden" name="where" value="${venue.id}">
										<div class="form-group">
											<div class="col-xs-12">
												<textarea rows="4" name="comment" placeholder="Escreva um comentário sobre esse local..."></textarea>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
						-->
					</div>
					
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
			});
			
			$("#submitComment").submit(function(event) {
				// Stop form from submitting normally
				event.preventDefault();
				
				// Get some values from elements on the page:
				var $form = $(this),
					where = $form.find("input[name='where']").val(),
					comment = $form.find("textarea[name='comment']").val(),
					url = $form.attr("action");
			 
				// Send the data using post
				qc.submitComment(where, comment);
			});
		</script>
	</body>
</html>
