package com.tang.service;

import com.tang.base.RegionDao;
import com.tang.domain.Region;
import com.tang.utils.Page;
import com.tang.utils.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Tang Jiujia
 * @version: 2017/5/8 0008 22:15
 */
@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionDao regionDao;

    public void saveRegionList(List<Region> list){

        for(Region region:list){
            regionDao.add(region);
        }
    }

    public Page<Region> findBypage(Page<Region> page){
        return regionDao.findByPage(page,"from Region where 1=:p1",new Parameters(1));
    }

    public List<Region> findAllRegions(){
        return regionDao.findAll();
    }

    public List<Region> findByQ(String q){
        return regionDao.find("from Region where briefcode like :p1 or citycode like :p1" ,new Parameters("%"+q+"%"));
    }
}























