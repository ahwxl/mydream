package examples;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import freemarker.template.TemplateException;

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);

		registry.addViewController("/").setViewName("/index");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		// registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		super.configureViewResolvers(registry);
		
		registry.viewResolver(freeMarkerViewResolver());
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		
		registry.addResourceHandler("/static/**")
        .addResourceLocations("classpath:/static/")
        .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
		
	}

	/*@Bean
	public InternalResourceViewResolver resourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		// 请求视图文件的前缀地址
		internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
		// 请求视图文件的后缀
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}*/
	
	@Bean
	public FreeMarkerViewResolver freeMarkerViewResolver() {
		
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		
		resolver.setCache(false);
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		resolver.setViewClass(org.springframework.web.servlet.view.freemarker.FreeMarkerView.class);
	    resolver.setRequestContextAttribute("re");
	    resolver.setExposeSpringMacroHelpers(true);
	    resolver.setExposeRequestAttributes(true);
	    resolver.setExposeSessionAttributes(true);
		resolver.setContentType("text/html; charset=UTF-8");
		
		return resolver;
	}
	
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() throws IOException, TemplateException {
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("xml_escape", new freemarker.template.utility.XmlEscape());
		
		/*FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("classpath:/templates/");
		configurer.setFreemarkerVariables(map);
		configurer.setDefaultEncoding("UTF-8");*/
		
		FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
	    factory.setTemplateLoaderPaths("classpath:/templates/", "src/main/resources/templates");
	    factory.setDefaultEncoding("UTF-8");
	    factory.setFreemarkerVariables(map);
	    FreeMarkerConfigurer result = new FreeMarkerConfigurer();

	    freemarker.template.Configuration configuration = factory.createConfiguration();
	    configuration.setClassicCompatible(true);
	    result.setConfiguration(configuration);
	    Properties settings = new Properties();
	    settings.put("template_update_delay", "0");
	    settings.put("default_encoding", "UTF-8");
	    settings.put("number_format", "0.##########");
	    settings.put("datetime_format", "yyyy-MM-dd HH:mm:ss");
	    settings.put("classic_compatible", true);
	    settings.put("template_exception_handler", "ignore");
	    result.setFreemarkerSettings(settings);
		
		return result;
	}

	/**
	 * 消息内容转换配置 配置fastJson返回json转换
	 * 
	 * @param converters
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 调用父类的配置
		super.configureMessageConverters(converters);
		/*// 创建fastJson消息转换器
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		// 创建配置类
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		// 修改配置返回内容的过滤
		fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		// 将fastjson添加到视图消息转换器列表内
		converters.add(fastConverter);*/
	}
	
	@Configuration
	static class MyErrorViewResolver implements ErrorViewResolver {

	    @Override
	    public ModelAndView resolveErrorView(HttpServletRequest request,
	            HttpStatus status, Map<String, Object> model) {
	        // Use the request or status to optionally return a ModelAndView
	        return new ModelAndView("");
	    }

	}

}
