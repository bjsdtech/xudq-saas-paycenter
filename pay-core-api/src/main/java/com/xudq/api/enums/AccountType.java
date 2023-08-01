package com.xudq.api.enums;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description 账户类型
 * U:用户 O:外部商户 I:内部商户
 * @date
 */
public enum AccountType {

    U100(100, "现金账户", parseInt("11111111"), AccountCategory.LIABILITY, AccountingType.Cr),
    U102(102, "保证金账户", parseInt("11111111"), AccountCategory.LIABILITY, AccountingType.Cr),

    C302(302, "银行存款-归集资金", parseInt("11111111"), AccountCategory.ASSETS, AccountingType.Dr),
    C304(304, "银行存款-渠道归集", parseInt("11111111"), AccountCategory.ASSETS, AccountingType.Dr),
    C305(305, "银行存款-渠道结算", parseInt("11111111"), AccountCategory.ASSETS, AccountingType.Dr),
    C312(312, "应收账款-待结算款", parseInt("11111111"), AccountCategory.ASSETS, AccountingType.Dr),
    C313(313, "应收账款-渠道充值款", parseInt("11111111"), AccountCategory.ASSETS, AccountingType.Dr),
    C341(341, "其他应收款-对账差异", parseInt("11111111"), AccountCategory.ASSETS, AccountingType.Dr),
    C361(361, "手续费支出-充值", parseInt("11111111"), AccountCategory.COST, AccountingType.Dr),
    C362(362, "手续费支出-结算", parseInt("11111111"), AccountCategory.COST, AccountingType.Dr),

    B402(402, "应付账款-待结算", parseInt("11111111"), AccountCategory.LIABILITY, AccountingType.Cr),
    B466(466, "业务线收入", parseInt("11111111"), AccountCategory.INCOME, AccountingType.Cr),

    M800(800, "应付账款-客户-企业现金账户", parseInt("11111111"), AccountCategory.LIABILITY, AccountingType.Cr),
    M801(801, "应付账款-客户-保证金账户", parseInt("11111111"), AccountCategory.LIABILITY, AccountingType.Cr),
    M806(806, "其他应收款-客户-商户专用支出账户", parseInt("11111111"), AccountCategory.ASSETS, AccountingType.Dr),
    M808(808, "应付账款-客户-商户结算账户", parseInt("11111111"), AccountCategory.LIABILITY, AccountingType.Cr),;

    private int id;
    private String name;
    private int privilege;
    private AccountCategory category;
    private AccountingType type;

    AccountType(int id, String name, int privilege, AccountCategory category, AccountingType type) {
        this.id = id;
        this.name = name;
        this.privilege = privilege;
        this.category = category;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrivilege() {
        return privilege;
    }

    public AccountCategory getCategory() {
        return category;
    }

    public void setCategory(AccountCategory category) {
        this.category = category;
    }

    private static final Logger logger = LoggerFactory.getLogger(AccountType.class);

    /**
     * 这个方法之类了消化异常，所以使用前一定保证自己的使用不会出现异常
     *
     * @param s
     * @return
     */
    private static int parseInt(String s) {
        try {
            return Integer.parseInt("11111111", 2);
        } catch (Exception e) {
            logger.error("初始化账户类型异常", e);
            return 0;
        }
    }

    public static AccountType getById(int id) {
        for (AccountType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }

}
