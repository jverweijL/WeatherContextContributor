package com.liferay.demo.weather;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import java.io.IOException;
@Component(
		immediate = true,
		service = WeatherInvoker.class
)
public class WeatherInvoker {
	public String getWeatherInfo() throws PortalException, IOException {
		return this.getWeatherInfo("metric");
	}

	public String getWeatherInfo(String units) throws PortalException, IOException {
		//Units of measurement. standard, metric and imperial
		long userId = PrincipalThreadLocal.getUserId();
		User user = userLocalService.getUser(userId);
		Address primaryAddress = null;
		for (Address address : user.getAddresses()) {
			if (address.isPrimary()) {
				primaryAddress = address;
				break;
			}
		}
		if ((primaryAddress != null) && (!primaryAddress.getCity().isEmpty())) {
			if (units.isEmpty()) units = "metric";
			String api = "weather?q=" + primaryAddress.getCity() + "&units=" + units +  "&appid=" + PortalUtil.getPortalProperties().getProperty("com.liferay.demo.openweathermap.apikey");
			String apiURL = "https://api.openweathermap.org/data/2.5/" + api;
			System.out.println("Calling " + apiURL);
			Http.Options options = new Http.Options();
			//options.addHeader("Authorization", PortalUtil.getPortalProperties().getProperty("com.liferay.demo.skilljar.authorization"));
			options.setLocation(apiURL);
			return HttpUtil.URLtoString(options);
		}
		return "";
	}
	@Reference
	private UserLocalService userLocalService;
}