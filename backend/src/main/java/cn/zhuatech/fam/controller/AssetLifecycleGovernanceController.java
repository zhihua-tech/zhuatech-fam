/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fam.controller;
import cn.zhuatech.fam.common.ApiResponse;
import cn.zhuatech.fam.service.AssetLifecycleGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/assets")
public class AssetLifecycleGovernanceController {
    private final AssetLifecycleGovernanceService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public AssetLifecycleGovernanceController(AssetLifecycleGovernanceService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/lifecycle-governance")
    public ApiResponse<AssetLifecycleGovernanceService.Result> evaluate(
            @Valid @RequestBody AssetLifecycleGovernanceService.Request request) { return ApiResponse.ok(service.evaluate(request)); }
}
