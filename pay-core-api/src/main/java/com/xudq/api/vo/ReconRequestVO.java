package com.xudq.api.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.validation.Valid;
import java.util.List;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 对账请求的VO
 * @date
 */
public class ReconRequestVO {

    @Valid
    private List<ReconVO> list;

    private int total;

    public List<ReconVO> getList() {
        return list;
    }

    public void setList(List<ReconVO> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
