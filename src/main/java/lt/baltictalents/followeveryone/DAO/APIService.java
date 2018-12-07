package lt.baltictalents.followeveryone.DAO;

import lt.baltictalents.followeveryone.entities.Coordinates;
import lt.baltictalents.followeveryone.entities.Device;

import java.util.List;

public interface APIService {

   void setCoordinates(Coordinates coordinates);
   List<Coordinates> getLastCoordinatesOfAllDevices();
   List<Coordinates> getDeviceCoordinatesByTime(String fromDate, String toDate, int deviceId);
   List<Device> getAllDevices();
   void addDevice(Device device);
   Device findDevice(String deviceId);
}
