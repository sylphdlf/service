package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FunResDTO {

    List<TreeNode> list;

    List<Long> originalIds;
}
