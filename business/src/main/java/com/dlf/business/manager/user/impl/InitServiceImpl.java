//package com.dlf.business.manager.user.impl;
//
//import FunDescriptionAnno;
//import MenuDescriptionAnno;
//import InitService;
//import DbUtils;
//import com.dlf.com.dlf.model.dto.user.FunDTO;
//import com.dlf.com.dlf.model.dto.user.UserReqDTO;
//import com.dlf.com.dlf.model.enums.user.FunctionEnums;
//import com.dlf.com.dlf.model.mapper.user.funMapper;
//import com.dlf.com.dlf.model.mapper.user.OrganizationMapper2;
//import com.dlf.com.dlf.model.mapper.user.RoleMapper2;
//import com.dlf.com.dlf.model.mapper.user.UserMapper2;
//import com.dlf.com.dlf.model.po.Fun;
//import com.dlf.com.dlf.model.po.Org;
//import com.dlf.com.dlf.model.po.Role;
//import com.dlf.com.dlf.model.po.User;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import javax.annotation.Resource;
//import java.io.File;
//import java.util.*;
//
//@Service
//public class InitServiceImpl implements InitService {
//
//    @Value("${spring.datasource.url}")
//    private String url;
//    @Value("${spring.datasource.username}")
//    private String username;
//    @Value("${spring.datasource.password}")
//    private String password;
//    @Value("${spring.datasource.driverClassName}")
//    private String driver;
//    @Resource
//    private WebApplicationContext applicationContext;
//    @Resource
//    private funMapper functionMapper;
//    @Resource
//    private OrganizationMapper2 organizationMapper;
//    @Resource
//    private RoleMapper2 roleMapper;
//    @Resource
//    private UserMapper2 userMapper;
//
//    @Override
//    public void init(File... files) {
//        try {
//            DbUtils dbUtils = new DbUtils(driver, url, username, password);
//            dbUtils.execSqlFileByMysql(files);
//            //删除文件
//            for(File thisFile : files){
//                if(thisFile.exists()){
//                    thisFile.deleteOnExit();
//                }
//            }
//        }catch (Throwable e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    @Transactional
//    public void initSource() {
//        Map<String, List<FunDTO>> menuMap = this.getDefaultMenuList();
//        Set<Map.Entry<String, List<FunDTO>>> entries = menuMap.entrySet();
//        Set<String> repeatSet = this.getCurrentSourceSet();
//        for(Map.Entry<String, List<FunDTO>> thisEntry : entries){
//            //如果重复则跳过
//            if(repeatSet.contains(thisEntry.getKey())){
//                continue;
//            }
//            //一级菜单
//            Fun Fun = new Fun();
//            String codeLevel1 = "0" + "-" + ((null == functionMapper.selectLastId() ? 0 : functionMapper.selectLastId()) + 1);
//            Fun.setName(thisEntry.getKey());
//            Fun.setCode(codeLevel1);
//            Fun.setParentCode("0");
//            Fun.setType(FunctionEnums.function_type_1.getCode());
//            Fun.setPath("");
//            Fun.setLevel(FunctionEnums.function_level_1.getCode());
//            functionMapper.insert(Fun);
//
//            List<FunDTO> menuList = thisEntry.getValue();
//            if(!CollectionUtils.isEmpty(menuList)){
//                for(FunDTO thisDTO : menuList){
//                    if(repeatSet.contains(thisDTO.getPath())){
//                        continue;
//                    }
//                    //二级菜单
//                    Fun menuLevel2 = new Fun();
//                    String codeLevel2 = codeLevel1 + "-" + ((null == functionMapper.selectLastId() ? 0 : functionMapper.selectLastId()) + 1);
//                    menuLevel2.setName(thisDTO.getName());
//                    menuLevel2.setCode(codeLevel2);
//                    menuLevel2.setParentCode(codeLevel1);
//                    menuLevel2.setType(FunctionEnums.function_type_1.getCode());
//                    menuLevel2.setPath(thisDTO.getPath());
//                    menuLevel2.setLevel(FunctionEnums.function_level_2.getCode());
//                    functionMapper.insert(menuLevel2);
//                    if(!CollectionUtils.isEmpty(thisDTO.getChildren())){
//                        for(FunDTO thisSourceDTO : thisDTO.getChildren()){
//                            if(repeatSet.contains(thisDTO.getPath())){
//                                continue;
//                            }
//                            //资源
//                            Fun menuLevel3 = new Fun();
//                            String codeLevel3 = codeLevel2 + "-" + ((null == functionMapper.selectLastId() ? 0 : functionMapper.selectLastId()) + 1);
//                            menuLevel3.setName(thisSourceDTO.getName());
//                            menuLevel3.setCode(codeLevel3);
//                            menuLevel3.setParentCode(codeLevel2);
//                            menuLevel3.setType(FunctionEnums.function_type_3.getCode());
//                            menuLevel3.setPath(thisSourceDTO.getPath());
//                            functionMapper.insert(menuLevel3);
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    @Transactional
//    public void initAdmin() {
//        //创建一个组织机构
//        Org org = new Org();
//        org.setCode("1");
//        org.setParentCode("0");
//        //创建一个角色关联组织机构
//        Role role = new Role();
//        role.setCode("1");
//        role.setName("admin");
//        //创建一个管理员用户
//        User user = new User();
//        user.setUsername("admin");
//        int countOrg = organizationMapper.countTreeNodeByParams(org);
//        int countRole = roleMapper.countByParams(role);
//        int countUser = userMapper.countByParams(user);
//        if(countOrg==0 && countRole==0 && countUser==0){
//            org.setName("ROOT");
//            org.setLevel(0);
//            org.setRemarks("系统创建的根节点");
//            org.setCreateUserId(0L);
//            organizationMapper.insertWithIdReturn(org);
//            role.setOrgId(org.getId());
//            role.setRemarks("系统创建的管理员");
//            role.setCreateUserId(0L);
//            roleMapper.insertWithIdReturn(role);
//            //创建一个管理员用户
//            user.setPassword(DigestUtils.md5Hex("admin").toUpperCase());
//            user.setRealName("admin");
//            user.setGender(3);
//            user.setMobile("");
//            user.setTelephone("");
//            user.setStatus(1);
//            user.setOrgCode(org.getCode());
//            user.setCreateUserId(0L);
//            //绑定管理员和角色
//            userMapper.insertWithIdReturn(user);
//            //绑定用户和角色
//            UserReqDTO reqDTO = new UserReqDTO();
//            reqDTO.setId(user.getId());
//            reqDTO.setToAddIds(Arrays.asList(role.getId()));
//            reqDTO.setCreateUserId(0L);
//            userMapper.insertUserRoles(reqDTO);
//        }
//    }
//
//    /**
//     * 获取默认的菜单
//     * @return
//     */
////    @RedisCacheAnno(key=IRedisPrefixEnums.menu_list, timeout = 900)
//    private Map<String, List<FunDTO>> getDefaultMenuList() {
//        Map<String, List<FunDTO>> menuLevel1 = new HashMap<String, List<FunDTO>>();
//        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
//        // 获取url与类和方法的对应信息
//        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
//        //用来存资源和资源描述
//        //判断二级菜单是否新增过
//        Map<String, FunDTO> menuLevel2CheckRepeat = new HashMap<String, FunDTO>();
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
//                    String path = requestAnno.value()[0];
//                    //用一级菜单名称判断
//                    if(!menuLevel1.containsKey(menuAnnotation.parent())){
//                        List<FunDTO> menuLevel2 = new ArrayList<FunDTO>();
//                        //初始化菜单
//                        FunDTO funDTO = new FunDTO();
//                        funDTO.setName(menuAnnotation.name());
//                        funDTO.setPath(path);
//                        funDTO.setLevel(FunctionEnums.function_level_2.getCode());
//                        funDTO.setType(FunctionEnums.function_type_1.getCode());
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
//                        menuLevel2.add(funDTO);
//                        menuLevel1.put(menuAnnotation.parent(), menuLevel2);
//                        //保存2份，一份在list中，一份在map中，修改map中的引用
//                        menuLevel2CheckRepeat.put(path, funDTO);
//                    }else{
//                        //二级菜单会有多个，保存过则直接获取source
//                        if(menuLevel2CheckRepeat.containsKey(path)){
//                            FunDTO funDTO = menuLevel2CheckRepeat.get(path);
//                            //获取资源
//                            List<FunDTO> children = funDTO.getChildren();
//                            FunDTO sourceDTO = new FunDTO();
//                            Set<String> patterns = thisInfo.getPatternsCondition().getPatterns();
//                            String pattern = patterns.isEmpty()? "" : patterns.iterator().next();
//                            String name = thisMethod.getMethodAnnotation(FunDescriptionAnno.class).name();
//                            sourceDTO.setPath(pattern);
//                            sourceDTO.setName(name);
//                            sourceDTO.setType(FunctionEnums.function_type_3.getCode());
//                            children.add(sourceDTO);
//                        //没保存过则新增
//                        }else{
//                            List<FunDTO> menuLevel2 = menuLevel1.get(menuAnnotation.parent());
//                            //初始化菜单
//                            FunDTO funDTO = new FunDTO();
//                            funDTO.setName(menuAnnotation.name());
//                            funDTO.setPath(path);
//                            funDTO.setLevel(FunctionEnums.function_level_2.getCode());
//                            funDTO.setType(FunctionEnums.function_type_1.getCode());
//                            List<FunDTO> children = new ArrayList<FunDTO>();
//                            FunDTO sourceDTO = new FunDTO();
//                            Set<String> patterns = thisInfo.getPatternsCondition().getPatterns();
//                            String pattern = patterns.isEmpty()? "" : patterns.iterator().next();
//                            String name = thisMethod.getMethodAnnotation(FunDescriptionAnno.class).name();
//                            sourceDTO.setPath(pattern);
//                            sourceDTO.setName(name);
//                            sourceDTO.setType(FunctionEnums.function_type_3.getCode());
//                            children.add(sourceDTO);
//                            funDTO.setChildren(children);
//                            menuLevel2.add(funDTO);
//                            menuLevel2CheckRepeat.put(path, funDTO);
//                        }
//                    }
//                }
//            }
//        }
//        return menuLevel1;
//    }
//
//    /**
//     * 获取当前资源列表并组装成set用来判断是否重复
//     * @return
//     */
//    private Set<String> getCurrentSourceSet(){
//        Set<String> result = new HashSet<String>();
//        //查询所有的一级菜单，通过名称 判断重复
//        Fun level1Select = new Fun();
//        level1Select.setType(FunctionEnums.function_type_1.getCode());
//        level1Select.setLevel(FunctionEnums.function_level_1.getCode());
//        List<FunDTO> level1Result = functionMapper.queryListByParams(level1Select);
//        //查询所有的二级菜单，通过path 判断重复
//        Fun level2Select = new Fun();
//        level2Select.setType(FunctionEnums.function_type_1.getCode());
//        level2Select.setLevel(FunctionEnums.function_level_2.getCode());
//        List<FunDTO> level2Result = functionMapper.queryListByParams(level2Select);
//        //查询所有的资源，通过path 判断重复
//        Fun sourceSelect = new Fun();
//        sourceSelect.setType(FunctionEnums.function_type_3.getCode());
//        List<FunDTO> SourceResult = functionMapper.queryListByParams(sourceSelect);
//        if(!CollectionUtils.isEmpty(level1Result)){
//            for(FunDTO thisDTO : level1Result){
//                result.add(thisDTO.getName());
//            }
//        }
//        if(!CollectionUtils.isEmpty(level2Result)){
//            for(FunDTO thisDTO : level2Result){
//                result.add(thisDTO.getPath());
//            }
//        }
//        if(!CollectionUtils.isEmpty(SourceResult)){
//            for(FunDTO thisDTO : SourceResult){
//                result.add(thisDTO.getPath());
//            }
//        }
//        return result;
//    }
//
//    public static void main(String[] args) {
//        Deque<Integer> deque = new LinkedList<Integer>();
//        int[] init = new int[]{17,13,11,2,3,5,7};
//
//        int[] init2 = new int[]{2,13,3,11,5,17,7};
//        int[] init3 = new int[]{2,7,3,17,5,13};
//        /**2 7 3 17 5 13
//         *
//         *
//         *
//         */
//        System.out.println(deckRevealedIncreasing(init));
//    }
//
//    public static int[] deckRevealedIncreasing(int[] deck) {
//        List<Integer> list = new LinkedList<Integer>();
//        for(int i:deck){
//            list.add(i);
//        }
//        Collections.sort(list, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2-o1;
//            }
//        });
//        for(Integer i:list){
//            System.out.println(i);
//        }
//        return null;
//    }
//}
