package com.dlf.model.dto.message;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageSearchDTO extends PageDTO {

    private String targetUserId;
}
