package com.jetsen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author NICKEL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdxVo {
    private String idxName;
    private IdxDDL idxDDL;

    @Data
    private class IdxDDL {
        private boolean dynamic = false;
        private Map<String, Map<String, Object>> properties;

    }
}
