<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Blog: show profile">
<div class="container">
	<div class="row panel">
        <div class="col-md-12  col-xs-12">
           <div class="header">
                <h1>${profile.name}</h1>
                <span>With Twister since ${profile.createdAt?string["dd.MM.yyyy"]}</span><br><br>
           </div>
        </div>
    </div>
</div>
</@layout.layout>