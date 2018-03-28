package com.lw.freemarker.study;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Sample {

	public static void main(String[] args) throws IOException, TemplateException {
		Configuration conf = new Configuration();
		String dir = "E:\\mycode\\mycode17\\freemarker-study\\ftl\\";
		conf.setDirectoryForTemplateLoading(new File(dir));
		Template template = conf.getTemplate("freemarker.html");

		Writer out = new FileWriter(new File(dir + "hello.html"));
		// 1、存放对象
		Map<String, Object> map = new HashMap<String, Object>();
		Person person = new Person();
		person.setId(11);
		person.setName("LiWen");
		map.put("person", person);
		// 2、存放List
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>() ;
		Map<String, Object> obj1 = new HashMap<String, Object>();
		obj1.put("name", "zhangsan");
		obj1.put("age", "22");
		list.add(obj1);
		Map<String, Object> obj2 = new HashMap<String, Object>();
		obj2.put("name", "lisi");
		obj2.put("age", "21");
		list.add(obj2);
		map.put("list", list);
		// 3、模板存值
		map.put("wrold", "世界,你好");
		template.process(map, out);
		out.flush();
		out.close();
	}

	 
}
