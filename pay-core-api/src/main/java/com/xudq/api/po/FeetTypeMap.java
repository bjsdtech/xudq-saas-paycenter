package com.xudq.api.po;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 费用类型的map，通过feeType获取feeType实体
 * @date
 */
@Component
public class FeetTypeMap {

    public FeetTypeMap(){
        //TODO init fee types,最好使用cache，五分钟失效
        getFeeTypeMap();
    }

    private static List<FeeType> feeTypes = Lists.newArrayList();
    public static Map<String, FeeType> feeTypesMap = Maps.newHashMap();

    public static Map<String, FeeType> getFeeTypeMap(){
        // map为空，使用init方法
        if(feeTypesMap == null || feeTypesMap.size() <= 0){
            init();
        }
        feeTypesMap = feeTypes.stream().collect(Collectors.toMap(feetype -> feetype.getFee_type(), feeType -> feeType));
        return feeTypesMap;
    }

    /**
     * init 方法，通过接口获取feetType内容 form settle.fee_type
     * */
    public static void init(){
        // TODO 通过Http接口获取feeTypes
        //TODO 测试暂时写死

        FeeType fee1 = new FeeType();
        fee1.setFee_type("xxx_cash");
        fee1.setRemark("xxx现金金");
        FeeType fee2 = new FeeType();
        fee2.setFee_type("policy_fee");
        fee2.setRemark("商务政策费");
        FeeType fee3 = new FeeType();
        fee3.setFee_type("buyer_margin");
        fee3.setRemark("买家现金");
        FeeType fee4 = new FeeType();
        fee4.setFee_type("seller_margin");
        fee4.setRemark("");
        FeeType fee5 = new FeeType();
        fee5.setFee_type("gps_fee");
        fee5.setRemark("金融收费项：YYYY");

        feeTypes.add(fee1);
        feeTypes.add(fee2);
        feeTypes.add(fee3);
        feeTypes.add(fee4);
        feeTypes.add(fee5);

    }

    /**
     * 根据feeType获取费用类型
     * */
    public static FeeType getFeeType(String feeType){
        // map为空，使用init方法
        if(feeTypesMap == null || feeTypesMap.size() <= 0){
            getFeeTypeMap();
        }
        return feeTypesMap.get(feeType);
    }
}
