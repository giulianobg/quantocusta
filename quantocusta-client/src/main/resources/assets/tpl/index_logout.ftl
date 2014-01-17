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
		
			<div class="row">
				<div class="col-xs-12">
					 <div class="container-bg">
						<img class="img" width="320" src="/assets/images/background.png">
						
						<div class="overlay">
							<h1 style="text-indent: -9999px;">Quanto Custa?</h1>
							<br><br><br><br><br><br><br><br><br>
							<div class="loading">
								<div id="circleG" title="Carregando conteúdo...">
									<div id="circleG_1" class="circleG"></div>
									<div id="circleG_2" class="circleG"></div>
									<div id="circleG_3" class="circleG"></div>
								</div>
							</div>
							<div class="hide">
								<a href="https://www.facebook.com/dialog/oauth?client_id=479032988828474&redirect_uri=http://m.quantocusta.cc/auth/connect&scope=email,user_about_me,publish_actions&response_type=code" class="btn btn-default btn-lg btn-block facebook"><i class="icon-facebook"></i> Conecte-se com o Facebook</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<#include "/assets/tpl/components/scripts.ftl">
		<script>
			$(document).ready(function() {
				localStorage.setItem('auth_connected', 'false');
				qc.loadCoordinates();
			});
		</script>
	</body>
</html>
