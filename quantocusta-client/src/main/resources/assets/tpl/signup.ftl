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
							<div class="page-title">Sobre</div>
							<a href="/buscar" id="btn-search" class="btn btn-link pull-right"><i class="icon icon-search"></i></a>
						</div>
					</nav>
					
					<div class="breathe"></div>
					
					<div class="row">
						
						<div class="panel panel-default">
							<div class="panel-body">
								<h2>Sobre o <strong>Quanto Custa?</strong></h2>
								<p>Quanto Custa é um aplicativo gratuito em versão beta que vai ajudar a você a saber qual a média de preço de algum lugar que você tem interesse em ir. Assim você não terá uma surpresa na hora de pagar a conta e poderá aproveitar o máximo para se divertir.</p>
								<p>Os valores são informados pelos usuários. Então quanto mais usuários contribuírem mais próximo o valor será.</p>
								<p>Os locais são apenas relacionados à categoria de alimentação, por enquanto nao há como consultar locais como “Salão de beleza”.</p>
								<p>Esperamos em breve poder melhorar a sua experiência e contamos com a colaboração de todos para enriquecer nossa base de consultas.</p>
								<br>
								<p>
									<strong>Quer conversar conosco, deixar uma sugestão ou tirar uma dúvida?</strong><br>
									<strong><a href="mailto:contato@quantocusta.cc">contato@quantocusta.cc</a></strong>
								</p> 
							</div>
						</div>
					
					</div>
					
					<div class="breathe breathe-big"></div>
					
					<#include "/assets/tpl/components/modal.ftl">
			
				</div>
				
			</div>
		
		</div>

		<#include "/assets/tpl/components/scripts.ftl">
	</body>
</html>
