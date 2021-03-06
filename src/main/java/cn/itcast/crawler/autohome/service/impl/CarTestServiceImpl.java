package cn.itcast.crawler.autohome.service.impl;

import cn.itcast.crawler.autohome.mapper.CarTestMapper;
import cn.itcast.crawler.autohome.pojo.CarTest;
import cn.itcast.crawler.autohome.service.CarTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarTestServiceImpl implements CarTestService {
    @Resource
    private CarTestMapper carTestMapper;

    @Override
    public List<String> queryByPage(Integer page, Integer rows) {
        // 设置从哪一条数据开始查
        Integer start = (page - 1) * rows;

        // 设置参数
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("start", start);
        map.put("rows", rows);

        // 分页查询数据
        List<String> list = this.carTestMapper.queryByPage(map);

        // 返回查询结果
        return list;

    }

    @Override
    public void saveCarTest(CarTest carTest) {
        carTestMapper.save(carTest);
    }


}
