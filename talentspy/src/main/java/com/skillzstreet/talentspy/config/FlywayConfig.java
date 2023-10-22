package com.skillzstreet.talentspy.config;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.skillzstreet.talentspy.master.repository.TenantRepository;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    public static String DEFAULT_SCHEMA = "DEFAULT_SCHEMA";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public Flyway flyway(DataSource dataSource) {
        logger.info("Migrating default schema ");
        
        
        
        Flyway flyway = new Flyway(Flyway.configure()
            	.dataSource(dataSource)
            	.locations("db/migration/default")
            	.schemas("public")
            	.baselineOnMigrate(true)
            	.baselineVersion("1")
            	);
        		
        flyway.migrate();
        return flyway;
    }

    @Bean
    public Boolean tenantsFlyway(TenantRepository repository, DataSource dataSource){
        repository.findAll().forEach(tenant -> {
        	logger.info("Migrating tenant schema: " + tenant.getDbName());
            Flyway flyway = new Flyway(Flyway.configure()
                	//.dataSource(dataSource)
            		.dataSource("jdbc:postgresql://localhost:5431/" + tenant.getDbName(), tenant.getDbUser(), tenant.getDbPassword())
                	.locations("db/migration/tenants")
                	.schemas("public")
                	.baselineOnMigrate(true)
                	.baselineVersion("1")
                	);
            flyway.migrate();
        });
        return true;
    }

}