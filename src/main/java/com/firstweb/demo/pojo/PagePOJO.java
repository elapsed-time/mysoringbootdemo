package com.firstweb.demo.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxx
 * @2019/7/20 10:34
 */
@Data
public class PagePOJO {
    private List<QuestionPOJO> questions;
    private boolean showPrevious;//前一页
    private boolean showFirstPage;//第一页
    private boolean showNext;//下一页
    private boolean showEndPage;//尾页
    private Integer page;//当前页
    private List<Integer> pages = new ArrayList<>();//页码数组
    private Integer totalPage;//总页数
    public void setPage(Integer totalcount, Integer page, Integer size) {//一共多少条数据，当前页，每页多少条数据


        //计算总页数
        if (totalcount % size == 0) {
            totalPage = totalcount / size;
        } else {
            totalPage = totalcount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        this.page = page;
        //控制页码显示数
        pages.add(page);//将当前页添加到页码数组中
        for (int i = 1; i <= 3; i++) {//只展示当前页的三个相邻页码
            if (page - i > 0) {
                pages.add(0, page - i);//将当前页左边的数据放入数组，限制不为负数
            }
            if (page + i <= totalPage) {
                pages.add(page + i);//将当前页右边数据放入数组
            }
        }

        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否展示首页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示尾页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
