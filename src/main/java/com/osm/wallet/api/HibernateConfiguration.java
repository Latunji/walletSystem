package com.osm.wallet.api;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
    @Value("${db.driver}")
    private String DRIVER;

    @Value("${db.password}")
    private String PASSWORD;

    @Value("${db.url}")
    private String URL;

    @Value("${db.username}")
    private String USERNAME;

    @Value("${hibernate.dialect}")
    private String DIALECT;

    @Value("${hibernate.show_sql}")
    private String SHOW_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HBM2DDL_AUTO;

    @Value("${entitymanager.packagesToScan}")
    private String PACKAGES_TO_SCAN;


    @Value("${hibernate.jdbc.batch_size}")
    private int JDBC_BATCH_SIZE;

    @Value("${hibernate.jdbc.fetch_size}")
    private int JDBC_FETCH_SIZE;

    @Value("${cache.provider_class}")
    private String CACHE_PROVIDER;

    @Value("${cache.use_query_cache}")
    private boolean USE_QUERY_CACHE;

    @Value("${max_fetch_depth}")
    private int FETCH_DEPTH;

    @Value("${cache.use_minimal_puts}")
    private boolean USE_MINIMAL_PUTS;

    @Value("${hibernate.jdbc.use_streams_for_binary}")
    private boolean USE_STREAMS_FOR_BINARY;


    @Bean
    public DataSource dataSource() {

        final HikariDataSource dataSource = new HikariDataSource();
       // dataSource.setIdleTimeout();
        
        dataSource.setMaximumPoolSize(60);
        dataSource.setDriverClassName(DRIVER);


        dataSource.setJdbcUrl(URL);
        dataSource.addDataSourceProperty("user", USERNAME);
        dataSource.addDataSourceProperty("password", PASSWORD);
        dataSource.addDataSourceProperty("cachePrepStmts", true);
        dataSource.addDataSourceProperty("prepStmtCacheSize", 250);
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        dataSource.addDataSourceProperty("useServerPrepStmts", true);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", DIALECT);
        hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);

        hibernateProperties.put("hibernate.jdbc.batch_size", JDBC_BATCH_SIZE);
        hibernateProperties.put("hibernate.jdbc.fetch_size", JDBC_FETCH_SIZE);

        hibernateProperties.put("cache.provider_class", CACHE_PROVIDER);
        hibernateProperties.put("hibernate.max_fetch_depth", FETCH_DEPTH);
        hibernateProperties.put("cache.use_minimal_puts", USE_MINIMAL_PUTS);
        hibernateProperties.put("hibernate.jdbc.use_streams_for_binary", USE_STREAMS_FOR_BINARY);

        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean(name="messageSource")
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
}
