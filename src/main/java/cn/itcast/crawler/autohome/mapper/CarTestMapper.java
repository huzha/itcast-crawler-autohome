package cn.itcast.crawler.autohome.mapper;

import cn.itcast.crawler.autohome.pojo.CarTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarTestMapper {
    /**
     * 分页查询数据
     * @param map
     * @return
     */
    @Select("SELECT title FROM car_test LIMIT #{start},#{rows}")
    List<String> queryByPage(Map<String,Integer> map);

    @Insert("INSERT INTO `car_test` (" +
            "	`title`," +
            "	`test_speed`," +
            "	`test_brake`," +
            "	`test_oil`," +
            "	`editor_name1`," +
            "	`editor_remark1`," +
            "	`editor_name2`," +
            "	`editor_remark2`," +
            "	`editor_name3`," +
            "	`editor_remark3`," +
            "	`image`," +
            "	`created`," +
            "	`updated`" +
            ")" +
            "VALUES" +
            "	(" +
            "		#{title}," +
            "		#{test_speed}," +
            "		#{test_brake}," +
            "		#{test_oil}," +
            "		#{editor_name1}," +
            "		#{editor_remark1}," +
            "		#{editor_name2}," +
            "		#{editor_remark2}," +
            "		#{editor_name3}," +
            "		#{editor_remark3}," +
            "		#{image}," +
            "		#{created}," +
            "		#{updated}" +
            "	)"
    )
    void save(CarTest carTest);
}
