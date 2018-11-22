package com.jiaoyimao.api.biz.${bean.packageName};


import com.jiaoyimao.api.biz.${bean.packageName}.dto.${bean.className}Dto;
import com.jiaoyimao.api.dto.PageResult;
import com.jiaoyimao.api.dto.ScResponse;

import java.util.List;
import jws.mvc.rpc.annotation.Path;

@Path("sc/${bean.className}")
public interface I${bean.className}Service {
    /**
     * ${bean.classComment}分面查询
     */
    @Path("findPage")
    ScResponse<PageResult<${bean.className}Dto>> find{bean.className}Page(
            <#list bean.searchList! as item>
                ${item.type} ${item.name},
            </#list>
             Integer page,
             Integer pageSize);

    /**
     *加入${bean.classComment}
     */
    @Path("addOne")
    ScResponse<Boolean> add${bean.className}list(
        <#list 0..(bean.addList!?size-1) as i>
            <#if i = 0>
                ${bean.addList[i].type} ${bean.addList[i].name}
            <#else>
                ,${bean.addList[i].type} ${bean.addList[i].name}
            </#if>
        </#list>);

    /**
     *加入${bean.classComment}
     */
    @Path("deleteOne")
    ScResponse<Boolean> delete$bean.{className}(<#list 0..(bean.addList!?size-1) as i>
            <#if i = 0>
                ${bean.deleteList[i].type} ${bean.deleteList[i].name}
            <#else>
                ,${bean.deleteList[i].type} ${bean.deleteList[i].name}
            </#if>
        </#list>);
}
