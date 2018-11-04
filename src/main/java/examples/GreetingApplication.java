/*
 * Copyright 2016-2017 Testify Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package examples;

import static org.hibernate.cfg.AvailableSettings.DATASOURCE;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * Greeting Spring Boot Application.
 *
 * @author saden
 */

@ComponentScan("com.shfft.mock.service")

@ComponentScan("com.shfft.mock.web")

@ImportResource({ "classpath*:/sofa/comsumer.xml","classpath*:/sofa/provider.xml" })
@SpringBootApplication
public class GreetingApplication {

	public static void main(String[] args) throws Exception {
		GreetingApplication application = new GreetingApplication();

		application.run(args);
	}

	public void run(String[] args) {
		SpringApplication.run(GreetingApplication.class, args);
	}

	/*
	 * @Bean DataSource productionDataSource() { PGSimpleDataSource dataSource = new
	 * PGSimpleDataSource(); dataSource.setServerName("production.acme.com");
	 * dataSource.setPortNumber(5432); dataSource.setDatabaseName("postgres");
	 * dataSource.setUser("postgres"); dataSource.setPassword("mysecretpassword");
	 * 
	 * return dataSource; }
	 */

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			DataSource dataSource) {
		Map<String, Object> properties = new HashMap<>();
		properties.put(DATASOURCE, dataSource);

		return builder.dataSource(dataSource).persistenceUnit("example.greeter").properties(properties).build();
	}

	@Bean
	ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();

		Configuration configuration = mapper.getConfiguration();
		configuration.setMatchingStrategy(MatchingStrategies.STRICT);
		configuration.setFieldAccessLevel(Configuration.AccessLevel.PUBLIC);
		configuration.setMethodAccessLevel(Configuration.AccessLevel.PUBLIC);
		configuration.setAmbiguityIgnored(false);
		configuration.setDestinationNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
		configuration.setSourceNamingConvention(NamingConventions.JAVABEANS_ACCESSOR);

		return mapper;
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		tomcat.addAdditionalTomcatConnectors(createConnector());
		return tomcat;
	}
	
	private Connector createConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		try {
			connector.setScheme("http");
			connector.setSecure(true);
			connector.setPort(8181);
			return connector;
		} catch (Exception ex) {
			throw new IllegalStateException(
					"can't access keystore: [" + "keystore" + "] or truststore: [" + "keystore" + "]", ex);
		}
	}

	private Connector createSslConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
		try {
			File keystore = new ClassPathResource("keystore").getFile();
			File truststore = new ClassPathResource("keystore").getFile();
			connector.setScheme("https");
			connector.setSecure(true);
			connector.setPort(8443);
			protocol.setSSLEnabled(true);
			protocol.setKeystoreFile(keystore.getAbsolutePath());
			protocol.setKeystorePass("changeit");
			protocol.setTruststoreFile(truststore.getAbsolutePath());
			protocol.setTruststorePass("changeit");
			protocol.setKeyAlias("apitester");
			return connector;
		} catch (IOException ex) {
			throw new IllegalStateException(
					"can't access keystore: [" + "keystore" + "] or truststore: [" + "keystore" + "]", ex);
		}
	}
}
