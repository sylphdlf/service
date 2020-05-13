package com.dlf.business.manager.order.impl;

import com.dlf.business.manager.order.OrderService;
import com.dlf.common.utils.CodeGenerateUtils;
import com.dlf.model.dao.order.OrderOuterDao;
import com.dlf.model.dao.user.UserMapper;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.order.OrderReqDTO;
import com.dlf.model.enums.order.OrderOuterEnums;
import com.dlf.model.enums.user.UserEnums;
import com.dlf.model.po.order.OrderOuter;
import com.dlf.model.po.user.User;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    UserMapper userMapper;
    @Resource
    OrderOuterDao orderOuterDao;

    @Override
    public GlobalResultDTO createOuter(OrderReqDTO reqDTO) {
        //新增用户信息
        User user = new User();
        user.setMobile(reqDTO.getMobile());
        Long userId = Optional.of(userMapper.findOne(Example.of(user))).get()
                        .orElseGet(() -> {
                            user.setUsername(reqDTO.getMobile());
                            user.setType(UserEnums.TYPE_1.getCode());
                            user.setStatus(UserEnums.STATUS_0.getCode());
                            return userMapper.saveAndFlush(user);
                        }).getId();
        //新增外部订单
        OrderOuter orderOuter = new OrderOuter();
        orderOuter.setUserId(userId);
        orderOuter.setCode(reqDTO.getCode());
        orderOuter.setMobile(reqDTO.getMobile());
        orderOuter.setVerifyCode(CodeGenerateUtils.getCode8() + "");
        orderOuter.setStatus(OrderOuterEnums.STATUS_0.getCode());
        orderOuterDao.save(orderOuter);
        return GlobalResultDTO.SUCCESS();
    }
}
