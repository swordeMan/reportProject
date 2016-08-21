package com.eliteams.quick4j.web.service;

import java.io.IOException;
import java.util.List;

import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.web.form.CreateReworkForm;
import com.eliteams.quick4j.web.model.Rework;
import com.eliteams.quick4j.web.model.ReworkReason;
/**
 * 
 *返修接口
 *
 * @author liuliu
 * 2016年7月3日 下午7:58:08
 */
public interface ReworkService {
	/**
	 * 查询所有返修单
	 * @return
	 */
   List<Rework> selectAllRework();
   /**
    * 根据返修ID查询返修单
    * @param reworkId
    * @return
    */
   Rework selectByReworkId(String reworkId);
   /**
    *添加返修单
    * @param rework
    */
   
   void insertRework(Rework rework);
  
   List<Rework> getReworkByPage(Page page);

   List<Rework> getReworkByPage(Page page,String orderByClause, String keywords);
   /**
    * 进行审核
    * @param rework
    */
   void insertAuditing(Rework rework);
   /**
    *添加返修单
    * @param reworkScrapForm
 * @throws
    */
   void insertRework(CreateReworkForm reworkScrapForm);
   /**
    * 截取物料描述的前两个字符串 判断是否进产线
    * @param materialDescribe
    * @return
    */
   String materialDescribeToName(String materialDescribe);
   /**
    * 打印返修单pdf
    * @param reworkId
    */
   public void printRewokList(String reworkId);
}
