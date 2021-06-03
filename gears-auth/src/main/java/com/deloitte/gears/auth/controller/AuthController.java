package com.deloitte.gears.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

	final static Logger logger = LoggerFactory.getLogger(AuthController.class);
	boolean fpw = false;
	
	public boolean isFpw() {
		return fpw;
	}
	public void setFpw(boolean fpw) {
		this.fpw = fpw;
	}

	@RequestMapping({ "/", "/auth" })
	public String authForm(final Credential cred, final Model model, final HttpServletRequest req) {

		final String requestId;
		final String resourceUrl;
		final String oamReq;

		requestId = req.getParameter("request_id");
		resourceUrl = req.getParameter("resource_url");
		oamReq = req.getParameter("OAM_REQ");
		String fpwParam = req.getParameter("fpw");
		if (null != fpwParam && !fpwParam.isEmpty() && fpwParam.equalsIgnoreCase("true")) {
			setFpw(true);
		} else {
			setFpw(false);
		}

		logger.debug("oam_req: " + (oamReq != null ? oamReq : "nil"));
		logger.debug("request_id: " + (requestId != null ? requestId : "nil"));
		logger.debug("resource_url: " + (resourceUrl != null ? resourceUrl : "nil"));
		logger.info("Credential " + cred.toString());
		cred.setUsername("");
		cred.setFpw(isFpw());
		model.addAttribute("reqId", requestId);
		model.addAttribute("resUrl", resourceUrl);
		/*
		 * Backing bean for Login Form Supports *{field_name} access
		 */
		model.addAttribute("cred", cred);

		return "auth";
	}

	@RequestMapping(value = "/auth", params = { "p_error_code", "p_error_codes_list", "request_id", "resource_url" })
	public String authFormError(final Credential cred, final Model model, final HttpServletRequest req) {
		final String requestId;
		final String resourceUrl;
		final String pErrorCode;
		final String pErrorCodesList;
		final String fpwParam;
		
		boolean unpError;
		boolean lockError;
		boolean disabledError;

		unpError = false;
		lockError = false;
		disabledError = false;

		requestId = req.getParameter("request_id");
		resourceUrl = req.getParameter("resource_url");
		pErrorCode = req.getParameter("p_error_code");
		pErrorCodesList = req.getParameter("p_error_codes_list");
		fpwParam = req.getParameter("fpw");
		
		if (null != fpwParam && !fpwParam.isEmpty() && fpwParam.equalsIgnoreCase("true")) {
			setFpw(true);
		} else {
			setFpw(false);
		}

		logger.debug("p_error_code: " + (pErrorCode != null ? pErrorCode : "nil"));
		logger.debug("p_error_codes_list: " + (pErrorCodesList != null ? pErrorCodesList : "nil"));
		logger.debug("request_id: " + (requestId != null ? requestId : "nil"));
		logger.debug("resource_url: " + (resourceUrl != null ? resourceUrl : "nil"));

		if (pErrorCode != null) {
			switch (pErrorCode) {
			case "OAM-1":
				unpError = true;
				break;
			case "OAM-2":
				unpError = true;
				break;
			case "OAM-3":
				unpError = true;
				break;
			case "OAM-4":
				unpError = true;
				break;
			case "OAM-5":
				disabledError = true;
				break;
			case "OAM-6":
				lockError = true;
				break;
			case "OAM-7":
				lockError = true;
				break;
			case "OAM-8":
				lockError = true;
				break;
			case "OAM-9":
				lockError = true;
				break;
			case "OAM-10":
				lockError = true;
				break;
			default:
				lockError = true;
				break;
			}
		}

		cred.setUnpError(unpError);
		cred.setLockError(lockError);
		cred.setDisabledError(disabledError);
		cred.setFpw(isFpw());
		
		logger.info("Credential " + cred.toString());
		cred.setUsername("");
		model.addAttribute("cred", cred);

		return "auth";
	}

}
