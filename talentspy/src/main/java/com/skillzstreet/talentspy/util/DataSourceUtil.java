package com.skillzstreet.talentspy.util;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skillzstreet.talentspy.master.entity.Tenant;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Utility class for DataSource
 * 
 * @author Sunit Katkar, sunitkatkar@gmail.com
 *         (https://sunitkatkar.blogspot.com/)
 * @since ver 1.0 (May 2018)
 * @version 1.0
 *
 */
public final class DataSourceUtil {

    private static final Logger LOG = LoggerFactory
            .getLogger(DataSourceUtil.class);

    /**
     * Utility method to create and configure a data source
     * 
     * @param masterTenant
     * @return
     */
    public static DataSource createAndConfigureDataSource(
            Tenant masterTenant) {
        HikariDataSource ds = new HikariDataSource();
        ds.setUsername(masterTenant.getDbUser());
        ds.setPassword(masterTenant.getDbPassword());
        ds.setJdbcUrl("jdbc:postgresql://localhost:5431/" + masterTenant.getDbName());
        ds.setDriverClassName("org.postgresql.Driver");

        // HikariCP settings - could come from the master_tenant table but
        // hardcoded here for brevity
        // Maximum waiting time for a connection from the pool
        ds.setConnectionTimeout(20000);

        // Minimum number of idle connections in the pool
        ds.setMinimumIdle(10);

        // Maximum number of actual connection in the pool
        ds.setMaximumPoolSize(20);

        // Maximum time that a connection is allowed to sit idle in the pool
        ds.setIdleTimeout(300000);
        ds.setConnectionTimeout(20000);

        // Setting up a pool name for each tenant datasource
        String tenantId = masterTenant.getId();
        String tenantConnectionPoolName = tenantId + "-connection-pool";
        ds.setPoolName(tenantConnectionPoolName);
        LOG.info("Configured datasource:" + masterTenant.getId()
                + ". Connection poolname:" + tenantConnectionPoolName);
        return ds;
    }
}
