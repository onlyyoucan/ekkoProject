package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TMedicalCostOutpatientInfo;
import com.ruoyi.system.domain.TMedicalCostPersonArchives;
import com.ruoyi.system.domain.TMedicalCostPersonArchivesWait;
import com.ruoyi.system.domain.dto.WaitDto;

/**
 * 门诊卡信息Service接口
 * 
 * @author ruoyi
 * @date 2022-09-01
 */
public interface ITMedicalCostOutpatientInfoService 
{
    /**
     * 查询门诊卡信息
     * 
     * @param id 门诊卡信息主键
     * @return 门诊卡信息
     */
    public TMedicalCostOutpatientInfo selectTMedicalCostOutpatientInfoById(Long id);

    /**
     * 查询门诊卡信息列表
     * 
     * @param tMedicalCostOutpatientInfo 门诊卡信息
     * @return 门诊卡信息集合
     */
    public List<TMedicalCostOutpatientInfo> selectTMedicalCostOutpatientInfoList(TMedicalCostOutpatientInfo tMedicalCostOutpatientInfo);

    /**
     * 新增门诊卡信息
     * 
     * @param tMedicalCostOutpatientInfo 门诊卡信息
     * @return 结果
     */
    public int insertTMedicalCostOutpatientInfo(TMedicalCostOutpatientInfo tMedicalCostOutpatientInfo);

    /**
     * 修改门诊卡信息
     * 
     * @param tMedicalCostOutpatientInfo 门诊卡信息
     * @return 结果
     */
    public int updateTMedicalCostOutpatientInfo(TMedicalCostOutpatientInfo tMedicalCostOutpatientInfo);

    /**
     * 批量删除门诊卡信息
     * 
     * @param ids 需要删除的门诊卡信息主键集合
     * @return 结果
     */
    public int deleteTMedicalCostOutpatientInfoByIds(Long[] ids);

    /**
     * 删除门诊卡信息信息
     * 
     * @param id 门诊卡信息主键
     * @return 结果
     */
    public int deleteTMedicalCostOutpatientInfoById(Long id);

    public int Jiandang(TMedicalCostPersonArchives tMedicalCostPersonArchives);

    public int wait(TMedicalCostPersonArchivesWait tmedicalCostPersonArchivesWait);
}
