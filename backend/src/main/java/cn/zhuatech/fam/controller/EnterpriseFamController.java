/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fam.controller;
import cn.zhuatech.fam.common.ApiResponse; import cn.zhuatech.fam.service.EnterpriseFamService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/enterprise/fam") public class EnterpriseFamController {
 private final EnterpriseFamService service; /**
                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                              */
public EnterpriseFamController(EnterpriseFamService service){this.service=service;}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/assess-asset") ApiResponse<?> execute(@Valid @RequestBody EnterpriseFamService.AssetRequest request){return ApiResponse.ok(service.assess(request));}
}

