package com.zxtech.ess.module.gen.mapper;

import com.zxtech.ess.module.gen.bean.BaseEmpCertificate;
import java.util.List;

public interface BaseEmpCertificateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_emp_certificate
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_emp_certificate
     *
     * @mbg.generated
     */
    int insert(BaseEmpCertificate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_emp_certificate
     *
     * @mbg.generated
     */
    int insertSelective(BaseEmpCertificate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_emp_certificate
     *
     * @mbg.generated
     */
    BaseEmpCertificate selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_emp_certificate
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(BaseEmpCertificate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_emp_certificate
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(BaseEmpCertificate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_emp_certificate
     *
     * @mbg.generated
     */
    List<BaseEmpCertificate> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_emp_certificate
     *
     * @mbg.generated
     */
    int deleteBySqlConditions(BaseEmpCertificate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_emp_certificate
     *
     * @mbg.generated
     */
    List<BaseEmpCertificate> selectBySqlConditions(BaseEmpCertificate record);
}