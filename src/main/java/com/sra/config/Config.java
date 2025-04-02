package com.sra.config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = "com/sra")
public class Config {

    //UI messages colours
    public static final String YELLOW_COLOUR ="\u001B[33m";
    public static final String GREEN_COLOUR ="\u001B[32m";
    public static final String RESET_COLOUR = "\u001B[0m";
    public static final String RED_COLOUR = "\u001B[31m";

    //UI messages texts
    public static final String WRONG_INPUT_MESSAGE = (RED_COLOUR + "Unacceptable input! Please try again." + RESET_COLOUR);
    public static final String NO_SPACE_WITH_NAME = (RED_COLOUR + "There is no space with such name." + RESET_COLOUR);
    public static final String EXIT_MESSAGE = (GREEN_COLOUR + "You have exited the application. See you next time!" + RESET_COLOUR);
    public static final String NO_EXISTING_SPACES = (YELLOW_COLOUR + "There are no existing spaces." + RESET_COLOUR);
    public static final String NO_EXISTING_RESERVATIONS = (YELLOW_COLOUR + "There are no existing reservations." + RESET_COLOUR);
    public static final String NO_EXISTING_CUSTOMERS = (YELLOW_COLOUR + "There are no existing customers." + RESET_COLOUR);
    public static final String WRONG_INPUT_EXCEPTION = (RED_COLOUR + "SYSTEM: Input Exception caught!" + RESET_COLOUR);
    public static final String EMPTY_INPUT = (RED_COLOUR + "Such input is not allowed. Please use letters and/or digits." + RESET_COLOUR);
    public static final String NO_CUSTOMER_WITH_NAME = (RED_COLOUR + "There is no customer with such name." + RESET_COLOUR);

    //Database connection properties
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/sra_db";
    private static final String USER = "root";
    private static final String PASSWORD = "SimbrionRoot321!";

    @Bean
    public DataSource emfDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(JDBC_DRIVER);
        dataSource.setUrl(DATABASE_URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    @Primary
    @Bean
    public DataSource hikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(DATABASE_URL);
        hikariConfig.setUsername(USER);
        hikariConfig.setPassword(PASSWORD);
        hikariConfig.setDriverClassName(JDBC_DRIVER);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setMaximumPoolSize(20);
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setMaxLifetime(1800000);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setAutoCommit(true);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(DataSource emfDataSource, @Qualifier("getHibernateProperties") Properties hibernateProperties, JpaVendorAdapter adapter) {
            LocalContainerEntityManagerFactoryBean emfFactoryBean = new LocalContainerEntityManagerFactoryBean();
            emfFactoryBean.setDataSource(emfDataSource);
            emfFactoryBean.setPackagesToScan("com.sra");
            emfFactoryBean.setJpaVendorAdapter(adapter);
            emfFactoryBean.setJpaProperties(hibernateProperties);
            return emfFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

}

