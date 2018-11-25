
package gcmall.${bean.packageName}.service;

import com.google.common.collect.Lists;

import com.jiaoyimao.api.biz.${bean.packageName}.dto.${bean.className}Dto;
import com.jiaoyimao.api.dto.PageResult;

import constants.CachePrefix;
import constants.GoodsDomain;
import gcmall.bussiness.goods.ddl.GoodsCategory;
import gcmall.bussiness.goods.services.GoodsCategoryService;
import gcmall.category.whitelist.dao.InspectCategoryWhitelistDao;
import gcmall.category.whitelist.ddl.InspectCategoryWhitelist;
import gcmall.category.whitelist.query.InspectCategoryWhitelistQuery;
import gcmall.support.game.ddl.GameInfo;
import gcmall.support.game.ddl.GamePublisher;
import gcmall.support.game.services.GameService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import utils.IdGenerator;
import utils.MemcacheUtil;
import utils.ParamUtil;

public class ${bean.className}Service {

    

    /**
     * 按页获取${bean.classComment}信息
     * @param page
     * @param pageSize
     * @return
     */
    public static PageResult<${bean.className}Dto> find${bean.className}Page(<#list bean.searchList! as item>${item.type} ${item.name},</#list> Integer page, Integer pageSize) {
        PageResult<${bean.className}Dto> pageResult = new PageResult();
        List<${bean.className}Dto> retResult = null;
        
        //1.添加查询条件bizType，按创建时间排序
        ${bean.className}Query query = new ${bean.className}Query();
        query.setSortName("ctime");
    <#list bean.searchList! as item>
        query.set${item.name?cap_first}(${item.name});
    </#list> 
        List<${bean.className}>  queryResult = ${bean.className}Dao.get${bean.className}List(query);
        Integer count = ${bean.className}Dao.count(query);

        retResult = get${bean.className}DtoList(queryResult);

        pageResult.setResult(retResult);
        pageResult.setCount(count);
        return pageResult;
    }

    /**
     * 核心业务 TODO
     * @return
     */
    private static List<${bean.className}Dto> get${bean.className}DtoList(List<${bean.className}> categoryWhitelists) {
        //TODO
    }

    /**
     * 添加${bean.classComment}
     * @return
     */
    public static Boolean add${bean.className}(<#list 0..(bean.addList!?size-1) as i> <#if i = 0>${bean.addList[i].type} ${bean.addList[i].name} <#else>,${bean.addList[i].type} ${bean.addList[i].name}</#if></#list>) {
        
        ${bean.className} entity = new ${bean.className}();
        entity.setId(IdGenerator.getBigIntId());
     <#list bean.addList! as item>
        entity.set${item.name?cap_first}(${item.name});
     </#list> 
        Boolean success = ${bean.className}Dao.add${bean.className}(entity);
    }

    /**
     * 删除${bean.classComment}
     * @return
     */
    public static Boolean delete${bean.className}(<#list 0..(bean.deleteList!?size-1) as i> <#if i = 0>${bean.deleteList[i].type} ${bean.deleteList[i].name} <#else>,${bean.deleteList[i].type} ${bean.deleteList[i].name}</#if></#list>) {
        
        ${bean.className}Query query = new ${bean.className}Query();
      <#list bean.deleteList! as item>
        query.set${item.name?cap_first}(${item.name});
      </#list> 
        boolean success = ${bean.className}Dao.delete(query);

        return success;
    }
}

