package util;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;

public final class FXMLHelper {

	public static final String DEFAULT_BUNDLE_CLASS = "app.bundle.Bundle";
	public static final Locale DEFAULT_LOCALE = Locale.getDefault();
	public static final ResourceBundle DEFAULT_BUNDLE = ResourceBundle.getBundle(DEFAULT_BUNDLE_CLASS, DEFAULT_LOCALE);

	private FXMLHelper() {
		super();
	}

	public static FXMLLoader newFXMLLoader(String viewPATH)
			throws Exception {
		return new FXMLLoader(FXMLHelper.class.getResource(viewPATH), DEFAULT_BUNDLE);
	}
}
