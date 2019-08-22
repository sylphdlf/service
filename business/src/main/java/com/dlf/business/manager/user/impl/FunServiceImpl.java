package com.dlf.business.manager.user.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dlf.business.anno.ExecuteTimeAnno;
import com.dlf.business.anno.FunDescriptionAnno;
import com.dlf.business.anno.RedisCacheAnno;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.business.manager.user.FunService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.*;
import com.dlf.model.enums.GlobalEnum;
import com.dlf.model.enums.redis.IRedisPrefixEnums;
import com.dlf.model.enums.redis.RedisPrefixEnums;
import com.dlf.model.enums.user.FunctionEnums;
import com.dlf.model.mapper.user.FunMapper;
import com.dlf.model.po.user.Fun;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.*;

@Service
public class FunServiceImpl implements FunService {

    @Resource
    private FunMapper funMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private WebApplicationContext applicationContext;

    @Override
    @Transactional
    public GlobalResultDTO getMenu(FunReqDTO reqDTO){
//        List<FunDTO> defaultMenuList = this.getDefaultMenuList();
//        //获取二级菜单
//        Fun Fun = new Fun();
//        Fun.setLevel(FunctionEnums.function_level_2.getCode());
//        Fun.setType(FunctionEnums.function_type_1.getCode());
//        Fun.setParentCode(reqDTO.getParentCode());
//        List<FunDTO> userMenuList = functionMapper.queryListByParams(Fun);
//        if(CollectionUtils.isEmpty(userMenuList)){
//            for(FunDTO thisDTO : defaultMenuList){
//                Fun menuInsert = new Fun();
//                BeanUtils.copyProperties(thisDTO, menuInsert);
//                menuInsert.setParentCode(reqDTO.getParentCode());
//                String code = menuInsert.getParentCode() + "-" + ((null == functionMapper.selectLastId() ? 0 : functionMapper.selectLastId()) + 1);
//                menuInsert.setCode(code);
//                menuInsert.setIsDeleted(GlobalEnum.IS_DELETED_1.getCode());
//                //新增菜单
//                functionMapper.insert(menuInsert);
//            }
//            userMenuList = functionMapper.queryListByParams(Fun);
//            return new GlobalResultDTO(userMenuList);
//        }else{
//            List<FunDTO> userMenuCopyList = CommonUtils.deepCopy(userMenuList);
//            Map<String, FunDTO> defaultMenuMap = new HashMap<String, FunDTO>();
//            for(FunDTO thisDTO : defaultMenuList){
//                defaultMenuMap.put(thisDTO.getPath(), thisDTO);
//            }
//            //FIXME 这段改为管理员进入才执行
//            {
//                //是否有新增或删除的默认菜单
//                Iterator<FunDTO> iterator = userMenuList.iterator();
//                while (iterator.hasNext()){
//                    FunDTO thisDTO = iterator.next();
//                    //如果存在，则从列表中移除
//                    if(defaultMenuMap.containsKey(thisDTO.getPath())){
//                        iterator.remove();
//                        //如果不存在，则更新数据为删除状态
//                    }else{
//                        Fun funUpdate = new Fun();
//                        BeanUtils.copyProperties(thisDTO, funUpdate);
//                        funUpdate.setIsDeleted(GlobalEnum.IS_DELETED_1.getCode());
//                    }
//                }
//                //遍历完之后是否还有数据，有则进行新增
//                if(!CollectionUtils.isEmpty(userMenuList)){
//                    for(FunDTO thisDTO : userMenuList){
//                        Fun funInsert = new Fun();
//                        BeanUtils.copyProperties(thisDTO, funInsert);
//                        funInsert.setParentCode(reqDTO.getParentCode());
//                        funInsert.setCode(funInsert.getParentCode() + "-" + ((null == functionMapper.selectLastId() ? 0 : functionMapper.selectLastId()) + 1));
//                        funInsert.setIsDeleted(GlobalEnum.IS_DELETED_1.getCode());
//                        functionMapper.insert(funInsert);
//                    }
//                }
//            }
//            //默认选中
//            for(FunDTO thisDTO : userMenuCopyList){
//                if(thisDTO.getIsDeleted().intValue() == GlobalEnum.IS_DELETED_0.getCode())
//                thisDTO.setChecked(true);
//            }
//            return new GlobalResultDTO(userMenuCopyList);
            return new GlobalResultDTO();
        }

//
//        //userMenuList转map,避免嵌套循环,标记出用户已经选择过的权限
//        Map<String, Long> checkedMap = new HashMap<String, Long>();
//        if(!CollectionUtils.isEmpty(userMenuList)){
//            //默认的菜单没有ID，只能对比path
//            for(FunDTO thisDTO : userMenuList){
//                checkedMap.put(thisDTO.getPath(), thisDTO.getId());
//            }
//            //循环对比
//            if(!CollectionUtils.isEmpty(defaultMenuList)){
//                for(FunDTO thisDefaultDTO : defaultMenuList){
//                    if(!StringUtils.isEmpty(checkedMap.containsKey(thisDefaultDTO.getPath()))){
//                        thisDefaultDTO.setChecked(true);
//                        //已经绑定过则设置id
//                        thisDefaultDTO.setId(checkedMap.get(thisDefaultDTO.getPath()));
//                    }
//                }
//                return new GlobalResultDTO(defaultMenuList);
//            }else{
//                return GlobalResultDTO.SUCCESS();
//            }
//        }else{
//            return new GlobalResultDTO(defaultMenuList);
//        }
//    }

