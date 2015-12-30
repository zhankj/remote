package cn.cttic.sysframe.frame.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cttic.sysframe.frame.domain.SmMenuFavorites;
import cn.cttic.sysframe.frame.domain.SmMenuFavoritesExample;

public interface SmMenuFavoritesMapper {
    int countByExample(SmMenuFavoritesExample example);

    int deleteByExample(SmMenuFavoritesExample example);

    int insert(SmMenuFavorites record);

    int insertSelective(SmMenuFavorites record);

    List<SmMenuFavorites> selectByExample(SmMenuFavoritesExample example);

    int updateByExampleSelective(@Param("record") SmMenuFavorites record, @Param("example") SmMenuFavoritesExample example);

    int updateByExample(@Param("record") SmMenuFavorites record, @Param("example") SmMenuFavoritesExample example);
}