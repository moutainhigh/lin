package star.web.product;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import star.bizbase.enums.OnOffDelEnums;
import star.bizbase.exception.BizRuleException;
import star.bizbase.util.BizRuleCheck;
import star.bizbase.util.ConvertUtil;
import star.facade.ecmanager.manager.vo.ManagerVo;
import star.facade.warehouse.ProductXyzcodeStorageFacade;
import star.facade.warehouse.vo.ProductXyzcodeStorageVo;
import star.fw.web.util.SearchUtil;
import star.fw.web.vo.SearchDataVo;
import star.fw.web.xss.XSSUtil;
import star.share.util.Results;
import star.util.ExceptionUtil;
import star.vo.result.ResultVo;
import star.vo.search.SearchResult;
import star.web.BasicController;

/**
 * 正常情况下，商品存储信息，只能查。---用于定位。。
 * 其他操作，只能通过入库和出库单，审核通过后，方能变更。。或者搬运变更数据。。。
 * @author:chistar
 * @since:2018年11月18日下午12:35:07
 */
@Controller
@RequestMapping("/product/storage")
public class ProductXyzcodeStorageController extends BasicController {

	@Autowired
	private ProductXyzcodeStorageFacade productXyzcodeStorageFacade;

	/**
	 * list 列表查询页面
	 * @param model
	 * @param dataVo
	 * @return 
	 * @author xxh
	 * @throws BizRuleException 
	 * @since  2018年8月27日 上午11:25:26
	 */
	@RequestMapping(value = "/list.html")
	public String list(Model model, ProductXyzcodeStorageVo dataVo) throws BizRuleException {
		SearchDataVo vo = SearchUtil.getVo();
		HashMap<String, Object> searchMap = null;
		try {
			searchMap = (HashMap<String, Object>) ConvertUtil.objectToMap(dataVo);
		} catch (Exception e) {
			logger.error("list error:{}",ExceptionUtil.getMessage(e));
		}
		if(searchMap==null || searchMap.size()==0) {
			searchMap = new HashMap<>();
			searchMap.put("status", OnOffDelEnums.ON.getType());
		}else {
			searchMap.put("status", OnOffDelEnums.ON.getType());
		}
		ManagerVo sysUser = this.getSysUser();
		SearchResult<ProductXyzcodeStorageVo> searchResult = productXyzcodeStorageFacade.getListTotalByWhere(sysUser.getMerchantId(),searchMap,vo.getStart(),vo.getSize());

		vo.setList(searchResult.getList());
		vo.setCount(searchResult.getTotal());
		SearchUtil.putToModel(model, vo);
		
		return "/product/storage/list";

	}

	/**
	 * 编辑页面
	 * @param model
	 * @param id
	 * @return
	 * @throws BizRuleException 
	 * @Author:chistar
	 * @Since : 2018年11月18日下午1:53:53
	 */
	@RequestMapping("/edit")
	public String edit(Model model,Long id) throws BizRuleException{
		ProductXyzcodeStorageVo dataVo=new ProductXyzcodeStorageVo();
		if(id!=null&&id.intValue()!=0){//修改
			ManagerVo sysUser = this.getSysUser();
			dataVo = productXyzcodeStorageFacade.getByPrimaryKey(sysUser.getMerchantId(),id);									
		}
		model.addAttribute("dataVo",dataVo);				
		return "/product/storage/edit";
	}

	/**
	 * 新增或保存dataVo
	 * @param reqeust
	 * @param dataVo
	 * @return
	 * @throws BizRuleException
	 * @Author:chistar
	 * @Since : 2018年11月18日下午2:10:29
	 */
	@ResponseBody
	@RequestMapping("/save")
	public Model save(HttpServletRequest reqeust, ProductXyzcodeStorageVo dataVo) throws BizRuleException {	
		ResultVo<String> result = Results.newResultVo();
		ExtendedModelMap model = new ExtendedModelMap();
		//保存前校验
		dataVo=XSSUtil.cleanXSS(dataVo);
		dataVo.setStatus(OnOffDelEnums.ON.getType());//上线
		ManagerVo loginUser = this.getSysUser();
		if(dataVo.getMerchantId()==null) {
			dataVo.setMerchantId(loginUser.getMerchantId());
		}
		//需要dataVo里增加必填选项的注解
		BizRuleCheck.validateByUseridAnnotation(dataVo,"","");
		Long dataid=0L;		
		
		logger.info("save dataVo={},sysUser={}",dataVo,loginUser);
		if(dataVo.getId() == null){
			dataid = productXyzcodeStorageFacade.insertProductXyzcodeStorageVo(loginUser.getMerchantId(),dataVo);
		}else{
			dataid = productXyzcodeStorageFacade.updateProductXyzcodeStorage(loginUser.getMerchantId(),dataVo);
		}				
		result = Results.checkArguments(dataid!=0, "保存失败！");

		model.addAttribute("ret", result);
		return model;
	}
	
	
	/**
	 * 删除id
	 * @param model
	 * @param reqeust
	 * @param id
	 * @return
	 * @throws BizRuleException
	 * @Author:chistar
	 * @Since : 2018年11月18日下午2:11:52
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Model delete(Model model, HttpServletRequest reqeust, Long id) throws BizRuleException {
		ResultVo<Boolean> ret = new ResultVo<>();
		ret.setSuccess(false);
		if (id == null || id < 1) {
			ret.setResultDes("id 不能为空");
			ret.setSuccess(false);
			model.addAttribute("ret", ret);
			return model;
		}
		ManagerVo sysUser = this.getSysUser();
		productXyzcodeStorageFacade.deleteBy(sysUser.getMerchantId(),id);
		
		ret.setSuccess(true);
		model.addAttribute("ret", ret);
		return model;
	}
	



}
