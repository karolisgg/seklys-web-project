package lt.baltictalents.followeveryone.DAO;

import lt.baltictalents.followeveryone.entities.Coordinates;

import java.util.List;

public interface CoordinatesDAO {

   void setCoordinates(Coordinates coordinates);
   List<Coordinates> getLastCoordinatesOfAllDevices();
   List<Coordinates> getDeviceCoordinatesByTime(String fromDate, String toDate, int deviceId);
}
