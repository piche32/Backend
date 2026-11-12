package com.ssafy.live.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.live.model.dto.Address;

@Mapper
public interface AddressDao {
    public int insert(Address address);

    public int delete(@Param("ano") int ano);

    public int deleteByMno(@Param("mno") int mno);

    public Address select(@Param("ano") int ano);

    public Address selectDetail(@Param("ano") int ano);

    // map의 key 속성을 where의 조건으로, value를 조건의 값으로
    public List<Address> queryStructureChange(Map<String, Object> param);

    public List<Address> dynamicSelect(Map<String, Object> param);

    public List<Address> selectUseIn(@Param("titles") List<String> titles);

    public int dynamicUpdate(Address address);

    public List<Address> carefulQuery(@Param("addr") String addr, @Param("mno") int mno);
}
