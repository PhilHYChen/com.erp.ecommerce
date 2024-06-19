package com.erp.ecommerce.configuration.security.securitycontext.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.erp.ecommerce.configuration.security.securitycontext.CurrentUserService;
import com.erp.ecommerce.configuration.security.userdetails.AccountDetails;
import com.erp.ecommerce.model.user.profile.AbstractUserProfile;

@Component
public class CurrentUserProfileArgumentResolver<T extends AbstractUserProfile>
		implements HandlerMethodArgumentResolver {

	@Autowired
	CurrentUserService<T> currentUserProfileService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(CurrentUserProfile.class) != null;
	}

	@Override
	public Object resolveArgument(
			MethodParameter parameter, 
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) throws Exception {
		// TODO
		Authentication authentication = (Authentication) webRequest.getUserPrincipal();
		if (authentication != null && authentication.getPrincipal() instanceof AccountDetails) {
			AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
	        return currentUserProfileService.getCurrentUserProfile(accountDetails);
		}
		
		throw new IllegalArgumentException("No account details found in security context");
	}

}
