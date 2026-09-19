/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fam.service;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class AssetLifecycleGovernanceService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result evaluate(Request request) {
        List<String> actions = new ArrayList<>();
        String accountingTreatment = request.acquisitionCents() >= request.capitalizationThresholdCents()
                ? "CAPITALIZE" : "EXPENSE";
        if (!request.inventoryMatched()) actions.add("先完成资产实物与台账核对");
        if (request.impairmentIndicator() && !request.impairmentReviewed()) actions.add("完成减值测试与审批");
        if (request.disposalRequested() && !request.disposalApproved()) actions.add("取得资产处置授权");
        if ("CAPITALIZE".equals(accountingTreatment) && request.usefulLifeMonths() < 1) actions.add("补充有效使用年限");
        String decision = request.disposalRequested() && !request.disposalApproved() ? "BLOCKED"
                : actions.isEmpty() ? "READY" : "REVIEW";
        return new Result(request.assetCode(), decision, accountingTreatment,
                request.usefulLifeMonths(), List.copyOf(actions), actions.isEmpty());
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String assetCode, @Min(0) long acquisitionCents,
                          @Min(0) long capitalizationThresholdCents, @Min(0) int usefulLifeMonths,
                          boolean inventoryMatched, boolean impairmentIndicator,
                          boolean impairmentReviewed, boolean disposalRequested, boolean disposalApproved) {
        /**
         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
         */
        public Request {
            if (assetCode == null || assetCode.isBlank()) throw new IllegalArgumentException("assetCode is required");
            if (acquisitionCents < 0 || capitalizationThresholdCents < 0 || usefulLifeMonths < 0)
                throw new IllegalArgumentException("numeric values must be non-negative");
        }
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(String assetCode, String decision, String accountingTreatment,
                         int usefulLifeMonths, List<String> requiredActions, boolean postingAllowed) {}
}
