package com.wang.server.common.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.server.common.Result.R;
import com.wang.server.common.utils.Tools;

import java.util.Map;

/**
 * 分页插件
 */
public class PageUtils {


    /**
     * 获取分页对象
     * @param pageStr
     * @param limitStr
     * @return
     */
    public static Page getPage(String pageStr,String limitStr){

        int pageNum = 0;
        int limit = 10;

        if (!Tools.isEmpty(pageStr)){
            pageNum = Integer.parseInt(pageStr);
        }

        if (!Tools.isEmpty(limitStr)){
            limit = Integer.parseInt(limitStr);
        }

        Page page = new Page(pageNum, limit);
        return page;
    }

    public static Page getPage(Map<String, Object> param){
        String page = String.valueOf(param.get("page"));
        String limit = String.valueOf(param.get("limit"));
        return getPage(page, limit);
    }

    /**
     * 获取分页结果集
     * @param iPage
     * @return
     */
    public static R getPageResult(IPage iPage){
        return R.ok().data("list" , iPage.getRecords()).data("count" , (int)iPage.getTotal());
    }

}
