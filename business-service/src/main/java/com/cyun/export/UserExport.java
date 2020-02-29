package com.cyun.export;

import com.cyun.dto.UserDTO;
import com.cyun.param.UserParam;
import com.cyun.sys.service.UserService;
//import com.cyun.utils.export.ExcelUtil;
//import com.cyun.utils.export.ExportBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @createUser daiyuan
 * @createTime 2019/12/17 19:56
 * @describe 用户列表导出
 **/
@Controller
@RequestMapping("/export")
public class UserExport {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list")

    public void exportList(HttpServletRequest request, HttpServletResponse response, UserParam param) {


        String[] headers = {"账号", "用户名", "手机号", "创建时间", "修改时间", "状态"};
        param.setLimit(5000);
        param.setOffset(0);
        List<UserDTO> userDTOS = (List<UserDTO>) userService.findByPage(param).getData();

        if (userDTOS.size() == 0) {
            throw new IllegalArgumentException("无数据");
        }
//        try {
//            ExportBean<UserDTO> exportBean = new ExportBean<UserDTO>();
//            exportBean.setHeader(headers);
//            exportBean.setPattern("yyyy-MM-dd");
//            exportBean.setTitle("用户列表管理");
//            exportBean.setPoList(userDTOS);
//            response.setContentType("application/vnd.ms-excel; charset=utf-8");
//            String filename = "用户列表管理";
//            String attachment = "attachment;filename=" + URLEncoder.encode(filename, "UTF-8") + ".xlsx;filename*=UTF-8''" + URLEncoder.encode(filename, "UTF-8") + ".xlsx";
//            response.setHeader("Content-Disposition", attachment);
//            OutputStream out = response.getOutputStream();
//            ExcelUtil.exportFromBean(exportBean, out);
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 有时经常会遇到页面某些数据类型是Date、Integer、Double等的数据要绑定到控制器的实体，
     * 或者控制器需要接受这些数据，如果这类数据类型不做处理的话将无法绑定。
     **/
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
