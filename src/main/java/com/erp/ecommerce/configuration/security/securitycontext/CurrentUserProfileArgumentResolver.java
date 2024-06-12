package com.erp.ecommerce.configuration.security.securitycontext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.erp.ecommerce.model.user.profile.AbstractUserProfile;

public class CurrentUserProfileArgumentResolver<T extends AbstractUserProfile>
		implements HandlerMethodArgumentResolver {

	@Autowired
	CurrentUserService<T> securityContextService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// TODO
		return null;
	}

}
