package com.auxiliarybus.config.datasource.mysql;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by wangchaohui on 2019/1/3
 */
@Configuration
@MapperScan(basePackages = "com.auxiliarybus.mapper", sqlSessionTemplateRef = "auxiliarybusSqlSessionTemplate")
@PropertySource(value = "classpath:datasource.properties")
public class DataSourceConfig {

    @Bean(name = "auxiliarybusDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.auxiliarybus")
    public DataSource auxiliarybusDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "auxiliarybusSqlSessionFactory")
    public SqlSessionFactory auxiliarybusSqlSessionFactory(@Qualifier("auxiliarybusDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/auxiliarybus/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "auxiliarybusSqlSessionTemplate")
    public SqlSessionTemplate auxiliarybusSqlSessionTemplate(@Qualifier("auxiliarybusSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "auxiliarybusTransaction")
    public DataSourceTransactionManager auxiliarybusTransaction(@Qualifier("auxiliarybusDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
