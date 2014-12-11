<#-- @ftlvariable name="model" type="ru.yandex.autoschool.splinter.models.Post[]" -->
<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Blog: create new post">
<script src="/public/js/valid/basicValid.js"></script>
<script src="/public/js/vendor/jqBootStrapValidation.js"></script>
<div class="row">
    <div class="col-md-12">
        <h3>Create post form</h3>
        <form class="form" role="form" action="/posts/new" method="POST">
            <div class="form-group">
                <input type="text" class="form-control" id="title" name="title"
                       placeholder="Post Title" required pattern="^(?!\s*$).+"">
            </div>
            <div class="form-group">
                <textarea class="form-control" id="content"  rows="10" name="content" placeholder="Post Body" required>
                </textarea>
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