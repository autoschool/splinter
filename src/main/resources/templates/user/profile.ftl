<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Blog: show profile">
<div class="row">
    <div class="col-md-3">
        Name
    </div>
    <div class="col-md-9">
        ${profile.name}
    </div>
</div>
<div class="row">
    <div class="col-md-3">
        With Twister since
    </div>
    <div class="col-md-9">
        ${profile.createdAt}
    </div>
</div>
</@layout.layout>