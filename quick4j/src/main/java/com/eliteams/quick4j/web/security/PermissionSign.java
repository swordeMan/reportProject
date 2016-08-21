package com.eliteams.quick4j.web.security;

/**
 * 权限标识配置类, <br>
 * 与 permission 权限表 中的 permission_sign 字段 相对应 <br>
 * 使用:
 * 
 * <pre>
 * &#064;RequiresPermissions(value = PermissionConfig.USER_CREATE)
 * public String create() {
 *     return &quot;拥有user:create权限,能访问&quot;;
 * }
 * </pre>
 * 
 * @author StarZou
 * @since 2014年6月17日 下午3:58:51
 **/
public class PermissionSign {

    /**
     * 用户新增权限 标识
     */
    public static final String USER_CREATE = "user:create";

    /**
     * 用户删除权限 标识
     */
    public static final String USER_DELETE = "user:delete";

    /**
     * 用户更新权限 标识
     */
    public static final String USER_UPDATE = "user:update";
    
    /**
     * 用户查看权限 标识
     */
    public static final String USER_VIEW = "user:view";
    
    /**
     * 角色新增权限 标识
     */
    public static final String ROLE_CREATE = "role:create";
    
    /**
     * 角色删除权限 标识
     */
    public static final String ROLE_DELETE = "role:delete";
    
    /**
     * 角色更新权限 标识
     */
    public static final String ROLE_UPDATE = "role:update";
    
    /**
     * 角色查看权限 标识
     */
    public static final String ROLE_VIEW = "role:view";
    
    public static final String ORDER_CREATE  ="order:create";
    
    public static final String REPORT_HAND  ="report:hand";
    
    public static final String SCRAP_CREATE  ="scrap:create";
    
    public static final String TASK_CREATE  ="task:create";
    
    public static final String TASK_START  ="task:start";
    
    public static final String SCRAP_VALIDE  ="scrap:valide";
    
    public static final String REPORT_CANCLE  ="report:cancle";
    
    public static final String STOCK_CORRECT  ="stock:correct";
    
    public static final String STOCK_REWORK  ="stock:rework";
    
    public static final String CORRECT_DETAIL  ="correct:detail";
    
    public static final String REWORK_DATAIL  ="rework:detail";
    
    public static final String REWORK_VALIDE  ="rework:valide";
    
    public static final String SCRAPREASON_CREATE  ="scrapReason:create";
    
    public static final String SCRAPREASON_UPDATE  ="scrapReason:update";
    
    public static final String SCRAPREASON_DELETE  ="scrapReason:delete";
    
    public static final String SCRAPREASON_VIEW  ="scrapReason:view";
    
    public static final String REWORKREASON_CREATE  ="reworkReason:create";
    
    public static final String REWORKREASON_UPDATE  ="reworkReason:update";
    
    public static final String REWORKREASON_DELETE  ="reworkReason:delete";
    
    public static final String REWORKREASON_VIEW  ="reworkReason:view";
    
    public static final String ORDER_VIEW  ="order:view";
    
    public static final String TASK_VIEW  ="task:view";
    
    public static final String SCRAP_VIEW  ="scrap:view";
    
    public static final String REPORT_VIEW  ="report:view";
    
    public static final String STOCK_VIEW  ="stock:view";
    
    public static final String CORRECT_VIEW  ="correct:view";
    
    public static final String REWORK_VIEW  ="rework:view";
    

}
