package com.ruoyi.system.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.domain.TMedicalCostOutpatientInfo;
import com.ruoyi.system.domain.dto.WaitDto;
import com.ruoyi.system.service.ITMedicalCostOutpatientInfoService;
import com.ruoyi.system.service.ITMedicalCostPersonArchivesWaitService;
import net.sf.jsqlparser.statement.select.Wait;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.TMedicalCostPersonArchives;
import com.ruoyi.system.service.ITMedicalCostPersonArchivesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 个人档案Controller
 * 
 * @author ruoyi
 * @date 2022-09-01
 */
@RestController
@RequestMapping("/system/archives")
public class TMedicalCostPersonArchivesController extends BaseController
{
    @Autowired
    private ITMedicalCostPersonArchivesService tMedicalCostPersonArchivesService;

    @Autowired
    private ITMedicalCostOutpatientInfoService tMedicalCostOutpatientInfoService;

    @Resource
    private ITMedicalCostPersonArchivesWaitService tMedicalCostPersonArchivesWaitService;

    /**
     * 查询个人档案列表
     */
    @PreAuthorize("@ss.hasPermi('system:archives:list')")
    @GetMapping("/list")
    public TableDataInfo list(TMedicalCostPersonArchives tMedicalCostPersonArchives)
    {
        startPage();
        List<TMedicalCostPersonArchives> list = tMedicalCostPersonArchivesService.selectTMedicalCostPersonArchivesList(tMedicalCostPersonArchives);
        return getDataTable(list);
    }

    /**
     * 导出个人档案列表
     */
    @PreAuthorize("@ss.hasPermi('system:archives:export')")
    @Log(title = "个人档案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TMedicalCostPersonArchives tMedicalCostPersonArchives)
    {
        List<TMedicalCostPersonArchives> list = tMedicalCostPersonArchivesService.selectTMedicalCostPersonArchivesList(tMedicalCostPersonArchives);
        ExcelUtil<TMedicalCostPersonArchives> util = new ExcelUtil<TMedicalCostPersonArchives>(TMedicalCostPersonArchives.class);
        util.exportExcel(response, list, "个人档案数据");
    }

    /**
     * 获取个人档案详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:archives:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tMedicalCostPersonArchivesService.selectTMedicalCostPersonArchivesById(id));
    }

    /**
     * 新增个人档案
     */
    @PreAuthorize("@ss.hasPermi('system:archives:add')")
    @Log(title = "个人档案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TMedicalCostPersonArchives tMedicalCostPersonArchives)
    {
        String id = UUID.randomUUID().toString().replace("-", "")+tMedicalCostPersonArchives.getCode().substring(tMedicalCostPersonArchives.getCode().length()-4);
        tMedicalCostPersonArchives.setId(id);
        return toAjax(tMedicalCostPersonArchivesService.insertTMedicalCostPersonArchives(tMedicalCostPersonArchives));
    }

    /**
     * 新增门诊卡信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "门诊卡信息", businessType = BusinessType.INSERT)
    @PostMapping("/addInfo")
    public AjaxResult addInfo(@RequestBody WaitDto waitDto)
    {
        System.out.println(waitDto.getWait());
        System.out.println(waitDto.getInfo());
        tMedicalCostOutpatientInfoService.wait(waitDto.getWait());
        return toAjax(tMedicalCostOutpatientInfoService.insertTMedicalCostOutpatientInfo(waitDto.getInfo()));
    }

    /**
     * 修改个人档案
     */
    @PreAuthorize("@ss.hasPermi('system:archives:edit')")
    @Log(title = "个人档案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TMedicalCostPersonArchives tMedicalCostPersonArchives)
    {
        return toAjax(tMedicalCostPersonArchivesService.updateTMedicalCostPersonArchives(tMedicalCostPersonArchives));
    }

    /**
     * 删除个人档案
     */
    @PreAuthorize("@ss.hasPermi('system:archives:remove')")
    @Log(title = "个人档案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tMedicalCostPersonArchivesService.deleteTMedicalCostPersonArchivesByIds(ids));
    }
}
