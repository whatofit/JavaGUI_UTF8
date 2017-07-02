package com.myblog.components.tree;

//Java 多叉树的实现，完成树的初始化和遍历。包括两个文件（TreeNode.java和TreeHelper.java） 
//TreeNode类完成树节点的数据结构，TreeHelper类通过输入一个TreeNode列表，生成一颗有一个树根的树！其它函数接口自己看看就明白了，希望对你有帮助。 

//三、打印
import java.io.*;
import java.io.File;

class FileTree {
	public static void main(String[] arg) {
		if (arg.length == 0) {
			System.out.println("请加上路径参数如: c:\\windows");
		}
		try {
		    //String path =arg[0];
		    String path ="c:\\windows";
		    File file = new File(path);
			System.out.println(file);
			madetree(0, file);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}
	}

	static void madetree(int hierarchy, File dothis) {
		File[] farray = dothis.listFiles();
		for (int i = 0; i < farray.length; i++) {
			int k = 0;
			while (k < hierarchy) {
				System.out.print("┃");
				k++;
			}
			if (i == farray.length - 1)
				System.out.print("┗");
			else
				System.out.print("┣");
			
			if (farray[i].isDirectory())
				System.out.println("[" + farray[i].getName() + "]");
			else
				System.out.println(farray[i].getName());
			
			if (farray[i].isDirectory())
				madetree(hierarchy + 1, farray[i]);
		}
	}
}

