package com.demo.Utils;

import java.util.List;

/**
 * @BelongsProject: hibAndFreeMarkerDemo
 * @BelongsPackage: com.demo.Utils
 * @Author: yinxiucahun
 * @CreateTime: 2019-05-20 18:03
 * @Description: 用于封装查询结果
 */

public class QueryResult {
    //总记录数
    private int count;
    //一页的数据
    private List list;

    public QueryResult(int count, List list) {
        this.count = count;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
