package model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GaoDeDTO {

    /**
     * 高德经度
     */
    private BigDecimal gaodeLng;

    /**
     * 高德纬度
     */
    private BigDecimal gaodeLat;

}
