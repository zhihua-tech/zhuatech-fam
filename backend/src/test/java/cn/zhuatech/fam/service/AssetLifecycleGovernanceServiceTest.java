/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fam.service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class AssetLifecycleGovernanceServiceTest {
    private final AssetLifecycleGovernanceService service = new AssetLifecycleGovernanceService();
    @Test void allowsCapitalizationForControlledAsset() {
        var result = service.evaluate(new AssetLifecycleGovernanceService.Request(
                "FA-001", 800_000, 500_000, 60, true, false, false, false, false));
        assertEquals("READY", result.decision());
        assertEquals("CAPITALIZE", result.accountingTreatment());
        assertTrue(result.postingAllowed());
    }
    @Test void blocksUnauthorizedDisposalAndFlagsImpairment() {
        var result = service.evaluate(new AssetLifecycleGovernanceService.Request(
                "FA-002", 900_000, 500_000, 48, false, true, false, true, false));
        assertEquals("BLOCKED", result.decision());
        assertEquals(3, result.requiredActions().size());
        assertFalse(result.postingAllowed());
    }
}
