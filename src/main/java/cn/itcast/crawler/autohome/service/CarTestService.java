package cn.itcast.crawler.autohome.service;

import cn.itcast.crawler.autohome.pojo.CarTest;

import java.util.List;

public interface CarTestService {
    /**
     * 分页查询数据
     * @param page
     * @param rows
     * @return
     */
    List<String> queryByPage(Integer page, Integer rows);

    /**
     * 保存数据
     * @param carTest
     */
    void saveCarTest(CarTest carTest);
}
