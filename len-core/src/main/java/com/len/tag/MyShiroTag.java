package com.len.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author zhuxiaomeng
 * @date 2017/12/4.
 * @email 154040976@qq.com
 */
public class MyShiroTag extends TagSupport{
  /**属性*/
  private String text;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public int doStartTag() throws JspException {
    return super.doStartTag();
  }
}
