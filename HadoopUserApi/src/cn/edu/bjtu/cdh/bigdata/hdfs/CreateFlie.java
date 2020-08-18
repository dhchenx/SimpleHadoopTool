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
        //��ȡ�����ļ�
        Configuration conf = new Configuration();
        // �ļ�ϵͳ
        FileSystem fs = null;
        String hdfsUri = HDFSUri;
        if(StringUtils.isBlank(hdfsUri)){
            // ����Ĭ���ļ�ϵͳ  ����� Hadoop��Ⱥ�����У�ʹ�ô��ַ�����ֱ�ӻ�ȡĬ���ļ�ϵͳ
            try {
                fs = FileSystem.get(conf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // ����ָ�����ļ�ϵͳ,����ڱ��ز��ԣ���Ҫʹ�ô��ַ�����ȡ�ļ�ϵͳ
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
     * �����ļ�Ŀ¼
     *
     * @param path �ļ�·��
     */
    public static void mkdir(String path) {
        try {
            FileSystem fs = getFileSystem();
            System.out.println("FilePath="+path);
            // ����Ŀ¼
            
            fs.mkdirs(new Path(path));
            //�ͷ���Դ
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    
    public static void main(String[] args){
    	mkdir("/test");
    }
   


}
