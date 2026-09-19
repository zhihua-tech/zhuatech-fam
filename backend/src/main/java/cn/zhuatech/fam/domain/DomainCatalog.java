/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fam.domain;

import org.springframework.stereotype.Component;
import java.util.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public DomainCatalog() {
        actions.put("CAPITALIZE", new WorkflowAction("CAPITALIZE", "确认转固", List.of("草稿"), "在账", "ADMIN"));
        actions.put("TRANSFER", new WorkflowAction("TRANSFER", "确认调拨", List.of("在账"), "已调拨", "OPERATOR"));
        actions.put("DISPOSE", new WorkflowAction("DISPOSE", "确认处置", List.of("在账","已调拨"), "已处置", "ADMIN"));
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName() { return "知华科技固定资产管理系统"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String scene() { return "资产采购、转固、折旧、盘点、调拨、减值、处置与财务对账"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String initialStatus() { return "草稿"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String partyLabel() { return "资产/组织"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String amountLabel() { return "资产原值"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String quantityLabel() { return "资产数量"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String dueLabel() { return "计划日期"; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<ModuleDefinition> modules() {
        return List.of(
            new ModuleDefinition("ASSET_MASTER", "资产卡片", "统一管理资产编号、分类、位置、责任人与财务属性"),
            new ModuleDefinition("ACQUISITION", "资产取得", "覆盖采购、验收、领用及在建工程转固"),
            new ModuleDefinition("CAPITALIZATION", "转固管理", "控制资本化日期、原值和会计期间"),
            new ModuleDefinition("DEPRECIATION", "折旧管理", "按资产政策生成折旧计划与月度计提结果"),
            new ModuleDefinition("TRANSFER", "调拨管理", "记录跨组织、库位与责任人的资产调拨"),
            new ModuleDefinition("INVENTORY", "盘点管理", "支持盘点任务、差异确认与盘盈盘亏处理"),
            new ModuleDefinition("IMPAIRMENT", "减值管理", "执行减值测试、审批和账面价值调整"),
            new ModuleDefinition("DISPOSAL", "处置管理", "覆盖报废、出售、捐赠及处置收益"),
            new ModuleDefinition("RECONCILIATION", "财务对账", "完成资产子账与总账的期间对账")
        );
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record ModuleDefinition(String code, String name, String description) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record WorkflowAction(String code, String label, List<String> from, String to, String requiredRole) {}
}
