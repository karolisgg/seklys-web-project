package lt.baltictalents.followeveryone.DAO;

import lt.baltictalents.followeveryone.entities.Device;

import java.util.List;

public interface DeviceDAO {

   List<Device> getAllDevices();
   Device findDevice(String deviceId);
   void addDevice(Device device);
}
