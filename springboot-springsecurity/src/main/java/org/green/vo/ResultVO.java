package org.green.vo;

import lombok.Data;

/**
 * @author : greenv
 * @since : 2024/12/17
 */
@Data
public class ResultVO {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;
    public ResultVO(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static ResultVO ok() {
        return new ResultVO(200, "成功", null);
    }

    public static ResultVO ok(Object data) {
        return new ResultVO(200, "成功", data);
    }

    public static ResultVO error(Object data) {
        return new ResultVO(500, "失败", data);
    }

}
