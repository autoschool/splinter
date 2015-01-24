<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Registration Page">

<div class="row">
    <div class="col-md-offset-4 col-md-4">
        <form class="form" role="form" action="/register" method="post">
            <div class="page-header">
                <#if error??>
                    <div class="alert alert-danger" role="alert">
                    ${error}
                    </div>
                </#if>
                <div class="form-group">
                    <input type="text" class="form-control" id="login" name="login" placeholder="Login" required>
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Name" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="sirname" name="sirname" placeholder="Sirname" required>               </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-danger pull-right" id="register">Register</button>
                </div>
            </div>
        </form>
    </div>
</div>
</@layout.layout>