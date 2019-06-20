package com.cirvor.learn.utils;

import com.cirvor.learn.vo.ResultData;

public class ResultUtils {
    /**
     * 成功但不返回数据
     *
     * @return
     */
    public static ResultData success() {
        return new ResultData(DataEnum.SUCCESS.getStatus(), DataEnum.SUCCESS.getMsg());
    }

    /**
     * 成功且返回数据
     *
     * @param object
     * @return
     */
    public static ResultData success(Object object) {
        return new ResultData(DataEnum.SUCCESS.getStatus(), DataEnum.SUCCESS.getMsg(), object);
    }
}
