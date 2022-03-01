package org.yd.ops.mysql.slowlog.dto;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode
@Data
@Getter
@Setter
public class RowObj {
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUserHost() {
		return userHost;
	}

	public void setUserHost(String userHost) {
		this.userHost = userHost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public String getLockTime() {
		return lockTime;
	}

	public void setLockTime(String lockTime) {
		this.lockTime = lockTime;
	}

	public String getRowsSent() {
		return rowsSent;
	}

	public void setRowsSent(String rowsSent) {
		this.rowsSent = rowsSent;
	}

	public String getRowsExamined() {
		return rowsExamined;
	}

	public void setRowsExamined(String rowsExamined) {
		this.rowsExamined = rowsExamined;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	@ExcelProperty("Time")
	private String time;
	
	@ExcelProperty("User@Host")
	private String userHost;
	
	@ExcelProperty("Id")
	private String id;
	
	@ExcelProperty("Query_time")
	private String queryTime;
	
	@ExcelProperty("Lock_time")
	private String lockTime;
	
	@ExcelProperty("Rows_sent")
	private String rowsSent;
	
	@ExcelProperty("Rows_examined")
	private String rowsExamined;
	
	@ExcelProperty("DB")
	private String db;
	
	@ExcelProperty("SQL")
	private String sql;
	
}
