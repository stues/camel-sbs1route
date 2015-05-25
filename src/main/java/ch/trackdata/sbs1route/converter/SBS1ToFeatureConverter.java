package ch.trackdata.sbs1route.converter;

import java.util.Map;

import org.apache.camel.Converter;
import org.apache.commons.beanutils.BeanMap;

import ch.trackdata.sbs1route.message.GeoJSONFeature;
import ch.trackdata.sbs1route.message.PointGeometry;
import ch.trackdata.sbs1route.message.SBS1Message;

/**
 * Converts a {@link SBS1Message} to a {@link GeoJSONFeature}
 * 
 * @author stue
 */
@Converter
public class SBS1ToFeatureConverter {

	/**
	 * Converts the given {@link SBS1Message} into a {@link GeoJSONFeature}
	 * 
	 * @param sbs1Message
	 *            the {@link SBS1Message}
	 * @return the {@link GeoJSONFeature} object
	 */
	@Converter
	public static GeoJSONFeature<PointGeometry> convert(SBS1Message sbs1Message) {
		Map<String, Object> properties = getProperties(sbs1Message);
		PointGeometry pointGeometry;
		if(sbs1Message.getLatitude() != null || sbs1Message.getLongitude() != null){
			pointGeometry = new PointGeometry(sbs1Message.getLongitude(), sbs1Message.getLatitude());
		}
		else{
			pointGeometry = null;
		}
		GeoJSONFeature<PointGeometry> geoJSONFeature = new GeoJSONFeature<PointGeometry>(pointGeometry, properties);
		return geoJSONFeature;
	}

	/**
	 * Extracts all the values from the sbs1Message into a String to Object Map
	 * @param sbs1Message the message
	 * @return a map which contains property name to value entries
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map<String,Object> getProperties(SBS1Message sbs1Message) {
		return (Map)new BeanMap(sbs1Message);
	}
}