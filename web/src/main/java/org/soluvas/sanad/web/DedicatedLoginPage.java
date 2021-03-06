package org.soluvas.sanad.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.soluvas.web.login.DedicatedLoginPanel;
import org.soluvas.web.login.LoginToken;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * Copy this class to your own {@link WebPage}, the logic is in {@link DedicatedLoginPanel}.
 * @author rudi
 */
@SuppressWarnings("serial")
@MountPath("login")
public class DedicatedLoginPage extends GuestLayoutPage {
	
	public DedicatedLoginPage(PageParameters params) {
		super(params);
		add(new DedicatedLoginPanel("dedicatedLoginPanel", new Model<>(new LoginToken()) ));
	}

}
