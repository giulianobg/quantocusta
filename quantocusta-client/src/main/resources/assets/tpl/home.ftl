<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="description" content="">
		<meta name="author" content="">

		<title>Quanto Custa?</title>

		<!-- Bootstrap core CSS -->
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
				</div><!--/span-->
				
				<div class="col-xs-12">
				
					<#include "/assets/tpl/components/search.ftl">
					
					<div class="breathe"></div>
		
					<span>Termo de busca</span>
					<div class="row">
						<div class="col-xs-12">
							<div class="list-group">
								<#if venues??>
									<#list venues as venue>
										<a href="/local/thrd/${venue.idFoursquare}" class="list-group-item">
											<!-- <img class="img img-circle pull-left" src="http://placehold.it/40x40"> -->
											<span class="pull-left">
												${venue.name?html}<br>
												<small><#if venue.category??>${venue.category.name!""}</#if></small>
											</span>
											<span class="pull-right"><i class="icon-chevron-right"></i></span>
											<div class="clearfix"></div>
										</a>
									</#list>
								</#if>
							</div>
						</div>
					</div>
					
					<#include "/assets/tpl/components/footer.ftl">
					
				</div>
				
			</div>
			
		</div>

		<#include "/assets/tpl/components/scripts.ftl">
	</body>
</html>
