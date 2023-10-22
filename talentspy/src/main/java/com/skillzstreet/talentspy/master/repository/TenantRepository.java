package com.skillzstreet.talentspy.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillzstreet.talentspy.master.entity.Tenant;

@Repository
public interface TenantRepository
        extends JpaRepository<Tenant, Long> {

    /**
     * Using a custom named query
     * @param tenantId
     * @return
     */
    
    Tenant findById(String id);
    
    Tenant findByName(String name);
}
