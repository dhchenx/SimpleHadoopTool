package cn.edu.bjtu.cdh.bigdata.hdfs;

import java.io.IOException;
import java.net.URI;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class CreateFlie {
	private static String HDFSUri = "hdfs://192.168.100.91:9000";
	
	public static FileSystem getFileSystem() {
        //读取配置文件
        Configuration conf = new Configuration();
        // 文件系统
        FileSystem fs = null;
        String hdfsUri = HDFSUri;
        if(StringUtils.isBlank(hdfsUri)){
            // 返回默认文件系统  如果在 Hadoop集群下运行，使用此种方法可直接获取默认文件系统
            try {
                fs = FileSystem.get(conf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // 返回指定的文件系统,如果在本地测试，需要使用此种方法获取文件系统
            try {
                URI uri = new URI(hdfsUri.trim());
                fs = FileSystem.get(uri,conf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fs;
    }
    
    
    /**
     * 创建文件目录
     *
     * @param path 文件路径
     */
    public static void mkdir(String path) {
        try {
            FileSystem fs = getFileSystem();
            System.out.println("FilePath="+path);
            // 创建目录
            
            fs.mkdirs(new Path(path));
            //释放资源
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    
    public static void main(String[] args){
    	mkdir("/test");
    }
   


}
