<#-- @ftlvariable name="model" type="ru.yandex.autoschool.splinter.models.Post" -->
<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Blog: show post">
<h1>All posts</h1>
<div id="posts">
    <#list model as post>
        <section class="post">
            <header class="entry-header">
                <#--<img class="entry-avatar" alt="Paul Laros" height="52" width="52" src="http://www.gravatar.com/avatar/ea67e860739cd91fe40b6797e97e6820.jpg?s=52">-->
                <h2 class="entry-title"><a href="/posts/${post.id}">${post.title}</a></h2>
                <p class="entry-meta">
                    Posted on <a class="entry-date" href="date.html">${post.created_at}</a> | By <a class="entry-author" href="/profile/1">Paul Gilbert</a><#-- | Tags <a class="label label-danger" href="tag.html">CSS3</a>-->
                </p>
            </header>
            <div class="entry-description">
                <p>
                    ${post.content}
                </p>
            </div>
        </section>
    </#list>
</div>

<#--<ul class="pagination pagination-md">-->
    <#--<li class="disabled"><a href="#">«</a></li>-->
    <#--<li class="active"><a href="#">1</a></li>-->
    <#--<li><a href="#">2</a></li>-->
    <#--<li><a href="#">3</a></li>-->
    <#--<li><a href="#">4</a></li>-->
    <#--<li><a href="#">5</a></li>-->
    <#--<li><a href="#">»</a></li>-->
<#--</ul>-->

</@layout.layout>
