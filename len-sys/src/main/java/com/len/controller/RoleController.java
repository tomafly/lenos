package com.len.controller;

import com.alibaba.fastjson.JSONArray;
import com.len.annotation.Log;
import com.len.base.BaseController;
import com.len.core.exception.MyException;
import com.len.entity.SysRole;
import com.len.entity.SysRoleMenu;
import com.len.entity.SysRoleUser;
import com.len.service.MenuService;
import com.len.service.RoleMenuService;
import com.len.service.RoleService;
import com.len.service.RoleUserService;
import com.len.util.BeanUtil;
import com.len.util.JsonUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2017/12/19.
 * @email 154040976@qq.com
 * 角色业务
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

  @Autowired
  private RoleService roleService;

  @Autowired
  private RoleUserService roleUserService;

  @Autowired
  private MenuService menuService;

  @Autowired
  private RoleMenuService roleMenuService;

  @GetMapping(value = "showRole")
  public String showRole(Model model){
    return "/system/role/roleList";
  }

  @ApiOperation(value = "/showRoleList", httpMethod = "GET", notes = "展示角色")
  @GetMapping(value = "showRoleList",produces = "text/json;charset=UTF-8")
  @ResponseBody
  public String showRoleList(SysRole role,Model model,String page,String limit){
   return roleService.show(role,Integer.valueOf(page),Integer.valueOf(limit));
  }

  @GetMapping(value = "showAddRole")
  public String addRole(Model model) {
    JSONArray jsonArray=menuService.getTreeUtil(null);
    model.addAttribute("menus",jsonArray.toJSONString());
    return "/system/role/add-role";
  }

  @ApiOperation(value = "/addRole", httpMethod = "POST", notes = "添加角色")
  @Log(desc = "添加角色")
  @PostMapping(value = "addRole")
  @ResponseBody
  public String addRole(SysRole sysRole,String[] menus){
    if(StringUtils.isEmpty(sysRole.getRoleName())){
      return "角色名称不能为空";
    }
    try{
      roleService.insertSelective(sysRole);
      //操作role-menu data
      SysRoleMenu sysRoleMenu=new SysRoleMenu();
      sysRoleMenu.setRoleId(sysRole.getId());
      for(String menu:menus){
        sysRoleMenu.setMenuId(menu);
        roleMenuService.insert(sysRoleMenu);
      }
    }catch (MyException e){
      e.printStackTrace();
    }
    return "保存成功";
  }

  @GetMapping(value = "updateRole")
  public String updateRole(String id,Model model,boolean detail){
    if (StringUtils.isNotEmpty(id)) {
      SysRole role = roleService.selectByPrimaryKey(id);
      model.addAttribute("role", role);
      JSONArray jsonArray=menuService.getTreeUtil(id);
      model.addAttribute("menus",jsonArray.toJSONString());
    }
    model.addAttribute("detail", detail);
    return "system/role/update-role";
  }

  @ApiOperation(value = "/updateRole", httpMethod = "POST", notes = "更新角色")
  @Log(desc = "更新角色")
  @PostMapping(value = "updateRole")
  @ResponseBody
  public JsonUtil updateUser(SysRole role,String[] menus) {
    JsonUtil jsonUtil = new JsonUtil();
    jsonUtil.setFlag(false);
    if (role == null) {
      jsonUtil.setMsg("获取数据失败");
      return jsonUtil;
    }
    try {
      SysRole oldRole = roleService.selectByPrimaryKey(role.getId());
      BeanUtil.copyNotNullBean(role, oldRole);
      roleService.updateByPrimaryKeySelective(oldRole);

      SysRoleMenu sysRoleMenu=new SysRoleMenu();
      sysRoleMenu.setRoleId(role.getId());
      List<SysRoleMenu> menuList=roleMenuService.selectByCondition(sysRoleMenu);
      for(SysRoleMenu sysRoleMenu1:menuList){
        roleMenuService.deleteByPrimaryKey(sysRoleMenu1);
      }
      for(String menu:menus){
        sysRoleMenu.setMenuId(menu);
        roleMenuService.insert(sysRoleMenu);
      }
      jsonUtil.setFlag(true);
      jsonUtil.setMsg("修改成功");
    } catch (MyException e) {
      e.printStackTrace();
    }
    return jsonUtil;
  }

  @ApiOperation(value = "/del", httpMethod = "POST", notes = "删除角色")
  @Log(desc = "删除角色")
  @PostMapping(value = "del")
  @ResponseBody
  public String del(String id) {
    if (StringUtils.isEmpty(id)) {
      return "获取数据失败";
    }
    SysRoleUser sysRoleUser=new SysRoleUser();
    sysRoleUser.setRoleId(id);
    try {
     int count= roleUserService.selectCountByCondition(sysRoleUser);
     if(count>0){
       return "已分配给用户，删除失败";
     }
        roleService.deleteByPrimaryKey(id);
    } catch (MyException e) {
      e.printStackTrace();
    }
    return "删除成功";
  }

}
