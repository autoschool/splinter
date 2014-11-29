<#-- @ftlvariable name="model" type="ru.yandex.autoschool.splinter.models.Post[]" -->
<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Error">
<div class="row">
    <div class="col-md-12">
        <h3>Error</h3>
        ${model}
    </div>
</div>
</@layout.layout>