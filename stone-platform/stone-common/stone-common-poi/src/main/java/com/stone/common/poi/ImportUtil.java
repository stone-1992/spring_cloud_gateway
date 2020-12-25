package com.stone.common.poi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;

/**
 * @author stone
 * @version 1.0
 * @Description
 * @date 2020年05月22日 10:50
 */
public class ImportUtil {
    /**
     * 导入支持的excel格式
     */
    private static final List<String> SUFFIX_LIST = Arrays.asList(PoiConstant.FILE_TYPE_XLS,
            PoiConstant.FILE_TYPE_XLSX);

    /**
     * 导入文件验证,文件大小验证默认不大于10MB,文件类型默认只支持.xlsx .xls格式文件
     *
     * @param file 导入文件
     */
    public static void importFileValidate(MultipartFile file) {
        importFileValidate(file, SUFFIX_LIST);
    }

    /**
     * 导入文件验证,文件大小验证默认不大于10MB
     *
     * @param file             导入文件
     * @param supportFileTypes 支持的文件类型
     */
    public static void importFileValidate(MultipartFile file, List<String> supportFileTypes) {
        importFileValidate(file, supportFileTypes, 10L);
    }

    /**
     * 导入文件验证,文件大小单位默认为MB
     *
     * @param file             导入文件
     * @param supportFileTypes 支持的文件类型
     * @param sizeLimit        文件大小限制
     */
    public static void importFileValidate(MultipartFile file, List<String> supportFileTypes, long sizeLimit) {
        importFileValidate(file, supportFileTypes, sizeLimit, PoiConstant.MB);
    }

    /**
     * 导入文件验证，文件类型默认只支持.xlsx .xls格式文件
     *
     * @param file      导入文件
     * @param sizeLimit 文件大小限制
     * @param sizeUnit  文件大小单位：B,KB,MB,GB,TB
     */
    public static void importFileValidate(MultipartFile file, long sizeLimit, String sizeUnit) {
        importFileValidate(file, SUFFIX_LIST, sizeLimit, sizeUnit);
    }

    /**
     * 导入文件验证
     *
     * @param file             导入文件
     * @param supportFileTypes 支持的文件类型
     * @param sizeLimit        文件大小限制
     * @param sizeUnit         文件大小单位：B,KB,MB,GB,TB
     */
    public static void importFileValidate(MultipartFile file, List<String> supportFileTypes, long sizeLimit,
                                          String sizeUnit) {
        Assert.notNull(file, "文件不能为空");
        String originalFileName = file.getOriginalFilename();
        Assert.notNull(originalFileName, "文件不能为空");
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        Assert.isTrue(supportFileTypes.contains(suffix), "只支持" + JSONUtil.toJsonStr(supportFileTypes) + "格式文件");
        long size = DataSizeUtil.sizeChange(file.getSize(), sizeUnit);
        Assert.isTrue(sizeLimit > size, "文件大小不能超过" + sizeLimit + sizeUnit + "限制");
    }

    /**
     * 唯一性校验
     *
     * @param excelList      excel数据
     * @param mapper         执行函数
     * @param valideFeildStr 校验字段名称
     */
    public static <E> void uniqueValidate(List<E> excelList, Function<? super E, ? extends Object> mapper,
                                          String valideFeildStr) {
        Map<Object, Long> countMap = excelList.stream().map(mapper)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Object> repeates = new ArrayList<>();
        countMap.forEach((k, v) -> {
            if (v > 1) {
                repeates.add(k);
            }
        });
        if (CollUtil.isNotEmpty(repeates)) {
            StringBuilder repeateMsg = new StringBuilder();
            repeateMsg.append(valideFeildStr + ":" + repeates.get(0));
            for (int i = 0; i < excelList.size(); i++) {
                E e = excelList.get(i);
                if (repeates.get(0).equals(mapper.apply(e))) {
                    repeateMsg.append(",第" + (i + 2 + 1) + "行");
                }
            }
            repeateMsg.append("重复");
            Assert.isTrue(StringUtils.isEmpty(repeateMsg), repeateMsg.toString());
        }
    }

    /**
     * 多个类型匹配(可以用于字典类型等匹配)
     *
     * @param typeList        类型集合
     * @param dataList        数据集合
     * @param dataMapers      数据获取匹配属性函数
     * @param predicates      类型过滤函数
     * @param valideFeildStrs 校验字段提示
     * @param keyMappers      类型map key
     * @param valueMappers    类型map value
     * @param isAbleNulls     类型匹配是否可以为空
     * @param isFalse         校验判断 true:包含 false:不包含
     */
    public static <T, U> void typeMatchValidate(List<T> typeList, List<U> dataList,
                                                List<Function<? super U, ? extends Object>> dataMapers, List<Predicate<? super T>> predicates,
                                                List<String> valideFeildStrs, List<Function<? super T, ? extends Object>> keyMappers,
                                                List<Function<? super T, ? extends Object>> valueMappers, List<Boolean> isAbleNulls, boolean isFalse) {
        if (CollUtil.isNotEmpty(typeList)) {
            List<Map<Object, Object>> typeMapList = new ArrayList<>();
            for (int i = 0; i < predicates.size(); i++) {
                Predicate<? super T> predicate = predicates.get(i);
                Function<? super T, ? extends Object> keyMapper = keyMappers.get(i);
                Function<? super T, ? extends Object> valueMapper = valueMappers.get(i);
                Map<Object, Object> typeMap = typeList.stream().filter(predicate)
                        .collect(Collectors.toMap(keyMapper, valueMapper, (k1, k2) -> k1));
                typeMapList.add(typeMap);
            }

            for (int i = 0; i < dataList.size(); i++) {
                U u = dataList.get(i);
                String rowMsg = "第" + (i + 2 + 1) + "行,";
                for (int j = 0; j < typeMapList.size(); j++) {
                    Object exectuResult = dataMapers.get(j).apply(u);
                    boolean isAbleNull = isAbleNulls.get(j);
                    boolean isContains = isFalse ? typeMapList.get(j).containsKey(exectuResult)
                            : !typeMapList.get(j).containsKey(exectuResult);
                    isContains = isAbleNull
                            ? isContains || (exectuResult instanceof String ? StringUtils.isEmpty(exectuResult)
                            : null == exectuResult)
                            : isContains;
                    Assert.isTrue(isContains, rowMsg + valideFeildStrs.get(j) + ":" + exectuResult);
                }
            }
        }
    }
}
