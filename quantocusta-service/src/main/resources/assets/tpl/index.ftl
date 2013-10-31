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
		<#include "/assets/tpl/components/header.ftl">
		
		<div class="breathe"></div>
		
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<form action="/buscar" role="form" class="form-horizontal" method="get">
						<div class="form-group">
							<div class="col-lg-12">
								Buscar por
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<input type="search" class="form-control input-lg" name="q" placeholder="Restaurantes, bares, delicatessen">
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<button type="submit" class="btn btn-primary btn-lg btn-block">Buscar</button><br>
							</div>
						</div>
					</form>
					
					
					
					<a class="btn btn-block btn-default" href="https://www.facebook.com/dialog/oauth?client_id=479032988828474&redirect_uri=http://m.quantocusta.cc/auth/connect&scope=email,user_about_me,publish_actions&response_type=code">
						Conectar-se com o Facebook
					</a>
				</div>
			</div>
		</div>

		<#include "/assets/tpl/components/footer.ftl">

		<#include "/assets/tpl/components/scripts.ftl">
	</body>
</html>