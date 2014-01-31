<ul class="nav nav-pills nav-stacked">
	<li style="padding: 15px; border-bottom: 0;"><img src="/assets/images/qc-logo.png"></li>
	<#if me??>
	<li><a href="/me"> <img src="http://graph.facebook.com/${me.thirdyId}/picture" class="img-circle"> ${me.name}</a></li>
	<#else>
	<li><a href="https://www.facebook.com/dialog/oauth?client_id=479032988828474&redirect_uri=http://m.quantocusta.cc/auth/connect&scope=email,user_about_me,publish_actions&response_type=code">Conecte-se</a></li>
	</#if>
	<li><a href="/sobre"><i class="icon icon-star"></i> Sobre</a></li>
	<li><a href="/sair"><i class=""></i> Sair</a></li>
</ul>