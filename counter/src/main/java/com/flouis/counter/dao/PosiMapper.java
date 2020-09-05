package com.flouis.counter.dao;

import com.flouis.counter.entity.Posi;
import com.flouis.counter.vo.DashboardVo;

import java.util.List;

public interface PosiMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Posi record);

    Posi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Posi record);

	List<Posi> queryPosiListByPage(DashboardVo vo);
	Integer queryPosiTotal(DashboardVo vo);

	List<Posi> queryPosiList(DashboardVo vo);
}