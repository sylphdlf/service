//package com.dlf.business.factory;
//
//import com.dlf.model.dto.user.RoleDTO;
//import org.springframework.util.CollectionUtils;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class OrgRolesFactory {
//    //组织所拥有的角色
//    private static Map<Long,Map<Long,RoleDTO>> orgRolesMap = new HashMap<Long, Map<Long, RoleDTO>>();
//    private static Map<Long,Map<Long,RoleDTO>> userRolesMap = new HashMap<Long, Map<Long, RoleDTO>>();
//
//    //初始化并获取组织对应的角色
//    public static synchronized Map<Long, RoleDTO> initOrgRoleMapAndGet(Long orgId, List<RoleDTO> roleList){
//        if(CollectionUtils.isEmpty(orgRolesMap.get(orgId))){
//            if(!CollectionUtils.isEmpty(roleList)){
//                Map<Long, RoleDTO> roleMap = new HashMap<Long, RoleDTO>();
//                for(RoleDTO thisDTO : roleList){
//                    roleMap.put(thisDTO.getId(), thisDTO);
//                }
//                orgRolesMap.put(orgId, roleMap);
//                return roleMap;
//            }
//        }
//        return getRoleMapByOrgId(orgId);
//    }
//    //初始化并获取用户对应的角色
//    public static synchronized Map<Long, RoleDTO> initUserRoleMapAndGet(Long userId, List<RoleDTO> roleList){
//        if(CollectionUtils.isEmpty(userRolesMap.get(userId))){
//            if(!CollectionUtils.isEmpty(roleList)){
//                Map<Long, RoleDTO> roleMap = new HashMap<Long, RoleDTO>();
//                for(RoleDTO thisDTO : roleList){
//                    roleMap.put(thisDTO.getId(), thisDTO);
//                }
//                userRolesMap.put(userId, roleMap);
//                return roleMap;
//            }
//        }
//        return getRoleMapByUserId(userId);
//    }
//    //获取组织所拥有的角色map
//    public static Map<Long, RoleDTO> getRoleMapByOrgId(Long orgId){
//        return orgRolesMap.get(orgId);
//    }
//
//    //获取组织所拥有的角色map
//    public static Map<Long, RoleDTO> getRoleMapByUserId(Long userId){
//        return userRolesMap.get(userId);
//    }
//
////    /**
////     * 移除对应的角色
////     * @param orgId
////     * @param roleId
////     */
////    public static synchronized void removeRoleId(Long orgId, Long roleId){
////        Map<Long, RoleDTO> roleMap = orgRolesMap.get(orgId);
////        if(!CollectionUtils.isEmpty(roleMap)){
////            Iterator<Long> it = roleMap.keySet().iterator();
////            while(it.hasNext()){
////                if(it.next().longValue() == roleId.longValue()){
////                    it.remove();
////                    break;
////                }
////            }
////        }
////    }
////    /**
////     * 添加角色
////     * @param orgId
////     * @param roleDTO
////     */
////    public static void addRoleId(Long orgId, RoleDTO roleDTO){
////        Map<Long, RoleDTO> roleMap = orgRolesMap.get(orgId);
////        if(!CollectionUtils.isEmpty(roleMap)){
////            roleMap.put(roleDTO.getId(), roleDTO);
////        }
////    }
//
//    public static void removeOrgId(Long orgId){
//        orgRolesMap.remove(orgId);
//    }
//}
