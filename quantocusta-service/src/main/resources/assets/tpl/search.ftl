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
				<span>Termo de busca</span>
				<div class="row">
					<div class="col-xs-12">
						<div class="list-group">
							<#if venues??>
								<#list venues as venue>
									<a href="/thrd/${venue.idFoursquare}" class="list-group-item">${venue.name?html} <span class="pull-right"><i class="fa fa-chevron-right"></i></span></a>
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