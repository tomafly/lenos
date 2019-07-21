package test.user;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.len.entity.SysRole;
import com.len.entity.SysRoleUser;
import com.len.entity.SysUser;
import com.len.service.RoleService;
import com.len.service.SysUserService;
import com.len.util.Checkbox;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhuxiaomeng
 * @date 2017/12/7.
 * @email 154040976@qq.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class UserTest {
  @Autowired
  SysUserService userService;
  @Autowired
  RoleService roleService;

  @Test
  public void page(){
    Page<SysUser> page=PageHelper.startPage(1,2);
    SysUser sysUser=new SysUser();
    sysUser.setUsername("zxm");
   List<SysUser> uList= userService.selectListByPage(sysUser);
   System.out.println(uList.size());
    System.out.println(page.size());
    System.out.println(page);
  }

  @Test
  public void add(){
    SysUser user=new SysUser();

   int result=userService.add(user);
   System.out.println(result);
  }
  @Test
  public void update(){
    SysUser user=new SysUser();
   /* CurrentUser sysUser=new CurrentUser("059c2173e16511e795ed201a068c6482","aaaa",24,"15404097@qq.com","zxm");
    sysUser.setPhoto("");
    CurrentUser o=userService.selectByPrimaryKey("059c2173e16511e795ed201a068c6482");
    BeanUtil.copyNotNullBean(sysUser,o);
    o.setPhoto(null);
    o.setRealName(null);
    int result=userService.updateByPrimaryKeySelective(o);
    System.out.println(result);*/
  }
  @Test
  public void del(){
    int result=userService.delById("acfc0e9232f54732a5d9ffe9071bf578");
    System.out.println(result==0?"失败":"成功");
  }

  /**
   * 拼装角色
   */
  @Test
  public void check(){
    List<SysRole> roleList=roleService.selectListByPage(new SysRole());
    SysRoleUser sysRoleUser =new SysRoleUser();
    sysRoleUser.setUserId("2211fec3e17c11e795ed201a068c6482");
   List<SysRoleUser>  kList= userService.selectByCondition(sysRoleUser);
   System.out.println(kList.size());
    List<Checkbox> checkboxList=new ArrayList<>();
    Checkbox checkbox=null;
   for(SysRole sysRole:roleList){
     checkbox=new Checkbox();
     checkbox.setId(sysRole.getId());
     checkbox.setName(sysRole.getRoleName());
     for(SysRoleUser sysRoleUser1 :kList){
       if(sysRoleUser1.getUserId().equals(sysRole.getId())){
          checkbox.setCheck(true);
       }
     }
     checkboxList.add(checkbox);
   }
    String json=JSON.toJSONString(checkboxList);
   System.out.println(json);
  }

}
