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
		
		<div class="section">
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
									<input type="search" class="form-control input-lg" name="q">
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-12">
									<button type="submit" class="btn btn-primary btn-lg btn-block">Buscar</button><br>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<#include "/assets/tpl/components/footer.ftl">

		<!-- Bootstrap core JavaScript -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="/assets/js/jquery-1.10.2.min.js"></script>
		<script src="/assets/js/bootstrap.min.js"></script>
	</body>
</html>