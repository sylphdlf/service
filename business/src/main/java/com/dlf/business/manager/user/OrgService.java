package com.dlf.business.manager.user;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.OrgReqDTO;
import com.dlf.model.dto.user.OrgSearchDTO;

public interface OrgService {
    /**
     * 获取组织结构树
     * @param reqDTO
     * @return
     */
    GlobalResultDTO getOrgTree(OrgReqDTO reqDTO);

    /**
     * 新增节点
     * @param reqDTO
     * @return
     */
    GlobalResultDTO addOrgNode(OrgReqDTO reqDTO);

    /**
     * 获取根节点
     * @return
     */
    GlobalResultDTO getOrgTreeRoot();

    /**
     * 懒加载树节点
     * @param reqDTO
     * @return
     */
    GlobalResultDTO getOrgTreeLazy(OrgReqDTO reqDTO);

    /**
     * 根据组织机构获取角色分页
     * @param searchDTO
     * @return
     */
    GlobalResultDTO getRolePageByOrg(OrgSearchDTO searchDTO);

    /**
     * 绑定角色
     * @param reqDTO
     * @return
     */
    GlobalResultDTO bindingRole(OrgReqDTO reqDTO);
}
