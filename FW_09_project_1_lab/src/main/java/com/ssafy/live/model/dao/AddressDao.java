package com.ssafy.live.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.live.model.dto.Address;

@Mapper
public interface AddressDao {
    public int insert(Address address);

    public int delete(@Param("ano") int ano);

    public int deleteByMno(@Param("mno") int mno);
}
