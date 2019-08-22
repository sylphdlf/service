//package com.dlf.business.manager.user.impl;
//
//import com.dlf.business.anno.ExecuteTimeAnno;
//import com.dlf.business.factory.OrgRolesFactory;
//import com.dlf.business.manager.redis.RedisService;
//import com.dlf.business.manager.user.OrgService;
//import com.dlf.common.utils.CompareUtils;
//import com.dlf.model.dto.GlobalResultDTO;
//import com.dlf.model.dto.user.*;
//import com.dlf.model.enums.user.OrgResultEnums;
//import com.dlf.model.mapper.user.OrgMapper2;
//import com.dlf.model.mapper.user.OrganizationMapper2;
//import com.dlf.model.mapper.user.RoleMapper2;
//import com.dlf.model.po.user.Org;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.util.*;
//
//@Service
//public class OrgServiceImpl implements OrgService {
//
//    @Resource
//    private OrgMapper2 orgMapper;
//    @Resource
//    private RoleMapper2 roleMapper2;
//    @Resource
//    private RedisService redisService;
//    //递归层级
////    @Value("${org.tree.recursion.level}")
//    private int recursionLevel = 2;
//
//    private void insert(Org org){
//        orgMapper.insert(org);
//    }
//    @Override
//    public GlobalResultDTO addOrgNode(OrgReqDTO reqDTO){
//        try {
//            if(StringUtils.isBlank(reqDTO.getParentCode())){
//                reqDTO.setParentCode("0");
//            }
//            //通过code查询组织
//            Org org = new Org();
//            org.setParentCode(reqDTO.getParentCode());
//            int count = organizationMapper2.countTreeNodeByParams(org);
//            //code自动生成，根据children个数并补全3位数
//            BeanUtils.copyProperties(reqDTO, org);
//            org.setCode(reqDTO.getParentCode() + String.format("%02d", count + 1));
//            organizationMapper2.insertWithIdReturn(org);
//            //只展示自定义字段
//            OrgResDTO resDTO = new OrgResDTO();
//            BeanUtils.copyProperties(org, resDTO);
//            return new GlobalResultDTO(resDTO);
//        }catch (Exception e){
//            return GlobalResultDTO.FAIL("");
//        }
//    }
//
//    @Override
//    public GlobalResultDTO getOrgTreeRoot() {
//        OrgReqDTO reqDTO = new OrgReqDTO();
//        reqDTO.setParentCode("0");
//        return getOrgTree(reqDTO);
//    }
//
//    @Override
//    public GlobalResultDTO getOrgTreeLazy(OrgReqDTO reqDTO) {
//        return getOrgTree(reqDTO);
//    }
//
//    @Override
//    @ExecuteTimeAnno
//    public GlobalResultDTO getRolePageByOrg(OrgSearchDTO searchDTO) {
//        RoleSearchDTO roleSearchDTO = new RoleSearchDTO();
//        BeanUtils.copyProperties(searchDTO, roleSearchDTO);
//        roleSearchDTO.setOrgId(searchDTO.getId());
//        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
//        List<RoleDTO> list = roleMapper2.queryListByParams(roleSearchDTO);
//        PageInfo<RoleDTO> pageInfo = new PageInfo<RoleDTO>(list);
//        if(!CollectionUtils.isEmpty(list)){
//            //获取组织下的所有角色id
//            Map<Long, RoleDTO> roleMap = OrgRolesFactory.getRoleMapByOrgId(searchDTO.getId());
//            if(CollectionUtils.isEmpty(OrgRolesFactory.getRoleMapByOrgId(searchDTO.getId()))){
//                //初始化组织对应角色列表
//                List<RoleDTO> roleList = organizationMapper2.getRoleIdsByOrg(searchDTO.getId());
//                if(!CollectionUtils.isEmpty(roleList)){
//                    roleMap = OrgRolesFactory.initOrgRoleMapAndGet(searchDTO.getId(), roleList);
//                }
//            }
//            //遍历返回值，查看参数是否有对应角色, 有则做标记
//            if(!CollectionUtils.isEmpty(roleMap)){
//                for(RoleDTO thisDTO : list){
//                    if(null != roleMap.get(thisDTO.getId())){
//                        thisDTO.setChecked(true);
//                    }
//                }
//            }
//        }
//        return new GlobalResultDTO(pageInfo);
//    }
//
//    @Override
//    @Transactional
//    @ExecuteTimeAnno
//    public GlobalResultDTO bindingRole(OrgReqDTO reqDTO) {
//        List<Long> originalIds = reqDTO.getOriginalIds();
//        List<Long> changedIds = reqDTO.getChangedIds();
//        //待新增的id
//        List<Long> toAddIds = new ArrayList<Long>();
//        //待删除的id
//        List<Long> toDelIds = new ArrayList<Long>();
//        CompareUtils.getAddAndDel(originalIds, changedIds, toAddIds, toDelIds);
//        //操作数据库
//        if(!CollectionUtils.isEmpty(toAddIds)){
//            reqDTO.setToAddIds(toAddIds);
//            organizationMapper2.insertOrgRoles(reqDTO);
//        }
//        if(!CollectionUtils.isEmpty(toDelIds)){
//            reqDTO.setToDelIds(toDelIds);
//            organizationMapper2.delOrgRoles(reqDTO);
//        }
//        //移除factory中缓存的数据
//        OrgRolesFactory.removeOrgId(reqDTO.getId());
//        return GlobalResultDTO.SUCCESS();
//    }
//
//    @Override
//    @ExecuteTimeAnno
//    public GlobalResultDTO getOrgTree(OrgReqDTO reqDTO) {
//        //从缓存中拿数据
//        List<TreeNode> list = null;
////        List<TreeNode> treeNode = null;
//        Org Org = new Org();
//        BeanUtils.copyProperties(reqDTO, Org);
//        list = organizationMapper2.getTreeNodeByParams(Org);
//        if(StringUtils.isBlank(reqDTO.getParentCode()) && CollectionUtils.isEmpty(list)){
//            return GlobalResultDTO.FAIL(OrgResultEnums.ORG_TREE_EMPTY.getCode(), OrgResultEnums.ORG_TREE_EMPTY.getMsg());
//        }else{
//            return new GlobalResultDTO(list);
//        }
////        if(StringUtils.isBlank(reqDTO.getParentCode())){
////            treeNode = (List<TreeNode>)redisService.getObj(RedisPrefixEnums.ORG_TREE_NODE.getCode());
////            if(!CollectionUtils.isEmpty(treeNode)){
////                return new GlobalResultDTO(treeNode);
////            }
////            long start = System.currentTimeMillis();
////            list = organizationMapper2.getAllAsTreeNode();
////            long end = System.currentTimeMillis();
////            System.out.println("查库时间：" + (end - start));
////        }else{
////
////        }
////        if(!CollectionUtils.isEmpty(list)){
////            //组装节点
////            treeNode = this.nodePackage(list);
////            //放到缓存中
////            redisService.put(RedisPrefixEnums.ORG_TREE_NODE.getCode(),treeNode);
////            return new GlobalResultDTO(treeNode);
////        }else{
////            //无节点数据
////            return GlobalResultDTO.FAIL(OrgResultEnums.ORG_TREE_EMPTY.getCode(), OrgResultEnums.ORG_TREE_EMPTY.getMsg());
////        }
//    }
//    /**
//     * 节点组装
//     * @param list
//     * @return
//     */
//    @ExecuteTimeAnno
//    private List<TreeNode> nodePackage(List<TreeNode> list){
//        //取出根节点
//        TreeNode root = null;
//        for(TreeNode thisNode: list){
//            if(thisNode.getParent().equals("0")){
//                root = thisNode;
//            }
//        }
//        return new ArrayList<TreeNode>(Collections.singletonList(recursionNode(list, root, recursionLevel, 1)));
//    }
//
//    /**
//     * 递归拼装节点
//     * @param list 节点集合
//     * @param node 节点
//     * @param level 递归层级，节点太深会导致页面崩溃
//     * @param count 每进一层，count++, 初始为1
//     * @return
//     */
//    public TreeNode recursionNode(List<TreeNode> list, TreeNode node, final int level, int count){
//        //递归层级=level则返回
//        if(count == level){
//            return node;
//        }
//        for (TreeNode thisNode : list) {
//            if (node.getCode().equals(thisNode.getParent())) {
//                if (CollectionUtils.isEmpty(node.getChildren())) {
//                    List<TreeNode> children = new ArrayList<TreeNode>();
//                    children.add(thisNode);
//                    node.setChildren(children);
//                } else {
//                    node.getChildren().add(thisNode);
//                }
//                int newCount = count + 1;
//                recursionNode(list, thisNode, level, newCount);
//            }
//        }
//        return node;
//    }
//}
