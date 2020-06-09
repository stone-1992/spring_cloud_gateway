package com.stone.common.poi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0
 * @Description
 * @date 2020年05月20日 10:03
 */
@Slf4j
public class ExportUtil {

	/**
	 * 导出Excel或者CSV文件：大于Excel文件导出最大行数限制（默认8000）则导出CSV文件，否则导出Excel文件
	 * 
	 * @param dataList
	 *            需要导出的对象集合
	 * @param response
	 *            需要导出对象的类型
	 * @param fileName
	 *            文件名
	 */
	public static <T> void export(List<T> dataList, HttpServletResponse response, String fileName, Class<T> clazz) {
		export(dataList, response, fileName, clazz, 8000);
	}

	/**
	 * 导出Excel或者CSV文件：大于Excel文件导出最大行数限制（默认8000）则导出CSV文件，否则导出Excel文件
	 * 
	 * @param dataList
	 *            需要导出的对象集合
	 * @param response
	 *            需要导出对象的类型
	 * @param fileName
	 *            文件名
	 * @param maxExcelRowLimit
	 *            Excel文件导出最大行数限制,如果超过改值，则会自动导出为CSV文件
	 */
	public static <T> void export(List<T> dataList, HttpServletResponse response, String fileName, Class<T> clazz,
			Integer maxExcelRowLimit) {
		if (dataList.size() > maxExcelRowLimit) {
			// 导出csv文件
			exportCsv(dataList, response, fileName, clazz);
		} else {
			// 导出excel文件
			exportExcel(dataList, response, fileName, clazz);
		}
	}

	/**
	 * 导出生成Excel格式的文件
	 * 
	 * @param dataList
	 *            需要导出的对象集合
	 * @param response
	 *            需要导出对象的类型
	 * @param fileName
	 *            文件名
	 */
	public static <T> void exportExcel(List<T> dataList, HttpServletResponse response, String fileName,
			Class<T> clazz) {
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("utf-8");
			// 这里URLEncoder.encode可以防止中文乱码
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
			// 这里需要设置不关闭流
			EasyExcel.write(response.getOutputStream(), clazz).sheet("sheet1").doWrite(dataList);
		} catch (Exception e) {
			log.error("导出Excel文件异常", e);
			// 重置response
			response.reset();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			try {
				response.getWriter().println("下载文件失败" + e.getMessage());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 导出生成csv格式的文件
	 * 
	 * @param list
	 *            需要导出的对象集合
	 * @param response
	 *            需要导出对象的类型
	 * @param fileName
	 *            文件名
	 */
	public static <T> void exportCsv(List<T> list, HttpServletResponse response, String fileName, Class<T> clazz) {
		OutputStreamWriter ow = null;
		OutputStream fos = null;
		try {
			response.setContentType("application/vnd.ms-csv");
			response.setCharacterEncoding("utf-8");
			// 这里URLEncoder.encode可以防止中文乱码
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".csv");
			fos = response.getOutputStream();
			ow = new OutputStreamWriter(fos, "gbk");
			// 利用反射获取所有字段
			Field[] fields = clazz.getDeclaredFields();
			// csv文件是逗号分隔，除第一个外，每次写入一个单元格数据后需要输入逗号
			// 写文件头
			for (Field field : fields) {
				// 设置字段可见性
				field.setAccessible(true);
				// 这里使用EasyExcel的注解，不另外添加注解了
				ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
				if (annotation != null) {
					ow.write(annotation.value()[0]);
				} else {
					ow.write(field.getName());
				}
				ow.write(",");
			}
			// 写完文件头后换行
			ow.write("\r\n");
			// 写内容
			for (T obj : list) {
				// 利用反射获取所有字段
				for (Field field : fields) {
					field.setAccessible(true);
					Object o = field.get(obj);
					if (o != null) {
						if (field.getType().getName().equals(Date.class.getName())) {
							String pattern = "yyyy-MM-dd HH:mm:ss";
							DateTimeFormat dtf = field.getAnnotation(DateTimeFormat.class);
							if (dtf != null) {
								pattern = dtf.pattern();
							}
							ow.write(DateUtil.format((Date) field.get(obj), pattern));
						} else if (field.getType().getName().equals(String.class.getName())) {
							ow.write(field.get(obj).toString()+"\t");
						} else {
							ow.write(field.get(obj).toString());
						}
					} else {
						ow.write("");
					}
					ow.write(",");
				}
				// 写完一行换行
				ow.write("\r\n");
			}
		} catch (Exception e) {
			log.error("导出CSV文件异常", e);
			// 重置response
			response.reset();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			try {
				response.getWriter().println("下载文件失败" + e.getMessage());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				if (ow != null) {
					ow.flush();
					ow.close();
				}
				if (fos != null) {
					fos.flush();
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * excel模板下载
	 * 
	 * @param response
	 *            response
	 * @param fileName
	 *            模板文件命
	 * @param filePath
	 *            模板文件路径
	 * @throws IOException
	 *             IO异常
	 */
	public static void excelTemplateDown(HttpServletResponse response, String fileName, String filePath)
			throws IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
		ClassPathResource classPathResource = new ClassPathResource(filePath);
		InputStream inputStream = classPathResource.getInputStream();
		IoUtil.copy(inputStream, response.getOutputStream());
	}
}
