package test.user;

import com.alibaba.fastjson.JSONArray;
import com.len.entity.SysMenu;
import com.len.service.MenuService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhuxiaomeng
 * @date 2017/12/12.
 * @email 154040976@qq.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class MenuTest {
  @Autowired
  MenuService menuService;

  @Test
  public void getAllNotP(){
  List<SysMenu> menuList= menuService.getMenuNotSuper();
    System.out.println(menuList);
  }


  @Test
  public void getChild(){
    List<SysMenu> mList=menuService.getMenuNotSuper();
    JSONArray jsonArr=new JSONArray();
    for(SysMenu sysMenu:mList){
      SysMenu menu=getChil(sysMenu.getId());
      jsonArr.add(menu);
    }
    System.out.println(jsonArr);

  }

  public SysMenu getChil(String id){
    SysMenu sysMenu=menuService.selectByPrimaryKey(id);
    List<SysMenu> mList=menuService.getMenuChildren(id);
    for(SysMenu menu:mList){
      SysMenu m=getChil(menu.getId());
      sysMenu.addChild(m);
    }
    return sysMenu;
  }

  @Test
  public void getUserMenu(){
   List<SysMenu> menuList= menuService.getUserMenu("acfc0e9232f54732a5d9ffe9071bf572");
    List<SysMenu> menuList1=new ArrayList<>(new HashSet<>(menuList));
    for(SysMenu menu:menuList1){
      System.out.println(menu);
    }

    JSONArray json=menuService.getMenuJsonByUser(menuList1);
    System.out.println(json);

  }

}
