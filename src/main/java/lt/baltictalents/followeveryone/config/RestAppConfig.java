package lt.baltictalents.followeveryone.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "lt.baltictalents.followeveryone")
@EnableTransactionManagement
@PropertySource({"classpath:persistence-mysql.properties"})
public class RestAppConfig implements WebMvcConfigurer {

   @Autowired
   private Environment env;
   private Logger logger = Logger.getLogger(getClass().getName());

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/js/**").addResourceLocations("/js/");
      registry.addResourceHandler("/css/**").addResourceLocations("/css/");
   }

   @Bean
   public ViewResolver viewResolver() {
      InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
      viewResolver.setPrefix("/WEB-INF/view/");
      viewResolver.setSuffix(".jsp");
      return viewResolver;
   }

   @Bean
   public DataSource myDataSource() {
      // create connection pool
      ComboPooledDataSource myDataSource = new ComboPooledDataSource();
      // set the jdbc driver
      try {
         myDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
      } catch (PropertyVetoException exc) {
         throw new RuntimeException(exc);
      }

      logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
      logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
      // set database connection props
      myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
      myDataSource.setUser(env.getProperty("jdbc.user"));
      myDataSource.setPassword(env.getProperty("jdbc.password"));
      // set connection pool props
      myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
      myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
      myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
      myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

      return myDataSource;
   }

   private Properties getHibernateProperties() {
      // set hibernate properties
      Properties props = new Properties();
      props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
      props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      return props;
   }

   @Bean
   public LocalSessionFactoryBean sessionFactory(){
      // create session factory
      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
      // set the properties
      sessionFactory.setDataSource(myDataSource());
      sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
      sessionFactory.setHibernateProperties(getHibernateProperties());
      return sessionFactory;
   }

   @Bean
   @Autowired
   public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
      HibernateTransactionManager txManager = new HibernateTransactionManager();
      txManager.setSessionFactory(sessionFactory);
      return txManager;
   }

   private int getIntProperty(String propName) {
      String propVal = env.getProperty(propName);
      return Integer.parseInt(propVal);
   }
}