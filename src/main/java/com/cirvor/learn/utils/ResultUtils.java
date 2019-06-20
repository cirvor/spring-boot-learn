package com.cirvor.learn.utils;

import com.cirvor.learn.vo.ResultData;

public class ResultUtils {
    /**
     * 成功但不返回数据
     *
     * @return ResultData
     */
    public static ResultData success() {
        return new ResultData(DataEnum.SUCCESS.getStatus(), DataEnum.SUCCESS.getMsg());
    }

    /**
     * 成功且返回数据
     *
     * @param object 返回数据
     * @return ResultData
     */
    public static ResultData success(Object object) {
        return new ResultData(DataEnum.SUCCESS.getStatus(), DataEnum.SUCCESS.getMsg(), object);
    }

    /**
     * 根据枚举对象返回错误信息
     *
     * @param dataEnum 枚举对象
     * @return ResultData
     */
    public static ResultData error(DataEnum dataEnum)
    {
        return new ResultData(dataEnum.getStatus(), dataEnum.getMsg());
    }

    /**
     * 自定义错误信息
     *
     * @param dataEnum 枚举对象
     * @param msg 错误信息
     * @return ResultData
     */
    public static ResultData error(DataEnum dataEnum, String msg)
    {
        return new ResultData(dataEnum.getStatus(), msg);
    }
}
