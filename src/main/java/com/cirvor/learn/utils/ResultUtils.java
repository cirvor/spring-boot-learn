package com.cirvor.learn.utils;

import com.cirvor.learn.vo.ResultData;

public class ResultUtils {
    /**
     * 成功但不返回数据
     *
     * @return ResultData
     */
    public static ResultData success() {
        return new ResultData(HttpEnum.SUCCESS.getStatus(), HttpEnum.SUCCESS.getMsg());
    }

    /**
     * 成功且返回数据
     *
     * @param object 返回数据
     * @return ResultData
     */
    public static ResultData success(Object object) {
        return new ResultData(HttpEnum.SUCCESS.getStatus(), HttpEnum.SUCCESS.getMsg(), object);
    }

    /**
     * 根据枚举对象返回错误信息
     *
     * @param httpEnum 枚举对象
     * @return ResultData
     */
    public static ResultData error(HttpEnum httpEnum)
    {
        return new ResultData(httpEnum.getStatus(), httpEnum.getMsg());
    }

    /**
     * 自定义错误信息
     *
     * @param httpEnum 枚举对象
     * @param msg 错误信息
     * @return ResultData
     */
    public static ResultData error(HttpEnum httpEnum, String msg)
    {
        return new ResultData(httpEnum.getStatus(), msg);
    }

    /**
     * 自定义错误信息
     *
     * @param httpEnum 枚举对象
     * @param msg 错误信息
     * @return ResultData
     */
    public static ResultData error(HttpEnum httpEnum, String msg, Object object)
    {
        return new ResultData(httpEnum.getStatus(), msg, object);
    }
}
