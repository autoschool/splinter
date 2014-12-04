<#-- @ftlvariable name="model" type="ru.yandex.autoschool.splinter.models.Post" -->
<#import "/layouts/public.ftl" as layout />
<#include "/templates/post/redact.ftl">
<script src="/public/js/valid/basicValid.js"></script>
<@layout.layout title="Blog: show post">
<div class="row">
    <div class="col-md-12">
        <div class="page-header">
            <h2>${model.title}</h2>
        </div>
        <div class="post-body">
        ${model.content}
            <div class="btn-group btn-group-sm pull-right">
                <a href="#editModal" role="button" class="btn btn-large btn-primary" data-toggle="modal">Edit Post</a>
                <a href="#deleteModal" role="button" class="btn btn-large btn-danger" data-toggle="modal">Delete</a>
            </div>
        </div>

        <div class="col-md-12">
            <h3>Comments</h3>
            <div class="post-body">
                <ul class="list-group">
                    <#list model.comments as comment>
                        <li class="list-group-item">
                            <h style="color: steelblue">Created at ${comment.createdAt}</h><br>
                        ${comment.content}
                        </li>
                        <br>
                    </#list>
                </ul>
            </div>

            <form class="form" role="form" action="/posts/${model.id}/comment" method="POST" >
                <div class="control-group">
                    <label class="control-label" for="commentContent">Comment</label>
                    <div class="controls">
                        <textarea name="content" id="commentContent" rows="8" class="form-control" placeholder="comment" required="true"></textarea>
                    </div>
                </div>
                <div class="form-actions">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-danger pull-right">comment</button>
                    </div>
                </div>
            </form>
        </div>
    </div>


</div>
</@layout.layout>
