package com.lk.service.system.sysbook;

import java.util.List;
import com.lk.entity.Page;
import com.lk.util.PageData;

/** 
 * 说明： 订座管理接口
 * 创建人：洪智鹏
 * 创建时间：2016-10-08
 * @version
 */
public interface SysBookManager{

	/**新增
	 * @param pd订座管理相关信息
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd订座管理相关信息的主键
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd订座管理相关信息
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page订座查询列表页面和检索字段
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd 无，查询全部
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd 通过主键获取订座信息
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS订座管理相关信息主键组成的数组
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;
	/**修改状态字段来确定是否订座
	 * @param pd 包含主键和状态字段等信息
	 * @throws Exception
	 */
	public void updateByState(PageData pd)throws Exception;
	
}

