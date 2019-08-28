package com.jitv.tv.Ttmertask;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import com.jitv.tv.util.PropertiesJitv;

public class FileRead {

	private final static String brasUrl = PropertiesJitv.getString("brasUrl");

	public static String unGzipFile(String sourcedir,String outFileName) {
		try {
			// 建立gzip压缩文件输入流
			FileInputStream fin = new FileInputStream(sourcedir);
			// 建立gzip解压工作流
			GZIPInputStream gzin = new GZIPInputStream(fin);
			File outdir = new File(brasUrl + java.io.File.separator);
			String path = extractFile(gzin, outdir, outFileName);
			gzin.close();
			fin.close();
			return path;
		} catch (Exception ex) {
			System.err.println(ex.toString());
		}
		return "";
	}

	// 将压缩文件解压到硬盘
	private static String extractFile(GZIPInputStream in, File outdir, String name) throws IOException {
		byte[] buffer = new byte[1024];
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outdir, name)));
		int count = -1;
		while ((count = in.read(buffer)) != -1) {
			out.write(buffer, 0, count);
		}
		out.close();
		return outdir.toString()+java.io.File.separator+name;
	}
	
	
	
    //find csv from folder
    public static List<File> walk( String path ) {
        File root = new File( path );
        File[] list = root.listFiles();
        List<File> file_list = new ArrayList<File>(10);
        //if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                file_list.add(f.getAbsoluteFile());
            }
            else {
            	file_list.add(f.getAbsoluteFile());
            }
        }
        return file_list;
    }
  //删除指定文件夹下所有文件
  //param path 文件夹完整绝对路径
     public static boolean delAllFile(String path) {
         boolean flag = false;
         File file = new File(path);
         if (!file.exists()) {
           return flag;
         }
         if (!file.isDirectory()) {
           return flag;
         }
         String[] tempList = file.list();
         File temp = null;
         for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
               temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
               temp.delete();
            }
            if (temp.isDirectory()) {
               delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
               //delFolder(path + "/" + tempList[i]);//再删除空文件夹
               flag = true;
            }
         }
         return flag;
       }

}
