package com.jiawa.wiki.mapper;

import com.jiawa.wiki.domain.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocMapperCust {
    public List<Test> increaseViewCount(@Param("id") Long id);
}
