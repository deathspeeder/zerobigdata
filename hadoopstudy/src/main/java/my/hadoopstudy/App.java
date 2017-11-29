package my.hadoopstudy;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
import java.net.URI;

/**
 * Examples from http://blog.csdn.net/kongxx/article/details/42339581
 * 
 * @author Jun
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		String uri = "hdfs://192.168.1.11:8020/";
		Configuration config = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri), config);

		// 列出hdfs上/tmp目录下的所有文件和目录
		FileStatus[] statuses = fs.listStatus(new Path("/tmp"));
		for (FileStatus status : statuses) {
			System.out.println(status);
		}

		// 在hdfs的/目录下创建一个文件，并写入一行文本
		FSDataOutputStream os = fs.create(new Path("/tmp/test.log"));
		os.write("Hello World!".getBytes());
		os.flush();
		os.close();

		// 显示在hdfs的/下指定文件的内容
		InputStream is = fs.open(new Path("/tmp/test.log"));
		IOUtils.copyBytes(is, System.out, 1024, true);
	}
}
