package com.lukaspradel.steamapi.webapi.request;

import java.util.Collections;
import java.util.Map;

import com.lukaspradel.steamapi.core.SteamApiRequest;
import com.lukaspradel.steamapi.webapi.SteamWebApiInterface;
import com.lukaspradel.steamapi.webapi.SteamWebApiInterfaceMethod;
import com.lukaspradel.steamapi.webapi.SteamWebApiVersion;

/**
 * Encapsulates a request to the Steam Web API with all its required and
 * optional properties.
 *
 * @author lpradel
 *
 */
public class SteamWebApiRequest extends SteamApiRequest {

	private static final String WEB_API_BASE_URL = "api.steampowered.com";

	private final SteamWebApiInterface apiInterface;

	private final SteamWebApiInterfaceMethod interfaceMethod;

	private final SteamWebApiVersion version;

	protected SteamWebApiRequest(SteamWebApiInterface apiInterface,
			SteamWebApiInterfaceMethod interfaceMethod,
			SteamWebApiVersion version, Map<String, String> parameters) {

		super(WEB_API_BASE_URL, parameters);

		this.apiInterface = apiInterface;
		this.interfaceMethod = interfaceMethod;
		this.version = version;
	}

	protected SteamWebApiRequest(SteamWebApiRequestBuilder builder) {

		super(WEB_API_BASE_URL, builder.getParameters());

		this.apiInterface = builder.getInterface();
		this.interfaceMethod = builder.getInterfaceMethod();
		this.version = builder.getVersion();
	}

	public SteamWebApiInterface getApiInterface() {
		return apiInterface;
	}

	public SteamWebApiInterfaceMethod getInterfaceMethod() {
		return interfaceMethod;
	}

	public SteamWebApiVersion getVersion() {
		return version;
	}

	public Class<?> getResponseType() {

		return interfaceMethod.getReponseType();
	}

	/**
	 * Generic builder class to create instances of {@link SteamWebApiRequest}.
	 * Consider using subclasses of this to create concrete requests for
	 * covenience.
	 *
	 * @author lpradel
	 *
	 */
	public static class SteamWebApiRequestBuilder {

		private final SteamWebApiInterface apiInterface;

		private final SteamWebApiInterfaceMethod interfaceMethod;

		private final SteamWebApiVersion version;

		private final Map<String, String> parameters;

		protected SteamWebApiRequestBuilder() {

			apiInterface = null;
			interfaceMethod = null;
			version = null;
			parameters = null;
		}

		protected SteamWebApiRequestBuilder(SteamWebApiInterface apiInterface,
				SteamWebApiInterfaceMethod interfaceMethod,
				SteamWebApiVersion version, Map<String, String> parameters) {

			this.apiInterface = apiInterface;
			this.interfaceMethod = interfaceMethod;
			this.version = version;
			this.parameters = parameters;
		}

		protected SteamWebApiInterface getInterface() {

			return apiInterface;
		}

		protected SteamWebApiInterfaceMethod getInterfaceMethod() {

			return interfaceMethod;
		}

		protected SteamWebApiVersion getVersion() {

			return version;
		}

		protected Map<String, String> getParameters() {

			return Collections.unmodifiableMap(parameters);
		}

		public SteamWebApiRequest build() {

			return new SteamWebApiRequest(this);
		}
	}
}
