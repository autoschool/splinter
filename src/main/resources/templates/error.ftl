<#import "/layouts/public.ftl" as layout />
<@layout.layout title="Blog: show error details">
<div class="row">
    <div class="col-md-12">
        <div class="page-header">
            <h2>Вывод ошибки</h2>
        </div>
        <div>
            ${model}
        </div>
    </div>
</div>
</@layout.layout>