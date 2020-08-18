package cn.edu.bjtu.cdh.bigdata.hdfs;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import cn.edu.bjtu.cdh.bigdata.hdfs.CreateFlie;

public class ExistDir {
	
	public static  boolean existDir(String filePath, boolean create){
        boolean flag = false;

        if (StringUtils.isEmpty(filePath)){
            return flag;
        }

        try{
            Path path = new Path(filePath);
            // FileSystem∂‘œÛ
            FileSystem fs = CreateFlie.getFileSystem();

            if (create){
                if (!fs.exists(path)){
                    fs.mkdirs(path);
                }
            }

            if (fs.isDirectory(path)){
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

}
