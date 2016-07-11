package com.kk.geo.baidu.model;

/**
 * 百度返回结果的状态
 * <p/>
 * <p/>
 * 返回码	定义
 * 0	正常
 * 1	服务器内部错误
 * 2	请求参数非法
 * 3	权限校验失败
 * 4	配额校验失败
 * 5	ak不存在或者非法
 * 101	服务禁用
 * 102	不通过白名单或者安全码不对
 * 2xx	无权限
 * 3xx	配额错误
 */
public abstract class Result {
    protected int status; // 返回结果状态值， 成功返回0

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return status == 0;
    }
}
