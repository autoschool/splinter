<#function max x y>
    <#if (x<y)><#return y><#else><#return x></#if>
</#function>

<#function min x y>
    <#if (x<y)><#return x><#else><#return y></#if>
</#function>

<#macro pages totalPages p linkUrl>
    <#assign size = totalPages?size>
    <#if (totalPages?last<2)>
        <#return>
    </#if>

    <#if (p<=5)> <#-- p among first 5 pages -->
        <#assign interval = 1..(min(5,size))>
    <#elseif ((size-p)<5)> <#-- p among last 5 pages -->
        <#assign interval = (max(1,(size-4)))..size >
    <#else>
        <#assign interval = (p-2)..(p+2)>
    </#if>

    <ul class="pagination pagination-md">
        <#if p=1>
            <li class="disabled"><a href="#"><i class="fa fa-angle-left"></i>&nbsp;</a></li>
        <#else>
            <li><a href="${linkUrl}?page=1"><i class="fa fa-angle-left"></i>&nbsp;</a></li>
        </#if>

        <#if !(interval?seq_contains(1))>
            <li><a href="${linkUrl}?page=1">1</a></li>
            <li class="disabled"><a href="#">...</a></li>
        </#if>

        <#list interval as page>
            <#if page=p>
                <li class="active"><a href="${linkUrl}?page=${page}">${page}</a></li>
            <#else>
                <li><a href="${linkUrl}?page=${page}">${page}</a></li>
            </#if>
        </#list>

        <#if !(interval?seq_contains(size))>
            <li class="disabled"><a href="#">...</a></li>
            <li><a href="${linkUrl}?page=${size}">${size}</a></li>
        </#if>
        <#if size=p>
            <li class="disabled"><a href="#"><i class="fa fa-angle-right"></i>&nbsp;</a></li>
        <#else>
            <li><a href="${linkUrl}?page=${size}"><i class="fa fa-angle-right"></i>&nbsp;</a></li>
        </#if>
    </ul>
</#macro>