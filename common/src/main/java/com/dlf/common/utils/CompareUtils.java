package com.dlf.common.utils;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompareUtils {
    /**
     * 筛选需要新增的和删除的ID
     * @param originals
     * @param targets
     * @param toAddIds
     * @param toDelIds
     */
    public static void getAddAndDel(List<Long> originals, List<Long> targets, List<Long> toAddIds, List<Long> toDelIds){
        //筛选出需要新增的ID和需要删除的ID
        //原始ID改为map减少对比时间
        Map<Long, Integer> originalMap = null;
        //只有删除的id
        if(CollectionUtils.isEmpty(targets)){
            toDelIds.addAll(originals);
            //只有新增的id
        }else if(CollectionUtils.isEmpty(originals)){
            toAddIds.addAll(targets);
        }else{
            //list转map方便查找
            originalMap = new HashMap<Long, Integer>();
            for(Long thisId : originals) {
                originalMap.put(thisId, 1);
            }
            //以修改后的ID为主
            for(Long thisId : targets){
                if(null == originalMap.get(thisId)){
                    toAddIds.add(thisId);
                }else{
                    originals.remove(thisId);
                }
            }
            toDelIds.addAll(originals);
        }
    }
}
