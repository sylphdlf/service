package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class TreeNode implements Serializable {

    private static final long serialVersionUID = -4861544747739346822L;

    private Long id;

    private String name;

    private String code;

    private String label;

    private String value;

    private String parent;

    private List<TreeNode> children;

//    private boolean leaf = true;
}
