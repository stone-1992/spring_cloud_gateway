package com.stone.common.poi;

/**
 * @version 1.0
 * @Description
 * @date 2020年05月22日　11:33
 */
public class DataSizeUtil {

    /**
     * 根据单位类型换算
     * @param size 大小
     * @param unit 单位类型
     * @return 换算后的大小
     */
    public static long sizeChange(long size,String unit) {
        switch (unit){
            case PoiConstant.B : return toBytes(size);
            case PoiConstant.KB : return toKilobytes(size);
            case PoiConstant.MB : return toMegabytes(size);
            case PoiConstant.GB : return toGigabytes(size);
            case PoiConstant.TB : return toTerabytes(size);
            default: return size;
        }
    }
    public static long toBytes(long size) {
        return size;
    }

    public static long toKilobytes(long size) {
        return size / 1024L;
    }

    public static long toMegabytes(long size) {
        return size / 1048576L;
    }

    public static long toGigabytes(long size) {
        return size / 1073741824L;
    }

    public static long toTerabytes(long size) {
        return size / 1099511627776L;
    }
}
