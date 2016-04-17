package com.huaxiaBank.spring.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DaoImpl implements BaseDao {

	private JdbcTemplate jdbcTemplate=null;

	@Autowired
	public void setDataSource(DataSource dataSource) {
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//		try {
//			System.out.println(dataSource.getConnection());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//System.out.println(dataSource);
//		System.out.println(jdbcTemplate);
	}

	@Override
	public void getAll() {
		System.out.println(jdbcTemplate);
		Integer count = this.jdbcTemplate.queryForInt("select count(*) from student", Integer.class);
		System.out.println(count);

		// String sql = "select count(*) from student";
		// System.out.println();
		// System.out.println(jdbcTemplate);
		// Integer count = jdbcTemplate.queryForInt(sql);

	}

}
