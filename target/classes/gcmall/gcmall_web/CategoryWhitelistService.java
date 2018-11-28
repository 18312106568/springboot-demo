
package gcmall.category.whitelist.service;

import com.google.common.collect.Lists;

import com.jiaoyimao.api.biz.category.whitelist.dto.CategoryWhitelistDto;
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

public class CategoryWhitelistService {

    

    /**
     * 按页获取验号白名单信息
     * @param page
     * @param pageSize
     * @return
     */
    public static PageResult<CategoryWhitelistDto> findCategoryWhitelistPage(Long cid,Integer bizType, Integer page, Integer pageSize) {
        PageResult<CategoryWhitelistDto> pageResult = new PageResult();
        List<CategoryWhitelistDto> retResult = null;
        
        //1.添加查询条件bizType，按创建时间排序
        CategoryWhitelistQuery query = new CategoryWhitelistQuery();
        query.setSortName("ctime");
        query.setCid(cid);
        query.setBizType(bizType);
        List<CategoryWhitelist>  queryResult = CategoryWhitelistDao.getCategoryWhitelistList(query);
        Integer count = CategoryWhitelistDao.count(query);

        retResult = getCategoryWhitelistDtoList(queryResult);

        pageResult.setResult(retResult);
        pageResult.setCount(count);
        return pageResult;
    }

    /**
     * 核心业务 TODO
     * @return
     */
    private static List<CategoryWhitelistDto> getCategoryWhitelistDtoList(List<CategoryWhitelist> categoryWhitelists) {
        //TODO
    }

    /**
     * 添加验号白名单
     * @return
     */
    public static Boolean addCategoryWhitelist( Long cid  ,Integer bizType) {
        
        CategoryWhitelist entity = new CategoryWhitelist();
        entity.setId(IdGenerator.getBigIntId());
        entity.setCid(cid);
        entity.setBizType(bizType);
        Boolean success = CategoryWhitelistDao.addCategoryWhitelist(entity);
    }

    /**
     * 删除验号白名单
     * @return
     */
    public static Boolean deleteCategoryWhitelist( Long cid  ,Integer bizType) {
        
        CategoryWhitelistQuery query = new CategoryWhitelistQuery();
        query.setCid(cid);
        query.setBizType(bizType);
        boolean success = CategoryWhitelistDao.delete(query);

        return success;
    }
}

