<#-- @ftlvariable name="model" type="ru.yandex.autoschool.splinter.models.Post" -->
<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Blog: show post">
<h1>All posts</h1>
<div id="posts">
    <#list model as post>
        <#include "/layouts/post.ftl">
    </#list>
</div>


<#include "/layouts/pagination.ftl">
<@pages 1..pagination.totalPages pagination.currentPage pagination.linkUrl />

</@layout.layout>
