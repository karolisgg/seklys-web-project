package lt.baltictalents.followeveryone.controller;


import lt.baltictalents.followeveryone.DAO.APIService;
import lt.baltictalents.followeveryone.entities.Coordinates;
import lt.baltictalents.followeveryone.entities.Device;
import lt.baltictalents.followeveryone.exceptions.DeviceNotFound;
import lt.baltictalents.followeveryone.exceptions.NameAlreadyExists;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class APIController {

   @Autowired
   private APIService apiService;

   @PostMapping("add_device")
   public Device addDevice(@RequestBody Device device) {
      try {
         apiService.addDevice(device);
      } catch (ConstraintViolationException e) {
         throw new NameAlreadyExists("Name " + device.getName() + " is already taken");
      }
      return device;
   }

   @GetMapping("get_device")
   public Device checkDevice(@RequestParam String deviceId) {
      Device device = apiService.findDevice(deviceId);
      if (device == null)
         throw new DeviceNotFound("There is no device with deviceId - " + deviceId);
      device.setCoordinates(null);
      return device;
   }

   @PostMapping("set_coordinates")
   public void setPosition(@RequestBody Coordinates coordinates) {
      coordinates.setDateTime(new Date(System.currentTimeMillis()));
      apiService.setCoordinates(coordinates);
   }

   @GetMapping("get_last_coordinates")
   public List<Coordinates> getLastCoordinates() {
      List<Coordinates> coordList = apiService.getLastCoordinatesOfAllDevices();
      for (Coordinates c : coordList) {
         c.getDevice().setCoordinates(null);
      }
      return coordList;
   }

   @GetMapping("get_by_time")
   public List<Coordinates> getCoordinatesByTime(@RequestParam String fromDate, @RequestParam String toDate, @RequestParam int deviceId) {
      List<Coordinates> coordList = apiService.getDeviceCoordinatesByTime(fromDate, toDate, deviceId);
      for (Coordinates c : coordList) {
         c.getDevice().setCoordinates(null);
      }
      return coordList;
   }
}
