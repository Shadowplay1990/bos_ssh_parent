package com.tang.web.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tang.domain.Region;
import com.tang.domain.Subarea;
import com.tang.service.SubareaService;
import com.tang.utils.FileUtils;
import com.tang.utils.Page;
import com.tang.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/9 0009 15:20
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea>{

    @Autowired
    private SubareaService subareaService;
    private String dzId;

    public String addSubarea(){
        subareaService.addSubarea(getModel());
        return NONE;
    }

    public String listByPage(){
        DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
//        dc.setProjection(Projections.projectionList()
//                .add(Projections.distinct(Projections.property("provice")))
//                .add(Projections.distinct(Projections.property("city")))
//                .add(Projections.distinct(Projections.property("area"))));

        String addresskey = getModel().getAddresskey();
        if(StringUtils.isNotBlank(addresskey)){
            dc.add(Restrictions.like("addresskey",
                    addresskey, MatchMode.ANYWHERE));
        }

        Region region = getModel().getRegion();
        if(region!=null){
            String provice = region.getProvice();
            String city = region.getCity();
            String area = region.getArea();

            dc.createAlias("region","r");
            if(StringUtils.isNotBlank(provice)){
                dc.add(Restrictions.like("r.provice",
                        provice,MatchMode.ANYWHERE));
            }

            if(StringUtils.isNotBlank(city)){
                dc.add(Restrictions.like("r.city",
                        city,MatchMode.ANYWHERE));
            }

            if(StringUtils.isNotBlank(area)){
                dc.add(Restrictions.like("r.area",
                        area,MatchMode.ANYWHERE));
            }
        }

        Page<Subarea> pageBean2 = subareaService.findByCriteria(pageBean, dc);
        pageHelp.setTotal(pageBean2.getTotalCount());
        pageHelp.setRows(pageBean2.getList());
        String jsonStr=null;
        ObjectMapper mapper = new ObjectMapper();
        try{
               jsonStr=mapper.writeValueAsString(pageHelp);
               writeJsonBack(jsonStr);
            }catch (Exception e){
                e.printStackTrace();
            }
        return NONE;
    }

    public String exportExcel(){
        List<Subarea> list = subareaService.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("分区数据");
        HSSFRow titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("分区编号");
        titleRow.createCell(1).setCellValue("分区关键字");
        titleRow.createCell(2).setCellValue("分区地址信息");
        titleRow.createCell(3).setCellValue("省市区");

        for (Subarea s:list){
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(s.getSubareaId());
            dataRow.createCell(1).setCellValue(s.getAddresskey());
            dataRow.createCell(2).setCellValue(s.getPosition());
            dataRow.createCell(3).setCellValue(s.getRegion().getBriefcode());
        }

        String fileName="分区数据表.xls";
        String agent = getRequest().getHeader("User-Agent");

        ServletOutputStream out=null;
        try {
            FileUtils.encodeDownloadFilename(fileName,agent);
            out = getRespone().getOutputStream();
            String mimeType = ServletActionContext.getServletContext().getMimeType(fileName);
            getRespone().setContentType(mimeType);
            getRespone().setHeader("content-disposition", "attachment;filename="+fileName);
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    public String listAjax(){
        List<Subarea> list = subareaService.findNotAssociation();
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr=null;
        try{
                jsonStr=mapper.writeValueAsString(list);
            }catch (Exception e){
                e.printStackTrace();
            }
            writeJsonBack(jsonStr);
        return NONE;
    }

    public String findByDzId(){
        List<Subarea> list = subareaService.findByDzId(dzId);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr=null;
        try{
              jsonStr=mapper.writeValueAsString(list);
            }catch (Exception e){
                e.printStackTrace();
            }
            writeJsonBack(jsonStr);
        return NONE;
    }

    public String getDzId() {
        return dzId;
    }

    public void setDzId(String dzId) {
        this.dzId = dzId;
    }
}
























