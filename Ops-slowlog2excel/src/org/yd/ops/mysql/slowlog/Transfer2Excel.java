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
		transfer("C:\\Users\\Glycinemax\\Desktop\\mysql-slow.txt", "d:\\2.xlsx");
		System.out.println("done");
	}

	private static List<List<String>> reader(String slowlogfile) throws IOException {
		List<List<String>> list = new ArrayList<>();
		List<String> obj = new ArrayList<>();

		BufferedReader br = new BufferedReader(new FileReader(slowlogfile));
		String line = null, lastLine = null, margerLine = null;
		try {
			while ((line = br.readLine()) != null) {
				System.out.println("line to:"+list.size());
				line = line.trim();
				if (line.startsWith("# Time:")) {
					obj.add(margerLine);
					list.add(obj);
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
			e.printStackTrace();
		}
		br.close();
		return list;
	}

	private static void transfer(String slowlogfile, String outputfile) throws IOException {
		EasyExcel.write(outputfile, RowObj.class).sheet("slow").doWrite(reader(slowlogfile));
	}
}
