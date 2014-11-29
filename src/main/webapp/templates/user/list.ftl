<#-- @ftlvariable name="model" type="ru.yandex.autoschool.splinter.models.User" -->
<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Blog: authors list">
<h1>Authors</h1>
<div id="authors">
    <#list users as user>
        <section class="author">
            <header class="entry-header">
            <img class="entry-avatar" alt="Paul Laros" height="52" width="52" src="http://www.gravatar.com/avatar/ea67e860739cd91fe40b6797e97e6820.jpg?s=52">
                <h2 class="entry-title"><a href="/users/${user.id}">${user.name} ${user.sirname}</a></h2>
            </header>
            <div class="entry-description">
                <p>
                ${user.password}
                </p>
            </div>
        </section>
    </#list>
</div>


    <#--<#include "/layouts/pagination.ftl">-->
    <#--<@pages 1..pagination.totalPages pagination.currentPage pagination.linkUrl />-->

</@layout.layout>
