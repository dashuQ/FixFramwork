package com.example.administrator.fixframwork.ok;

import com.example.administrator.fixframwork.Replace;

public class Caclutor {
	//模拟服务器
	@Replace(clazz = "com.example.administrator.fixframwork.Caclutor",method = "caculator")
	public String caculator()
	{
		return "通过系统源码层的热修复，终于在老板发现之前，把bug修复好了";
	}
}
