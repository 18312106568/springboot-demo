package com.jiaoyimao.api.biz.category.whitelist;


import com.jiaoyimao.api.biz.category.whitelist.dto.CategoryWhitelistDto;
import com.jiaoyimao.api.dto.PageResult;
import com.jiaoyimao.api.dto.ScResponse;

import java.util.List;
import jws.mvc.rpc.annotation.Path;

@Path("sc/CategoryWhitelist")
public interface ICategoryWhitelistService {
    /**
     * 验号白名单分面查询
     */
    @Path("findPage")
    ScResponse<PageResult<CategoryWhitelistDto>> find{bean.className}Page(
                Long cid,
                Integer bizType,
             Integer page,
             Integer pageSize);

    /**
     *加入验号白名单
     */
    @Path("addOne")
    ScResponse<Boolean> addCategoryWhitelistlist(
                Long cid
                        ,Integer bizType
        );

    /**
     *加入验号白名单
     */
    @Path("deleteOne")
    ScResponse<Boolean> delete$bean.{className}(
                Long cid
        
                ,Integer bizType
        );
}
