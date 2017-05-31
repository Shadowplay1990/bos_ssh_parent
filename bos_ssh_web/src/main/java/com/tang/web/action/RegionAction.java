package com.tang.web.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tang.domain.Region;
import com.tang.domain.RegionHelper;
import com.tang.service.RegionService;
import com.tang.utils.Page;
import com.tang.utils.PinYin4jUtils;
import com.tang.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/8 0008 17:51
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{


    @Autowired
    private RegionService regionService;
    private File regionFile;
    private String q;

    public String uploadXls(){
        ArrayList<Region> list = new ArrayList<>();
        String f="1";
        try{
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
            HSSFSheet sheet1 = workbook.getSheet("Sheet1");
            for(Row row:sheet1){
                int rowNum=row.getRowNum();
                if(rowNum==0){
                    continue;
                }

                String id = row.getCell(0).getStringCellValue();
                String province = row.getCell(1).getStringCellValue();
                String city = row.getCell(2).getStringCellValue();
                String area = row.getCell(3).getStringCellValue();
                String postCode = row.getCell(4).getStringCellValue();

                //生成简码和城市编码
                String province2=province.substring(0,province.length()-1);
                String city2=city.substring(1,city.length()-1);
                String area2=area.substring(1,area.length()-1);

                String[] strs = PinYin4jUtils.
                        getHeadByString(province2 + city2 + area2);
                String shotCode = StringUtils.join(strs, "");

                String cityCode = PinYin4jUtils.hanziToPinyin(city, "");
                Region region = new Region();
                region.setId(id);
                region.setArea(area);
                region.setPostcode(postCode);
                region.setCity(city);
                region.setProvice(province);
                region.setBriefcode(shotCode);
                region.setCitycode(cityCode);

                list.add(region);
            }
            regionService.saveRegionList(list);
        }catch (Exception e){
            e.printStackTrace();
            f="0";
        }

        getRespone().setContentType("text/html;charset=utf-8");
        try {
            getRespone().getWriter().write(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    public String listRegions(){
        Page<Region> pageBean2 = regionService.findBypage(pageBean);
        pageHelp.setRows(pageBean2.getList());
        pageHelp.setTotal(pageBean2.getTotalCount());
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr=null;
        try{
              jsonStr=mapper.writeValueAsString(pageHelp);
            }catch (Exception e){
                e.printStackTrace();
            }
            writeJsonBack(jsonStr);
        return NONE;
    }

    public String listajax(){

        List<Region> list=null;
        if(StringUtils.isNotBlank(q)){
           list=regionService.findByQ(q);
        }else{
           list= regionService.findAllRegions();
        }

        List<RegionHelper> regionHelpers=new ArrayList<>();
        for(Region region:list){
            RegionHelper helper = new RegionHelper();
            helper.setId(region.getId());
            helper.setName(region.getProvice()+" "+
                    region.getCity()+" "+region.getArea());
            regionHelpers.add(helper);
        }

        String jsonStr=null;
        ObjectMapper mapper=new ObjectMapper();
        try{
             jsonStr=mapper.writeValueAsString(regionHelpers);
            }catch (Exception e){
                e.printStackTrace();
            }
            writeJsonBack(jsonStr);
        return NONE;
    }

    public File getRegionFile() {
        return regionFile;
    }

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
































