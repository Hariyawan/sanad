package org.soluvas.sanad.web;

import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.html.MobileViewportMetaTag;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.ImmutableNavbarComponent;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.ComponentPosition;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.Position;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeIconType;
import de.agilecoders.wicket.less.LessResourceReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.soluvas.commons.AppManifest;
import org.soluvas.sanad.web.claim.ClaimListPage;
import org.soluvas.sanad.web.claim.TestimonyListPage;
import org.soluvas.sanad.web.claim.TestimonyLiteralAddPage;
import org.soluvas.sanad.web.hadith.HadithCollectionListPage;
import org.soluvas.sanad.web.quran.QuranPage;
import org.soluvas.sanad.web.thing.TransliterationListPage;
import org.soluvas.web.bootstrap.FontAwesome;
import org.soluvas.web.bootstrap.Footer;
import org.soluvas.web.bootstrap.GrowlBehavior;

/**
 * @author ceefour
 */
@SuppressWarnings("serial")
public abstract class GuestLayoutPage extends WebPage {

    @SpringBean
    private AppManifest appManifest;

    public GuestLayoutPage(PageParameters parameters) {
        super(parameters);
        add(new BootstrapBaseBehavior());
        add(new GrowlBehavior());

        add(new HtmlTag("html", getLocale()));
        add(new MobileViewportMetaTag("viewport"));
        add(new MetaTag("description", new Model<>("description"), new Model<>(appManifest.getDescription())));
        add(new MetaTag("author", new Model<>("author"), new Model<>("Hendy Irawan <hendy@soluvas.com>")));

        final Navbar navbar = new Navbar("navbar");
        navbar.setPosition(Position.TOP);
        navbar.setBrandName(new Model<>(appManifest.getTitle()));
        navbar.setBrandImage(new SharedResourceReference(GuestLayoutPage.class, "cloud-27.png"), new Model<>(appManifest.getTitle()));
        navbar.addComponents(
                new ImmutableNavbarComponent(new NavbarButton<>(TransliterationListPage.class, new Model<>("Transliterations"))),
                new ImmutableNavbarComponent(new NavbarButton<>(TestimonyListPage.class, new Model<>("Testimonies"))),
                new ImmutableNavbarComponent(new NavbarButton<>(ClaimListPage.class, new Model<>("Claims"))),
                new ImmutableNavbarComponent(new NavbarButton<>(QuranPage.class, new Model<>("Quran"))),
                new ImmutableNavbarComponent(new NavbarButton<>(HadithCollectionListPage.class, new Model<>("Hadith"))),
                new ImmutableNavbarComponent(new NavbarButton<>(TestimonyLiteralAddPage.class, new Model<>("Add Testimony"))
                        .setType(Buttons.Type.Default).setIconType(FontAwesomeIconType.pencil),
                        ComponentPosition.RIGHT)
        );
        add(navbar);

        add(new Footer("Sanad &copy; 2014 Soluvas"));
    }

    protected IModel<String> createPageTitleModel() {
        return new Model<>(appManifest.getTitle());
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        IModel<String> pageTitleModel = createPageTitleModel();
        add(new Label("pageTitle", pageTitleModel));

//		add(new MustacheMarkupContainer("headLinks", new Model<>()).setRenderBodyOnly(true));

//		add(new NavbarPanel("navbar"));
//		
//		add(new SidebarMenuPanel("sidebarMenu"));
//
//		add(new FooterPanel("footer"));
        add(new HeaderResponseContainer("footer-container", "footer-container"));

//		add(new FooterScriptPanel("footerScript"));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(new LessResourceReference(GuestLayoutPage.class, "sanad.less")));
        response.render(FontAwesome.asHeaderItem());
    }

}
