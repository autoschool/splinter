<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Login">

<div class="row">
    <div class="col-md-offset-4 col-md-4">
        <form class="form" role="form" action="/signin" method="post">
            <div class="page-header">
                <#if error??>
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </#if>
                <div class="form-group">
                    <input type="text" class="form-control" id="login" name="email" value="admin@localhost.ru"
                           placeholder="Login" required>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="pass" name="pass" value="password"
                           placeholder="Password" required>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-danger pull-right">Sign in</button>
                    <a href="/register" class="btn btn-link pull-right">Register</a>
                </div>
            </div>
        </form>
    </div>
</div>

</@layout.layout>