package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTests {
  
  //의존성주입
  @Autowired
  private DataSource dataSource;
  
  //root-context.xml에 의해 의존주입을 받는다.
  @Autowired
  private SqlSessionFactory sqlSessionFactory;
  
  @Test
  public void testConnection() {
    
    //JDBCTests 클래스와 비교해서 코드가 얼마나 간편해졌는지 확인해보자
    try(
        //mybatis
        SqlSession session = sqlSessionFactory.openSession();
        //hikaricp
        Connection con = dataSource.getConnection()
    ){
      log.info(session);
      log.info(con);
      
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
  
}
