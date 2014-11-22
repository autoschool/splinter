<#-- @ftlvariable name="model" type="ru.yandex.autoschool.splinter.models.Post[]" -->
<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Blog: posts">
<div class="row">
    <div class="col-md-12">
        <div class="page-header">
            <h2>Latest updates</h2>
        </div>
        <#list model as post>
            <#include "/layouts/post.ftl">
        </#list>
    </div>
</div>
</@layout.layout>