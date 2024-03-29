package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.ItemBom;
import java.util.List;

public interface ItemBomMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_bom
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_bom
     *
     * @mbg.generated
     */
    int insert(ItemBom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_bom
     *
     * @mbg.generated
     */
    int insertSelective(ItemBom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_bom
     *
     * @mbg.generated
     */
    ItemBom selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_bom
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ItemBom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_bom
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ItemBom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_bom
     *
     * @mbg.generated
     */
    List<ItemBom> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_bom
     *
     * @mbg.generated
     */
    int deleteBySqlConditions(ItemBom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_bom
     *
     * @mbg.generated
     */
    List<ItemBom> selectBySqlConditions(ItemBom record);
}