    @Override
    @Transactional
    public GlobalResultDTO bindingMenuByUser(FunReqDTO reqDTO) {
        //用户选中的菜单
        List<FunReqDTO> checkedDTO = reqDTO.getChildren();
        //转SET
        Set<Long> checkedIdSet = new HashSet<Long>();
        for(FunReqDTO thisReqDTO : checkedDTO){
            checkedIdSet.add(thisReqDTO.getId());
        }
        //获取用户或有的二级菜单
        Fun Fun = new Fun();
        Fun.setType(FunctionEnums.function_type_1.getCode());
        Fun.setLevel(FunctionEnums.function_level_2.getCode());
        Fun.setParentCode(reqDTO.getParentCode());
        List<FunDTO> orgMenuList = funMapper.queryListByParams(Fun);
        //对比，更新状态
        for(FunDTO thisDTO : orgMenuList){
            Fun funUpdate;
            if(checkedIdSet.contains(thisDTO.getId()) && thisDTO.getIsDeleted().intValue() == GlobalEnum.IS_DELETED_1.getCode().intValue()){
                funUpdate = new Fun();
                BeanUtils.copyProperties(thisDTO, funUpdate);
                funUpdate.setIsDeleted(GlobalEnum.IS_DELETED_0.getCode());
                funMapper.updateByPrimaryKey(funUpdate);
            }else if(!checkedIdSet.contains(thisDTO.getId()) && thisDTO.getIsDeleted().intValue() == GlobalEnum.IS_DELETED_0.getCode().intValue()){
                funUpdate = new Fun();
                BeanUtils.copyProperties(thisDTO, funUpdate);
                funUpdate.setIsDeleted(GlobalEnum.IS_DELETED_1.getCode());
                funMapper.updateByPrimaryKey(funUpdate);
            }
        }
        return GlobalResultDTO.SUCCESS();
    }

