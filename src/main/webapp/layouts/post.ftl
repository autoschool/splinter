<#-- @ftlvariable name="post" type="ru.yandex.autoschool.splinter.models.Post" -->
<section class="post">
    <header class="entry-header">
    <#--<img class="entry-avatar" alt="Paul Laros" height="52" width="52" src="http://www.gravatar.com/avatar/ea67e860739cd91fe40b6797e97e6820.jpg?s=52">-->
        <h2 class="entry-title"><a href="/posts/${post.id}">${post.title}</a></h2>
        <p class="entry-meta">
            Posted on <a class="entry-date" href="#">${post.createdAt}</a> | By <a class="entry-author" href="/profile/1">Paul Gilbert</a><#-- | Tags <a class="label label-danger" href="tag.html">CSS3</a>-->
        </p>
    </header>
    <div class="entry-description">
        <p>
        (post.content)
        </p>
    </div>
</section>
