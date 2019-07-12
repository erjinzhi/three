package com.xcy.controller;

import com.xcy.dao.AreaMapper;
import com.xcy.pojo.Area;
import com.xcy.utils.JedisClient;
import com.xcy.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AreaController {

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private JedisClient jedisClient;

    @RequestMapping("/getProvince")
    @ResponseBody
    public List<Area> getProvince(){

	   int a = 1100;

	   int b = 10;
	   int c = a - b;
        System.out.println("laoyantianjia");
        List<Area> list = null;
        boolean isExists =  jedisClient.exists("PROVINCEALL");
      if(isExists){
          String jsonStr = jedisClient.get("PROVINCEALL");
          list = JsonUtils.jsonToList(jsonStr,Area.class);
      }else{
          System.out.println("12312312");
          list = areaMapper.getProvince();
          jedisClient.set("PROVINCEALL",JsonUtils.objectToJson(list));
      }

       return  list;

    }

    @RequestMapping("/getCity")
    @ResponseBody
    public List<Area> getCity(int id){

        return  areaMapper.getCity(id);

    }

    @RequestMapping("/getCountry")
    @ResponseBody
    public List<Area> getCountry(int id){

        return  areaMapper.getCountry(id);

    }
}
