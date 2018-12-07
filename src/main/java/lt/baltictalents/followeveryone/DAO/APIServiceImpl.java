package lt.baltictalents.followeveryone.DAO;

import lt.baltictalents.followeveryone.entities.Coordinates;
import lt.baltictalents.followeveryone.entities.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class APIServiceImpl implements APIService {

   @Autowired
   private CoordinatesDAO coordinatesDAO;

   @Autowired
   private DeviceDAO deviceDAO;

   @Override
   public void setCoordinates(Coordinates coordinates) {
      Device device = deviceDAO.findDevice(coordinates.getDevice().getDeviceId());
      coordinates.setDevice(device);
      coordinatesDAO.setCoordinates(coordinates);
   }

   @Override
   public List<Coordinates> getLastCoordinatesOfAllDevices() {
      return coordinatesDAO.getLastCoordinatesOfAllDevices();
   }

   @Override
   public List<Coordinates> getDeviceCoordinatesByTime(String fromDate, String toDate, int deviceId) {
      return coordinatesDAO.getDeviceCoordinatesByTime(fromDate, toDate, deviceId);
   }

   @Override
   public List<Device> getAllDevices() {
      return deviceDAO.getAllDevices();
   }

   @Override
   public void addDevice(Device device) {
      deviceDAO.addDevice(device);
   }

   @Override
   public Device findDevice(String deviceId) {
      return deviceDAO.findDevice(deviceId);
   }
}
