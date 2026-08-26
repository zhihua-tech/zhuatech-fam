/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fam.controller;
import cn.zhuatech.fam.common.ApiResponse; import cn.zhuatech.fam.service.EnterpriseFamService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/enterprise/fam") public class EnterpriseFamController {
 private final EnterpriseFamService service; public EnterpriseFamController(EnterpriseFamService service){this.service=service;}
 @PostMapping("/assess-asset") ApiResponse<?> execute(@Valid @RequestBody EnterpriseFamService.AssetRequest request){return ApiResponse.ok(service.assess(request));}
}

