package io.susimsek.filteringexample.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import tech.jhipster.config.liquibase.SpringLiquibaseUtil;

import javax.sql.DataSource;
import java.util.concurrent.Executor;

@EnableConfigurationProperties(LiquibaseProperties.class)
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Configuration
public class LiquibaseConfig {

    final Environment env;

    @Bean
    public SpringLiquibase liquibase(
        @Qualifier("taskExecutor") Executor executor,
        @LiquibaseDataSource ObjectProvider<DataSource> liquibaseDataSource,
        LiquibaseProperties liquibaseProperties,
        ObjectProvider<DataSource> dataSource,
        DataSourceProperties dataSourceProperties
    ) {
        // If you don't want Liquibase to start asynchronously, substitute by this:
        // SpringLiquibase liquibase = SpringLiquibaseUtil.createSpringLiquibase(liquibaseDataSource.getIfAvailable(), liquibaseProperties, dataSource.getIfUnique(), dataSourceProperties);
        SpringLiquibase liquibase = SpringLiquibaseUtil.createAsyncSpringLiquibase(
            this.env,
            executor,
            liquibaseDataSource.getIfAvailable(),
            liquibaseProperties,
            dataSource.getIfUnique(),
            dataSourceProperties
        );
        liquibase.setChangeLog("classpath:liquibase/master.xml");
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setLiquibaseSchema(liquibaseProperties.getLiquibaseSchema());
        liquibase.setLiquibaseTablespace(liquibaseProperties.getLiquibaseTablespace());
        liquibase.setDatabaseChangeLogLockTable(liquibaseProperties.getDatabaseChangeLogLockTable());
        liquibase.setDatabaseChangeLogTable(liquibaseProperties.getDatabaseChangeLogTable());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setLabels(liquibaseProperties.getLabels());
        liquibase.setChangeLogParameters(liquibaseProperties.getParameters());
        liquibase.setRollbackFile(liquibaseProperties.getRollbackFile());
        liquibase.setTestRollbackOnUpdate(liquibaseProperties.isTestRollbackOnUpdate());
        if (env.acceptsProfiles(Profiles.of("no-liquibase"))) {
            liquibase.setShouldRun(false);
        } else {
            liquibase.setShouldRun(liquibaseProperties.isEnabled());
            log.debug("Configuring Liquibase");
        }
        return liquibase;
    }
}
