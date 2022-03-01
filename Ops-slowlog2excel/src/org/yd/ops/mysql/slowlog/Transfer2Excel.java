package org.yd.ops.mysql.slowlog;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yd.ops.mysql.slowlog.dto.RowObj;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;

public class Transfer2Excel {

	public static void main(String[] args) throws IOException {
		System.out.println("init");
		write();
		System.out.println("done");
	}

	private static List<List<String>> reader() throws IOException {
		List<List<String>> list = new ArrayList<>();
		list.add(Arrays.asList("a1111", "b2222", "", "d3444"));
		List<String> obj = new ArrayList<>();

		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Glycinemax\\Desktop\\mysql-slow.txt"));
		String line = null, lastLine = null, margerLine = null;
		try {
			while ((line = br.readLine()) != null) {
				System.out.println("line to:"+list.size());
//				if (line.isEmpty())
//					break;
				line = line.trim();
				if (line.startsWith("# Time:")) {
					// 结束上一行
					obj.add(margerLine);
					list.add(obj);
					// 开始新的行
					margerLine = "";
					obj = new ArrayList<>();
					obj.add(line.replace("# Time: ", ""));
				}
				if (line.startsWith("# User@Host: ")) {
					obj.add(line.substring(0, line.indexOf("Id: ")).replace("# User@Host: ", ""));
					obj.add(line.substring(line.indexOf("Id: ")).replace("Id: ", ""));
				}
				if (line.startsWith("# Query_time:")) {
					obj.add(line.substring(0, line.indexOf("Lock_time: ")).replace("# Query_time: ", ""));
					obj.add(line.substring(line.indexOf("Lock_time: "), line.indexOf("Rows_sent: "))
							.replace("Lock_time: ", ""));
					obj.add(line.substring(line.indexOf("Rows_sent: "), line.indexOf("Rows_examined: "))
							.replace("Rows_sent: ", ""));
					obj.add(line.substring(line.indexOf("Rows_examined: ")).replace("Rows_examined: ", ""));
				}
				if (line.toLowerCase().startsWith("use")) {
					obj.add(line.toLowerCase().replace("use ", ""));
				} else if (lastLine != null && lastLine.startsWith("# Query_time:")) {
					obj.add("");
				}
				if (!line.startsWith("# "))
					margerLine += line;
				lastLine = line;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br.close();
		return list;
	}

	private static void write() throws IOException {
		String fileName = "d:\\2.xlsx";
		List<HashMap> list = new ArrayList<>();
		EasyExcel.write(fileName, RowObj.class).sheet("slow1").doWrite(reader());
	}
}
