package sc;

import com.jiaoyimao.api.ScResponseWarpper;
import com.jiaoyimao.api.biz.category.whitelist.ICategoryWhitelistService;
import com.jiaoyimao.api.biz.category.whitelist.dto.CategoryWhitelistDto;

import com.jiaoyimao.api.constants.ResultCode;
import com.jiaoyimao.api.dto.ScResponse;
import framework.logging.Logger;
import gcmall.category.whitelist.service.CategoryWhitelistService;
import utils.ParamUtil;

public class CategoryWhitelistServiceImpl extends ScResponseWarpper implements ICategoryWhitelistService {
    private static final Logger LOGGER = Logger.getLogger();

    public ScResponse<PageResult<CategoryWhitelistDto>> findCategoryWhitelistPage(Long cid,Integer bizType, Integer page, Integer pageSize) {
        //1.校验分页参数并校正
        if (ParamUtil.isNullOrZero(page)) {
            page = 1;
        }


        if (ParamUtil.isNullOrZero(pageSize)) {
            pageSize = 10;
        }

        //2.校验是否有空参数 直接返回参数不合法
       
        if (ParamUtil.isOneOfNullorEmpty(  cid  , bizType)) {
            return error(ResultCode.ILLEGAL_PARAM, ResultCode.ILLEGAL_PARAM.getDescription());
        }
        try {
            PageResult pageResult = CategoryWhitelistService.findCategoryWhitelistPage( cid, bizType,, page, pageSize);
            return success(pageResult);
        } catch (Exception ex) {
            LOGGER.error(ex, "[CategoryWhitelistServiceImpl.findCategoryWhitelistPage] Exception error,cid[%s],bizType[%s]," ,cid ,bizType);
            return error(ResultCode.INTERNAL_ERROR, ResultCode.INTERNAL_ERROR.getDescription());
        }
    }


    public ScResponse<Boolean> addCategoryWhitelist( Long cid  ,Integer bizType) {
        if (ParamUtil.isOneOfNullorEmpty(  cid  , bizType)) {
            return error(ResultCode.ILLEGAL_PARAM, ResultCode.ILLEGAL_PARAM.getDescription());
        }
        try {
            Boolean result = CategoryWhitelistService.addCategoryWhitelist(  cid  , bizType);
            return success(result);
        } catch (Exception ex) {
            LOGGER.error(ex, "[CategoryWhitelistServiceImpl.addCategoryWhitelist] Exception error,  cid[%s],bizType[%s]," ,cid ,bizType);
            return error(ResultCode.INTERNAL_ERROR, ResultCode.INTERNAL_ERROR.getDescription());
        }
    }

    public ScResponse<Boolean> deleteCategoryWhitelist( Long cid  ,Integer bizType) {
        if (ParamUtil.isOneOfNullorEmpty(  cid  , bizType)) {
            return error(ResultCode.ILLEGAL_PARAM, ResultCode.ILLEGAL_PARAM.getDescription());
        }
        try {
            Boolean result = CategoryWhitelistService.deleteCategoryWhitelist(  cid  , bizType);
            return success(result);
        } catch (Exception ex) {
            LOGGER.error(ex, "[CategoryWhitelistServiceImpl.deleteCategoryWhitelist] Exception error,  cid[%s],bizType[%s]," ,cid ,bizType);
            return error(ResultCode.INTERNAL_ERROR, ResultCode.INTERNAL_ERROR.getDescription());
        }
    }
}

