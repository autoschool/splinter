<#-- @ftlvariable name="model" type="ru.yandex.autoschool.splinter.models.Post[]" -->
<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Blog: create new post">
<div class="row">
    <div class="col-md-12">
        <h3>Create post form</h3>
        <form class="form" role="form" action="/posts/new" method="POST">
            <div class="form-group">
                <input type="text" class="form-control" id="title" name="title"
                       placeholder="Post Title">
            </div>
            <div class="form-group">
                <textarea class="form-control" rows="10" name="content" placeholder="Post Body"></textarea>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-danger pull-right">Create</button>
                </div>
            </div>
        </form>
    </div>
</div>
</@layout.layout>