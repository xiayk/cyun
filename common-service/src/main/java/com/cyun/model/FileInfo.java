package com.cyun.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user")
@Data
public class FileInfo {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * imd5编码后的字符串
     */
    private String name;

    /**
     * imd5编码后的字符串
     */
    private Integer age;

    /**
     * imd5编码后的字符串
     */
    private String sex;
}