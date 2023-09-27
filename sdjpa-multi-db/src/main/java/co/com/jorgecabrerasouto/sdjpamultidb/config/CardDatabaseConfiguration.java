package co.com.jorgecabrerasouto.sdjpamultidb.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import co.com.jorgecabrerasouto.sdjpamultidb.domain.creditcard.CreditCard;

@EnableJpaRepositories(basePackages = "co.com.jorgecabrerasouto.sdjpamultidb.repositories.creditcard",
entityManagerFactoryRef = "cardEntityManagerFactory", transactionManagerRef = "cardTransactionManager")
@Configuration
public class CardDatabaseConfiguration {

	@Bean
	@ConfigurationProperties("spring.card.datasource")
	public DataSourceProperties cardDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource cardDataSource(
			@Qualifier("cardDataSourceProperties") DataSourceProperties cardDataSourceProperties) {
		return cardDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean cardEntityManagerFactory(
			@Qualifier("cardDataSource") DataSource cardDataSource, EntityManagerFactoryBuilder builder) {
		return builder.dataSource(cardDataSource).packages(CreditCard.class).persistenceUnit("card").build();
	}

	@Bean
	public PlatformTransactionManager cardTransactionManager(
			@Qualifier("cardEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardEntityManagerFactory) {

		return new JpaTransactionManager(cardEntityManagerFactory.getObject());
	}

}