    @Override
    @RedisCacheAnno(key=IRedisPrefixEnums.fun_list, timeout = 900)
    public GlobalResultDTO getDefaultSource(){
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        //用来存资源和资源描述
        Map<String, String> funMap = new HashMap<String, String>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> thisMap : map.entrySet()) {
            HandlerMethod thisMethod = thisMap.getValue();
            //方法描述
            if(thisMethod.hasMethodAnnotation(FunDescriptionAnno.class) && !thisMethod.hasMethodAnnotation(Deprecated.class)){
                RequestMappingInfo thisInfo = thisMap.getKey();
                Set<String> patterns = thisInfo.getPatternsCondition().getPatterns();
                String pattern = patterns.isEmpty()? "" : patterns.iterator().next();
                String desc = thisMethod.getMethodAnnotation(FunDescriptionAnno.class).name();
                funMap.put(pattern, desc);
            }
        }
        //获取二级菜单
        Fun Fun = new Fun();
        Fun.setLevel(FunctionEnums.function_level_2.getCode());
        List<FunDTO> list = funMapper.queryListByParams(Fun);
        //返回的格式化列表
        JSONArray jsonArray = new JSONArray();
        if(!CollectionUtils.isEmpty(list)){
            for(FunDTO thisDTO : list){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("value", thisDTO.getPath());
                jsonObject.put("label", thisDTO.getName());
                //children
                JSONArray children = new JSONArray();
                //遍历map，找到则移除
                Iterator<Map.Entry<String, String>> iterator = funMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    String parent = entry.getKey().split("/")[1];
                    if(parent.equals(thisDTO.getPath())){
                        JSONObject childObject = new JSONObject();
                        childObject.put("value", entry.getKey());
                        childObject.put("label", entry.getValue());
                        children.add(childObject);
                        iterator.remove();
                    }
                }
                if(!children.isEmpty()){
                    jsonObject.put("children", children);
                }
                jsonArray.add(jsonObject);
            }
        }
        return new GlobalResultDTO(jsonArray.toJSONString());
    }

    @Override
    public GlobalResultDTO queryMenuByParams(FunSearchDTO searchDTO) {
        Fun Fun = new Fun();
        BeanUtils.copyProperties(searchDTO, Fun);
        Fun.setType(FunctionEnums.function_type_1.getCode());
//        Fun.setLevel(FunctionEnums.function_level_1.getCode());
        Fun.setIsDeleted(GlobalEnum.IS_DELETED_0.getCode());
        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        List<FunDTO> list = funMapper.queryListByParams(Fun);
        PageInfo<FunDTO> pageInfo = new PageInfo<FunDTO>(list);
        return new GlobalResultDTO(pageInfo);
    }

    @Override
    public GlobalResultDTO queryFunByParams(FunSearchDTO searchDTO) {
        Fun Fun = new Fun();
        Fun.setParentCode(searchDTO.getCode());
        List<FunDTO> list = funMapper.queryListByParams(Fun);
        return new GlobalResultDTO(list);
    }

    @Override
    @ExecuteTimeAnno
    public GlobalResultDTO getFunTree(FunReqDTO reqDTO) {
//        List<TreeNode> treeList = null;
//        //从缓存中获取数据
//        treeList = (List<TreeNode>)redisService.getObj(RedisPrefixEnums.FUN_TREE_NODE.getCode());
//        if(CollectionUtils.isEmpty(treeList)){
//            List<TreeNode> funList = funMapper.getTreeNodeByParams(reqDTO);
//            //拼接树
//            //模拟一个根节点
//            TreeNode treeNode = new TreeNode();
//            treeNode.setId(0L);
//            treeNode.setCode("0");
//            treeNode.setLabel("权限树");
//            this.recursionNode(funList, treeNode, 4, 1);
//            treeList = new ArrayList<TreeNode>(Collections.singletonList(treeNode));
//            try {
//                redisService.put(RedisPrefixEnums.FUN_TREE_NODE.getCode(), treeList);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        List<Long> selectedFunList = funMapper.getCachedFunIdsByRoleId(reqDTO);
//        FunResDTO funResDTO = new FunResDTO();
//        funResDTO.setList(treeList);
//        funResDTO.setOriginalIds(selectedFunList);
//        return new GlobalResultDTO(funResDTO);
        return null;
    }

    @Override
    public GlobalResultDTO addMenu(FunReqDTO reqDTO) {
        reqDTO.setLevel(FunctionEnums.function_level_1.getCode());
        reqDTO.setType(FunctionEnums.function_type_1.getCode());
        reqDTO.setParentCode("0");
        this.add(reqDTO);
        return GlobalResultDTO.SUCCESS();
    }

    @Override
    public GlobalResultDTO add(FunReqDTO reqDTO) {
        Fun fun = new Fun();
        BeanUtils.copyProperties(reqDTO, fun);
        //parentCode + code
        fun.setCode(fun.getParentCode() + "-" + ((null == funMapper.selectLastId() ? 0 : funMapper.selectLastId()) + 1));
        int count = funMapper.insert(fun);
        if(count == 1){
            return GlobalResultDTO.SUCCESS();
        }
        return GlobalResultDTO.FAIL("");
    }
    /**
     * 递归拼装节点
     * @param list 节点集合
     * @param node 节点
     * @param level 递归层级，节点太深会导致页面崩溃
     * @param count 每进一层，count++, 初始为1
     * @return
     */
    public TreeNode recursionNode(List<TreeNode> list, TreeNode node, final int level, int count){
        //递归层级=level则返回
        if(count == level){
            return node;
        }
        for (TreeNode thisNode : list) {
            if (node.getCode().equals(thisNode.getParent())) {
                if (CollectionUtils.isEmpty(node.getChildren())) {
                    List<TreeNode> children = new ArrayList<TreeNode>();
                    children.add(thisNode);
                    node.setChildren(children);
                } else {
                    node.getChildren().add(thisNode);
                }
                int newCount = count + 1;
                recursionNode(list, thisNode, level, newCount);
            }
        }
        return node;
    }

