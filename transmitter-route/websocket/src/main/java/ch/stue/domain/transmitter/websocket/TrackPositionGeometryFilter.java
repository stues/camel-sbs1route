package ch.stue.domain.transmitter.websocket;

import org.apache.commons.collections4.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

import ch.stue.domain.Feature;
import ch.stue.domain.PointGeometry;

/**
 * This predicate filters by boundingBox.
 *
 * The given {@link TrackPositionMessages} are within the configured BoundingBox
 *
 * If boundingBox is null every TrackPosition will be accepted
 *
 * @author stue
 */
public class TrackPositionGeometryFilter implements Predicate<Feature<PointGeometry>> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TrackPositionGeometryFilter.class);

	private Geometry geometry;

	/**
	 * Default Constructor
	 */
	public TrackPositionGeometryFilter(){
		//Nothing to do!
	}

	/**
	 * Constructor with geometry
	 * @param geometry the geometry
	 */
	public TrackPositionGeometryFilter(Geometry geometry){
		this.geometry = geometry;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean evaluate(Feature<PointGeometry> trackPositionMessage) {
		if(this.geometry != null){
			Point position = GeometryConverterHelper.getPointGeometry(trackPositionMessage.getGeometry());
			if(position != null && this.geometry.contains(position)){
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the geometry
	 */
	public Geometry getGeometry() {
		return this.geometry;
	}

	/**
	 * @param geometry the geometry to set
	 */
	public void setGeometry(Geometry geometry) {
		LOGGER.trace("geometry set");
		this.geometry = geometry;
	}
}
