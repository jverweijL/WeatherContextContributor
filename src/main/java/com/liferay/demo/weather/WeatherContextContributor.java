package com.liferay.demo.weather;

import com.liferay.demo.weather.WeatherInvoker;
import com.liferay.portal.kernel.template.TemplateContextContributor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component(
        immediate = true,
        property = "type=" + TemplateContextContributor.TYPE_GLOBAL,
        service = TemplateContextContributor.class
)
public class WeatherContextContributor implements TemplateContextContributor {
    @Override
    public void prepare(Map<String, Object> contextObjects, HttpServletRequest httpServletRequest) {
        contextObjects.put("weather", weatherInvoker);
    }
    @Reference
    private WeatherInvoker weatherInvoker;
}