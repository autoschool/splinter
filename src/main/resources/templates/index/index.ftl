<#-- @ftlvariable name="model" type="ru.yandex.autoschool.splinter.models.Post[]" -->
<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Blog: posts">
<div class="row">
    <div class="col-md-12">
        <div class="page-header">
            <h2>Latest updates</h2>
        </div>

        <#if model?size=0>
            У нас пусто. Будьте первым, добавьте <a href="/posts/new">пост</a>.
        </#if>

        <#list model as post>
            <#include "/layouts/post.ftl">
        </#list>
    </div>
</div>
</@layout.layout>
