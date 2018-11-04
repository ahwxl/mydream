package com.shfft.mock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shfft.mock.service.OrderService;

@Controller
public class ApiController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(
            path = "/api/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String index() {
		
		orderService.print("大家号码！");
		
		return "你好";
	}
	
	@RequestMapping(
            path = "/api/table",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public String table() {
		
		orderService.print("大家号码！");
		
		return "table";
	}
	
	@RequestMapping(
            path = "/api/data",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String data() {
		
		return "{\r\n" + 
				"\"rows\":[{\"id\":\"123\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"124\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"125\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"126\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"127\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"128\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"129\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"1210\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"1211\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"1212\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"1213\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"1214\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"1215\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"1216\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"},\r\n" + 
				"	{\"id\":\"1217\",\"name\":\"张三\",\"sex\":\"0\",\"birthday\":326044800000,\"grade\":\"一年级四班\",\"address\":\"湖北省武汉市 430017江岸区沿江大道159号\"}],\r\n" + 
				"\"results\": 40\r\n" + 
				"}";
		
	}
	
	
	
}
