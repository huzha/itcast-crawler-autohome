package cn.itcast.crawler.autohome.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CarTest {
    private Long id;
    private String title;
    private int test_speed;
    private int test_brake;
    private int test_oil;
    private String editor_name1;
    private String editor_remark1;
    private String editor_name2;
    private String editor_remark2;
    private String editor_name3;
    private String editor_remark3;
    private String image;
    private Date created;
    private Date updated;

}
