/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fam.service;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.http.*; import org.springframework.stereotype.Service; import org.springframework.web.server.ResponseStatusException;
import java.math.*; import java.time.*; import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class EnterpriseFamService {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public AssetResult assess(@Valid AssetRequest r){
  if(r.residualValue().compareTo(r.cost())>0) throw bad("预计净残值不能大于资产原值");
  if(r.elapsedMonths()>r.usefulLifeMonths()) throw bad("已使用月数不能大于使用寿命");
  BigDecimal base=r.cost().subtract(r.residualValue()), monthly=money(base.divide(BigDecimal.valueOf(r.usefulLifeMonths()),8,RoundingMode.HALF_UP));
  BigDecimal accumulated=money(monthly.multiply(BigDecimal.valueOf(r.elapsedMonths()))).min(base);
  BigDecimal carrying=money(r.cost().subtract(accumulated).subtract(r.impairment())).max(r.residualValue());
  List<String> warnings=new ArrayList<>(); if(r.capitalizationDate().isAfter(LocalDate.now())) warnings.add("资本化日期晚于当前日期"); if(r.impairment().signum()>0) warnings.add("存在资产减值");
  return new AssetResult(r.assetNo(),monthly,accumulated,carrying,r.usefulLifeMonths()-r.elapsedMonths(),warnings,warnings.isEmpty()?"POSTABLE":"REVIEW_REQUIRED");
 }
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 private BigDecimal money(BigDecimal v){return v.setScale(2,RoundingMode.HALF_UP);} /**
                                                                                     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                     */
private ResponseStatusException bad(String m){return new ResponseStatusException(HttpStatus.BAD_REQUEST,m);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record AssetRequest(@NotBlank String assetNo,@NotNull @DecimalMin("0.01") BigDecimal cost,@NotNull @DecimalMin("0") BigDecimal residualValue,@Min(1) int usefulLifeMonths,@Min(0) int elapsedMonths,@NotNull @DecimalMin("0") BigDecimal impairment,@NotNull LocalDate capitalizationDate){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record AssetResult(String assetNo,BigDecimal monthlyDepreciation,BigDecimal accumulatedDepreciation,BigDecimal carryingAmount,int remainingMonths,List<String>warnings,String decision){}
}

