package cn.cebest.instructions;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.servlet.support.RequestContext;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.jfinal.template.Directive;
import com.jfinal.template.Env;
import com.jfinal.template.expr.ast.Expr;
import com.jfinal.template.stat.Scope;


/**
 *  jfinal扩展指令
  * @author maming  
  * @date 2018年10月8日
 */
public class Param extends Directive {

	@Override
	public void exec(Env env, Scope scope, Writer writer) {
		// 获取参数
		Expr key = exprList.getExpr(0);
		RequestContext requestContext = (RequestContext) scope.get("springMacroRequestContext");
		String queryString = requestContext.getQueryString();
		if(StringUtils.isEmpty(queryString)){
			write(writer, "");
			return ;
		}
		
		String[] params = queryString.split("&");
		Map<String, String> data = new HashMap<>();
		for (String param : params) {
			String[] kv = param.split("=");
			data.put(kv[0], kv[1]);
		}
		
		if(data.containsKey(key.toString())){
			write(writer, data.get(key.toString()));
		} else {
			write(writer, "");
		}
		
	}

}
