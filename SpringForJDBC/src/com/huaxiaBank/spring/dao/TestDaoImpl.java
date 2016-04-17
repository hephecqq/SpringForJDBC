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
		// ��ȡ����ԴDataSource��Bean
		dataSource = ctx.getBean(DataSource.class);
		System.out.println("����Դ���ӳɹ�..." + dataSource.getClass().getSimpleName());
		// ��ȡJdbcTemplate��Bean
		jdbcTemplate = ctx.getBean(JdbcTemplate.class);
		System.out.println("JdbcTemplate..." + JdbcTemplate.class.getSimpleName());
		Integer count = jdbcTemplate.queryForInt("select count(*) from student");
		System.out.println("ѧ���ļ�¼��Ϊ..." + count);
	}

	/**
	 * ����ִ���������£�������INSERT,UPDATE,DELETE
	 */

	/**
	 * ���Դ����ݿ��л�ȡһ����¼,ʵ�ʵõ���Ӧ��һ������ queryForObject(String sql ,RowMapper
	 * <Employee> rowMapper,Object... args)
	 * 1.���е�RowMap�ն�ָ�����ȡӳ���������У����õ�ʵ����ΪBeanPropertyRowMapper
	 * 2.ʹ��SQL�е��еı������Ǻ������������ӳ��
	 * 3.��֧�ּ������Ե�ӳ��
	 */
	@Test
	public void testQueryForObject() {
		String sql = "select id,name,age from student where id=?";
		Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), 1);
		System.out.println("�����ݿ��ȡ��Student����..." + student);
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
	 * ����ִ��INSERT,UPDATE,DELETE���
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
