package com.len.base;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.len.core.exception.MyException;
import com.len.util.ReType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhuxiaomeng
 * @date 2017/12/19.
 * @email 154040976@qq.com
 */
public abstract class BaseController<T> {

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class, new CustomDateEditor(
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    binder.registerCustomEditor(Date.class, new CustomDateEditor(
        new SimpleDateFormat("yyyy-MM-dd"), true));
  }


}
