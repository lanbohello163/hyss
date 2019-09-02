package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.MtItem;
import java.util.List;

public interface MtItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_item
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_item
     *
     * @mbg.generated
     */
    int insert(MtItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_item
     *
     * @mbg.generated
     */
    int insertSelective(MtItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_item
     *
     * @mbg.generated
     */
    MtItem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MtItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MtItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_item
     *
     * @mbg.generated
     */
    List<MtItem> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_item
     *
     * @mbg.generated
     */
    int deleteBySqlConditions(MtItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mt_item
     *
     * @mbg.generated
     */
    List<MtItem> selectBySqlConditions(MtItem record);
}