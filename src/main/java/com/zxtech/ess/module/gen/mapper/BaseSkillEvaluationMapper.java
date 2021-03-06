package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.BaseSkillEvaluation;
import java.util.List;

public interface BaseSkillEvaluationMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_skill_evaluation
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_skill_evaluation
	 * @mbg.generated
	 */
	int insert(BaseSkillEvaluation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_skill_evaluation
	 * @mbg.generated
	 */
	int insertSelective(BaseSkillEvaluation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_skill_evaluation
	 * @mbg.generated
	 */
	BaseSkillEvaluation selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_skill_evaluation
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(BaseSkillEvaluation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_skill_evaluation
	 * @mbg.generated
	 */
	int updateByPrimaryKey(BaseSkillEvaluation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_skill_evaluation
	 * @mbg.generated
	 */
	List<BaseSkillEvaluation> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_skill_evaluation
	 * @mbg.generated
	 */
	int deleteBySqlConditions(BaseSkillEvaluation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table base_skill_evaluation
	 * @mbg.generated
	 */
	List<BaseSkillEvaluation> selectBySqlConditions(BaseSkillEvaluation record);
}