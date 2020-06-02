package core.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * 分页对象
 *
 * @title
 * @author 龚进
 * @date 2019年11月12日
 * @version 1.0
 */
@ApiModel(value = "分页对象实体")
@NoArgsConstructor
public class PageBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8235976291920850306L;

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码")
    private Long pageNo = 1L;

    /**
     * 每一页的记录条数
     */
    @ApiModelProperty(value = "每一页的记录条数")
    private Long pageSize = 30L;

    /**
     * 总记录
     */
    @ApiModelProperty(value = "总记录数")
    private Long pageDataCount = 0L;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Long pageCount = 0L;

    public PageBean(Long pageNo, Long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageBean(Long pageNo, Long pageSize, Long pageDataCount, Long pageCount) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.pageDataCount = pageDataCount;
        this.pageCount = pageCount;
    }

    /**
     * 构建分页对象
     *
     * @param pageNo
     * @param pageSize
     * @param pageDataCount
     * @return
     */
    public static PageBean of(Long pageNo, Long pageSize, Long pageDataCount) {
        long pageCount = pageDataCount % pageSize > 0 ? pageDataCount / pageSize + 1 : pageDataCount / pageSize;
        return new PageBean(pageNo, pageSize, pageDataCount, pageCount);
    }

    public void build(Long pageNo, Long pageSize, Long pageDataCount) {
        long pageCount = pageDataCount % pageSize > 0 ? pageDataCount / pageSize + 1 : pageDataCount / pageSize;
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
        this.setPageDataCount(pageDataCount);
        this.setPageCount(pageCount);
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
        if(Objects.isNull(pageNo) || pageNo < 1){
            this.pageNo = 1L;
        }
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
        if(Objects.isNull(pageSize) || pageSize < 1){
            this.pageSize = 30L;
        }
    }

    public Long getPageDataCount() {
        return pageDataCount;
    }

    public void setPageDataCount(Long pageDataCount) {
        this.pageDataCount = pageDataCount;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

}
