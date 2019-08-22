//package com.dlf.business.manager.comm.impl;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.TypeReference;
//import com.dlf.business.anno.RedisCacheAnno;
//import com.dlf.business.anno.ValidateAnno;
//import com.dlf.business.comm.ValidateUtils;
//import com.dlf.business.manager.comm.DictService;
//import com.dlf.model.dto.GlobalResultDTO;
//import com.dlf.model.dto.comm.DictDTO;
//import com.dlf.model.dto.comm.DictReqDTO;
//import com.dlf.model.dto.comm.DictSearchDTO;
//import com.dlf.model.enums.EnumsFactory;
//import com.dlf.model.enums.GlobalEnum;
//import com.dlf.model.enums.comm.DictEnums;
//import com.dlf.model.mapper.comm.DictionaryMapper2;
//import com.dlf.model.po.comm.Dictionary;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class DictServiceImpl implements DictService {
//
//    @Resource
//    DictionaryMapper2 dictionaryMapper;
//
//    @Override
//    @RedisCacheAnno(timeout = 1800)
//    public GlobalResultDTO getAreaCode() throws Exception {
//        org.springframework.core.io.Resource resource = new ClassPathResource("areaCode.json");
//        File sourceFile = resource.getFile();
//        JSONArray areaJson = JSONObject.parseObject(new FileInputStream(sourceFile), JSONArray.class);
//        return GlobalResultDTO.SUCCESS(areaJson);
//    }
//
//    @Override
//    @ValidateAnno(names = {"dictKey"})
//    public GlobalResultDTO getKeyValue(DictReqDTO reqDTO) {
//        Map<String, Map<Integer, String>> thisMap = this.queryListByParams();
//        return new GlobalResultDTO<>(thisMap.get(reqDTO.getDictKey()));
//    }
//
//    @RedisCacheAnno(timeout = 86400)
//    @SuppressWarnings("unchecked")
//    public Map<String, Map<Integer, String>> queryListByParams(){
//        Dictionary dictionary = new Dictionary();
//        dictionary.setType(DictEnums.TYPE_1.getCode());
//        List<Dictionary> resultList = dictionaryMapper.queryListByParams(dictionary);
//        Map<String, Map<Integer, String>> thisMap = new HashMap<>();
//        if(!CollectionUtils.isEmpty(resultList)){
//            for(Dictionary thisPo: resultList){
//                Map<Integer, String> innerMap = JSONObject.parseObject(thisPo.getDictValue(), new TypeReference<Map<Integer, String>>(){});
//                thisMap.put(thisPo.getDictKey(), innerMap);
//            }
//            //初始化枚举工厂类
//            EnumsFactory.keyValueMap.putAll(thisMap);
//            return thisMap;
//        }
//        return thisMap;
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    @ValidateAnno(names = {"pageNum"})
//    public GlobalResultDTO queryPageByParams(DictSearchDTO searchDTO) {
//        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
//        List<DictDTO> resultList = dictionaryMapper.queryPageByParams(searchDTO);
//        PageInfo<DictDTO> pageInfo = new PageInfo<>(resultList);
//        return GlobalResultDTO.SUCCESS(pageInfo);
//    }
//
//    @Override
//    @ValidateAnno(names = {"id"})
//    public GlobalResultDTO delDict(DictReqDTO reqDTO) {
//        Dictionary dictionary = dictionaryMapper.selectByPrimaryKey(reqDTO.getId());
//        dictionary.setIsDeleted(GlobalEnum.IS_DELETED_1.getCode() + "");
//        if(dictionaryMapper.updateByPrimaryKey(dictionary) == GlobalEnum.COUNT_1.getCode()){
//            return GlobalResultDTO.SUCCESS();
//        }
//        return GlobalResultDTO.FAIL();
//    }
//
//    @Override
//    public GlobalResultDTO addOrEdit(DictReqDTO reqDTO) {
//        if (StringUtils.isBlank(reqDTO.getId())) {
//            return this.add(reqDTO);
//        } else {
//            return this.edit(reqDTO);
//        }
//    }
//
//    private GlobalResultDTO add(DictReqDTO reqDTO) {
//        ValidateUtils.validate(reqDTO, new String[]{"name", "dictKey", "dictValue", "type"});
//        Dictionary dictionary = new Dictionary();
//        BeanUtils.copyProperties(reqDTO, dictionary);
//        dictionary.setStatus(DictEnums.STATUS_1.getCode());
//        if (dictionaryMapper.insert(dictionary) == GlobalEnum.COUNT_1.getCode()) {
//            return GlobalResultDTO.SUCCESS();
//        }
//        return GlobalResultDTO.FAIL();
//    }
//
//    private GlobalResultDTO edit(DictReqDTO reqDTO) {
//        Dictionary dictionary = dictionaryMapper.selectByPrimaryKey(reqDTO.getId());
//        dictionary.setDictKey(reqDTO.getDictKey());
//        dictionary.setDictValue(reqDTO.getDictValue());
//        dictionary.setName(reqDTO.getName());
//        dictionary.setType(reqDTO.getType());
//        if (dictionaryMapper.updateByPrimaryKey(dictionary) == GlobalEnum.COUNT_1.getCode()) {
//            return GlobalResultDTO.SUCCESS();
//        }
//        return GlobalResultDTO.FAIL();
//    }
//}
