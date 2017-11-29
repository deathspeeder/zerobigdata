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

		// �г�hdfs��/tmpĿ¼�µ������ļ���Ŀ¼
		FileStatus[] statuses = fs.listStatus(new Path("/tmp"));
		for (FileStatus status : statuses) {
			System.out.println(status);
		}

		// ��hdfs��/Ŀ¼�´���һ���ļ�����д��һ���ı�
		FSDataOutputStream os = fs.create(new Path("/tmp/test.log"));
		os.write("Hello World!".getBytes());
		os.flush();
		os.close();

		// ��ʾ��hdfs��/��ָ���ļ�������
		InputStream is = fs.open(new Path("/tmp/test.log"));
		IOUtils.copyBytes(is, System.out, 1024, true);
	}
}
