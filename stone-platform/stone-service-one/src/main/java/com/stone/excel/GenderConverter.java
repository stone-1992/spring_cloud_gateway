package com.stone.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @classname GenderConverter
 * @description 性别转换器
 * @date 2020/4/2 15:12
 * @author stone
 */
@Slf4j
public class GenderConverter implements Converter<Integer> {
    /**
     * 性别枚举
     */
    @Getter
    enum Gender{
        /**
         * (code, gender)
         */
        UNKNOWN(0,"未知"),
        MAN(1,"男"),
        WOMAN(2,"女"),;
        private Integer code;
        private String gender;

        Gender(Integer code, String gender) {
            this.code = code;
            this.gender = gender;
        }
    }

    @Override
    public Class<String> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 这里读的时候会调用
     * @param cellData
     * @param contentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String value = cellData.getStringValue();
        if(Gender.MAN.getGender().equals(value)){
            return Gender.MAN.getCode();
        }else if(Gender.WOMAN.getGender().equalsIgnoreCase(value)){
            return Gender.WOMAN.getCode();
        }else{
            return Gender.UNKNOWN.getCode();
        }
    }

    /**
     * 这里是写的时候会调用 不用管
     * @param value
     * @param contentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public CellData<Integer> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData<>(value);
    }
}
