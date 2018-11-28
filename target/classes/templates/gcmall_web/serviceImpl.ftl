package sc;

import com.jiaoyimao.api.ScResponseWarpper;
import com.jiaoyimao.api.biz.${bean.packageName}.I${bean.className}Service;
import com.jiaoyimao.api.biz.${bean.packageName}.dto.${bean.className}Dto;

import com.jiaoyimao.api.constants.ResultCode;
import com.jiaoyimao.api.dto.ScResponse;
import framework.logging.Logger;
import gcmall.${bean.packageName}.service.${bean.className}Service;
import utils.ParamUtil;

public class ${bean.className}ServiceImpl extends ScResponseWarpper implements I${bean.className}Service {
    private static final Logger LOGGER = Logger.getLogger();

    public ScResponse<PageResult<${bean.className}Dto>> find${bean.className}Page(<#list bean.searchList! as item>${item.type} ${item.name},</#list> Integer page, Integer pageSize) {
        //1.校验分页参数并校正
        if (ParamUtil.isNullOrZero(page)) {
            page = 1;
        }


        if (ParamUtil.isNullOrZero(pageSize)) {
            pageSize = 10;
        }

        //2.校验是否有空参数 直接返回参数不合法
       
        if (ParamUtil.isOneOfNullorEmpty(<#list 0..(bean.searchList!?size-1) as i> <#if bean.searchList[i].isMust??><#if i = 0> ${bean.searchList[i].name} <#else>, ${bean.searchList[i].name}</#if></#if></#list>)) {
            return error(ResultCode.ILLEGAL_PARAM, ResultCode.ILLEGAL_PARAM.getDescription());
        }
        try {
            PageResult pageResult = ${bean.className}Service.find${bean.className}Page(<#list bean.searchList! as item> ${item.name},</#list>, page, pageSize);
            return success(pageResult);
        } catch (Exception ex) {
            LOGGER.error(ex, "[${bean.className}ServiceImpl.find${bean.className}Page] Exception error,<#list bean.searchList! as item>${item.name}[%s],</#list>"<#list bean.searchList! as item> ,${item.name}</#list>);
            return error(ResultCode.INTERNAL_ERROR, ResultCode.INTERNAL_ERROR.getDescription());
        }
    }


    public ScResponse<Boolean> add${bean.className}(<#list 0..(bean.addList!?size-1) as i> <#if i = 0>${bean.addList[i].type} ${bean.addList[i].name} <#else>,${bean.addList[i].type} ${bean.addList[i].name}</#if></#list>) {
        if (ParamUtil.isOneOfNullorEmpty(<#list 0..(bean.addList!?size-1) as i> <#if bean.addList[i].isMust??><#if i = 0> ${bean.addList[i].name} <#else>, ${bean.addList[i].name}</#if></#if></#list>)) {
            return error(ResultCode.ILLEGAL_PARAM, ResultCode.ILLEGAL_PARAM.getDescription());
        }
        try {
            Boolean result = ${bean.className}Service.add${bean.className}(<#list 0..(bean.addList!?size-1) as i> <#if i = 0> ${bean.addList[i].name} <#else>, ${bean.addList[i].name}</#if></#list>);
            return success(result);
        } catch (Exception ex) {
            LOGGER.error(ex, "[${bean.className}ServiceImpl.add${bean.className}] Exception error,  <#list bean.addList! as item>${item.name}[%s],</#list>"<#list bean.addList! as item> ,${item.name}</#list>);
            return error(ResultCode.INTERNAL_ERROR, ResultCode.INTERNAL_ERROR.getDescription());
        }
    }

    public ScResponse<Boolean> delete${bean.className}(<#list 0..(bean.deleteList!?size-1) as i> <#if i = 0>${bean.deleteList[i].type} ${bean.deleteList[i].name} <#else>,${bean.deleteList[i].type} ${bean.deleteList[i].name}</#if></#list>) {
        if (ParamUtil.isOneOfNullorEmpty(<#list 0..(bean.deleteList!?size-1) as i> <#if bean.deleteList[i].isMust??><#if i = 0> ${bean.deleteList[i].name} <#else>, ${bean.deleteList[i].name}</#if></#if></#list>)) {
            return error(ResultCode.ILLEGAL_PARAM, ResultCode.ILLEGAL_PARAM.getDescription());
        }
        try {
            Boolean result = ${bean.className}Service.delete${bean.className}(<#list 0..(bean.deleteList!?size-1) as i> <#if i = 0> ${bean.deleteList[i].name} <#else>, ${bean.deleteList[i].name}</#if></#list>);
            return success(result);
        } catch (Exception ex) {
            LOGGER.error(ex, "[${bean.className}ServiceImpl.delete${bean.className}] Exception error,  <#list bean.deleteList! as item>${item.name}[%s],</#list>"<#list bean.deleteList! as item> ,${item.name}</#list>);
            return error(ResultCode.INTERNAL_ERROR, ResultCode.INTERNAL_ERROR.getDescription());
        }
    }
}

