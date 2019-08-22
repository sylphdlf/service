package com.dlf.model.dto.message;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MessageReqDTO {

    private String id;

    private String targetUserId;

    private String content;

    private List<String> ids;
}
