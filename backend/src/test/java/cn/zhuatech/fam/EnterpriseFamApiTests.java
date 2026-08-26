/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fam;
import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; import org.springframework.http.MediaType; import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic; import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class EnterpriseFamApiTests { @Autowired MockMvc mvc;

 @Test void depreciationAndCarryingAmountAreCalculated() throws Exception {mvc.perform(post("/api/enterprise/fam/assess-asset").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"assetNo":"FA-001","cost":120000,"residualValue":6000,"usefulLifeMonths":60,"elapsedMonths":12,"impairment":0,"capitalizationDate":"2025-08-01"}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.monthlyDepreciation").value(1900.00)).andExpect(jsonPath("$.data.carryingAmount").value(97200.00)).andExpect(jsonPath("$.data.decision").value("POSTABLE"));}
 @Test void invalidResidualValueIsRejected() throws Exception {mvc.perform(post("/api/enterprise/fam/assess-asset").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"assetNo":"FA-002","cost":1000,"residualValue":1200,"usefulLifeMonths":12,"elapsedMonths":1,"impairment":0,"capitalizationDate":"2026-01-01"}
 """)).andExpect(status().isBadRequest());}
}

