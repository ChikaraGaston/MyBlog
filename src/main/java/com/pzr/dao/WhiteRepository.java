package com.pzr.dao;

import com.pzr.po.White;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WhiteRepository extends JpaRepository<White, Long>, JpaSpecificationExecutor<White> {

    @Query("select w.ip from White w")
    List<String> findAllWhiteIp();
}
