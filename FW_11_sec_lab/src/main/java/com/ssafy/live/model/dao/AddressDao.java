package com.ssafy.live.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.live.model.dto.Address;

@Mapper
public interface AddressDao {
    public int insert(Address address);

    public int delete(int ano);

    public int deleteByMno(int mno);
}
