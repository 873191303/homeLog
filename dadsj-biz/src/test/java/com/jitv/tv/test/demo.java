package com.jitv.tv.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jitv.tv.Ttmertask.FileRead;

public class demo {
	
	public static void main(String[] args) {
        File root = new File("D:\\homelog\\1");
        File[] list = root.listFiles();
        List<File> file_list = new ArrayList<File>(10);
		
		
		
		String path = file_list.get(0).getPath();
		System.out.println(list);
	}

}