//    /**
//     * 获取默认的菜单
//     * @return
//     */
//    @RedisCacheAnno(key=IRedisPrefixEnums.menu_list, timeout = 900)
//    private List<FunDTO> getDefaultMenuList() {
//        List<FunDTO> defaultMenuList = new ArrayList<FunDTO>();
//        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
//        // 获取url与类和方法的对应信息
//        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
//        //用来存资源和资源描述
//        //去重
//        Map<String, FunDTO> repeatMap = new HashMap<String, FunDTO>();
//        for (Map.Entry<RequestMappingInfo, HandlerMethod> thisMap : map.entrySet()) {
//            HandlerMethod thisMethod = thisMap.getValue();
//            if(thisMethod.hasMethodAnnotation(FunDescriptionAnno.class) && !thisMethod.hasMethodAnnotation(Deprecated.class)){
//                //方法描述
//                RequestMappingInfo thisInfo = thisMap.getKey();
//                HandlerMethod handlerMethod = thisMap.getValue();
//                //是否有菜单注解
//                if(handlerMethod.getBeanType().isAnnotationPresent(MenuDescriptionAnno.class)){
//                    MenuDescriptionAnno menuAnnotation = handlerMethod.getBeanType().getAnnotation(MenuDescriptionAnno.class);
//                    RequestMapping requestAnno = handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
//                    //获取路径
//                    String value = requestAnno.value()[0];
//                    if(!repeatMap.containsKey(value)){
//                        FunDTO funDTO = new FunDTO();
//                        funDTO.setName(menuAnnotation.name());
//                        funDTO.setPath(value);
//                        funDTO.setLevel(FunctionEnums.function_level_2.getCode());
//                        funDTO.setType(FunctionEnums.function_type_1.getCode());
//                        defaultMenuList.add(funDTO);
//                        //放入资源
//                        List<FunDTO> children = new ArrayList<FunDTO>();
//                        FunDTO sourceDTO = new FunDTO();
//                        Set<String> patterns = thisInfo.getPatternsCondition().getPatterns();
//                        String pattern = patterns.isEmpty()? "" : patterns.iterator().next();
//                        String name = thisMethod.getMethodAnnotation(FunDescriptionAnno.class).name();
//                        sourceDTO.setPath(pattern);
//                        sourceDTO.setName(name);
//                        sourceDTO.setType(FunctionEnums.function_type_3.getCode());
//                        children.add(sourceDTO);
//                        funDTO.setChildren(children);
//                        repeatMap.put(value, funDTO);
//                    }else{
//                        FunDTO funDTO = repeatMap.get(value);
//                        List<FunDTO> children = funDTO.getChildren();
//                        FunDTO sourceDTO = new FunDTO();
//                        Set<String> patterns = thisInfo.getPatternsCondition().getPatterns();
//                        String pattern = patterns.isEmpty()? "" : patterns.iterator().next();
//                        String name = thisMethod.getMethodAnnotation(FunDescriptionAnno.class).name();
//                        sourceDTO.setPath(pattern);
//                        sourceDTO.setName(name);
//                        sourceDTO.setType(FunctionEnums.function_type_3.getCode());
//                        children.add(sourceDTO);
//                    }
//                }
//            }
//        }
//        return defaultMenuList;
//    }
}
