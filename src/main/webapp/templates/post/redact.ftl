<#-- @ftlvariable name="model" type="ru.yandex.autoschool.splinter.models.Post" -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="authFormLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Edit post</h4>
            </div>
            <div class="modal-body">
                <form class="form" role="form" action="/posts/${model.id}/edit" method="POST">
                    <div class="form-group">
                        <textarea class="form-control" rows="1" name="title" placeholder=${model.title}></textarea>
                        <textarea class="form-control" rows="5" name="content" placeholder=${model.content}></textarea>
                    </div>
                    <p>Do you want to save changes in this post?</p>
                    <p class="text-warning"><small>If you don't save, your changes will be lost.</small></p>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-danger pull-right">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">x</button>
                <h4 class="modal-title" id="myModalLabel">Delete post</h4>
            </div>
            <div class="modal-body">
                <h4>Are you sure you want to delete this post?</h4>
            </div>
            <form class="form" role="form" action="/posts/${model.id}/delete" method="post">
            <div class="modal-footer"><button class="btn btn-default" type="button" data-dismiss="modal">No</button>
                <button class="btn btn-primary" type="submit">Yes</button></div>
            </form>
        </div>
    </div>
</div>

