package cn.itcast.crawler.autohome.service.impl;

import cn.itcast.crawler.autohome.service.ApiService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private PoolingHttpClientConnectionManager cm;

    @Override
    public String getHtml(String url) {
        //使用连接池管理器获取连接
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        //创建httpGet请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            //发起请求
            response = httpClient.execute(httpGet);
            //判断请求是否成功
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                //判断是否有响应体
                if (entity != null) {
                    //如果有响应体，则进行解析
                    String html = EntityUtils.toString(entity, "UTF-8");
                    return html;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放连接
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public String getImage(String url) {
        //使用连接池管理器获取连接
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        //创建httpGet请求
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try {
            //发起请求
            response = httpClient.execute(httpGet);
            //判断请求是否成功
            if(response.getStatusLine().getStatusCode() == 200){
                //判断是否有响应体
                if(response.getEntity() != null){
                    //如果有响应体，则下载
                    //获取文件后缀
                    String extName = StringUtils.substringAfterLast(url,".");
                    //用UUID重新生成文件名
                    String image = UUID.randomUUID().toString()+"."+extName;
                    //下载图片
                    OutputStream outputStream = new FileOutputStream(new File("C:/images/" + image));
                    response.getEntity().writeTo(outputStream);
                    //返回图片名
                    return image;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放连接
            if(response != null){
                try{
                    response.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
