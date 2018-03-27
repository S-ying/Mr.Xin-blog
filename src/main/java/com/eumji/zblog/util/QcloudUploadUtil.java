package com.eumji.zblog.util;

import com.eumji.zblog.vo.PhotoResult;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Calendar;

/**
 * 腾讯云上传文件工具类
 * Created by WangXin on 2018/1/22.
 */
@Component
public class QcloudUploadUtil {
    @Value("${qcloud.APPID}")
    private String APPID;
    @Value("${qcloud.SecretId}")
    private String SecretId;
    @Value("${qcloud.SecretKey}")
    private String SecretKey;
    @Value("${qcloud.Region}")
    private String Region;
    @Value("${qcloud.BaseUrl}")
    private String BaseUrl;

    /**
     * 创建cos客户端
     */
    private COSClient CreateCOSClient() {

        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(SecretId, SecretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(Region));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        return cosclient;
    }

    /**
     * 上传文件
     */
    public PhotoResult UploadFileFromLocal(String realName, String filename){
        PhotoResult result = new PhotoResult();
        String bucketName = "zblog-2-" + APPID;
        COSClient cosclient = this.CreateCOSClient();
        File localFile = new File(realName);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, getFilePath(filename), localFile);
        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia), 近线(nearline)
        putObjectRequest.setStorageClass(StorageClass.Standard_IA);
        try {
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            String etag = putObjectResult.getETag();
            result.setSuccess(1);
            result.setUrl(BaseUrl+getFilePath(filename));
            return result;
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        // 关闭客户端
        cosclient.shutdown();
        return result;
    }

    public String getFilePath(String fileName){
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH)+1;
        int day = instance.get(Calendar.DATE);
        return year+"/"+month+"/"+day+"/"+fileName;
    }
}
