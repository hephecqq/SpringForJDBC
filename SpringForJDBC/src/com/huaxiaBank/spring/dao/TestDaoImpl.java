package com.huaxiaBank.spring.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestDaoImpl {
	ApplicationContext ctx;
	DataSource dataSource;
	JdbcTemplate jdbcTemplate;

	@Before
	public void initContext() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 获取数据源DataSource的Bean
		dataSource = ctx.getBean(DataSource.class);
		System.out.println("数据源连接成功..." + dataSource.getClass().getSimpleName());
		// 获取JdbcTemplate的Bean
		jdbcTemplate = ctx.getBean(JdbcTemplate.class);
		System.out.println("JdbcTemplate..." + JdbcTemplate.class.getSimpleName());
		Integer count = jdbcTemplate.queryForInt("select count(*) from student");
		System.out.println("学生的记录数为..." + count);
	}

	/**
	 * 测试执行批量更新：批量的INSERT,UPDATE,DELETE
	 */

	/**
	 * 测试从数据库中获取一条记录,实际得到对应的一个对象 queryForObject(String sql ,RowMapper
	 * <Employee> rowMapper,Object... args)
	 * 1.其中的RowMap普洱指定如何取映射结果集的行，常用的实现类为BeanPropertyRowMapper
	 * 2.使用SQL中的列的别名王城和类的属性名的映射
	 * 3.不支持级联属性的映射
	 */
	@Test
	public void testQueryForObject() {
		String sql = "select id,name,age from student where id=?";
		Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), 1);
		System.out.println("从数据库获取的Student对象..." + student);
	}

	@Test
	public void testBatchUpdate() {
		String sql = "INSERT INTO student(ID,NAME,AGE) values(?,?,?)";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[] {});
		batchArgs.add(new Object[] {});
		// ...
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	/**
	 * 测试执行INSERT,UPDATE,DELETE语句
	 */
	@Test
	public void test() {
		String sql = "update student set name=? where id > ?";
		jdbcTemplate.update(sql, "Jack", 1005);

	}

	@Test
	public void testGetAll() {
		DaoImpl impl = new DaoImpl();
		impl.getAll();
	}
}
