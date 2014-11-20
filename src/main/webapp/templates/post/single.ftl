<#-- @ftlvariable name="model" type="ru.yandex.autoschool.splinter.models.Post" -->
<#-- @ftlvariable name="comment" type="ru.yandex.autoschool.splinter.models.Comment" -->
<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Blog: show post">
<div class="row">
    <div class="col-md-12">
        <div class="page-header">
            <h2>${model.title}</h2>
        </div>
        <div class="post-body">
        ${model.content}
        </div>

        <div class="col-md-12">
            <h3>Comments</h3>
            <form class="form" role="form" action="/posts/${model.id}/comment" method="POST">

                <div class="form-group">
                    <textarea class="form-control" rows="5" name="content" placeholder="comment"></textarea>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-danger pull-right">comment</button>
                    </div>
                </div>
            </form>
        </div



    </div>


</div>
</@layout.layout>
