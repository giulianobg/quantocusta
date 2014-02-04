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
						<div class="overlay">
							<h1 class="text-hide">Quanto Custa?</h1>
							<br><br><br><br><br><br><br><br>
							<#include "/assets/tpl/components/loading.ftl">
							<div class="hide">
								<div class="row">
									<div class="col-xs-12">
										<a href="https://www.facebook.com/dialog/oauth?client_id=479032988828474&redirect_uri=http://${params.callbackHost}.quantocusta.cc/auth/connect&scope=email,user_about_me,publish_actions&response_type=code" class="btn btn-default btn-lg btn-block facebook"><i class="icon icon-facebook"></i> Conecte-se com o Facebook</a><br>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12"><a href="/discovery" class="btn btn-link btn-block">Descubra os melhores lugares para comer</a></div>
								</div>
								<!-- 
								<div class="row">
									<div class="col-xs-6"><a href="#" class="btn btn-inverse btn-lg btn-block">Conecte-se</a></div>
									<div class="col-xs-6"><a href="#" class="btn btn-inverse btn-lg btn-block">Inscreva-se</a></div>
								</div>
								-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<#include "/assets/tpl/components/scripts.ftl">
		<script>
			$(document).ready(function() {
				<#if params.logout?? && params.logout == 'true'>
				localStorage.setItem('auth_connected', 'false');
				</#if>
				qc.loadCoordinates();
			});
		</script>
	</body>
</html>
