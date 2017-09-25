package util;

import java.io.IOException;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

public class BaseObject {

	private static transient final ObjectMapper jsonObjectMapper;

	static {
		jsonObjectMapper = new ObjectMapper();
		jsonObjectMapper.setSerializationInclusion(Include.NON_NULL);
		jsonObjectMapper.registerModule(new JaxbAnnotationModule());
		jsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@XmlTransient
	transient protected final Logger LOG = new util.Logger(this.getClass().getName());

	@Override
	public boolean equals(final Object pObj) {
		return EqualsBuilder.reflectionEquals(this, pObj, true);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, true);
	}
	
	public static <T extends BaseObject> T fromString(Class<T> clazz, String json) {
		try {
			return jsonObjectMapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString() {
		try {
			return jsonObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (Throwable t) {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE, false);
		}
	}

}